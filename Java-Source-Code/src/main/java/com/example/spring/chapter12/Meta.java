package com.example.spring.chapter12;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str();
    int val();
}

public class Meta {
    // Annotate a method
    @MyAnno(str = "Annotation Example", val = 100)
    public static void myMeth() {
        Meta obj = new Meta();

        // Obtain the annotation for this method
        // and display the associated values

        try {
            // first get a class object that represents
            // this class
            Class<?> c = obj.getClass();

            // Now, get a Method object that represents
            // this method
            Method m = c.getMethod("myMeth");

            // Next, get the annotation for this class
            MyAnno myAnno = m.getAnnotation(MyAnno.class);

            // Finally display the values
            System.out.println(myAnno.str() + " " + myAnno.val());
        } catch (NoSuchMethodException e) {
            System.out.println("Method Not Found.");
        }
    }

    public static void main(String[] args) {
        myMeth();
    }
}
