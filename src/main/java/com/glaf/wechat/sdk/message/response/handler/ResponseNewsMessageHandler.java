package com.glaf.wechat.sdk.message.response.handler;

import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;

/**
 * message response news handler
 * 
 */
public class ResponseNewsMessageHandler extends ResponseMessageHandlerHelper {

	@Override
	public String response(Message message) {
		ResponseNewsMessage newsMessage = (ResponseNewsMessage) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer items = new StringBuffer();
		StringBuffer item = new StringBuffer();
		ItemArticle itemArticle = null;
		for (int i = 0; i < newsMessage.getCount(); i++) {
			item = new StringBuffer();
			itemArticle = newsMessage.getArticleItems().get(i);
			item.append(wrapperContent(TAG_TITLE, itemArticle.getTitle(), true))
					.append(wrapperContent(TAG_DESCRIPTION,
							itemArticle.getDescription(), true))
					.append(wrapperContent(TAG_PICURL, itemArticle.getPicUrl(),
							true))
					.append(wrapperContent(TAG_URL, itemArticle.getUrl(), true));
			items.append(wrapperContent(TAG_ITEM, item.toString(), false));
		}
		String articles = wrapperContent(TAG_ARTICLES, items.toString(), false);
		buffer.append(
				wrapperContent(TAG_TOUSERNAME, newsMessage.getToUserName(),
						true))
				.append(wrapperContent(TAG_FROMUSERNAME,
						newsMessage.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME,
						newsMessage.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, newsMessage.getMsgType(),
						true))
				.append(wrapperContent(TAG_ARTICLECOUNT, newsMessage.getCount()
						+ "", false))
				.append(articles)
				.append(wrapperContent(TAG_FUNCFLAG, newsMessage.getFuncFlag()
						+ "", false));
		return wrapperXmlContent(buffer.toString());
	}

}
