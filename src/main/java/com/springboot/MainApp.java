package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(MainApp.class, args);
    }
}
