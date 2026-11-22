package com.core.chapter21;

import java.io.File;
import java.io.FilenameFilter;

class OnlyExt implements FilenameFilter {
	String ext;
	public OnlyExt(String ext) {
		this.ext = "." + ext;
	}

	public boolean accept(File dir, String name) {
		return name.endsWith(ext);
	}
}

public class DirListOnly {
	public static void main(String[] args) {
		String dirName = "/java";
		File myDirFile = new File(dirName);
		FilenameFilter htmlOnly = new OnlyExt("html");
		String[] files = myDirFile.list(htmlOnly);
		for (String file: files) {
			System.out.println(file);
		}
	}
}
