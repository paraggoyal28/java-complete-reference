package com.core.chapter21;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class ConsoleFlushExample {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in, StandardCharsets.UTF_8));
		// Wrap System.out in a BufferedWriter (do NOT close it, as that closes System.out)
		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(System.out, StandardCharsets.UTF_8));

		try {
			out.write("Enter your name: ");
			out.flush(); // Required so the prompt shows up before readLine() blocks

			String name = reader.readLine();
			if (name == null) {
				return;
			}

			out.write("Enter your role: ");
			out.flush(); // Flush second prompt before waiting for input

			String role = reader.readLine();
			if (role == null) {
				role = "guest";
			}

			out.write("Welcome, " + name.trim() + " [" + role.trim() + "]!");
			out.newLine();
			out.flush(); // Flush final message immediately
		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
}
