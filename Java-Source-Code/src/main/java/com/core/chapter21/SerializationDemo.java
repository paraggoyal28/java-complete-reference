package com.core.chapter21;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class MyClass implements Serializable {
	String s;
	int i;
	double d;
	transient int tr;
	static String myStr;

	public MyClass(String s, int i, double d, int tr, String myStr) {
		this.s = s;
		this.i = i;
		this.d = d;
		this.tr = tr;
		this.myStr = myStr;
	}

	public String toString() {
		return "s= " + s + ", i=" + i + ", d=" + d + " tr: " + tr + ", myStr: " + myStr;
	}
}

public class SerializationDemo {
	public static void main(String[] args) {
		// Object serialization
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serial"))) {
			MyClass object1 = new MyClass("Hello", 89, 2.7e10, 45, "Hello Java");
			System.out.println("Object1: " + object1);
			MyClass.myStr = "Java Programming";

			objectOutputStream.writeObject(object1);
		} catch (IOException e) {
			System.err.println("Exception during serialization " + e);
		}

		// Object deserialization
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serial"))) {
			MyClass object2 = (MyClass) objectInputStream.readObject();
			System.out.println("object2: " + object2);

 		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Exception during deserialization: " + e);
		}
	}
}
