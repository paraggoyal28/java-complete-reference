package com.core.chapter28;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

public class SequencedCollectionsDemo {
    public static void main(String[] args) {
        SequencedCollection<String> names = new ArrayList<>();
        names.add("Vaibhav");
        names.add("Manish");
        names.add("Parag");
        System.out.println(names.getFirst());
        System.out.println(names.getLast());

        var reversed = names.reversed();
        names.forEach(System.out::println);

        SequencedSet<String> set = new LinkedHashSet<>();

        set.add("A");
        set.add("C");
        set.add("B");

        System.out.println(set.getFirst());
        System.out.println(set.getLast());

        SequencedMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "A");
        map.put(2, "C");
        map.put(3, "B");

        System.out.println(map.firstEntry());
        System.out.println(map.lastEntry());
    }
}
