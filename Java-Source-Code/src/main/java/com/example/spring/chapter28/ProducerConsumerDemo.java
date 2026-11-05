package com.example.spring.chapter28;

import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumerBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;

    public ProducerConsumerBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        // If buffer is full, wait for consumer
        while (buffer.size() == capacity) {
            System.out.println("Buffer is full. Producer: " + Thread.currentThread().getName() + " waiting...");
            wait();
        }

        buffer.add(item);
        System.out.println("Producer produced: " + item + ". [Buffer size: " + buffer.size() + "]");
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        // If buffer is empty, wait for producer
        while (buffer.size() == 0) {
            System.out.println("Buffer is empty. Consumer: " + Thread.currentThread().getName() + " waiting...");
            wait();
        }

        int item = buffer.poll();
        System.out.println("Consumer consumed: " + item + ". [Buffer size: " + buffer.size() + "]");
        notifyAll();
        return item;
    }

}


public class ProducerConsumerDemo {
    public static void main(String[] args) {
        ProducerConsumerBuffer buffer = new ProducerConsumerBuffer(10);

        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    buffer.produce(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Producer");

        Thread consumerThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    buffer.consume();
                    Thread.sleep(250);
                }  catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Consumer");


        producerThread.start();
        consumerThread.start();
    }
}
