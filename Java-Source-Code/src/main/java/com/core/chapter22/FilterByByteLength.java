package com.core.chapter22;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterByByteLength {
	public static void main(String[] args) {
		List<String> inputs = List.of("12345678", "abcdefghij", "0123456789abcdef", "hi!");
		Charset charset = StandardCharsets.UTF_8;

		Map<Integer, List<String>> grouped = inputs.stream()
				.filter(s -> {
				   int len = s.getBytes(charset).length;
				   System.out.println(s);
				   System.out.println(len);
				   return  len == 8 || len == 16;
				}).collect(Collectors.groupingBy(s -> s.getBytes(charset).length));
		System.out.println("8-byte strings: " + grouped.getOrDefault(8, List.of()));
		System.out.println("16 byte strings: " + grouped.getOrDefault(16, List.of()));
	}
}
