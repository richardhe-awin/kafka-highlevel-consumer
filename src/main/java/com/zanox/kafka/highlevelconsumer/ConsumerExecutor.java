package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConsumerExecutor {
    private final String topic;
    private final ConsumerConnector consumerConnector;
    private ExecutorService executorService;

    public ConsumerExecutor(String topic, ConsumerConnector consumerConnector) {
        this.topic = topic;
        this.consumerConnector = consumerConnector;
    }

    public Collection<Future> run(int numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<>();
        System.out.println("create consumer map with topic: " + topic + " with " + numThreads + " threads");
        topicCountMap.put(topic, numThreads);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        executorService = Executors.newFixedThreadPool(numThreads);

        int threadNumber = 0;
        Collection<Future> futureSubmissions = new ArrayList<>();
        for (final KafkaStream stream : streams) {
            System.out.println("executing thread " + threadNumber);
            futureSubmissions.add(executorService.submit(new ConsumerTest(stream, threadNumber)));
            threadNumber++;
        }

        return futureSubmissions;
    }
//
//    public void shutdown() {
//        if (consumerConnector != null) consumerConnector.shutdown();
//        if (executorService != null) executorService.shutdown();
//        try {
//            if (executorService != null && !executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
//                System.out.println("Timed out waiting for consumer threads to shut down, exiting uncleanly");
//            }
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted during shutdown, exiting uncleanly");
//        }
//    }

}
