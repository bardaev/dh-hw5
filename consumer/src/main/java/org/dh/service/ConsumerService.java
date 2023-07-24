package org.dh.service;

import lombok.extern.slf4j.Slf4j;
import org.dh.config.ConsumerConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = ConsumerConfig.TOPIC_NAME_1)
    public void consume1(String msg) {
        log.info("Message from topic {}: {}", ConsumerConfig.TOPIC_NAME_2, msg);
    }

    @KafkaListener(topics = ConsumerConfig.TOPIC_NAME_2)
    public void consume2(String msg) {
        log.info("Message from topic {}: {}", ConsumerConfig.TOPIC_NAME_2, msg);
    }

}
