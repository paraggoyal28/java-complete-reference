package com.core.chapter21;

import java.io.Console;
import java.util.Arrays;

public class ConsoleDemo {
	public static void main(String[] args) {
		String str;
		Console con;

		// obtain a reference to the console
		con = System.console();
		// If no console is available, exit
		if (con == null) return;

		// Read a string and then display it
		str = con.readLine("Enter a string: ");
		con.printf("Here is your string: %s\n", str);

		char[] password = con.readPassword("Enter a password:");
		char[] actualPassword = {'s', 'e', 'c', 'u', 'r', 'e'};
		if (password != null && Arrays.equals(password, actualPassword)) {
			System.out.println("Password is correct");
		}
		Arrays.fill(password, '\0');
		Arrays.fill(actualPassword, '\0');
	}
}
