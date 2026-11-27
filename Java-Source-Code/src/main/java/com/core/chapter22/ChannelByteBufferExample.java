package com.core.chapter22;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ChannelByteBufferExample {
	private static void writeToFile(Path path, String text) throws IOException {
		byte[] bytes = text.getBytes(StandardCharsets.UTF_8);

		// Allocate a byte buffer large enough for the data
		ByteBuffer buffer = ByteBuffer.allocate(256);
		buffer.put(bytes);

		// Switch buffer from writing mode to reading mode
		// limit = position, position = 0
		buffer.flip();

		try (FileChannel channel = FileChannel.open(path,
				StandardOpenOption.CREATE,
				StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING)) {
			// Loop because a single write() might not be write all remaining bytes
			while (buffer.hasRemaining()) {
				channel.write(buffer);
			}
		}
	}

	public static void main(String[] args) {
		Path filePath = Path.of("nio-demo.txt");
		try {
			writeToFile(filePath, "hello, Java NIO channels and bytebuffer\n second line");
			String content = readFromFile(filePath);
			System.out.println("Read back\n" + content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readFromFile(Path path) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(64);
		StringBuilder result = new StringBuilder();

		try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
			while (channel.read(buffer) != -1) {
				buffer.flip();
				while (buffer.hasRemaining()) {
					result.append((char) buffer.get());
				}
				buffer.clear();
			}
		}

		return result.toString();
	}

}
