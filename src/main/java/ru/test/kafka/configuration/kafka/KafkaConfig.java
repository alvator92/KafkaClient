package ru.test.kafka.configuration.kafka;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

@ConfigurationProperties(prefix = "kafka")
@EqualsAndHashCode
@Data
public class KafkaConfig {
    private String bootstrapAddress;
    private String group;
    private String security;
    private KeyStoreConfig keyStoreConfigs;
    private Topics message;

}
