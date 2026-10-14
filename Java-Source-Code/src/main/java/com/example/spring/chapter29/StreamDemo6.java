package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class StreamDemo6 {
    public static void main(String[] args) {
        // A list of double values!
        ArrayList<Double> myList = new ArrayList<>();

        myList.add(1.1);
        myList.add(3.5);
        myList.add(9.2);
        myList.add(4.7);
        myList.add(12.1);
        myList.add(5.0);

        System.out.println("Original values in myList are: ");
        myList.stream().forEach((a) -> {
            System.out.print(a + " ");
        });

        System.out.println();

        // Map the ceiling of the elements in myList to an IntStream
        IntStream cStrm = myList.stream().mapToInt((a) -> (int) Math.ceil(a));

        System.out.println("The ceilings of the values in myList are: ");
        cStrm.forEach(a -> {
            System.out.print(a + " ");
        });

        System.out.println();
    }
}
