package com.core.chapter29;

import java.util.ArrayList;
import java.util.List;

public class VarDemo {


    public static void main(String[] args) {
        var names = List.of("Bob", "Charlie", "Den");
        for (var name : names) {
            System.out.println(name);
        }
        var names2 = new ArrayList<>();
        System.out.println(names2);
       //  var x = null;
    }
}
