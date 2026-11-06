package com.core.chapter28;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class MyThreadBarrier implements Runnable {
    CyclicBarrier cbar;
    String name;

    MyThreadBarrier(CyclicBarrier cb, String n) {
        this.cbar = cb;
        this.name = n;
    }

    public void run() {
        System.out.println("Current thread: " + name);
        try {
            cbar.await();
        } catch (BrokenBarrierException | InterruptedException e) {
            System.out.println(e);
        }
    }
}

class BarAction implements Runnable {
    public void run() {
        System.out.println("Barrier Reached!");
    }
}

public class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
        System.out.println("Starting....");
        new Thread(new MyThreadBarrier(cb, "A")).start();
        new Thread(new MyThreadBarrier(cb, "B")).start();
        new Thread(new MyThreadBarrier(cb, "C")).start();
        new Thread(new MyThreadBarrier(cb, "X")).start();
        new Thread(new MyThreadBarrier(cb, "Y")).start();
    }
}
