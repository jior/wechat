package com.glaf.wechat.sdk.message.filter;

import java.util.ArrayList;
import java.util.List;

import com.glaf.core.context.ContextFactory;
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.domain.WxKeywords;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.query.WxKeywordsQuery;
import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;
import com.glaf.wechat.sdk.message.TextMessage;
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.service.WxKeywordsService;

/**
 * ¹Ø¼ü×Ö»Ø¸´
 * 
 */
public class KeywordsMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		if (message instanceof TextMessage) {
			TextMessage msg = (TextMessage) message;
			String content = msg.getContent();
			WxContentService wxContentService = ContextFactory
					.getBean("wxContentService");
			WxKeywordsService wxKeywordsService = ContextFactory
					.getBean("wxKeywordsService");
			WxKeywordsQuery query = new WxKeywordsQuery();
			query.createBy(message.getCustomer());
			query.keywords(content);
			List<WxKeywords> list = wxKeywordsService.list(query);
			if (list != null && !list.isEmpty()) {
				List<Long> contentIds = new ArrayList<Long>();
				for (WxKeywords m : list) {
					contentIds.add(m.getContentId());
				}
				WxContentQuery q = new WxContentQuery();
				q.contentIds(contentIds);
				q.type("K");
				List<WxContent> rows = wxContentService.list(q);
				if (rows != null && !rows.isEmpty()) {
					ResponseNewsMessage newsMessage = new ResponseNewsMessage();
					newsMessage.setCount(rows.size());
					for (WxContent c : rows) {
						ItemArticle art = new ItemArticle();
						art.setDescription(c.getSummary());
						art.setTitle(c.getTitle());
						art.setUrl(c.getUrl());//
						art.setPicUrl(c.getIcon());//
						newsMessage.addItemArticle(art);
					}
					return newsMessage;
				}
			}
		}
		return null;
	}

}
