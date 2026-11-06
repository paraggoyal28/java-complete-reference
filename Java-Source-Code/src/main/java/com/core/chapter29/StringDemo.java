package com.core.chapter29;

public class StringDemo {
    public static void main(String[] args) {
        String s1 = "";
        String s2 = "   ";
        String s3 = "  Java";
        System.out.println(s1.isBlank());
        System.out.println(s2.isBlank());
        System.out.println(s3.isBlank());
        System.out.println("".isEmpty());
        System.out.println("  ".isEmpty());
        System.out.println("".isBlank());
        System.out.println("  ".isBlank());
        String s = "    Hello  Java     ";
        String result = s.strip();
        System.out.println(result);
        System.out.println("    Java   ".stripLeading());
        System.out.println("    Java   ".stripTrailing());
        String text = "JavaPython\nC++\nPerl";
        text.lines().forEach(System.out::println);
        long count = text.lines().count();
        System.out.println(count);
        String toBeRepeated = "Hello";
        String res = toBeRepeated.repeat(4);
        System.out.println(res);
        Object ob = "Hello";
        if (ob instanceof String) {
            System.out.println((String) ob);
        }
        if (ob instanceof String str) {
            System.out.println(str);
        }
    }
}
