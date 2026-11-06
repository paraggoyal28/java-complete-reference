package com.core.chapter28;

class SequentialFourThreads {
    private final int limit;
    private volatile int count = 0;
    private int turn = 0;

    SequentialFourThreads(int limit) {
        this.limit = limit;
    }

    public synchronized void print(int threadId) {
        while (count <= limit) {
            while (turn != threadId) {
               try {
                   wait();
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
            }

            if (count > limit) {
                break;
            }

            System.out.println(Thread.currentThread().getName() + " prints " + count);
            count++;

            turn = (turn + 1) % 4;
            notifyAll();
        }
    }
}

public class SequentialDemo {
    public static void main(String[] args) {
        SequentialFourThreads sequentialFourThreads = new  SequentialFourThreads(10);

        Thread t1 = new Thread(() -> sequentialFourThreads.print(0), "Thread-1");
        Thread t2 = new Thread(() -> sequentialFourThreads.print(1), "Thread-2");
        Thread t3 = new Thread(() -> sequentialFourThreads.print(2), "Thread-3");
        Thread t4 = new Thread(() -> sequentialFourThreads.print(3), "Thread-4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
