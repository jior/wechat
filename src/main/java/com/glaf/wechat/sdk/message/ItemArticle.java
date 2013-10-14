package com.glaf.wechat.sdk.message;

/**
 * item of article
 * 
 */
public class ItemArticle {

	private String title;
	private String description;
	private String picUrl;
	private String url;

	public ItemArticle() {
	}

	public ItemArticle(String title, String description, String picUrl,
			String url) {
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public String getPicUrl() {
		return picUrl;
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

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
