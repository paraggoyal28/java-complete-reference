package com.core.chapter21;

import java.io.File;
import java.io.IOException;

public class MkdirDemo {
	public static void main(String[] args) {
		File file = new File("/myjava/html/file1.html");
		try {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				System.out.println("Folders created: " + parent.mkdirs()); // creates missing parent folders
			}

			boolean created = file.createNewFile();
			if (created) {
				System.out.println("File was newly created.");
			} else {
				System.out.println("File already existed");
			}
		} catch (IOException e) {
			System.err.println("Error creating file: " + e.getMessage());
		}
	}
}
