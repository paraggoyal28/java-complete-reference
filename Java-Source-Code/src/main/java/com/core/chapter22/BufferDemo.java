package com.core.chapter22;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class BufferDemo {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(64);
		buf.order(ByteOrder.LITTLE_ENDIAN);
		// position = 0, limit = 64, capacity = 64
		buf.put("NIO Buffer".getBytes(StandardCharsets.UTF_8));
		// position = 10, limit = 64
		buf.flip(); // read mode position = 0, limit = 10
		byte[] firstChunk = new byte[3];
		buf.get(firstChunk); // reads first 3 bytes
		System.out.println("First three bytes:" + new String(firstChunk, StandardCharsets.UTF_8));
		// position 3, limit = 10 (7 bytes remaining)
		buf.compact(); // moves remaining 7 bytes to 0..6, position = 7, limit = 64
		buf.put((byte) '!'); // append at index 7, position 8, limit 64
		buf.flip(); // load more, position = 0, limit = 8
		byte[] remaining = new byte[buf.remaining()];
		buf.get(remaining);
		System.out.println(new String(remaining, StandardCharsets.UTF_8));
	}
}
