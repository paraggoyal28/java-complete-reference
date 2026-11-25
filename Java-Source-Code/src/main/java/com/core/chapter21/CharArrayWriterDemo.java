package com.core.chapter21;

import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CharArrayWriterDemo {
	public static void main(String[] args) throws IOException {
		CharArrayWriter f = new CharArrayWriter();
		String s = "This should end up in the array";
		char buf[] = new char[s.length()];

		s.getChars(0, s.length(), buf, 0);
		try {
			f.write(buf);
		} catch (IOException e) {
			System.err.println("Error writing to buffer");
			return;
		}

		System.out.println("Buffer as a string");
		System.out.println(f.toString());
		System.out.println("Into array");

		char c[] = f.toCharArray();
		for (int i = 0; i < c.length; ++i) {
			System.out.print(c[i]);
		}

		System.out.println("\n To a FileWriter");

		// Use try-with-resources to manage the file stream
		try (FileWriter f2 = new FileWriter("test.txt")) {
			f.writeTo(f2);
		} catch (IOException e) {
			System.err.println("I/O Error: " + e.getMessage());
		}

		System.out.println("Doing a reset");
		f.reset();
		for (int i = 0 ; i < 3; ++i) f.write('X');

		System.out.println(f.toString());
	}
}
