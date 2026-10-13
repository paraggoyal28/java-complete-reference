package com.example.spring.chapter12;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker {}

public class Marker {

    // Annotate method with marker interface
    @MyMarker()
    public static void myMeth() {
        Marker obj = new Marker();

        try {
            Method m = obj.getClass().getMethod("myMeth");

            // Determine if the annotation is present
            if (m.isAnnotationPresent(MyMarker.class))
                System.out.println("myMeth has a marker annotation");


        } catch (NoSuchMethodException e) {
            System.out.println("Method not found!");
        }
    }

    public static void main(String[] args) {
        myMeth();
    }
}
