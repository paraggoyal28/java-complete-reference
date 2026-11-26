package com.core.chapter22;

import java.nio.charset.StandardCharsets;

public class Utf16StringEndian {
	public static void main(String[] args) {
		String text = "Hi There";
    	byte[] utf16BeBytes = text.getBytes(StandardCharsets.UTF_16LE);

		// Correct decoding; matching UTF_16BE
		System.out.println("Decoded as UTF-16BE " +  new String(utf16BeBytes, StandardCharsets.UTF_16BE));

		// Mismatched endianness: swaps each 2 byte pair -> different/garbled string
		System.out.println("Decoded as UTF-16LE: " + new String(utf16BeBytes, StandardCharsets.UTF_16LE));
	}
}
