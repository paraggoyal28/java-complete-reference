package com.core.chapter22;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PositionedReadExample {
	public static void main(String[] args) {
		Path file = Path.of("notes.txt");
		try (FileChannel channel = FileChannel.open(file, StandardOpenOption.READ)) {
			ByteBuffer buf = ByteBuffer.allocate(100);

			channel.read(buf, 6);
			buf.flip();
			System.out.println(StandardCharsets.UTF_8.decode(buf));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
