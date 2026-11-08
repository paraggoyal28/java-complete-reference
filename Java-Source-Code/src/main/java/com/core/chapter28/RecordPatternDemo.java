package com.core.chapter28;

record Student(int id, String name) {}

record Address(String city) {}

record Athelete(String name, Address address) {}

record Point (int row, int col) {}

public class RecordPatternDemo {

    private static void evaluate(Object obj) {
        switch (obj) {
            case Integer i -> System.out.println("Given obj is an integer");
            case String s -> System.out.println("Given obj is a String");
            case Double d -> System.out.println("Given obj is a double");
            case Point(int x, int y) -> System.out.println("Point with x: " + x + " and y: " + y);
            default -> System.out.println("Type not known");
        }
    }

    public static void main(String[] args) {
        Student student = new Student(34, "Aman");

        System.out.println(student.id());
        System.out.println(student.name());

        if (student instanceof Student(int id, String name)) {
            System.out.println(id);
            System.out.println(name);
        }

        Athelete athelete = new Athelete("Parag", new Address("Jaipur"));

        if (athelete instanceof Athelete(String name, Address(String city))) {
            System.out.println(name);
            System.out.println(city);
        }

        Object obj = "Hello";
        Object obj2 = 99.3;

        evaluate(obj);
        evaluate(obj2);

        Point a = new Point(20, 30);
        Point b = new Point(0, 0);

        evaluate(a);
        evaluate(b);
    }
}
