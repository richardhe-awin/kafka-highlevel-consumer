package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.KafkaStream;

public class TestConsumerFactory implements ConsumerFactory {
    @Override
    public Consumer create(KafkaStream<byte[], byte[]> stream, int threadNumber) {
        return new TestConsumer(stream, threadNumber);
    }
}
