package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.stream.Stream;

class NamePhoneEmail {
    String name;
    String phone;
    String email;
    NamePhoneEmail(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

class NamePhone {
    String name;
    String phone;

    NamePhone(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

public class StreamDemo5 {
    public static void main(String[] args) {
        // A list of name, phone and email addresses
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail("Larry", "555-5555", "larry@HerbSchidlt.com"));
        myList.add(new NamePhoneEmail("James", "555-4444", "James@HerbSchidlt.com"));
        myList.add(new NamePhoneEmail("Mary", "555-2233", "Mary@HerbSchidlt.com"));

        System.out.println("Original values in myList: ");
        myList.stream().forEach((s) -> {
            System.out.println(s.name + " " + s.phone + " " + s.email);
        });
        System.out.println();

        // Map just the name and phone numbers to new stream
        Stream<NamePhone> nameAndPhone = myList.stream().map((s) -> new NamePhone(s.name, s.phone));

        System.out.println("List of names and phone numbers: ");

        nameAndPhone.forEach((s) -> {
            System.out.println(s.name + " " + s.phone);
        });
    }
}
