package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.Message;

/**
 * 消息响应接口
 * 
 */
public interface IResponseMessageHandler {

	/**
	 * 根据消息主体封装响应消息
	 * 
	 * @param message
	 * @return
	 */
	String response(Message message);

}
