package org.dh.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dh.config.ProducerConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    public void sendMsgPartition(String key, String msg) {
        Random random = new Random();
        int topic = random.ints(0, ProducerConfig.PARTITIONS_TOPIC_1).findFirst().getAsInt();
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(ProducerConfig.TOPIC_NAME_1, topic, key, msg);
        SendResult<String, String> result = future.get();
        if (result == null || result.getRecordMetadata() == null) {
            throw new RuntimeException("Cannot read record metadata");
        }
        log.info(
            "Send message to: Topic -> {}, Offset -> {}, Partition -> {}",
            result.getRecordMetadata().topic(),
            result.getRecordMetadata().offset(),
            result.getRecordMetadata().partition()
        );
    }

    @SneakyThrows
    public void sendMsgNoPartition(String key, String msg) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(ProducerConfig.TOPIC_NAME_2, key, msg);
        SendResult<String, String> result = future.get();
        if (result == null || result.getRecordMetadata() == null) {
            throw new RuntimeException("Cannot read record metadata");
        }
        log.info(
                "Send message to: Topic -> {}, Offset -> {}, Partition -> {}",
                result.getRecordMetadata().topic(),
                result.getRecordMetadata().offset(),
                result.getRecordMetadata().partition()
        );
    }
}
