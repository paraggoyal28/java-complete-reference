package com.core.chapter21;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DataIODemo {
	public static void main(String[] args) {
		// First, write the data.
		try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("Test.txt"))) {

			dout.writeDouble(98.3);
			dout.writeInt(1300);
			dout.writeBoolean(false);

		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Cannot open output files");
			return;
		} catch (IOException ioException) {
			System.err.println("I/O error: " + ioException);
		}

		// Now, read the data back
		try (DataInputStream din = new DataInputStream(new FileInputStream("Test.txt"))) {
			double d = din.readDouble();
			int i = din.readInt();
			boolean b = din.readBoolean();

			System.out.println("Here are the values: " + d + " " + i + " " + b);
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Cannot open file to read " + fileNotFoundException.getMessage());
			return;
		} catch (IOException ioException) {
			System.err.println("IO Error: " + ioException.getMessage());
		}
	}
}
