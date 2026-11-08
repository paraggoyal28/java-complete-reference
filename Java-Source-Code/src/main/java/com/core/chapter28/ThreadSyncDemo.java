package com.core.chapter28;

class VolatileHappensBeforeDemo  {
    private int a = 0;
    private int b = 0;
    private boolean flag = false;

    public void reset() {
        a = 0;
        b = 0;
        flag = false;
    }

    public void writerStep() {
        a = 42;         // 1. Non-volatile write
        b = 99;         // 2. Non-volatile write
        flag = true;    // 3. Volatile Write (Flushes a, b, and flag to main memory)
        reset();
    }

    public void readerStep() {
        if (flag) {     // 4. Volatile Read (Invalidates local cache, reads from main memory)
            // The Happens-Before rule guarantees that if flag is true,
            // the updates to 'a' and 'b' MUST be visible here.
            if (a == 0 || b == 0) {
                System.out.println("❌ HAPPNES-BEFORE VIOLATION DETECTED! a=" + a + ", b=" + b);
                System.exit(0);
            }
        }
        reset();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🚀 Starting Concurrency Stress Test... Press Ctrl+C to stop.");
        System.out.println("With 'volatile', this loop will run smoothly without violations.");

        long iterations = 0;

        while (true) {
            iterations++;
            VolatileHappensBeforeDemo demo = new VolatileHappensBeforeDemo();
            // Thread 1: Writer
            Thread writer = new Thread(() -> {
                demo.writerStep();
            });

            // Thread 2: Reader
            Thread reader = new Thread(() -> {
                demo.readerStep();
            });

            // Start both threads roughly simultaneously
            writer.start();
            reader.start();

            // Wait for both threads to finish this iteration
            writer.join();
            reader.join();

            // Print progress every 100,000 iterations to show active execution
            // Print progress every 100,000 iterations to show active execution
            if (iterations % 100000 == 0) {
                System.out.println("Checked " + iterations + " iterations... No violations.");
            }
        }
    }
}