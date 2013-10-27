package com.glaf.wechat.sdk.message;

public class VideoMessage extends Message {
	private static final long serialVersionUID = 1L;

	// √ΩÃÂID
	private String mediaId;
	// ”Ô“Ù∏Ò Ω
	private String thumbMediaId;

	public VideoMessage() {

	}

	public String getMediaId() {
		return mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

}
