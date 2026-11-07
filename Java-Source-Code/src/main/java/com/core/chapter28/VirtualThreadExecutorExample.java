package com.core.chapter28;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadExecutorExample {
    public static void main(String[] args) {

        var factory = Thread.ofVirtual().name("worker-", 0).factory();
        long startTime = System.currentTimeMillis();
        System.out.println("Start time for virtual threads: " + startTime);

        try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory)) {
            for (int i = 1; i <= 10_000; ++i) {
                int taskId = i;
                executorService.submit(() -> {
                    // Simulate blocking I/O operation (e.g HTTP Call or DB query)
                    Thread.sleep(1000);
                    //   System.out.println("Task: " + taskId + " completed on " + Thread.currentThread().getName());
                    return taskId;
                });
            }
        }
        System.out.println("Duration for Virtual threads: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println("Start time for platform threads: " + startTime);

        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 1; i <= 10_000; ++i) {
                int taskId = i;
                executorService.submit(() -> {
                    // Simulate blocking I/O operation (e.g HTTP Call or DB query)
                    Thread.sleep(1000);
                    //System.out.println("Task: " + taskId + " completed on " + Thread.currentThread().getName());
                    return taskId;
                });
            }
        }
        System.out.println("Duration for platform threads: " + (System.currentTimeMillis() - startTime));


        // Scope End - Bl
        // ocks until all 10,000 virtual threads finish
        System.out.println("All 10,000 tasks completed successfully.");
    }
}
