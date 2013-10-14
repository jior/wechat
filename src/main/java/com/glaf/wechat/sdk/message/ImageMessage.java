package com.glaf.wechat.sdk.message;

/**
 * image message
 */
public class ImageMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected String picUrl;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
