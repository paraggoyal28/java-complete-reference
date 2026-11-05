package com.example.spring.chapter28;

import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeCounter {

    // Shared atomic variable backed by CPU CAS instructions
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int current;
        int next;

        // The CAS Spin-Lock (Optimistic Locking)
        do {
            current = counter.get(); // 1. Read the current expected value
            next = current + 1; // 2. Compute the desired new value

            // 3. Compare and Set
            // If the value in memory is still 'current', update it to 'next' and return true
            // If another thread modified it in the mid time, return false and loop again
        } while (!counter.compareAndSet(current, next));
    }

    public int get() {
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        LockFreeCounter sharedCounter = new LockFreeCounter();

        // Create 10 threads that can increment the counter 1,000 times
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; ++i) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; ++j) {
                    sharedCounter.increment();
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < 10; ++i) {
            threads[i].join();
        }

        System.out.println("Final counter value: " + sharedCounter.get());
    }
}


