package com.core.chapter21;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UpperCaseFilterDemo {
	static class UppercaseByteFilterStream extends FilterOutputStream {
		public UppercaseByteFilterStream(OutputStream out) {
			super(out);
		}

		@Override
		public void write(int b) throws IOException {
			if (b >= 'a' && b <= 'z') {
				b = b - ('a' - 'A');
			}
			super.write(b);
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			for (int i = off; i < off + len; ++i) {
				write(b[i]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try (UppercaseByteFilterStream filteredOut = new UppercaseByteFilterStream(baos)) {
			byte[] input = "hello spring binary stream".getBytes(StandardCharsets.UTF_8);
			filteredOut.write(input);
			filteredOut.flush();
		}

		byte[] resultingBytes = baos.toByteArray();
		System.out.println(new String(resultingBytes, StandardCharsets.UTF_8));

		// Reading back through ByteArrayInputStream
		try (ByteArrayInputStream bais = new ByteArrayInputStream(resultingBytes)) {
			int ch;
			while ((ch = bais.read()) != -1) {
				System.out.print((char) ch);
			}
			System.out.println();
		}
	}
}
