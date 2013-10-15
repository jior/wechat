package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseTextMessage;

/**
 * message response text handler
 */
public class TextResponseMessageHandler extends AbstractResponseMessageHandler {

	@Override
	public String response(Message message) {
		ResponseTextMessage textMessage = (ResponseTextMessage) message;
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				wrapperContent(TAG_TOUSERNAME, textMessage.getToUserName(),
						true))
				.append(wrapperContent(TAG_FROMUSERNAME,
						textMessage.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME,
						textMessage.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, textMessage.getMsgType(),
						true))
				.append(wrapperContent(TAG_CONTENT, textMessage.getContent(),
						true))
				.append(wrapperContent(TAG_FUNCFLAG, textMessage.getFuncFlag()
						+ "", false));
		return wrapperXmlContent(buffer.toString());
	}

}
