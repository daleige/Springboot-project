package com.chenyangqi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(MyApplication.class, args);

    }
}
