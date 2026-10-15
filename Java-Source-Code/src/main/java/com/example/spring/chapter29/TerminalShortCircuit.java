package com.example.spring.chapter29;

import java.util.stream.Stream;

public class TerminalShortCircuit {
    public static void main(String[] args) {
        // A stream of numbers that prints when an item is evaluated
        boolean found = Stream.of(1, 2, 3, 4, 5)
                .peek(n -> System.out.println("Processing: " + n))
                .anyMatch(n -> n == 3);
        System.out.println("Result: " + found);
    }
}
