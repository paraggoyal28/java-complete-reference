package com.core.chapter21;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessSyncExample {
	public static void main(String[] args) {
		try (RandomAccessFile raf = new RandomAccessFile("metrics.bin", "rwd")) {

			raf.writeDouble(30.45); // bytes 0..7
			raf.writeDouble(22.45); // bytes 8..15

			// Jump already to the second double (8 bytes in) and overwrites it
			raf.seek(8);
			raf.writeDouble(99.78);

			// Seek back to the first and read both doubles
			raf.seek(0);
			System.out.println("First: " + raf.readDouble());
			System.out.println("Second: " + raf.readDouble());

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
