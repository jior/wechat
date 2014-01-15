package com.glaf.wechat.test;

import java.io.File;
import java.util.*;

import net.sf.jxls.transformer.XLSTransformer;

import com.glaf.core.util.StringTools;

public class FileInfoGen {

	public static void main(String[] args) throws Exception {
		List<FileInfo> files = new ArrayList<FileInfo>();
		File dir = new File(args[0]);
		scanFiles(dir, files, args[0]);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("rows", files);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS("code2.xls", context, "dest.xls");
		System.out.println("OK");
	}

	public static void scanFiles(File dir, List<FileInfo> files,
			String relativePath) {
		File contents[] = dir.listFiles();
		if (contents != null) {
			for (int i = 0; i < contents.length; i++) {
				if (contents[i].isFile()) {
					FileInfo f = new FileInfo();
					String path = contents[i].getParentFile().getAbsolutePath();
					path = StringTools.replace(path, relativePath, "");
					if (path.startsWith("\\")) {
						path = path.substring(1, path.length());
					}
					f.setName(contents[i].getName());
					f.setPath(path);
					f.setSortNo(files.size() + 1);
					files.add(f);
				} else {
					scanFiles(contents[i], files, relativePath);
				}
			}
		}
	}

}
