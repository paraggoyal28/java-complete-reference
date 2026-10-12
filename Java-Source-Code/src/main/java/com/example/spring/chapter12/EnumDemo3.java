package com.example.spring.chapter12;

// 1. Define the interface to implement
interface Pinnable {
    void displayPin();
}

// 2. Define the Enum class implementing the interface
enum CourierStatus implements Pinnable {

    // Enum constants must always be declared first.
    // They pass values directly to the custom constructor.
    PENDING(101, "Package is at the warehouse."),
    IN_TRANSIT(102, "Package is on the way."),
    DELIVERED(103, "Package reached its destination.");

    // --- VARIABLES ---
    // Instance variables (usually private and final to maintain immutability)
    private final int statusCode;
    private final String description;

    // A static variable (shared across the entire enum class)
    public static final String COMPANY_NAME = "GlobalExpress";

    // --- CONSTRUCTOR ---
    // Enum constructors are always private or package-private
    CourierStatus(int statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    // --- INSTANCE METHOD ---
    // A standard method that operates on a specific enum instance
    public String getFullDetails() {
        return "Status " + statusCode + ": " + description;
    }

    // --- STATIC METHOD ---
    // A utility method belonging to the class itself, not a specific instance
    public static CourierStatus getByCode(int code) {
        for (CourierStatus status : CourierStatus.values()) {
            if (status.statusCode == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }

    // --- IMPLEMENTED INTERFACE METHOD ---
    // Fulfilling the contract from the Pinnable interface
    @Override
    public void displayPin() {
        System.out.println("[" + COMPANY_NAME + "] Current state: " + this.name());
    }
}

public class EnumDemo3 {
     public static void main(String[] args) {
         CourierStatus myStatus = CourierStatus.DELIVERED;

         String details = myStatus.getFullDetails();
         System.out.println(details);
         System.out.println("Courier Company: " + CourierStatus.COMPANY_NAME);
         CourierStatus shippingStatus = CourierStatus.PENDING;

// Call the overridden interface method
         shippingStatus.displayPin();
// Output: [GlobalExpress] Current state: PENDING

// You can also assign it to an interface reference variable
         Pinnable pinnableItem = CourierStatus.IN_TRANSIT;
         pinnableItem.displayPin();

         CourierStatus statusFromDatabase = CourierStatus.getByCode(102);

         System.out.println("Found status: " + statusFromDatabase.name());
     }
}