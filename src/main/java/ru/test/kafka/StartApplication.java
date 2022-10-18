package ru.test.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.test.kafka.configuration.kafka.KafkaConfig;

@SpringBootApplication
public class StartApplication {

    @Autowired
    private KafkaConfig kafkaConfig;
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(StartApplication.class, args);
        System.out.println("Hello KafkaReplicator!");
    }
}
