package com.core.chapter28;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterSimulator {
    private AtomicInteger currentId = new AtomicInteger(1000);

    public int incrementCurrentId() {
        return  currentId.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        // Reduced to 50 threads so our console isn't flooded.
        // but the failure rate will still be massive.
        int numberOfThreads = 50;
        AtomicCounterSimulator atomicCounterSimulator = new AtomicCounterSimulator();

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Set<Integer> generatedIds = Collections.newSetFromMap(new ConcurrentHashMap<>());

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; ++i) {
            executorService.submit(() -> {
                try {
                    startSignal.await();
                    int id = atomicCounterSimulator.incrementCurrentId();

                    if (!generatedIds.add(id)) {
                        System.out.println("Duplicate Detected: ID " + id + " was generated twice!");
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
        executorService.shutdown();

        int totalIdsGenerated = generatedIds.size();
        int expectedFinalId = 1000 + numberOfThreads;
        int lostUpdates = expectedFinalId - atomicCounterSimulator.currentId.get();

        System.out.println("\n=== SIMULATED RESULTS====");
        System.out.println("Expected Final ID: " + expectedFinalId);
        System.out.println("Actual Final ID: " + atomicCounterSimulator.currentId.get());
        System.out.println("Lost Updates: " + lostUpdates);
        System.out.println("Unique Ids Saved: " + totalIdsGenerated + " out of " + numberOfThreads);
    }
}
