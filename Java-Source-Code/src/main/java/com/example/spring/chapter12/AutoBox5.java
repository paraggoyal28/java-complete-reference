package com.example.spring.chapter12;

public class AutoBox5 {
    public static void main(String[] args) {

        // Autobox/unbox a boolean
        Boolean b = true;

        // Below, b is auto-boxed when used in
        // a conditional expression, such as an if
        if (b) {
            System.out.println("b is true");
        }

        // Autobox/unbox a char
        Character ch = 'x'; // box a char
        char ch2 = ch;

        System.out.println("ch2 is: " + ch2);
    }
}
