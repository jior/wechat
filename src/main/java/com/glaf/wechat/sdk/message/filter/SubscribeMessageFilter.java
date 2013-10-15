package com.glaf.wechat.sdk.message.filter;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;

public class SubscribeMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		Message msg = new ResponseNewsMessage();
		
		return msg;
	}

}
