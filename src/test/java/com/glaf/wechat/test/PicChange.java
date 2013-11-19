package com.glaf.wechat.test;

import java.io.File;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class PicChange {

	public static final Rename MX_PREFIX_DOT_THUMBNAIL = new Rename() {
		@Override
		public String apply(String fileName, ThumbnailParameter param) {
			return appendPrefix(fileName, "small.");
		}
	};

	public static void main(String[] args) throws Exception {
		Thumbnails.of(new File("WebContent/wx/images").listFiles())
				.scale(0.25, 0.25).outputFormat("jpg")
				.toFiles(MX_PREFIX_DOT_THUMBNAIL);
	}
}
