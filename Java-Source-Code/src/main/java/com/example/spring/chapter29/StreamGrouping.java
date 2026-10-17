package com.example.spring.chapter29;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
The Problem: Given a list of strings, count the frequency of each string, group them,
and return a Map<String, Long>. However, you must filter out any strings that are completely
blank or null, and the keys in the resulting map must all be in uppercase.

 */
public class StreamGrouping {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Apple", null, "Banana", "  ", "Apple", "Orange", "Banana");
        Map<String, Long> frequencyMap = words.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(word -> !word.isBlank())
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(frequencyMap);
    }
}
