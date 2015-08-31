package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageStreamFactory {
    private String topic;
    private ConsumerConnector consumerConnector;

    public MessageStreamFactory(String topic,
                                ConsumerConnector consumerConnector) {
        this.topic = topic;
        this.consumerConnector = consumerConnector;
    }

    public List<KafkaStream<byte[], byte[]>> create(int numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<>();
        System.out.println("create consumer map with topic: " + topic + " with " + numThreads + " threads");
        topicCountMap.put(topic, numThreads);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        return consumerMap.get(topic);
    }
}
