package ru.test.kafka.configuration.kafka;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class KeyStoreConfig {
    private String path;
    private String password;
}
