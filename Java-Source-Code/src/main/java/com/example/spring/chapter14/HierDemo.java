package com.example.spring.chapter14;

class GenSuper<T> {
    T ob;

    GenSuper(T ob) {
        this.ob = ob;
    }

    T getOb() {
        return ob;
    }
}

class GenSub<T, V> extends GenSuper<T> {
    V obj2;

    GenSub(T ob1, V ob2) {
        super(ob1);
        this.obj2 = ob2;
    }

    V getOb2() {
        return this.obj2;
    }
}

public class HierDemo {
    public static void main(String[] args) {
        // Create a Gen2 object for String and Integer
        GenSub<String, Integer> x = new GenSub<>("Value is: ", 99);

        System.out.print(x.getOb());
        System.out.println(x.getOb2());
    }
}
