package com.core.chapter21;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class RwVsRwdTimingDifference {
	private static long writeManyDoubles(Path path, String mode, int count) throws IOException {
		Files.deleteIfExists(path);
		long startNs = System.nanoTime();

		try (RandomAccessFile raf = new RandomAccessFile(path.toFile(), mode)) {
			for (int i = 0; i < count; ++i) {
				raf.writeDouble(i * 1.5);
			}
		}

		return System.nanoTime() - startNs;
	}

	public static void main(String[] args) throws IOException {
		int iterations = 1_000;
	    Path rwFile = Path.of("bench-rw.bin");
		Path rwdFile = Path.of("bench-rwd.bin");

		long rwNanos = writeManyDoubles(rwFile, "rw", iterations);
		long rwdNanos = writeManyDoubles(rwdFile, "rwd", iterations);

		System.out.printf("\"rw\" (%d writes): %.2f ms\n", iterations, rwNanos/1_000_000.0);
		System.out.printf("\"rwd\" (%d writes): %.2f ms\n", iterations, rwdNanos/1_000_000.0);

		Files.deleteIfExists(rwFile);
		Files.deleteIfExists(rwdFile);
	}

}
