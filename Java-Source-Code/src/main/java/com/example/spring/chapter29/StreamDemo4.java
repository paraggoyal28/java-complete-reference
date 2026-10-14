package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamDemo4 {
    public static void main(String[] args) {
        // A list of double values
        ArrayList<Double> myList = new ArrayList<>();

        myList.add(8.7);
        myList.add(18.9);
        myList.add(19.2);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);

        // Map the square root of the elements in myList of a new stream
        Stream<Double> sqrtRootStream = myList.stream().map(Math::sqrt);

        // Find the product of the square roots
        double productOfSqrRoots = sqrtRootStream.reduce(1.0, (a, b) -> a * b);

        System.out.println("Product of square roots is " + productOfSqrRoots);
    }
}
