package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.Message;

/**
 * interface for handling message response
 * 
 */
public interface IResponseMessageHandler {

	/**
	 * build the content of message response
	 * 
	 * @param message
	 * @return
	 */
	String response(Message message);

}
