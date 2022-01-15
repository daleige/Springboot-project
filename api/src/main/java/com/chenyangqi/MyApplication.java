package com.chenyangqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.chenyangqi")
public class MyApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(MyApplication.class, args);

    }
}
