package com.example.spring.chapter29;

import java.util.List;

public class StreamComparison {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 30, 75, 40, 50);

        System.out.println("--- Using filter ---");
        numbers.stream()
                .filter(n -> n < 50)
                .forEach(System.out::println);

        System.out.println("--- Using takeWhile ----");
        numbers.stream()
                .takeWhile(n -> n < 50)
                .forEach(System.out::println);

    }
}
