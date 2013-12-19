package com.glaf.wechat.sdk.message.filter;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.glaf.core.context.ContextFactory;
 
import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.domain.WxLog;
import com.glaf.wechat.domain.WxUser;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.sdk.message.ItemArticle;
import com.glaf.wechat.sdk.message.Message;
import com.glaf.wechat.sdk.message.ResponseNewsMessage;
import com.glaf.wechat.sdk.message.LocationMessage;
import com.glaf.wechat.sdk.util.LocationUtils;
 
import com.glaf.wechat.service.WxContentService;
import com.glaf.wechat.util.Constants;
import com.glaf.wechat.util.WxIdentityFactory;
import com.glaf.wechat.util.WxLogFactory;

public class LocationMessageFilter extends AbstractMessageFilter implements
		IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		if (message instanceof LocationMessage) {
			LocationMessage msg = (LocationMessage) message;
		 
			WxContentService wxContentService = ContextFactory
					.getBean("wxContentService");
			WxContentQuery query = new WxContentQuery();
			query.accountId(message.getAccountId());
			query.createBy(message.getCustomer());
			query.type("L");
		 
			WxUser user = WxIdentityFactory.getUserByAccountId(message.getAccountId());
			List<WxContent> rows = wxContentService.list(query);
			if (rows != null && !rows.isEmpty()) {
				ResponseNewsMessage newsMessage = new ResponseNewsMessage();
				int number = 0;
				for (WxContent c : rows) {
					double distance = LocationUtils.getDistance(
							msg.getLatitude(), msg.getLongitude(),
							c.getLatitude(), c.getLongitude());
					if (user != null && user.getLbsPosition() != null) {
						if (distance > user.getLbsPosition()) {
							continue;
						}
					}
					number++;
					ItemArticle art = new ItemArticle();
					art.setDescription(c.getSummary());
					art.setTitle(c.getTitle());
					if (StringUtils.isNotEmpty(c.getUrl())) {
						if (StringUtils.startsWith(c.getUrl(), "/website/wx/")) {
							String url = message.getServiceUrl() + c.getUrl();
							art.setUrl(url);
						} else {
							art.setUrl(c.getUrl());
						}
					} else {
						String url = message.getServiceUrl()
								+ "/website/wx/content/view/" + c.getId();
						art.setUrl(url);
					}
					if (StringUtils.isNotEmpty(c.getPicUrl())) {
						if (StringUtils.startsWith(c.getPicUrl(),
								"/website/wx/")) {
							String url = message.getServiceUrl()
									+ c.getPicUrl();
							art.setPicUrl(url);
						} else {
							art.setPicUrl(c.getPicUrl());
						}
					} else {
						if (StringUtils.isNotEmpty(c.getIcon())) {
							if (StringUtils.startsWith(c.getIcon(),
									"/wx/upload/")) {
								String url = message.getServiceUrl()
										+ c.getIcon();
								art.setPicUrl(url);
							}
						}
					}
					newsMessage.addItemArticle(art);
				}
				if (number > 0) {
					newsMessage.setCount(number);
					
					try {
						WxLog bean = new WxLog();
						bean.setOpenId(message.getFromUserName());
						bean.setActorId(message.getCustomer());
						bean.setCreateTime(new Date());
						bean.setFlag(Constants.LOCATION_LOG_FLAG);//地理信息回复
						bean.setIp(message.getRemoteIPAddr());
						bean.setOperate("location");
						WxLogFactory.create(bean);
					} catch (Exception ex) {
					}
					
					return newsMessage;
				}
			}
		}
		return null;
	}
}
