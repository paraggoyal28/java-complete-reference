package com.example.spring.chapter29;

import java.util.Arrays;
import java.util.List;

/*
You are given a list of Order objects. Each order contains a list of Item objects.
Each item has a name and a price. Write a stream query to get a distinct, alphabetically
sorted list of all item names across all orders that cost more than $15.
 */
class Item {
    String name;
    double price;
    Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    List<Item> items;
    Order(List<Item> items) {
        this.items = items;
    }
}

public class OrderFlattening {
    public static void main(String[] args) {
        Order o1 = new Order(Arrays.asList(new Item("Laptop", 1200), new Item("Mouse", 12)));
        Order o2 = new Order(Arrays.asList(new Item("Keyboard", 25), new Item("Mouse", 12)));

        List<Order> orders = Arrays.asList(o1, o2);

        List<String> premimumItems = orders.stream()
                // flatten List<Order> to List<Item>
                .flatMap(order -> order.items.stream())
                // Filter items
                .filter(item -> item.price > 15)
                // get the names of item
                .map(item -> item.name)
                // get the distinct names
                .distinct()
                // sort the names
                .sorted()
                .toList();
        System.out.println("Sorted distinct names of items having price greater than 15");
        System.out.println(premimumItems);
    }
}
