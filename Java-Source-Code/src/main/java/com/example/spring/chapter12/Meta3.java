package com.example.spring.chapter12;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnoDefault {
    String str() default "Testing";
    int val() default 999;
}

public class Meta3 {

    // Annotate a method
    @MyAnnoDefault()
    public static void myMeth() {
        Meta3 obj = new Meta3();

        // Obtain the annotation for this method
        // and display the values of the members
        try {
            Class<?> c = obj.getClass();

            Method m = c.getMethod("myMeth");

            MyAnnoDefault myAnnoDefault = m.getAnnotation(MyAnnoDefault.class);

            System.out.println(myAnnoDefault.str() + " " + myAnnoDefault.val());
        } catch (NoSuchMethodException e) {
            System.out.println("No Such Method Found!");
        }
    }
    public static  void main(String[] args) {
        myMeth();
    }
}
