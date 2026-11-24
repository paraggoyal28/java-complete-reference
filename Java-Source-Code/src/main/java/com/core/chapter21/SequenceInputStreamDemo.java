package com.core.chapter21;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

class InputStreamEnumerator implements Enumeration<InputStream> {
	private Enumeration<String> files;

	public InputStreamEnumerator(Vector<String> files) {
		this.files = files.elements();
	}

	public boolean hasMoreElements() {
		return files.hasMoreElements();
	}

	public InputStream nextElement() {
		return InputStreamEnumerator.class.getResourceAsStream(files.nextElement());
	}
}

public class SequenceInputStreamDemo {
	public static void main(String[] args) {
		int c;
		Vector<String> files = new Vector<>();

		files.addElement("/helper/file1.txt");
		files.addElement("/helper/file2.txt");
		files.addElement("/helper/file3.txt");
		InputStreamEnumerator ise = new InputStreamEnumerator(files);
		InputStream input = new SequenceInputStream(ise);

		try {
			while ((c = input.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (NullPointerException npe) {
			System.err.println("Error opening file. " + npe);
		} catch (IOException ioe) {
			System.err.println("I/O Error: " + ioe);
		} finally {
			try {
				input.close();
			} catch (IOException ioException) {
				System.err.println("Error closing SequenceInputStream");
			}
		}
	}
}
