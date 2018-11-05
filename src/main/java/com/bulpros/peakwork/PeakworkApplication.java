package com.bulpros.peakwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The Class PeakworkApplication.
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class PeakworkApplication {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PeakworkApplication.class, args);
    }
}
