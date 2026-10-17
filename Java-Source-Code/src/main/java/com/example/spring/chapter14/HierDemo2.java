package com.example.spring.chapter14;

class NonGen {
    int num;

    NonGen(int i) {
        this.num = i;
    }

    int getNum() {
        return this.num;
    }
}

// A generic subclass
class GenSubNonGenSup<T> extends NonGen {
    T ob; // declares an object of type T

    // Pass the constructor a reference to
    // an object of type T
    GenSubNonGenSup(T o, int i) {
        super(i);
        ob = o;
    }

    // Return ob
    T getOb() {
        return ob;
    }
}

public class HierDemo2 {
    public static void main(String[] args) {
        // create a gen object for string
        GenSubNonGenSup<String> w = new GenSubNonGenSup<>("Hello", 47);

        System.out.print(w.getOb() + " ");
        System.out.println(w.getNum());
    }
}
