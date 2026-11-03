package com.example.spring.newJavaFeatures;

import java.util.HashMap;
import java.util.List;


public class VarUsage {
    public static void main(String[] args) {
        // Basic Types
        var name = "John Doe"; // Compile infers String
        var age = 30; // Compiler infers int
        var salary = 76000.0; // Compiler infers Double

        // Complex Long types (where it really shines
        var employeeMap = new HashMap<String, List<String>>();

        // Inside loops
        for (var i = 0; i < 10; ++i) {
            System.out.println(i);
        }

        System.out.println(name);
        System.out.println(age);
        System.out.println(salary);
    }
}
