package com.core.chapter21;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PrintfDemo {
	public static void main(String[] args) {
		System.out.println("Here are some numeric values in different formats.\n");
		System.out.printf("Various integer formats: ");
		System.out.printf("%d %(d %+d %05d\n", 3, 3, 3, 3);
		System.out.println();
		System.out.printf("Default floating point format: %f\n", 123456.123);
		System.out.printf("Floating point with commas: %,f\n", 1234567.123);
		System.out.printf("Negative floating point default: %,f\n", -1234567.123);
		System.out.printf("Negative floating point option: %,(f\n", -1234567.123);
		System.out.println();
		System.out.printf("Line up positive and negative values: \n");
		System.out.printf("%, .2f\n%, .2f\n", 1234567.123, -1234567.123);

		String outputFile = "output.txt";
		try (PrintStream out = new PrintStream(outputFile, StandardCharsets.UTF_8)) {
			out.println("First line");
			out.printf("Port: %d, SSL enabled: %b\n", 8443, true);
			out.print("Inline text without newline");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
