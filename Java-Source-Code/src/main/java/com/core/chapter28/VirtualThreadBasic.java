package com.core.chapter28;

public class VirtualThreadBasic {
    public static void main(String[] args) throws InterruptedException {
        Thread vThread = Thread.ofVirtual().start(() -> {
            System.out.println("Running inside virtual thread: " + Thread.currentThread().getName());
        });

        vThread.join();
        System.out.println("Main Thread finished..");
    }
}
