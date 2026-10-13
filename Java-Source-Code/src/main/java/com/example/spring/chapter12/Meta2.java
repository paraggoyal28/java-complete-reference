package com.example.spring.chapter12;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno2 {
    String str();
    int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What {
    String description();
}

@What(description = "An annotation test class")
@MyAnno2(str = "Meta2", val = 98)
public class Meta2 {

    @What(description = "An annotation test method")
    @MyAnno2(str = "Testing", val = 100)
    public static void myMeth() {
        Meta2 obj = new Meta2();

        try {
            Annotation[] annosClass = obj.getClass().getAnnotations();
            // Display all annotations for Meta2 class
            System.out.println("All annotations for Meta2 class!");
            for (Annotation a : annosClass) {
                System.out.println(a);
            }

            System.out.println();

            // Display all annotations for myMeth()
            Method m = obj.getClass().getMethod("myMeth");

            Annotation[] annosMethod = m.getAnnotations();

            System.out.println("All annotations for myMeth method!");
            for (Annotation a: annosMethod) {
                System.out.println(a);
            }

        } catch (NoSuchMethodException e) {
            System.out.println("Method not found");
        }
    }

    public static void main(String[] args) {
        myMeth();
    }
}
