package com.core.chapter21;

import java.io.ByteArrayInputStream;

public class ByteArrayInputStreamDemo2 {
	public static void main(String[] args) {
		String tmp = "abc";
		byte[] b = tmp.getBytes();
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		// print once in lowercase and once in uppercase
		for (int i = 0; i < 2; ++i) {
			int c;
			while ((c = in.read()) != -1) {
				if (i == 0) {
					System.out.print((char) c);
				} else {
					System.out.print(Character.toUpperCase((char) c));
				}
			}
			System.out.println();
			in.reset();
		}
	}
}
