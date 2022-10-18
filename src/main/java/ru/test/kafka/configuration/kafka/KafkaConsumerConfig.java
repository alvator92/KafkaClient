package ru.test.kafka.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConsumerConfig {

    @Autowired
    private KafkaConfig kafkaConfig;

    @Bean
    public ConsumerFactory<String, String> consumerFactory(String groupId) {
        Map<String, Object> pc = new HashMap<>();
        pc.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapAddress());
        pc.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.getGroup());
        pc.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        pc.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //SSL
        pc.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaConfig.getSecurity());
        pc.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,"");
        pc.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,kafkaConfig.getKeyStoreConfigs().getPath());
        pc.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaConfig.getKeyStoreConfigs().getPassword());
        pc.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, kafkaConfig.getKeyStoreConfigs().getPath());
        pc.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,kafkaConfig.getKeyStoreConfigs().getPassword());
        pc.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, kafkaConfig.getKeyStoreConfigs().getPassword());

        return new DefaultKafkaConsumerFactory<>(pc);
    }

    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaConfig.getGroup()));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory(kafkaConfig.getGroup());
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static class MessageListener {
        @KafkaListener(
                topics = "TOPIC_NAME_1",
                groupId = "KafkaReplicator",
                topicPartitions = {@TopicPartition(topic = "TOPIC_NAME_1", partitions = {"0", "1"})},
                containerFactory = "KafkaListenerContainerFactory")
        public void readMessageToTopic(String message) {
            log.info("Recieved message in group :" + message);
        }
    }
}
