package com.example.spring.chapter12;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MySingle {
   int value();
}

public class Single {

    // Annotate a method using a single member annotation
    @MySingle(120)
    public static void myMeth() {
        Single obj = new Single();

        try {

            Method m = obj.getClass().getMethod("myMeth");

            MySingle anno = m.getAnnotation(MySingle.class);

            System.out.println(anno.value());
        } catch (NoSuchMethodException e) {
            System.out.println("No Such Method Found");
        }
    }

    public static void main(String[] args) {
        myMeth();
    }
}
