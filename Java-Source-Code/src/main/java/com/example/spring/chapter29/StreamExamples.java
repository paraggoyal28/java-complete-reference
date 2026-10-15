package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StreamExamples {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "mango");
        list.stream().peek(System.out::println);
        Stream.of("A", "B", "C")
                .filter(s -> {
                    System.out.print("filter: " + s + " ");
                    return true;
                }).forEach(s -> System.out.print("forEach: " + s + " "));
        System.out.println();
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3));
        Iterator<Integer> itr = numbers.iterator();
        while (itr.hasNext()) {
            Integer current = itr.next();
            if (current == 2) {
                itr.remove();
                continue;
            }
            System.out.println(current);
        }

        System.out.println(numbers);
    }
}
