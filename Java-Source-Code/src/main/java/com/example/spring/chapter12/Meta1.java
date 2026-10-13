package com.example.spring.chapter12;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno1 {
    String str();
    int val();
}

public class Meta1 {
    // myMeth has two parameters
    @MyAnno1(str = "Two Parameters", val = 19)
    public static void myMeth(String str, int i) {
        Meta1 obj = new Meta1();

        try {
            Class<?> c = obj.getClass();

            Method m = c.getMethod("myMeth", String.class, int.class);

            MyAnno1 myAnno = m.getAnnotation(MyAnno1.class);

            System.out.println("String value is: " + myAnno.str() + " and the integer value is: " +
                    myAnno.val());
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found");
        }
    }

    public static void main(String[] args) {
        myMeth("Test", 10);
    }
}
