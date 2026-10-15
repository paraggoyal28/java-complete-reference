package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.stream.Stream;

public class StreamDemo10 {
    public static void main(String[] args) {

        // Create a list of strings
        ArrayList<String> myList = new ArrayList<>();

        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
        myList.add("Omega");

        // Obtain a stream to the original array
        Stream<String> myStream = myList.stream();

        // Obtain the spliterator
        Spliterator<String> spliterator = myStream.spliterator();

        // Now split the first iterator
        Spliterator<String> splitIterator2 = spliterator.trySplit();

        // if splitIterator could be split, use splitIterator2 first
        if (splitIterator2 != null) {
            System.out.println("Output from spliterator2: ");
            splitIterator2.forEachRemaining(System.out::println);
        }

        System.out.println();

        // Now, use the splitIterator
        if (spliterator != null) {
            System.out.println("Output from spliterator: ");
            spliterator.forEachRemaining(System.out::println);
        }

    }
}
