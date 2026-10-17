package com.example.spring.chapter14;

class GenRaw<T> {
    T ob;

    GenRaw(T o) {
        this.ob = o;
    }

    T getOb() {
        return ob;
    }
}

public class RawDemo {
    public static  void main(String[] args) {
        // Creates a Gen objects for Integers
        Gen<Integer> iOb = new Gen<>(88);

        // Create a Gen object for String
        Gen<String> strOb = new Gen<>("Generic String");

        // Create a raw type Gen object and give it a double value
        Gen raw = new Gen(Double.valueOf(98.6));

        // cast here is necessary because type is unknown
        double d = (double) raw.getOb();
        System.out.println("value: " + d);

        // The use of raw type can lead to run-time
        // exceptions. Here are some examples
     //   int i = (Integer) raw.getOb(); // run time exception

        strOb = raw; // ok
      //  String str = strOb.getOb();

        raw = iOb; // ok
//        d = (Double) raw.getOb();



    }
}
