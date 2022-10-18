package ru.test.kafka.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaProducerConfig {

    @Autowired
    private KafkaConfig kafkaConfig;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> pc = new HashMap<>();
        pc.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapAddress());
        pc.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        pc.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //SSL
        pc.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaConfig.getSecurity());
        pc.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,"");
        pc.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,kafkaConfig.getKeyStoreConfigs().getPath());
        pc.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaConfig.getKeyStoreConfigs().getPassword());
        pc.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, kafkaConfig.getKeyStoreConfigs().getPath());
        pc.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,kafkaConfig.getKeyStoreConfigs().getPassword());
        pc.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, kafkaConfig.getKeyStoreConfigs().getPassword());

        return new DefaultKafkaProducerFactory<>(pc);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
