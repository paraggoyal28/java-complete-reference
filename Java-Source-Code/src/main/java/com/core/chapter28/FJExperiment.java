package com.core.chapter28;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class Transform extends RecursiveAction {
    // Sequential threshold, which is set by the constructor
    int seqThreshold;

    // Array to be accessed
    double[] data;

    // Determine the start, end of the data
    int start, end;

    Transform(double[] data, int start, int end, int seqThreshold) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.seqThreshold = seqThreshold;
    }

    // Method to do parallel execution
    protected void compute() {
        // If the number of elements is below the sequential threshold
        // then process sequentially
        if ((end - start) <= seqThreshold) {
            // Introduce some delayed code
            for (int i = start; i < end; i++) {
                // even elements
                if (data[i] % 2 == 0) {
                    data[i] = Math.sqrt(data[i]);
                } else {
                    data[i] = Math.cbrt(data[i]);
                }
            }
        } else {
            int middle = (start + end) / 2;
            invokeAll(new Transform(data, start, middle, seqThreshold),
                    new Transform(data, middle, end, seqThreshold));
        }
    }
}

public class FJExperiment {
    public static void main(String[] args) {
        int pLevel;
        int threshold;

        if (args.length != 2) {
            System.out.println("Usage: FJExperiment requires parallel level and threshold");
            return;
        }

        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);

        long beginT, endT;

        ForkJoinPool pool = new ForkJoinPool(pLevel);

        double[] nums = new double[1000000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) i;
        }

        Transform task = new Transform(nums, 0, nums.length, threshold);

        // Starting the timing
        beginT = System.nanoTime();

        pool.invoke(task);

        endT = System.nanoTime();

        System.out.println("Level of parallelism: " + pLevel);
        System.out.println("Sequential threshold: " + threshold);
        System.out.println("Elapsed Time: "  + (endT - beginT) + " ns.");
        System.out.println();
    }
}
