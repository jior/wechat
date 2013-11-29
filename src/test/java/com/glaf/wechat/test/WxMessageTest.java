package com.glaf.wechat.test;

import java.util.*;

import org.junit.Test;

import com.glaf.apps.message.domain.WxMessage;
import com.glaf.apps.message.query.WxMessageQuery;
import com.glaf.apps.message.service.WxMessageService;
import com.glaf.test.AbstractTest;

public class WxMessageTest extends AbstractTest {

	protected WxMessageService wxMessageService;

	public WxMessageTest() {
		wxMessageService = getBean("wxMessageService");
	}

	@Test
	public void insertWxMessage() {
		for (int i = 0; i < 10; i++) {
			WxMessage wxMessage = new WxMessage();
			wxMessage.setName("Name");
			wxMessage.setMobile("Mobile");
			wxMessage.setTitle("Title");
			wxMessage.setContent("Content");
			wxMessage.setCreateBy("CreateBy");
			wxMessage.setCreateDate(new Date());

			wxMessageService.save(wxMessage);
		}
	}

	@Test
	public void list() {
		WxMessageQuery wxMessageQuery = new WxMessageQuery();

		logger.debug("---------------------total----------------------");
		int total = wxMessageService
				.getWxMessageCountByQueryCriteria(wxMessageQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxMessage> list = wxMessageService
					.getWxMessagesByQueryCriteria(0, 10, wxMessageQuery);
			logger.debug(list);
		}
	}

}
