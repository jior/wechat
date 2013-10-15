package com.glaf.wechat.sdk.message.filter;

 
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;

/**
 * filter whether the message is for everything<br>
 * so,if this filter locates in the last of filterchain,then it certainly
 * returns the message response
 * 
 */
public class DefaultResponseMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		Message msg = new ResponseNewsMessage();
		
		return msg;
	}

}
