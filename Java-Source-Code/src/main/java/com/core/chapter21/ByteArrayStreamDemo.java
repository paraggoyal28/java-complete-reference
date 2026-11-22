package com.core.chapter21;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByteArrayStreamDemo {
	public static void main(String[] args) {
		try {
			// 1. Write bytes into memory using ByteArrayOutputStream
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write("Hello ".getBytes(StandardCharsets.UTF_8));
			out.write("Java I/O".getBytes(StandardCharsets.UTF_8));

			byte[] capturedBytes = out.toByteArray();
			// copy of valid written bytes
			System.out.println("Captured byte count: " + out.size());

			// 2. Read bytes back from memory using ByteArrayInputStream
			ByteArrayInputStream in = new ByteArrayInputStream(capturedBytes);

			int b;
			StringBuilder decoded = new StringBuilder();
			while ((b = in.read()) != -1) {
				decoded.append((char) b);
			}

			System.out.println("Read back: " + decoded);

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
