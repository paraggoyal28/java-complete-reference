package com.example.spring.chapter12;

// Autoboxing/unboxing within method parameters and return values
public class AutoBox2 {
    // Take an integer parameter and return
    // an int value
    static int m(Integer v) {
        return v; // auto unbox to int
    }

    public static void main(String[] args) {
        // Pass an int to m() and assign the return value
        // to an Integer. Here, the argument 100 is autoboxed
        // into an Integer. The return value is also autoboxed
        // into an Integer
        Integer iObj = m(100);

        System.out.println(iObj);
    }
}
