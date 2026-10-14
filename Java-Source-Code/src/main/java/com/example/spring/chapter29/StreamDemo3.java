package com.example.spring.chapter29;

import java.util.ArrayList;

public class StreamDemo3 {
    public static void main(String[] args) {
        // This is now a list of double values
        ArrayList<Double> myList = new ArrayList<>();

        myList.add(7.0);
        myList.add(18.9);
        myList.add(34.3);
        myList.add(234.3);
        myList.add(33.44);
        myList.add(5.4);

        double productOfSqrRoots = myList.parallelStream().reduce(1.0,
                (a, b) -> a * Math.sqrt(b),
                (a, b) -> a * b);
        double productOfSqrRoots2 = myList.parallelStream().reduce(1.0,
                (a, b) -> a * Math.sqrt(b));
        double productOfSqrRoots3 = myList.stream().reduce(1.0,
                (a, b) -> a * Math.sqrt(b));

        myList.parallelStream().unordered().forEach(e -> System.out.print(e + " "));
        System.out.println();
        myList.parallelStream().unordered().forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println("Product of square roots: " + productOfSqrRoots);
        System.out.println("Product of square roots: " + productOfSqrRoots2);
        System.out.println("Product of square roots: " + productOfSqrRoots3);

    }
}
