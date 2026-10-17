package com.example.spring.chapter14;

class GenBridge<T> {
    T ob;

    GenBridge(T ob) {
        this.ob = ob;
    }

    T getOb() {
        return ob;
    }
}

class Gen2Bridge extends GenBridge<String> {
    Gen2Bridge(String o) {
        super(o);
    }

    // A string specific override of getOb()
    String getOb() {
        System.out.println("You called String getOb(): ");
        return ob;
    }
}

public class BridgeDemo {
    public static void main(String[] args) {

        // create a Gen2 object for Strings
        Gen2Bridge strOb2 = new Gen2Bridge("Generics Bridge Test");

        System.out.println(strOb2.getOb());
    }
}
