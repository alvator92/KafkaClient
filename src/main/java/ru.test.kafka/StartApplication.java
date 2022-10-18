package ru.test.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StartApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(StartApplication.class, args);
        System.out.println("Hello LoadGen!");

    }
}
