package com.core.chapter12;

public class UnboxError {
    public static void main(String[] args) {
        Integer iOb = 1000;

        int i = iOb.byteValue();

        System.out.println(i);
    }
}
