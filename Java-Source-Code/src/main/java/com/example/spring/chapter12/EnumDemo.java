package com.example.spring.chapter12;

// An enumeration of apple variables
enum Apple {
    Jonathan, Goldendel, RedDel, Winesap, Cortland
}

public class EnumDemo {
    public static void main(String[] args) {
        Apple ap;

        ap = Apple.RedDel;

        // Output an enum value
        System.out.println("Value of ap: " + ap);
        System.out.println();

        ap = Apple.Goldendel;

        // Compare the enum values
        if (ap == Apple.Goldendel) {
            System.out.println("ap contains Goldendel.\n");
        }

        // Use an enum to control a switch statement
        switch (ap) {
            case Jonathan:
                    System.out.println("Jonathan is red.");
                    break;
            case Goldendel:
                    System.out.println("Golden Delicious is yellow.");
                    break;
            case RedDel:
                    System.out.println("Red Delicious is red.");
                    break;
            case Winesap:
                    System.out.println("Winesap is red.");
                    break;
            case Cortland:
                    System.out.println("Cortland is red.");
                    break;
        }
    }
}
