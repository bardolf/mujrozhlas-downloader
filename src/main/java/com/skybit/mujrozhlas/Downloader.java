package com.skybit.mujrozhlas;

import com.skybit.mujrozhlas.util.Holder;
import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
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
import org.openqa.selenium.devtools.v94.network.Network;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Downloader {
    private static final Logger log = LoggerFactory.getLogger(Downloader.class);

    @Value("${output.folder}")
    private String outputFolder;

    @Value("${url.to.download}")
    private String url;

    @Autowired
    private ChromeDriverFactory driverFactory;

    @Autowired
    private ShutdownManager shutdownManager;

    public void download() {
        ChromeDriver driver = null;
        try {
            if (url.isEmpty()) {
                log.error("No url to download was specified!");
                return;
            }
            log.info("Downloading from {}", url);
            driver = driverFactory.getChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.of(5, ChronoUnit.SECONDS));
            driver.get(url);

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
            if (items.isEmpty()) {
                log.error("The given url doesn't contain any episodes to download.");
                return;
            }

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
                        String filename = StringUtils.stripAccents(title.getText() + meta + ".mp3").replace(' ', '_');
                        downloadFile(urlHolder.getValue(), outputFolder, filename);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            //download cover image
            List<WebElement> figure = driver.findElements(By.className("b-detail__img"));
            if (!figure.isEmpty()) {
                WebElement img = figure.get(0).findElement(By.tagName("img"));
                String src = img.getDomAttribute("src");
                downloadFile("https://www.mujrozhlas.cz" + src, outputFolder, "cover.jpg");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
            shutdownManager.initiateShutdown(0);
        }
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
