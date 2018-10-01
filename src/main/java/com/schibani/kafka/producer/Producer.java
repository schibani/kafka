package com.schibani.kafka.producer;

import com.schibani.kafka.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

@Slf4j
public class Producer implements Runnable {

    private final String topic;
    private final String message;

    public Producer(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }

    @Override
    public void run() {

        final Properties props = KafkaConfig.getProducerConfig();
        final KafkaProducer<String, String> producer = new KafkaProducer(props);
        try {
            log.info("Begin producer 1------------------------- ");
            producer.send(new ProducerRecord(this.topic, this.message));
            log.info("Message '{}' sent to topic: {}", this.message, this.topic);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }

    }
}
