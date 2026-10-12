package com.example.spring.chapter12;

enum AppleWithPrice {
    Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland(8);

    private int price;

    AppleWithPrice(int p) {
        price = p;
    }

    int getPrice() {
        return price;
    }
}

public class EnumDemo4 {
    public static void main(String[] args) {
        AppleWithPrice ap;
        // Display price of Winesap
        System.out.println("Winesap Price: " + AppleWithPrice.Winesap.getPrice() + " cents.\n");

        // Display all apples and prices
        System.out.println("All apple prices:");
        for (AppleWithPrice a: AppleWithPrice.values()) {
            System.out.println(a + " costs: " + a.getPrice() + " cents.");
        }


    }
}
