package ru.test.kafka.configuration.kafka;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Topics {
    private String topic_1;
    private String topic_2;
}
