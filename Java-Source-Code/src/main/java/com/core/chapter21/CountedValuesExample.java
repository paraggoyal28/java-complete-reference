package com.core.chapter21;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CountedValuesExample {
	public static void main(String[] args) {
		String file = "values.bin";
		double[] prices = {10.4, 25.6, 99.89};
		int[] count = {1, 3, 6};

		try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeInt(prices.length); // to help in reading

			for (double price: prices) {
				out.writeDouble(price);
			}

			out.writeInt(count.length); // length to help in reading

			for (int cnt: count) {
				out.writeInt(cnt);
			}

		} catch (IOException ioException) {
			System.err.println("Error writing to file: " + ioException.getMessage());
		}

		try (DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {

			int pricesLength = din.readInt();
			System.out.println("Total prices: " + pricesLength);

			for (int i = 0; i < pricesLength; ++i) {
				System.out.print(din.readDouble() + " ");
			}

			System.out.println();

			int countLength = din.readInt();
			System.out.println("Count length: " + countLength);

			for (int i = 0; i < countLength; ++i) {
				System.out.print(din.readInt() + " ");
			}
			System.out.println();

		} catch (IOException ioException) {
			System.err.println("Error reading file: " + ioException.getMessage());
		}
	}
}
