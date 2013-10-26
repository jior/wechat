package com.glaf.wechat.test;

import java.util.*;

import org.junit.Test;

import com.glaf.wechat.domain.WxContent;
import com.glaf.wechat.query.WxContentQuery;
import com.glaf.wechat.service.WxContentService;
import com.glaf.test.AbstractTest;

public class WxContentTest extends AbstractTest {

	protected WxContentService wxContentService;

	public WxContentTest() {
		wxContentService = getBean("wxContentService");
	}

 
	public void insertWxContent() {
		for (int i = 0; i < 10; i++) {
			WxContent wxContent = new WxContent();
			wxContent.setCategoryId(100L);
			wxContent.setTitle("Title");
			wxContent.setContent("Content");
			wxContent.setStatus(1);
			wxContent.setPriority(1);
			wxContent.setType("Type");
			wxContent.setUuid("Uuid");
			wxContent.setKeywords("Keywords");
			wxContent.setKeywordsCount(1);
			wxContent.setSummary("Summary");
			wxContent.setIcon("Icon");
			wxContent.setBigIcon("BigIcon");
			wxContent.setSmallIcon("SmallIcon");
			wxContent.setUrl("Url");
			wxContent.setCreateBy("CreateBy");
			wxContent.setCreateDate(new Date());

			wxContentService.save(wxContent);
		}
	}

	@Test
	public void list() {
		WxContentQuery wxContentQuery = new WxContentQuery();
		List<Long> contentIds = new ArrayList<Long>();
		contentIds.add(2494L);
		wxContentQuery.contentIds(contentIds);
		logger.debug("---------------------total----------------------");
		int total = wxContentService
				.getWxContentCountByQueryCriteria(wxContentQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxContent> list = wxContentService
					.getWxContentsByQueryCriteria(0, 10, wxContentQuery);
			logger.debug(list);
		}
	}

}
