package com.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.crypto.repositories")
@EntityScan(basePackages = "com.crypto.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println();
    }
}
