package com.example.spring.chapter29;

import java.util.ArrayList;
import java.util.Optional;

public class StreemDemo2 {
    public static void main(String[] args) {
        // create a list of Integer values
        ArrayList<Integer> myList = new ArrayList<>();

        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        // Two ways to obtain the Integer product of the elements
        // in myList to use of reduce()
        Optional<Integer> productObj = myList.stream().reduce((a, b) -> a * b);

        productObj.ifPresent(integer -> System.out.println("Product as Optional: " + integer));

        int product = myList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product as int: " + product);

        int evenProduct = myList.stream().reduce(1, (a, b) -> b%2 == 0 ? a * b : a);
        System.out.println("Even product: " + evenProduct);


    }
}
