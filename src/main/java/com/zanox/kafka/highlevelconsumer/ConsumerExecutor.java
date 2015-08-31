package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.KafkaStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ConsumerExecutor {
    private ConsumerFactory consumerFactory;
    private MessageStreamFactory messageStreamFactory;
    private ExecutorServiceFactory executorServiceFactory;

    public ConsumerExecutor(ConsumerFactory consumerFactory,
                            MessageStreamFactory messageStreamFactory,
                            ExecutorServiceFactory executorServiceFactory) {
        this.consumerFactory = consumerFactory;
        this.messageStreamFactory = messageStreamFactory;
        this.executorServiceFactory = executorServiceFactory;
    }

    public Collection<Future> run(int numThreads) {

        int threadNumber = 0;
        ExecutorService executorService = executorServiceFactory.create(numThreads);
        Collection<Future> futureSubmissions = new ArrayList<>();
        for (final KafkaStream<byte[], byte[]> stream : messageStreamFactory.create(numThreads)) {
            System.out.println("executing thread " + threadNumber);
            futureSubmissions
                    .add(executorService.submit(consumerFactory.create(stream, threadNumber)));
            threadNumber++;
        }

        return futureSubmissions;
    }

}
