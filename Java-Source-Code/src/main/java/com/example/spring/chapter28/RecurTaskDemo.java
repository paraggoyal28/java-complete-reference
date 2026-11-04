package com.example.spring.chapter28;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumRecurse extends RecursiveTask<Double> {
    // The sequential threshold value
    final int seqThreshold = 500;

    // Array to be accessed
    double[] data;

    // Determine what part of data to be processed
    int begin;
    int end;

    SumRecurse(double[] data, int begin, int end) {
        this.data = data;
        this.begin = begin;
        this.end = end;
    }

    // Find the summation of an array of double
    protected Double compute() {
        double sum = 0;

        // If number of elements is below the sequential threshold
        // then process sequentially
        if ((end - begin) < seqThreshold) {
            // Sum the elements
            for (int i = begin; i < end; i++) {
                sum += data[i];
            }
        } else {
            // Otherwise, compute to break the data into smaller parts

            // Find the midpoint
            int middle = (begin + end)/2;

            // invoke new tasks, using the subdivided data
            SumRecurse subTaskA =  new SumRecurse(data, begin, middle);
            SumRecurse subTaskB =  new SumRecurse(data, middle, end);

            subTaskA.fork();
            subTaskB.fork();

            sum =  subTaskA.join() + subTaskB.join();
        }

        return sum;
    }
}

public class RecurTaskDemo {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();

        double[] nums = new double[5000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) (((i%2) == 0) ? -i : +i);
        }

        SumRecurse task = new SumRecurse(nums, 0, nums.length);

        // Start the forkJoinTask. Notice that, in this case
        // invoke() returns a result
        double summation = fjp.invoke(task);

        System.out.println("Summation... " + summation);
    }
}

