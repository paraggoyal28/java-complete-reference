package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo7 {
    public static void main(String[] args) {
        // A list of name, phone numbers and email addresses
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail("Larry", "555-5344", "larry@HerbSchidlt.com"));
        myList.add(new NamePhoneEmail("James", "555-4444", "James@HerbSchidlt.com"));
        myList.add(new NamePhoneEmail("Mary", "555-3322", "Mary@HerbSchidlt.com"));

        // Map just the names and phone numbers to a new stream
        Stream<NamePhone> nameAndPhones = myList.stream().map((a) -> new NamePhone(a.name, a.phone));

        // use collect to create a list of the names and phone numbers
        List<NamePhone> npList = nameAndPhones.collect(Collectors.toList());

        System.out.println("Names and phone numbers in a list: ");

        npList.add(new NamePhone("Parag", "234-3334"));

        for(NamePhone np : npList) {
                System.out.println(np.name + ": " + np.phone);
        }

//        List<NamePhone> directList = nameAndPhones.toList();

  //      System.out.println("Names and phone numbers in a list: ");

//        directList.add(new NamePhone("Parag", "234-3334"));

 //       for(NamePhone np : directList) {
  //          System.out.println(np.name + ": " + np.phone);
  //      }
        // Obtain another mapping of the name and phone numbers
        nameAndPhones = myList.stream().map((s) -> new NamePhone(s.name, s.phone));

        // Now create a set by use of collect()
        Set<NamePhone> npSet = nameAndPhones.collect(Collectors.toSet());

        System.out.println("\nNames and phone numbers in a Set.");

        for (NamePhone s: npSet) {
            System.out.println(s.name + " " + s.phone);
        }
    }
}
