package com.example.spring.chapter28;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;


public class PhaserAwaitAdvanceExample2 {

    public static void main(String[] args) throws InterruptedException {
        // Register 2 parties (the 2 worker threads)
        final int totalWorkers = 2;
        Phaser phaser = new Phaser(totalWorkers);

        ExecutorService executor = Executors.newFixedThreadPool(totalWorkers);

        System.out.println("[Main Thread] Starting multi-phase worker pipeline...");

        // Start the workers
        for (int i = 1; i <= totalWorkers; i++) {
            final int workerId = i;
            executor.submit(() -> {
                // --- PHASE 0: Load Configurations ---
                System.out.println("Worker " + workerId + " is loading configurations (Phase 0)...");
                try { Thread.sleep((long) (Math.random() * 500)); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                // Signal arrival and wait for the other worker to finish Phase 0
                phaser.arriveAndAwaitAdvance();

                // --- PHASE 1: Warm up Caches ---
                System.out.println("Worker " + workerId + " is warming up caches (Phase 1)...");
                try { Thread.sleep((long) (Math.random() * 500)); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                // Workers finish Phase 1 and deregister since they are completely done
                System.out.println("Worker " + workerId + " finished Phase 1 tasks.");
                phaser.arriveAndDeregister();
            });
        }

        System.out.println("[Main Thread] Waiting passively for Phase 0 to finish first...");
        phaser.awaitAdvance(0);

        // The main thread wants to wait specifically for Phase 1 to finish.
        // It passes '1' to say: "Block me until Phase 1 transitions to Phase 2 (or terminates)."
        System.out.println("[Main Thread] Waiting passively via awaitAdvance(1)...");

        phaser.awaitAdvance(0);


        int nextPhase = phaser.awaitAdvance(1);

        // This line won't execute until all workers have finished Phase 1
        System.out.println("\n[Main Thread] Notification received! Phase 1 completed. Next phase token is: " + nextPhase);
        System.out.println("[Main Thread] All setup phases are complete. Launching application core...");

        executor.shutdown();
    }
}


