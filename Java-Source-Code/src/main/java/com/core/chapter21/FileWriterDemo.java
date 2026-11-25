package com.core.chapter21;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
	public static void main(String[] args) throws IOException  {
		String source = "Now is the time for all good men of the country to come to the aid of their country and pay their taxes";
		char buffer[] = new char[source.length()];
		source.getChars(0, buffer.length, buffer, 0);

		try (FileWriter f0 = new FileWriter("file1.txt");
		    FileWriter f1 = new FileWriter("file2.txt");
			FileWriter f2 = new FileWriter("file3.txt")) {

			// write to first file
			for (int i = 0; i < buffer.length; i += 2) {
				f0.write(buffer[i]);
			}

			f1.write(buffer);

			f2.write(buffer, buffer.length - buffer.length / 4, buffer.length / 4);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
