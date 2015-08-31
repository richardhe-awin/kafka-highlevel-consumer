package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class ConsumerTest implements Runnable {

    private final KafkaStream stream;
    private final int threadNumber;

    public ConsumerTest(KafkaStream<byte[], byte[]> stream, int threadNumber) {
        System.out.println("stream: " + stream);
        this.stream = stream;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            String output = "Thread " + threadNumber + ": " + new String(it.next().message());
            System.out.println(output);
        }

        System.out.println("Shutting down Thread: " + threadNumber);
    }
}
