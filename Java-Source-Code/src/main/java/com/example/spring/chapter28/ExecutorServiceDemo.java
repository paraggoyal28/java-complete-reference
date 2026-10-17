package com.example.spring.chapter28;

import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        // 1. Create a fixed thread pool with a fixed worker threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 2. Define a callable task that returns a String
        Callable<String> task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] Processing complex task...");
            Thread.sleep(1000);
            return "Result from: " + threadName;
        };

        try {
            System.out.println("[Main] Submitting tasks to the executor.....");

            // 3. Submit tasks and capture the futures
            Future<String> future1 = executor.submit(task);
            Future<String> future2 = executor.submit(task);
            Future<String> future3 = executor.submit(task);

            // 4. Retrieve results (blocking operations)
            System.out.println("[Main] Waiting for results....");
            System.out.println("Received: " + future1.get());
            System.out.println("Received: " + future2.get());
            System.out.println("Received: " + future3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 5. CRUCIAL STEP: Always shut down the executor service
            System.out.println("[Main] Shutting down the executor service...");
            executor.shutdown();

            try {
                // Wait up to 5 seconds for existing tasks to finish
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow(); // force cancel if the tasks take too long
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
        System.out.println("[Main] System execution completed..");
    }
}
