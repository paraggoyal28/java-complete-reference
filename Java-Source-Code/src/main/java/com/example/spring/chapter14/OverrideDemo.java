package com.example.spring.chapter14;

class GenOverride<T> {
    T ob;

    GenOverride(T ob) {
        this.ob = ob;
    }

    T getOb() {
        System.out.println("Inside GenOverride method");
        return this.ob;
    }
}

class Gen2Override<T> extends GenOverride<T> {
    Gen2Override(T ob) {
        super(ob);
    }

    // Override
    T getOb() {
        System.out.println("Inside Gen2Override method");
        return this.ob;
    }
}

public class OverrideDemo {
    public static void main(String[] args) {
        // create a GenOverride object for Integers
        GenOverride<Integer> iOb = new GenOverride<>(88);

        // Create a Gen2Override object for integers
        GenOverride<Integer> iOb2 = new Gen2Override<>(99);

        // Create a Gen2Override object for Strings
        GenOverride<String> strOb2 = new Gen2Override<>("Generics Test");

        System.out.println(iOb.getOb());
        System.out.println(iOb2.getOb());
        System.out.println(strOb2.getOb());
    }
}
