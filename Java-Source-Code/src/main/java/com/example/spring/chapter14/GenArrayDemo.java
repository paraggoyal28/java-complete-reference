package com.example.spring.chapter14;

import java.util.Arrays;

class GenArray<T extends Number> {
    T obj;
    T vals[];

    GenArray(T ob, T[] nums) {
        this.obj = ob;
        // Error: vals = new T[10];
        vals = nums;
    }

    @Override
    public String toString() {
        return "GenArray{" +
                "obj=" + obj +
                ", vals=" + Arrays.toString(vals) +
                '}';
    }
}

public class GenArrayDemo {
    public static void main(String[] args) {
        Integer nums[] = {1, 2, 3, 4, 5, 6, 8};
        Double dNums[] = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};

        GenArray<Integer> iOb = new GenArray<>(50, nums);
        GenArray<Double> dOb = new GenArray<>(20.0, dNums);
        // Does n't compile
        //GenArray<Integer> t[] = new GenArray<Integer>[10];

        GenArray<?> gen[] = new GenArray<?>[10];
        gen[0] = iOb;
        gen[1] = dOb;

        System.out.println(gen[0]);
        System.out.println(gen[1]);

    }
}
