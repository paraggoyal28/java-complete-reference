package com.example.spring.chapter29;

import java.util.Collection;
import java.util.List;

public class MapAndFlatMap {
    public static void main(String[] args) {
        List<String> myList = List.of("Alice", "Bob", "Charlie", "Kevin", "George");
        List<List<String>> myRecList = List.of(List.of("Alice", "Bob"), List.of("Charlie","George"), List.of("Kevin"));
        System.out.println("Actual list: ");
        myList.stream().map(n -> n).forEach(n -> System.out.print(n + " "));
        System.out.println();
        myRecList.stream().flatMap(Collection::stream).forEach(n -> System.out.print(n + " "));
    }
}
