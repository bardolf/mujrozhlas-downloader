package com.skybit.mujrozhlas;

import com.skybit.mujrozhlas.util.Holder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v93.network.Network;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class Downloader {
    private static final Logger log = LoggerFactory.getLogger(Downloader.class);

    @Value("${output.folder}")
    private String outputFolder;

    @Autowired
    private ChromeDriver driver;

    @Autowired
    private ShutdownManager shutdownManager;

    public void download(String url) {
        log.info("Downloading from {}", url);
        driver.manage().timeouts().implicitlyWait(Duration.of(5, ChronoUnit.SECONDS));
        driver.get(url);
        Actions actions = new Actions(driver);

        //show all episodes
        List<WebElement> nextEpisodes = driver.findElements(By.className("more-link__link"));
        while (!nextEpisodes.isEmpty()) {
            nextEpisodes.get(0).click();
            WebElement spinner = driver.findElement(By.className("more-link__spinner"));
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
            webDriverWait.until(ExpectedConditions.invisibilityOf(spinner));
            nextEpisodes = driver.findElements(By.className("more-link__link"));
        }

        //iterate through all items
        List<WebElement> items = driver.findElements(By.className("c-episodes__item"));
        int i = 1;
        for (WebElement item : items) {
            WebElement button = item.findElement(By.className("b-episode__play-btn"));
            WebElement title = item.findElement(By.className("b-episode__title"));
            List<WebElement> metas = item.findElements(By.className("meta-info__episode"));
            String meta = metas.isEmpty() ? "" : String.format("_%02d_", i++) + metas.get(0).getText();

            CountDownLatch latch = new CountDownLatch(1);
            Holder<String> urlHolder = new Holder<>();
            waitForMedia(driver, latch, urlHolder);

            //scroll to element
            int elementPosition = button.getLocation().getY();
            String js = String.format("window.scroll(0, %s)", elementPosition);
            ((JavascriptExecutor) driver).executeScript(js);

            //start the playing
            button.click();
            try {
                boolean success = latch.await(2, TimeUnit.SECONDS);
                if (success) {
                    log.info("Downloading {}/{} - {}.", title.getText(), meta, urlHolder.getValue());
                    downloadFile(urlHolder.getValue(), outputFolder, title.getText() + meta + ".mp3");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        driver.quit();
        shutdownManager.initiateShutdown(0);
    }

    private void waitForMedia(ChromeDriver driver, CountDownLatch latch, Holder<String> urlHolder) {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),
                entry -> {
                    if (entry.getRequest().getUrl().endsWith("mp3")) {
                        urlHolder.setValue(entry.getRequest().getUrl());
                        latch.countDown();
                    }
                });
    }

    private void downloadFile(String url, String folder, String name) {
        File folderFile = new File(folder);
        folderFile.mkdirs();
        File myFile = new File(folderFile, name);

        CloseableHttpClient client = HttpClients.createDefault();
        try (CloseableHttpResponse response = client.execute(new HttpGet(url))) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try (FileOutputStream outstream = new FileOutputStream(myFile)) {
                    entity.writeTo(outstream);
                }
            }
        } catch (Exception e) {
            log.error("Downloading error ", e);
        }
    }
}
