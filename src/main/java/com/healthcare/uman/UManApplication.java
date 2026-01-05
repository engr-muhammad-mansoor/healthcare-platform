package com.healthcare.uman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.mongock.runner.springboot.EnableMongock;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
@EnableMongock
public class UManApplication {

    public static void main(String[] args) {
        SpringApplication.run(UManApplication.class, args);
    }
}
