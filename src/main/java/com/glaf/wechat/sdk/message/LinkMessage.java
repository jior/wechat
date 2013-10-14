package com.glaf.wechat.sdk.message;

/**
 * link message
 * 
 */
public class LinkMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected String url;
	protected String title;
	protected String description;

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
