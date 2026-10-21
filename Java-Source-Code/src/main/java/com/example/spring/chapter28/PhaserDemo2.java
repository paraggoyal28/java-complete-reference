package com.example.spring.chapter28;

import java.util.concurrent.Phaser;


// Extend Phaser class to allow only a specific number of phases to be executed
class MyPhaser extends Phaser {
    int numPhases;

    MyPhaser(int parties, int phaseCount) {
        super(parties);
        numPhases = phaseCount - 1;
    }

    // Override onAdvance() to execute the specified number of phases
    protected boolean onAdvance(int p, int regParties) {
        // The println() statement is for illustration only
        // Normally, onAdvance() will not display output.
        System.out.println("Phase: " + p + " completed");

        // Execute critical code need to be executed after each phase
        System.out.println("Critical code executed here.");

        // If all phases have completed return true
        if (p == numPhases || regParties == 0) return true;

        return false;
    }
}

public class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser phsr = new MyPhaser(1, 4);

        System.out.println("Starting");

        new Thread(new MyThreadPhaser(phsr, "A")).start();
        new Thread(new MyThreadPhaser(phsr, "B")).start();
        new Thread(new MyThreadPhaser(phsr, "C")).start();

        // Wait for specified number of phases to complete
        while (!phsr.isTerminated()) {
            phsr.arriveAndAwaitAdvance();
        }

        System.out.println("The phaser is terminated!");
    }
}


class MyThreadPhaser implements  Runnable {

    Phaser phsr;
    String name;

    MyThreadPhaser(Phaser p, String n) {
        this.phsr = p;
        this.name = n;
        this.phsr.register();
    }

    @Override
    public void run() {
        while (!phsr.isTerminated()) {
            System.out.println("Thread " + name + " Beginning Phase: " + phsr.getPhase());

            phsr.arriveAndAwaitAdvance();


            // Pause a bit to prevent jumbled output. This is for illustration
            // only. It is not required for the proper operation of the phaser
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}