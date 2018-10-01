package com.schibani.kafka;

import java.util.Properties;

public class KafkaConfig {

    private static Properties producerConfig = new Properties();
    private static Properties consumerConfig = new Properties();

    static {
        producerConfig.put("bootstrap.servers", "localhost:9092");
        producerConfig.put("acks", "all");
        producerConfig.put("retries", 0);
        producerConfig.put("batch.size", 16384);
        producerConfig.put("linger.ms", 1);
        producerConfig.put("buffer.memory", 33554432);
        producerConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    static {
        consumerConfig.put("bootstrap.servers", "localhost:9092");
        consumerConfig.put("group.id", "test");
        consumerConfig.put("enable.auto.commit", "true");
        consumerConfig.put("auto.commit.interval.ms", "1000");
        consumerConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public static Properties getProducerConfig() {
        return producerConfig;
    }

    public static Properties getConsumerConfig() {
        return consumerConfig;
    }
}
