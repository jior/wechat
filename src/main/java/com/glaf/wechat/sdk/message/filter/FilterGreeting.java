package com.glaf.wechat.sdk.message.filter;

import com.glaf.wechat.sdk.ResourceManager;
import com.glaf.wechat.sdk.message.Message;

/**
 * filter whether the message is for everything<br>
 * so,if this filter locates in the last of filterchain,then it certainly
 * returns the message response
 * 
 */
public class FilterGreeting extends MessageFilterHelper implements
		IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		return buildResponseTextMessage(ResourceManager
				.getValue("default_greeting"));
	}

}
