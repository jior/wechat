package com.glaf.wechat.sdk.message;

/**
 * text message
 * 
 */
public class TextMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
