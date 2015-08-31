package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.KafkaStream;

public interface ConsumerFactory {
    Consumer create(KafkaStream<byte[], byte[]> stream, int threadNumber);
}
