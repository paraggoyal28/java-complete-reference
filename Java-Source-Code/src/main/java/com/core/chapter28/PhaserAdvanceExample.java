package com.core.chapter28;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserAdvanceExample {
    public static void main(String[] args) {
        final int totalRounds = 3;
        final int totalWorkers = 3;

        // 1. Override onAdvance to control phase actions and termination
        Phaser phaser = new Phaser(totalWorkers) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("\n--- [Phaser] Phase " + phase + " completed successfully! .....");
                System.out.println("[Phaser] Registered workers running...." + registeredParties);

                // If we reached our target round limit, return  true to terminate the phaser
                if (phase >= totalRounds - 1) {
                    System.out.println("[Phaser] Maximum rounds reached. Terminating phaser.\n");
                    return true;
                }

                // otherwise, return false to let workers continue to next phase
                return false;
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(totalWorkers);

        // 2. Start worker threads
        for (int i = 1; i <= totalWorkers; ++i) {
            final int workerId = i;
            executorService.submit(() -> {
               // Keep running until the phaser is officially terminated
               while (!phaser.isTerminated()) {
                   System.out.println("Worker " + workerId + " is working on Phase: " + phaser.getPhase());

                   try {
                       // Simulate random work duration
                       Thread.sleep((long) (Math.random() * 500));
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                   }

                   System.out.println("Worker: " + workerId + " finished Phase: " + phaser.getPhase() + ". Waiting...");

                   // Arrive and wait for other workers...
                   // This triggers onAdvance() once everyone arrives for the phase.
                   phaser.arriveAndAwaitAdvance();
               }

               System.out.println("Worker: " + workerId + " exiting loop.");
            });
        }
        executorService.shutdown();
    }
}
