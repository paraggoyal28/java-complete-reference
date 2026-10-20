package com.example.spring.chapter28;

import java.util.concurrent.Semaphore;

class Q {
    int n;
    Semaphore semProd = new Semaphore(1);
    Semaphore semCons = new Semaphore(0);

    public void put(int n) {
        try {
            semProd.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception caught");
        }
        // critical section
        this.n = n;
        // Print log
        System.out.println("Put: " + this.n);

        semCons.release();
    }

    public void get() {
        try {
            semCons.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception caught");
        }

        System.out.println("Got: " + n);
        semProd.release();
    }
}

class Producer implements Runnable {
    Q q;
    Producer(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            q.put(i);
        }
    }
}

class Consumer implements Runnable {
    Q q;
    Consumer(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            q.get();
        }
    }
}

public class ProdCon {
    public static void main(String[] args) {
        Q q = new Q();
        new Thread(new Producer(q), "Producer").start();
        new Thread(new Consumer(q), "Consumer").start();
    }
}
