package com.odestopolanco.odestopolancoblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Loader class of the project
 */
@SpringBootApplication
public class BlogApiApplication {

    /**
     * Entry point of the application
     *
     * @param args string arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }
}
