package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        // create a list of integer values
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(10);
        myList.add(18);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        System.out.println("Original List: " + myList);

        // Obtain a stream to the array list
        Stream<Integer> myStream = myList.stream();

        // Obtain the minimum and maximum value by use of min()
        // and max(), isPresent(), and get()
        Optional<Integer> minVal = myStream.min(Integer::compare);
        minVal.ifPresent(integer -> System.out.println("Minimum Value: " + integer));

        // Must obtain a new stream because previous call to min()
        // is a terminal operation that consumed this stream
        myStream = myList.stream();
        Optional<Integer> maxVal = myStream.max(Integer::compare);
        maxVal.ifPresent(integer -> System.out.println("Maximum value: " + integer));

        // Sort the stream by use of sorted()
        Stream<Integer> sortedStream = myList.stream().sorted();

        // Display the sorted stream by use of forEach()
        System.out.println("Sorted stream: ");
        sortedStream.forEach(System.out::println);

        System.out.println();

        // Display the odd values by use of filter()
        Stream<Integer> oddValues = myList.stream().sorted().filter(n -> n%2 == 1);
        System.out.println("Odd values: ");
        oddValues.forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Display the odd values that are greater than 5. Notice that
        // two filter operations are pipelined
        oddValues = myList.stream().filter(n -> n%2 == 1)
                .filter(n -> n > 5);

        System.out.print("Odd Values greater than 5 are: ");
        oddValues.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
