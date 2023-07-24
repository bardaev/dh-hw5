package org.dh.config;

import lombok.val;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class ProducerConfig {

    public static final String TOPIC_NAME_1 = "partition";
    public static final String TOPIC_NAME_2 = "no_partition";
    public static final int PARTITIONS_TOPIC_1 = 3;

    @Bean
    public NewTopic partitionTopic() {
        return TopicBuilder.name(TOPIC_NAME_1)
                .partitions(PARTITIONS_TOPIC_1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic noPartitionTopic() {
        return TopicBuilder.name(TOPIC_NAME_2)
                .replicas(1)
                .build();
    }

    @Value("${spring.kafka.bootstrap-servers}")
    private String address;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        val config = new HashMap<String, Object>();
        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> factory) {
        return new KafkaTemplate<>(factory);
    }
}
