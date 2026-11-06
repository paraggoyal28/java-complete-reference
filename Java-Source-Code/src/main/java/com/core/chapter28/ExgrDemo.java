package com.core.chapter28;

import java.util.concurrent.Exchanger;

public class ExgrDemo {
    public static void main(String[] args) {
        Exchanger<String> stringExchanger = new Exchanger<>();
        new Thread(new MakeString(stringExchanger)).start();
        new Thread(new UseString(stringExchanger)).start();
    }
}

// A thread that constructs a string
class MakeString implements Runnable {
    Exchanger<String> ex;
    String str;

    MakeString(Exchanger<String> ex) {
        this.ex = ex;
        str = new String();
    }

    public void run() {
        char ch = 'A';
        for (int i = 0; i < 3; ++i) {
            // Fill buffer
            for (int j = 0; j < 5; ++j) {
                str += ch++;
            }
            try {
                // Exchange a full buffer for an empty one
                str = ex.exchange(str);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}


// A thread that uses a string
class UseString implements Runnable {
    Exchanger<String> ex;
    String str;
    UseString(Exchanger<String> c) {
        this.ex = c;
    }
    public void run() {
        for (int i = 0; i < 3; ++i) {
            try {
                // Exchange an empty buffer for a full one
                str = ex.exchange(new String());
                System.out.println("Got: " + str);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}