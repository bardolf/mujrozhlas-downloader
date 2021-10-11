package com.skybit.mujrozhlas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MujRozhlasDownloaderApplication {
    private static final Logger log = LoggerFactory.getLogger(MujRozhlasDownloaderApplication.class);

    @Autowired
    private Downloader downloader;

    public static void main(String[] args) {
        SpringApplication.run(MujRozhlasDownloaderApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> downloader.download();
    }

}
