package com.glaf.wechat.sdk.message;

/**
 * item of music
 * 
 */
public class ItemMusic {

	private String title;
	private String description;
	private String musicUrl;
	private String hqMusicUrl;

	public ItemMusic() {
	}

	public ItemMusic(String title, String description, String musicUrl,
			String hqMusicUrl) {
		this.title = title;
		this.description = description;
		this.musicUrl = musicUrl;
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getDescription() {
		return description;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
