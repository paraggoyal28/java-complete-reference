package com.core.chapter21;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {
	public static void main(String[] args) {
		int size;
		try (FileInputStream f = new FileInputStream("Java-Source-Code/src/main/java/com/core/chapter21/FileInputStreamDemo.java")) {
			System.out.println("Total Available Bytes: " + (size = f.available()));

			int n = size / 20;
			System.out.println("First " + n + " bytes of the file one read() at a time");
			for (int i = 0; i < n; ++i) {
				System.out.print((char) f.read());
			}

			System.out.println("\nSill available: " + f.available());
			System.out.println("Reading the next " + n + " with one read(b[])");
			byte b[] = new byte[n];
			if (f.read(b) != n) {
				System.err.println("Couldn't read " + n + " bytes.");
			}

			System.out.println(new String(b, 0, n));
			System.out.println("\n Still available: " + (size = f.available()));
			System.out.println("Skipping half of remaining bytes with skip[]");
			f.skip(size/2);
			System.out.println("Still available: " + f.available());

			System.out.println("Reading: " + n/2 + " into the end of the array");
			if (f.read(b, 0, n) != n) {
				System.err.println("Couldn't read: "+ n + " bytes");
			}
			System.out.println(new String(b));
			System.out.println("\n Still available: " + f.available());
		} catch (IOException e) {
			System.err.println("I/O Error: " + e);
		}
	}
}
