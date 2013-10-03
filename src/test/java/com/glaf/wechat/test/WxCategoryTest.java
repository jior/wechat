package com.glaf.wechat.test;

import java.util.*;

import org.junit.Test;

import com.glaf.wechat.domain.WxCategory;
import com.glaf.wechat.query.WxCategoryQuery;
import com.glaf.wechat.service.WxCategoryService;
import com.glaf.test.AbstractTest;

public class WxCategoryTest extends AbstractTest {

	protected WxCategoryService wxCategoryService;

	public WxCategoryTest() {
		wxCategoryService = getBean("wxCategoryService");
	}

	@Test
	public void insertWxCategory() {
		for (int i = 0; i < 10; i++) {
			WxCategory wxCategory = new WxCategory();
			wxCategory.setParentId(100L);
			wxCategory.setTreeId("TreeId");
			wxCategory.setSort(1);
			wxCategory.setIcon("Icon");
			wxCategory.setIconCls("IconCls");
			wxCategory.setCoverIcon("CoverIcon");
			wxCategory.setIndexShow(1);
			wxCategory.setLocked(1);
			wxCategory.setName("Name");
			wxCategory.setCode("Code");
			wxCategory.setDesc("Desc");
			wxCategory.setEventType("EventType");
			wxCategory.setUrl("Url");
			wxCategory.setUuid("Uuid");
			wxCategory.setCreateBy("CreateBy");
			wxCategory.setCreateDate(new Date());

			wxCategoryService.save(wxCategory);
		}
	}

	@Test
	public void list() {
		WxCategoryQuery wxCategoryQuery = new WxCategoryQuery();

		logger.debug("---------------------total----------------------");
		int total = wxCategoryService
				.getWxCategoryCountByQueryCriteria(wxCategoryQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxCategory> list = wxCategoryService
					.getWxCategorysByQueryCriteria(0, 10, wxCategoryQuery);
			logger.debug(list);
		}
	}

}
