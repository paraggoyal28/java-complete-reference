package com.core.chapter22;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileChannelExample {
	public static void main(String[] args) throws IOException  {
		Path file = Path.of("notes.txt");

		// 1. Write to file
		try (FileChannel writeChannel = FileChannel.open(file,
					StandardOpenOption.CREATE,
					StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING)) {
			ByteBuffer buffer = ByteBuffer.wrap("Hello filechannel\n".getBytes(StandardCharsets.UTF_8));
			while (buffer.hasRemaining()) {
				writeChannel.write(buffer);
			}
		}

		// 2. Read from buffer
		try (FileChannel readChannel = FileChannel.open(file, StandardOpenOption.READ)) {
			ByteBuffer buffer = ByteBuffer.allocate(64);
			while (readChannel.read(buffer) != -1) {
				buffer.flip();
				System.out.println(StandardCharsets.UTF_8.decode(buffer));
				buffer.clear();
			}
		}
	}
}
