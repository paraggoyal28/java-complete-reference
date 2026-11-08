package com.core.chapter28;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

class Bank {
    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transform(int from, int to, double amount) {
        if (accounts[from] < amount) return;

        System.out.println(Thread.currentThread());

        accounts[from] -= amount;

        System.out.printf("%10.2f from %d to %d", amount, from, to);

        accounts[to] += amount;

        System.out.printf(" Total Balance: %10.2f", getTotalBalance());
    }

    /*
    Gets the sum of all account balances
     */

    public double getTotalBalance() {
        double sum = 0;

        for (double a: accounts) {
            sum += a;
        }

        return sum;
    }

    public int size() {
        return accounts.length;
    }
}

public class ThreadTest {
    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        var bank = new Bank(4, 1000);
        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; ++i) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transform(0, 1, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable task2 = () -> {
            try {
                for (int i = 0; i < STEPS; ++i) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transform(2, 3, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
