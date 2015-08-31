package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerConnectorFactory {
    private ConsumerConfig consumerConfig;

    public ConsumerConnectorFactory(ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
    }

    public ConsumerConnector create() {
        return Consumer.createJavaConsumerConnector(consumerConfig);
    }
}
