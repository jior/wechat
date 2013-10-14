package com.glaf.wechat.sdk.message;

/**
 * response message music
 */
public class ResponseMusicMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected ItemMusic music;
	protected int funcFlag;

	public int getFuncFlag() {
		return funcFlag;
	}

	public ItemMusic getMusic() {
		return music;
	}

	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}

	public void setMusic(ItemMusic music) {
		this.music = music;
	}

}
