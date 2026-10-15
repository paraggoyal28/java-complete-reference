package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.stream.Stream;

public class StreamDemo9 {
    public static void main(String[] args) {
        // Create a list of streams
        ArrayList<String> myList = new ArrayList<>();

        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
        myList.add("Omega");

        // Obtain a stream to the array list
        Stream<String> myStream = myList.stream();

        // Obtain a splitIterator
        Spliterator<String> splitIterator = myStream.spliterator();

        // Iterate the elements of the stream
      //  while (splitIterator.tryAdvance(System.out::println));
        splitIterator.forEachRemaining(System.out::println);

    }
}
