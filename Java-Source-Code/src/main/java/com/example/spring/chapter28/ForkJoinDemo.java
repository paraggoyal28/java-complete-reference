package com.example.spring.chapter28;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class SqrtTransform extends RecursiveAction {
    final int seqThreshold = 1000;

    // Array to be accessed
    double[] data;
    int start;
    int end;

    SqrtTransform(double[] vals, int s, int e) {
        this.data = vals;
        this.start = s;
        this.end = e;
    }

    protected void compute() {

        // If the number of elements is below the sequential threshold,
        // then process sequentially
        if ((end - start) < seqThreshold) {
            // Transform each element into its square root
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            // Otherwise, compute to break down the data into smaller pieces
            int mid = start + (end - start) / 2;
            invokeAll(new SqrtTransform(data, start, mid),
                        new SqrtTransform(data, mid, end));
        }
    }
}


public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        double[] nums = new double[1000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) i;
        }

        System.out.println("A portion of the original sequence.");

        for (int i = 0; i < 1000; ++i) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);

        long startTime = System.currentTimeMillis();
        System.out.println("Start Time: " + System.currentTimeMillis());
        forkJoinPool.invoke(task);
        System.out.println("Duration: " + (System.currentTimeMillis() - startTime) + " milliseconds");


        System.out.println("A portion of the transformed sequence to four decimal places");

        for (int i = 0; i < 1000; ++i) {
           System.out.format("%.4f ", nums[i]);
        }

        System.out.println();
    }
}
