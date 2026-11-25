package com.core.chapter21;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {
	public static void main(String[] args) {

		try (FileReader fr = new
				 FileReader("Java-Source-Code\\src\\main\\java\\com\\core\\chapter21\\FileReaderDemo.java")) {
			int c;

			// Read and display the file
			while ((c = fr.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (IOException e){
			System.err.println(e.getMessage());
		}
	}
}
