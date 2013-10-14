package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.ItemMusic;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseMusicMessage;

/**
 * message response music handler
 * 
 */
public class ResponseMusicMessageHandler extends ResponseMessageHandlerHelper {

	@Override
	public String response(Message message) {
		ResponseMusicMessage musicMessage = (ResponseMusicMessage) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer item = new StringBuffer();
		ItemMusic itemMusic = musicMessage.getMusic();
		item.append(wrapperContent(TAG_TITLE, itemMusic.getTitle(), true))
				.append(wrapperContent(TAG_DESCRIPTION,
						itemMusic.getDescription(), true))
				.append(wrapperContent(TAG_URL, itemMusic.getMusicUrl(), true))
				.append(wrapperContent(TAG_HQMUSICURL,
						itemMusic.getHqMusicUrl(), true));
		String music = wrapperContent(TAG_MUSIC, item.toString(), false);
		buffer.append(
				wrapperContent(TAG_TOUSERNAME, musicMessage.getToUserName(),
						true))
				.append(wrapperContent(TAG_FROMUSERNAME,
						musicMessage.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME,
						musicMessage.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, musicMessage.getMsgType(),
						true))
				.append(music)
				.append(wrapperContent(TAG_FUNCFLAG, musicMessage.getFuncFlag()
						+ "", false));
		return wrapperXmlContent(buffer.toString());
	}

}
