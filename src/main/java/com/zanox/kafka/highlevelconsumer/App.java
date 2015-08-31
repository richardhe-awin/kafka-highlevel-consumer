package com.zanox.kafka.highlevelconsumer;

import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Future;

public class App {
    public static void main(String[] args) {
        String zookeeper = args[0];
        String groupId = args[1];
        String topic = args[2];
        int numTheads = Integer.parseInt(args[3]);

        System.out.println("zookeeper: " + zookeeper);
        System.out.println("groupId: " + groupId);
        System.out.println("topic: " + topic);
        System.out.println("number of threads: " + numTheads);

        //reset zookeeper offset to the beginning
        new ZkOffsetReseter(zookeeper, 2181, groupId).reset();

        ConsumerConfig consumerConfig = ConsumerConfigFactory.create(zookeeper, groupId);
        ConsumerConnector consumerConnector = new ConsumerConnectorFactory(consumerConfig).create();


        ConsumerExecutor consumerExecutor = new ConsumerExecutor(topic, consumerConnector);
        Collection<Future> futureSessions = consumerExecutor.run(numTheads);

        Iterator<Future> iterator = futureSessions.iterator();
        while (iterator.hasNext()) {
            Future nextSession = iterator.next();
            while (!nextSession.isDone()) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Consumer is still running...");
                } catch (InterruptedException e) {
                    if (!nextSession.isCancelled()) {
                        nextSession.cancel(true);
                        System.out.println("Thread has been interrupted, "
                                + nextSession.toString() + " has been cancelled");
                    }
                }
            }
        }

        System.out.println("All consumer threads have been stopped");

    }
}
