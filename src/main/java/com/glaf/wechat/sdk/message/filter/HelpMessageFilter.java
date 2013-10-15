package com.glaf.wechat.sdk.message.filter;

import java.util.regex.Pattern;

import com.glaf.wechat.sdk.ResourceManager;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.TextMessage;

/**
 * filter whether the message is for help
 * 
 */
public class HelpMessageFilter extends AbstractMessageFilter implements IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		if (message instanceof TextMessage) {
			TextMessage msg = (TextMessage) message;
			if (Pattern.compile(ResourceManager.getValue("help"))
					.matcher(msg.getContent()).find()) {
				return buildResponseTextMessage(ResourceManager
						.getValue("response_help"));
			}
		}
		return null;
	}

}
