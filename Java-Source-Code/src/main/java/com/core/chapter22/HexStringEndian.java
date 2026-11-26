package com.core.chapter22;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HexFormat;

public class HexStringEndian {

	private static String toHexString(ByteOrder order, int value) {
		ByteBuffer buf = ByteBuffer.allocate(4).order(order);
		buf.putInt(value);
		return HexFormat.of().withDelimiter(" ").formatHex(buf.array());
	}

	public static void main(String[] args) {
		int value = 0x12345678;
		System.out.println("Native order name: " + ByteOrder.nativeOrder());
		System.out.println("Big Endian hex: " + toHexString(ByteOrder.BIG_ENDIAN, value));
		System.out.println("Little Endian hex: " + toHexString(ByteOrder.LITTLE_ENDIAN, value));
	}
}
