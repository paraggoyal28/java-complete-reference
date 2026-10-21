package com.example.spring.chapter28;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserAwaitAdvanceExample {

    public static void main(String[] args) throws InterruptedException {
        // Register 2 parties (the 2 worker threads)
        // Note: The main thread is NOT registered because it doesn't participate in the work
        final int totalWorkers = 2;
        Phaser phaser = new Phaser(totalWorkers);

        ExecutorService executor = Executors.newFixedThreadPool(totalWorkers);

        System.out.println("[Main Thread] Starting worker initialization...");

        // Start the workers
        for (int i = 1; i <= totalWorkers; i++) {
            final int workerId = i;
            executor.submit(() -> {
                System.out.println("Worker " + workerId + " is initializing system components...");
                try {
                    Thread.sleep((long) (Math.random() * 1000 + 500)); // Simulate variable work time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Worker " + workerId + " finished initialization.");

                //phaser.arriveAndAwaitAdvance();
                //Workers signal arrival and deregister since they are done with Phase 0 tasks
                phaser.arriveAndDeregister();
            });
        }

        // The main thread wants to wait specifically for Phase 0 to complete.
        // It passes '0' to say: "Block me until Phase 0 transitions to Phase 1 (or terminates)."
        System.out.println("[Main Thread] Waiting passively via awaitAdvance(0)...");
        //phaser.arriveAndAwaitAdvance();



        int currentPhase = phaser.awaitAdvance(0);
      //  phaser.arriveAndAwaitAdvance();
        // This line won't execute until all workers have called arriveAndDeregister() for Phase 0
        System.out.println("\n[Main Thread] Notification received! Phase 0 completed. Next phase number is: " + currentPhase);
        System.out.println("[Main Thread] System is fully initialized. Launching application core...");

        executor.shutdown();
    }
}
