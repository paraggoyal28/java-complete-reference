package com.core.chapter28;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceConditionSimulator {
    private int currentId = 1000;

    // We split currentId++ explicitly and introduce a micro-delay
    public int generateNextId() {
        int rawValue = currentId; // 1. React current id

        // 2. CONTEXT SWITCH: force the CPU to pause this thread
        // and switch to another thread right in middle of the operation
        Thread.yield();

        int newValue = rawValue + 1;

        currentId = newValue;

        return currentId;
    }

    public static void main(String[] args) throws InterruptedException {
        // Reduced to 50 threads so our console isn't flooded,
        // but the failure rate will still be massive
        int numberOfThreads = 50;
        RaceConditionSimulator simulator = new RaceConditionSimulator();

        // Setup a thread pool and a thread-safe set to track generated IDs
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Set<Integer> generatedIds = Collections.newSetFromMap(new ConcurrentHashMap<>());

        // Latches to coordinate the simultaneous attack
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; ++i) {
            executor.submit(() -> {
                try {
                    // All threads wait here until the gate opens
                    startSignal.await();

                    int currentId = simulator.generateNextId();

                    // If the ID was already in the set, we found a duplicate !
                    if (!generatedIds.add(currentId)) {
                        System.out.println("DUPLICATES DETECTED: ID " + currentId + " was generated twice!");
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneSignal.countDown();
                }
            });
        }

        startSignal.countDown();
        doneSignal.await();
        executor.shutdown();

        int totalIdsGenerated = generatedIds.size();
        int expectedFinalId = 1000 + numberOfThreads;
        int lostUpdates = expectedFinalId - simulator.currentId;

        System.out.println("\n===SIMULATION RESULTS ====");
        System.out.println("Expected Final ID: " + expectedFinalId);
        System.out.println("Actual Final ID: " + simulator.currentId);
        System.out.println("Lost Updates: " + lostUpdates);
        System.out.println("Unique IDs saved: " + totalIdsGenerated + " out of " + numberOfThreads);
    }
}
