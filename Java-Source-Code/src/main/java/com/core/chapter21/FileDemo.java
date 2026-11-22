package com.core.chapter21;

import java.io.File;
import java.io.IOException;

public class FileDemo {
	static void p(String s) {
		System.out.println(s);
	}
	public static void main(String[] args) {
		File f1 = new File("/java/COPYRIGHT"); // does not throw error if file doesnot exits

		try {
			File parent = f1.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs(); // create missing parent folders
			}



			boolean created = f1.createNewFile();
			if (created) {
				System.out.println("File was newly created.");
			} else {
				System.out.println("File already existed.");
			}
		} catch (IOException e) {
			System.out.println("Error creating file: " + e.getMessage());
		}


		p("File Name: " + f1.getName());
		p("Path:" + f1.getPath());
		p("Abs Path: " + f1.getAbsolutePath());
		p("Parent: "  + f1.getParent());
		p(f1.exists() ? "exists": "don't exists");
		p(f1.canWrite() ? "is writeable" : "is not writeable");
		p(f1.canRead() ? "is readable" : "is not readable");
		p("is " + (f1.isDirectory() ? " " : "not a directory"));
		p(f1.isFile() ? "is normal file" : "might be a named pipe");
		p(f1.isAbsolute() ? "is absolute": "is not absolute");
		p("File last modified: " + f1.lastModified());
		p("File size: " + f1.length() + " Bytes");

		File newFile = new File("/java/COPYRIGHT2.txt");
		System.out.println(f1.renameTo(newFile));
		System.out.println(f1.getName());
		System.out.println(f1.exists());
		System.out.println(newFile.exists());
		System.out.println(f1.delete());
		System.out.println(f1.getName());

		System.out.println(f1.toPath());
	}
}
