package com.schibani.kafka.consumer;

import com.schibani.kafka.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

@Slf4j
public class Consumer implements Runnable {
    private final String topic;

    public Consumer(String topic) {
        this.topic = topic;
    }

    @Override
    public void run() {
        final KafkaConsumer<String, String> consumer = new KafkaConsumer(KafkaConfig.getConsumerConfig());
        consumer.subscribe(Arrays.asList(topic));
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(100);
            log.info("nombre de record = {}", records.count());
            for (ConsumerRecord<String, String> record : records) {
                log.info("offset = {}, value = {]", record.offset(), record.value());
            }
        }
    }
}
