package com.glaf.wechat.sdk.message.filter;

import org.dom4j.Element;

import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.handler.MessageHandlerHelper;

/**
 * message filter helper class <br>
 * extends message handler helper,then filter can access the message handling
 * method
 */
public abstract class MessageFilterHelper extends MessageHandlerHelper
		implements IMessageFilter {

	// subclass handle it
	public abstract Message doSpecailMessageFilter(Message message);

	@Override
	public Message filterMessage(Message message) {
		this.message = message;// must do this! otherwise, message may be null
		return doSpecailMessageFilter(message);
	}

	@Override
	protected Message handleSpecialMessage(Message message) {// do nothing
		return null;
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {// do
																		// nothing
	}

}
