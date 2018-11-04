package com.bulpros.peakwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class PeakworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeakworkApplication.class, args);
    }
}
