package com.example.spring.chapter14;

class GenHier<T> {
    T ob;

    GenHier(T ob) {
        this.ob = ob;
    }

    T getOb() {
        return this.ob;
    }
}

class GenHier2<T> extends GenHier<T> {
    GenHier2(T ob) {
        super(ob);
    }
}

public class HierDemo3 {
    public static void main(String[] args) {

        // Creates a GenHier object for Integers
        GenHier<Integer> iOb = new GenHier<>(88);

        // Creates a GenHier2 object for integers
        GenHier2<Integer> iOb2 = new GenHier2<>(99);

        // Create a GenHier2 object for strings
        GenHier2<String> strOb2 = new GenHier2<>("Generics Test");

        // See if iOb2 is some form of GenHier2
        if (iOb2 instanceof GenHier2<?>) {
            System.out.println("iOb2 is instance of GenHier2");
        }

        // See if iOb2 is some form of GenHier
        if (iOb2 instanceof GenHier<?>) {
            System.out.println("iOb2 is instance of GenHier");
        }

        System.out.println();

        // see if strOb2 instanceof Gen2
        if (strOb2 instanceof GenHier2<?>) {
            System.out.println("strOb2 instance of GenHier2");
        }

        // see if strOb2 instanceof Gen
        if (strOb2 instanceof GenHier<?>) {
            System.out.println("strOb2 instance of GenHier");
        }

        System.out.println();

        // See if iOb is instance of GenHier2, which it is not
        if (iOb instanceof GenHier2<?>) {
            System.out.println("iOb is instance of GenHier2");
        }

        if (iOb instanceof GenHier<?>) {
            System.out.println("iOb is instance of GenHier");
        }


        iOb = (GenHier<Integer>) iOb2;

      //  GenHier<Long> lOb = (GenHier<Long>) iOb2;
        // The following won't be compiled because
        // generic type info does not exist at run time
        if (iOb2 instanceof GenHier2<Integer>) {
            System.out.println("iOb2 is instance of Gen2<Integer>");
        }

    }
}
