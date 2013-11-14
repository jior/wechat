package com.glaf.wechat.test;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		File dir = new File(args[0]);
		File contents[] = dir.listFiles();
		if (contents != null) {
			for (int i = 0; i < contents.length; i++) {
				if (contents[i].isFile()) {
					if (contents[i].getName().endsWith(".jpg")) {
						contents[i].renameTo(new File(args[1], "pic" + (i + 1)
								+ ".jpg"));
					}
				}
			}
		}
	}
}
