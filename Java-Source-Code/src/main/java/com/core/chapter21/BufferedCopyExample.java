package com.core.chapter21;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class BufferedCopyExample {
	public static void main(String[] args) {


		String sourcePath = "D:\\java\\COPYRIGHT.txt";
		String targetPath = "D:\\java\\COPYRIGHT2.txt";

		int bufferSize = 128;

		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourcePath), bufferSize);
			 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetPath), bufferSize)) {

			int byteRead;

			while ((byteRead = bis.read()) != -1) {
				System.out.print((char) byteRead);
				bos.write(byteRead);
			}

			bos.flush();

			System.out.println("File copied successfully, using buffered byte streams.");

		} catch (IOException e) {
			System.err.println("I/O Exception while copy: " + e.getMessage());
		}
	}
}
