package com.example.spring.chapter12;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

// Container annotation
@Retention(RetentionPolicy.RUNTIME)
@interface MyRepeatedAnnos {
    MyAnnoRep[] value();
}


// Repeatable annotation MyAnno
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyRepeatedAnnos.class)
@interface MyAnnoRep {
    String str() default "Testing";
    int val() default 1000;
}

public class RepeatAnno {

    // Repeat myAnno on myMeth()
    @MyAnnoRep(str = "First annotation", val = -1)
    @MyAnnoRep(str = "Second annotation", val = 999)
    public static void myMeth(String str, int i) {
        RepeatAnno ob = new RepeatAnno();

        try {
            Class<?> c = ob.getClass();

            // Obtain the annotations for myMeth()
            Method m = c.getMethod("myMeth", String.class, int.class);

            // Display the repeated MyAnno annotation
            Annotation annos = m.getAnnotation(MyRepeatedAnnos.class);
            System.out.println(annos);

            Annotation[] myAnnos = m.getAnnotationsByType(MyAnnoRep.class);
            for (Annotation anno: myAnnos) {
                System.out.println(anno);
            }

        } catch (NoSuchMethodException e) {
            System.out.println("No such method present");
        }
    }

    public static void main(String[] args) {
        myMeth("Hello World", 1000);
    }
}
