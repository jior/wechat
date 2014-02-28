package com.glaf.wechat.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.glaf.test.AbstractTest;
import com.glaf.wechat.domain.WxTemplate;
import com.glaf.wechat.query.WxTemplateQuery;
import com.glaf.wechat.service.WxTemplateService;

public class WxTemplateTest extends AbstractTest {

	protected WxTemplateService wxTemplateService;

	public WxTemplateTest() {
		wxTemplateService = getBean("wxTemplateService");
	}

	@Test
	public void insertWxTemplate() {
		for (int i = 0; i < 10; i++) {
			WxTemplate wxTemplate = new WxTemplate();
			wxTemplate.setSkinImage("SkinImage");
			wxTemplate.setType("Type");
			wxTemplate.setPath("path");
			wxTemplate.setDefaultFlag(1);
			wxTemplate.setCreateBy("CreateBy");
			wxTemplate.setCreateDate(new Date());

			wxTemplateService.save(wxTemplate);
		}
	}

	@Test
	public void list() {
		WxTemplateQuery wxTemplateQuery = new WxTemplateQuery();

		logger.debug("---------------------total----------------------");
		int total = wxTemplateService
				.getWxTemplateCountByQueryCriteria(wxTemplateQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxTemplate> list = wxTemplateService
					.getWxTemplatesByQueryCriteria(0, 10, wxTemplateQuery);
			logger.debug(list);
		}
	}

}
