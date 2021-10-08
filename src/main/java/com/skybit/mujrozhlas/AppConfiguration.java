package com.skybit.mujrozhlas;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;

import java.util.Collections;

@Configuration
@EnableScheduling
@EnableCaching
public class AppConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);

    @Value("${chrome.driver.binary.path}")
    private String chromeDriverBinary;

    @Value("${chrome.headless}")
    private boolean headless;

    @Bean
    public ChromeDriver getChromeDriver() {
        if (StringUtils.hasLength(chromeDriverBinary)) {
            System.setProperty("webdriver.chrome.driver", chromeDriverBinary);
        } else {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        options.addArguments("--window-size=1920,1080");
        options.addArguments("incognito");

        //next lines not necessary
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        return new ChromeDriver(options);
    }
}
