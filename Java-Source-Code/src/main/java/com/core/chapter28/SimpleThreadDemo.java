package com.core.chapter28;

public class SimpleThreadDemo {
    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 100; ++i) {
                System.out.println("Count is: " + i + " via: " + Thread.currentThread().getName());
            }
        };
        Thread t = new Thread(r,  "My-Thread");
        t.start();
    }
}
