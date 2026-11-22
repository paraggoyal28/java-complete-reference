package com.core.chapter21;

import java.io.ByteArrayInputStream;

public class ByteArrayInputStreamDemo {
	private static void print(ByteArrayInputStream b) {
		int c;
		while ((c = b.read()) != -1) {
			System.out.print((char) c);
		}
		System.out.println();
	}
	public static void main(String[] args) {
		String tmp = "abcdefghijklmnopqrstuvwxyz";
		byte b[] = tmp.getBytes();
		ByteArrayInputStream input1 = new ByteArrayInputStream(b);
		ByteArrayInputStream input2 = new ByteArrayInputStream(b, 0, 3);
		print(input1);
		print(input2);
	}
}
