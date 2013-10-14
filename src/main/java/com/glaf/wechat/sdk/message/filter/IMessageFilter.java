package com.glaf.wechat.sdk.message.filter;

import com.glaf.wechat.sdk.message.Message;

/**
 * interface of message filter
 */
public interface IMessageFilter {

	/**
	 * message come from
	 * 
	 * @param message
	 * @return
	 */
	Message filterMessage(Message message);

}
