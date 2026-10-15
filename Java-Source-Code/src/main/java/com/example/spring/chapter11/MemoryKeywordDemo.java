package com.example.spring.chapter11;


import java.io.*;

// A class represents a User session that will be serialized
class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;

    // Normal fields are serialized
    public String username;

    // 1. TRANSIENT KEYWORD DEMONSTRATION
    // This password will be skipped during serialization for security reasons
    public transient String password;

    // 2. VOLATILE KEYWORD DEMONSTRATION
    // Without 'volatile', the BackgroundWorker thread might cache this variable
    // in a CPU register and never see the main thread change it to 'true'
    public volatile boolean isShutDownRequested = false;

    public UserSession(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class MemoryKeywordDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== 1. STARTING VOLATILE DEMONSTRATION === ");

        UserSession userSession = new UserSession("alice_dev", "Supersecret123");

        // This background thread simulates a worker reading the volatile flag
        Thread backgroundWorker = new Thread(() -> {
            System.out.println("[Worker] started. Waiting for shutdown request...");

            // Loop runs until isShutDownRequested becomes true
            while (!userSession.isShutDownRequested) {
                // If 'volatile' was missing, this loop could run FOREVER
                // because the thread might never fetch the updated value from the main memory
            }
            System.out.println("[Worker] Detected shutdown flag! Thread exiting gracefully!");
        });

        backgroundWorker.start();

        // Let the main thread sleep for 1 second to let the worker run
        Thread.sleep(1000);

        System.out.println("[Main] Requesting worker thread to shutdown....");

        // Main thread updates the volatile variable, because it is volatile,
        // it writes directly to Main memory, making it immediately visible to the worker thread
        userSession.isShutDownRequested = true;

        // Wait for the background thread to finish
        backgroundWorker.join();

        System.out.println("\n==== 2. STARTING TRANSIENT DEMONSTRATION ======");
        System.out.println("Before serialization");
        System.out.println("Username = " + userSession.username);
        System.out.println("Password = " + userSession.password);

        // SERIALIZE THE OBJECT (write to file)
        String fileName = "session.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(userSession);
            System.out.println("\n[System] Object successfully serialized to: " + fileName);
        }

        // DESERIALIZE THE OBJECT (Read from file)
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            UserSession restoredSession = (UserSession) in.readObject();

            System.out.println("\n After deserialization");
            System.out.println("Username: " + restoredSession.username);
            // The transient password field reset to its default reference type value (null)
            System.out.println("Password: " + restoredSession.password + " <--- Notice this will be null");
        }

        // Clean up temporary file
      //  new File(fileName).delete();
    }
}
