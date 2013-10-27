package com.glaf.wechat.sdk.message;

public class VoiceMessage extends Message {
	private static final long serialVersionUID = 1L;

	// ý��ID
	private String mediaId;
	// ������ʽ
	private String format;

	public VoiceMessage() {

	}

	public String getFormat() {
		return format;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
