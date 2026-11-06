package com.core.chapter29;

import java.util.HashSet;
import java.util.Set;

public class SetOfDemo {
    public static void main(String[] args) {
        Set<String> mySet = Set.of("Bob", "Charlie", "Den");
        System.out.println(mySet);
       // mySet.add("Go");
        //System.out.println(mySet);
      //  Set<String> notWorking = Set.of(null);
      //  System.out.println(notWorking);
       // Set<String> withDuplicates = Set.of("Bob", "Bob");
       // System.out.println(withDuplicates);
    }
}
