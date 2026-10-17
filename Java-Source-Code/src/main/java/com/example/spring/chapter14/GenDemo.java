package com.example.spring.chapter14;

// A simple Generics class
class Gen<T> {
    T ob; // declare an object of type T

    // Pass the constructor a reference to
    // an object of type T
    Gen(T ob) {
        this.ob = ob;
    }

    T getOb() {
        return this.ob;
    }

    void showType() {
        System.out.println("Type of T is: " + ob.getClass().getName());
    }
}

// Demonstrate the generics class
public class GenDemo {
    public static void main(String[] args) {
        // Create a Generic reference for Integers
        Gen<Integer> iOb;

        // Create a generic Gen<Integer> object and assign its reference
        // to iOb. Notice the use of autoboxing to encapsulate the value as
        // within as Integer object
        iOb = new Gen<Integer>(90);

        // show the type of data
        iOb.showType();

        // get the value in iob.
        int v = iOb.getOb();

        System.out.println("Value: " + v);

        System.out.println();

        // Create a Gen object for Strings
        Gen<String> strOb = new Gen<String>("Generics Test");

        // show the type of data used by strOb
        strOb.showType();

        // Get the value of strOb. Again, notice that no cast is needed
        String str = strOb.getOb();

        System.out.println("Value: " + str);

    }
}
