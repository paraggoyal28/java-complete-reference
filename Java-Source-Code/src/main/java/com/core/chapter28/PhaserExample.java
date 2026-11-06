package com.core.chapter28;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        // Create a phaser for 3 parties (1 Manager + 2 Workers)
        final Phaser phaser = new Phaser(3);

        // Start Worker Threads
        new Thread(new Worker(phaser, "Worker-1"), "Worker-1").start();
        new Thread(new Worker(phaser, "Worker-2"), "Worker-2").start();

        // Manager Thread behavior (Main Thread)
        System.out.println("[Manager] Preparing Phase 1 resources...");
        try { Thread.sleep(500); } catch (InterruptedException e) { }

        System.out.println("[Manager] Phase 1 resources ready. Signaling arrival.");

        // 1. arrive() -> Signals arrival but DOES NOT BLOCK.
        // The manager announces its work for Phase 1 is done, then immediately starts preparing for Phase 2.
        phaser.arrive();

        System.out.println("[Manager] Continuing immediately to log paperwork while workers process...");
    }

    static class Worker implements Runnable {
        private final Phaser phaser;
        private final String name;

        Worker(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("[" + name + "] Processing Phase 1 tasks...");
            try {
                // Simulating variable work times
                long duration = name.equals("Worker-1") ? 1000 : 2000;
                Thread.sleep(duration);
            } catch (InterruptedException e) { }

            System.out.println("[" + name + "] Finished Phase 1. Waiting at the barrier...");

            // 2. arriveAndAwaitAdvance() -> Signals arrival AND BLOCKS.
            // Workers cannot move to Phase 2 until the manager and all other workers have arrived.
            phaser.arriveAndAwaitAdvance();

            System.out.println("[" + name + "] Step Advanced! Moving to Phase 2.");
        }
    }
}