package com.core.chapter21;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.PushbackReader;

public class PushbackReaderDemo {
	public static void main(String[] args) {
		String s = "if (a == 5) a = 0; \n";
		char buf[] = new char[s.length()];
		s.getChars(0, s.length(), buf, 0);
		CharArrayReader in = new CharArrayReader(buf);

		int c;
		try (PushbackReader pb = new PushbackReader(in)) {

			while ((c = pb.read()) != -1) {
				switch (c) {
					case '=':
					    if ((c = pb.read()) == '=') {
							System.out.print(".eq.");
						} else {
							System.out.print("<-");
							pb.unread(c);
						}
						break;
					default:
						System.out.print((char) c);
						break;
				}
			}
		} catch (IOException e) {
			System.err.println("I/O Error: " + e.getMessage());
		}
	}
}
