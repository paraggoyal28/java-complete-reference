package com.example.spring.chapter29;

/*
Grouping, Transformation, and Nested Collections Sorting
Scenario: We are processing transaction batches. We are given list of Transaction objects. Each transaction
consists of id, an amount(double) and a category(String).
Write a stream pipeline that groups these transactions by category, inside each category bucket, the
transactions must be sorted by amount in descending order. Finally return a TreeMap<String, List<Transaction>>
so that the categories themselves remain sorted alphabetically.
 */

import java.util.*;
import java.util.stream.Collectors;

class Transaction {
    private final int id;
    private final double amount;
    private final String category;

    public Transaction(int id, double amount, String category) {
        this.id = id;
        this.amount = amount;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d, $%.0f]", id, amount);
    }
}

public class TransactionProcessor {
    public static void main(String[] args) {
        List<Transaction> transactions =
                Arrays.asList(
                        new Transaction(1, 150.0, "Electronics"),
                        new Transaction(2, 1200.0, "Electronics"),
                        new Transaction(3, 45.0, "Groceries"),
                        new Transaction(4, 90.0, "Groceries"),
                        new Transaction(5, 300.0, "Clothing")
                );

        Map<String, List<Transaction>> processedMap =
                                            transactions.stream()
                                                    .collect(Collectors.groupingBy(
                                                            Transaction::getCategory,
                                                            TreeMap::new,
                                                            // Downstream Collector
                                                            Collectors.collectingAndThen(
                                                                    Collectors.toList(),
                                                                    list -> {
                                                                        list.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());
                                                                        return list;
                                                                    }
                                                            )
                                                    ));
        // Output will print sorted categories
        processedMap.forEach((category, txnList) -> {
            System.out.println(category + " -> " + txnList);
        });

    }
}
