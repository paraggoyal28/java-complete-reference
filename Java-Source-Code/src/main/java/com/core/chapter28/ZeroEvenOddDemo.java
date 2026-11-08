package com.core.chapter28;


class ZeroEvenOdd {
    private int state; // state = 0 represents zero, 1 represents odd, 2 represents even
    // sequence 0102030405
    private final int limit;
    private int counter;

    public ZeroEvenOdd(int n) {
        this.limit = n;
        this.state = 0;
        this.counter = 1;
    }

    public synchronized void printZero() {
        while (counter <= limit) {
            while (state != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (counter > limit) break;

            System.out.print(0);

            if (counter % 2 == 1) {
                state = 1; // print odd number
            } else {
                state = 2; // print even number
            }

            notifyAll();
        }
    }

    public synchronized  void printOdd() {
        while (counter <= limit) {
            while (state != 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (counter > limit) break;

            System.out.print(counter);
            counter++;
            state = 0;
            notifyAll();
        }
    }

    public synchronized  void printEven() {
        while (counter <= limit) {
            while (state != 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (counter > limit) break;

            System.out.print(counter);
            counter++;
            state = 0;
            notifyAll();
        }
    }
}

public class ZeroEvenOddDemo {
    public static  void main(String[] args) {
        ZeroEvenOdd shared = new ZeroEvenOdd(20);
        Thread zero = new Thread(shared::printZero, "Zero-Thread");
        Thread odd = new Thread(shared::printOdd, "Odd-Thread");
        Thread even = new Thread(shared::printEven, "Even-Thread");

        zero.start();
        odd.start();
        even.start();

        try {
            zero.join();
            odd.join();
            even.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}