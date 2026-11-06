package com.core.chapter28;

import java.util.concurrent.Phaser;

// A thread of execution that uses a Phaser
class MyPhaserThread implements Runnable {
    Phaser phaser;
    String name;

    MyPhaserThread(Phaser p, String n) {
        this.name = n;
        this.phaser = p;
        phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Thread: " + name + " Beginning Phase One");
        phaser.arriveAndAwaitAdvance(); // Signal arrival

        // Pause a bit to prevent jumbled output. This is for illustration
        // only it is not required for the proper operation of the phaser
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread: " + name + " Beginning Phase Two");
        phaser.arriveAndAwaitAdvance(); // Signal arrival

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread: " + name + " Beginning Phase Three");
        phaser.arriveAndAwaitAdvance();
    }
}


public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        int curPhase;

        System.out.println("Starting: ");

        new Thread(new MyPhaserThread(phaser, "A")).start();
        new Thread(new MyPhaserThread(phaser, "B")).start();
        new Thread(new MyPhaserThread(phaser, "C")).start();

        // Wait for all threads to complete phase one
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase: " + (curPhase + 1) + " Completed.");

        // Wait for all threads to complete phase two
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase: " + (curPhase + 1) + " Completed.");

        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase: " + (curPhase + 1) + " Completed.");

        // Deregister the main thread
        phaser.arriveAndDeregister();

        if (phaser.isTerminated()) {
            System.out.println("The Phaser is terminated.");
        }
    }
}
