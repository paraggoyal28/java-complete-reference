package com.core.chapter21;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RwVsRwdRecordExample {
	public static void updateBalance(String filePath, String mode, int recordIndex, double newBalance)
		    throws IOException {
		// Each record is 1 double (8 bytes)
		long offset = (long) recordIndex * Double.BYTES;

		try (RandomAccessFile raf = new RandomAccessFile(filePath, mode)) {
			raf.seek(offset);
			raf.writeDouble(newBalance);
			// with "rwd", This makes call waits for the content bytes to reach storage
			// before returning. with "rw", it returns as soon as the OS page cache accepts it
		}
	}

	public static void main(String[] args) throws IOException {
		String file = "balances.bin";

		// Initialize 3 account balances (24 bytes total) using "rw"
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.writeDouble(1000.0);
			raf.writeDouble(2400.0);
			raf.writeDouble(4200.0);
		}

		// high frequency/temporary update; OS-buffered ("rw")
		updateBalance(file, "rw", 0, 2000.0);

		// Critical state change that must survive power loss immediately
		updateBalance(file, "rwd", 1, 2475.34);

		// Read all three blocks
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			for (int i = 0; i < 3; ++i) {
				System.out.print("Account - " + i + " : " + raf.readDouble());
			}
		}
	}
}
