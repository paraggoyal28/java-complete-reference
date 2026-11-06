package com.app;

import com.core.Greeter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String message = Greeter.getGreeting("Parag");
        System.out.println(message);
    }
}