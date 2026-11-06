package com.core.chapter28;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // Parameters; corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueueSize
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // Core threads
                4, // Max threads
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2) // Bounded queue of capacity 2
        );

        System.out.println("===Submitting 6 tasks to LinkedBlockingQueue pool===");

        for (int i = 1; i <= 6; ++i) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " running on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("All tasks finished");
    }
}
