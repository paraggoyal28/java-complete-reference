package com.example.spring.chapter14;

interface MinMax<T extends Comparable<T>> {
    T min();
    T max();
}

class MyClass<T extends Comparable<T>> implements MinMax<T> {
    T[] vals;

    MyClass(T[] o) {
        this.vals = o;
    }

    // Return minimum
    public T min() {
        T v = vals[0];

        for (int i = 1; i < vals.length; ++i) {
            if (vals[i].compareTo(v) < 0) v = vals[i];
        }

        return v;
    }

    // Return maximum
    public T max() {
        T v = vals[0];

        for (int i = 1; i < vals.length; ++i) {
            if (vals[i].compareTo(v) > 0) v = vals[i];
        }

        return v;
    }
}

public class GenIFDemo {
    public static void main(String[] args) {
        Integer iNums[] = {3, 5, 7, 2, 5, 6};
        Character chars[] = {'b', 'r', 'p', 'w'};

        MyClass<Integer> iOb = new MyClass<>(iNums);
        MyClass<Character> cOb = new MyClass<>(chars);

        System.out.println("Max value in inums: " + iOb.max());
        System.out.println("Min value in inums: " + iOb.min());

        System.out.println("Max value in chars: " + cOb.max());
        System.out.println("Min value in chars: " + cOb.min());
    }
}
