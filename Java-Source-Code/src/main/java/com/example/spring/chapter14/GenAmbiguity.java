package com.example.spring.chapter14;

class GenAmb<T, V extends  Number> {
    T val;
    V val2;

    void set(T val) {
        this.val = val;
    }

    void set(V val2) {
        this.val2 = val2;
    }
}
/*
The below not compile

class StaticError<T> {
    static T ob;

    static T getOb() {
        return ob;
    }
}
*/


class ExceptionResolve<T> {
    T ob;

    static <T> T run(T myOb) {
        System.out.println(myOb.getClass().getName() + " : " + myOb);
        return myOb;
    }
}


class GenAmbiguity {
    public static void main(String[] args) {
        GenAmb<Number, Number> myObj = new GenAmb<>();
        //myObj.set(23);

        ExceptionResolve<Integer> ex = new ExceptionResolve<>();
        ex.run(10);
        ex.run("Generic Method");
        ex.run(true);
        ex.run(30.34);
    }
}
