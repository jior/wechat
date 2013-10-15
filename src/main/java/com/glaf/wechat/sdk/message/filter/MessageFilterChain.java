package com.glaf.wechat.sdk.message.filter;

import java.util.ArrayList;
import java.util.List;

import com.glaf.wechat.sdk.message.Message;

/**
 * message filter chain
 * 
 */
public class MessageFilterChain {

	public List<IMessageFilter> filters = new ArrayList<IMessageFilter>();

	// add a filter
	public void addFilter(IMessageFilter filter) {
		filters.add(filter);
	}

	// do filter chain
	public Message doFilterChain(Message message) {
		Message msg = null;
		for (int i = 0; i < filters.size(); i++) {
			msg = filters.get(i).filterMessage(message);
			if (msg != null) {
				return msg;// if one filter can deal the message,then
							// do it!so,it can be returned!
			}
		}
		return msg;// if none of the filter can do it!
	}

	public List<IMessageFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<IMessageFilter> filters) {
		this.filters = filters;
	}

}
