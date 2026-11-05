package com.example.spring.chapter28;

class EvenOddCounter {
    private int counter = 0;
    private final int limit;

    EvenOddCounter(int limit) {
        this.limit = limit;
    }

    public synchronized void printOdd() {
        while (counter <= limit) { // limit is inclusive
            if (counter % 2 == 0) {
                // if current count is even, wait for even thread to print
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (counter > limit) break;

            System.out.println(Thread.currentThread().getName() + " -> " + counter);
            counter++;
            notifyAll();
        }
    }

    public synchronized void printEven() {
        while (counter <= limit) {
            if (counter % 2 == 1) {
                // if counter is odd wait for odd thread to print
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (counter > limit) break;

            System.out.println(Thread.currentThread().getName() + " -> " + counter);
            counter++;
            notifyAll();
        }
    }
}

public class PrintEvenOddSequentially {
    public static void main(String[] args) {
        EvenOddCounter counter = new EvenOddCounter(20);

        Thread even = new Thread(counter::printEven, "Even");
        Thread odd = new Thread(counter::printOdd, "Odd");

        even.start();
        odd.start();
    }
}
