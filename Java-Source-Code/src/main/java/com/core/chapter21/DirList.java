package com.core.chapter21;

import java.io.File;

public class DirList {
	public static void main(String[] args) {
		String dirName = "/java";
		File dir = new File(dirName);

		if (dir.isDirectory()) {
			System.out.println("Directory of " + dirName);
			String files[] = dir.list();

			for (int i = 0; i < files.length; ++i) {
				System.out.println(files[i]);
				File file = new File(dirName + "/" + files[i]);

				if (file.isDirectory()) {
					System.out.println(files[i] + " is a directory");
				} else {
					System.out.println(files[i] + " is a file");
				}
			}
		} else {
			System.out.println(dirName + " is not a directory");
		}
	}
}
