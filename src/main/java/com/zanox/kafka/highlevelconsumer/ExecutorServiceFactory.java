package com.zanox.kafka.highlevelconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceFactory {
    public ExecutorServiceFactory() {
    }

    public ExecutorService create(int numThreads) {
        return Executors.newFixedThreadPool(numThreads);
    }
}
