package com.glaf.wechat.test;

import java.util.*;

import org.junit.Test;

import com.glaf.wechat.domain.WxCover;
import com.glaf.wechat.query.WxCoverQuery;
import com.glaf.wechat.service.WxCoverService;
import com.glaf.test.AbstractTest;

public class WxCoverTest extends AbstractTest {

	protected WxCoverService wxCoverService;

	public WxCoverTest() {
		wxCoverService = getBean("wxCoverService");
	}

	@Test
	public void insertWxCover() {
		for (int i = 0; i < 10; i++) {
			WxCover wxCover = new WxCover();
			wxCover.setBigIcon("BigIcon");
			wxCover.setSmallIcon("SmallIcon");
			wxCover.setUuid("Uuid");
			wxCover.setCreateBy("CreateBy");
			wxCover.setCreateDate(new Date());

			wxCoverService.save(wxCover);
		}
	}

	@Test
	public void list() {
		WxCoverQuery wxCoverQuery = new WxCoverQuery();

		logger.debug("---------------------total----------------------");
		int total = wxCoverService.getWxCoverCountByQueryCriteria(wxCoverQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxCover> list = wxCoverService.getWxCoversByQueryCriteria(0,
					10, wxCoverQuery);
			logger.debug(list);
		}
	}

}
