package com.example.spring.chapter14;

class TwoGen<T, V> {
    T obj1;
    V obj2;

    TwoGen(T obj1, V obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    // Show types of T and V
    void showTypes() {
        System.out.println("Type of T is " + obj1.getClass().getName());
        System.out.println("Type of V is " + obj2.getClass().getName());
    }

    T getObj1() {
        return obj1;
    }

    V getObj2() {
        return obj2;
    }
}

public class SimpGen {
    public static void main(String[] args) {
        TwoGen<Integer, String> tgObj = new TwoGen<>(88, "Gdnerics");

        // show types
        tgObj.showTypes();

        int v = tgObj.getObj1();
        System.out.println("Value of object1: " + v);

        String str = tgObj.getObj2();
        System.out.println("Value of object2: " + str);
    }
}
