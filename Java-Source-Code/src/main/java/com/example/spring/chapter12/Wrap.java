package com.example.spring.chapter12;

public class Wrap {
    public static void main(String[] args) {
        Integer iObj = Integer.valueOf(100);

        int i = iObj.intValue();

        System.out.println(i + " " + iObj);
    }
}
