package com.glaf.wechat.sdk.message.handler;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;

/**
 * interface of handling message
 * 
 */
public interface IMessageHandler {

	/**
	 * handle message, and return the message response
	 * 
	 * @param message
	 * @return
	 */
	Message handleMessage(Message message);

	/**
	 * handle message, and return the message response
	 * 
	 * @param message
	 * @param element
	 */
	void parseMessage(Message message, Element element);

}
