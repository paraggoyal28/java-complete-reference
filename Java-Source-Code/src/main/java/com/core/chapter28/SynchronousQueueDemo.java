package com.core.chapter28;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                3,
                5, TimeUnit.SECONDS,
                new SynchronousQueue<>()
        );

        System.out.println("=== Submitting 4 tasks to synchronous queue ====");
        // As the maxPoolSize is 3 and we are creating 4 threads simultaneously, one thread is rejected
        // throwing RejectedExecutionException
        for (int i = 1; i <= 4; ++i) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task: " + taskId + " accepted and running on: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("=== Waiting for tasks to complete ===");
    }
}
