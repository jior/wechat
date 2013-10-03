package com.glaf.wechat.test;

import java.util.*;

import org.junit.Test;

import com.glaf.wechat.domain.WxMenu;
import com.glaf.wechat.query.WxMenuQuery;
import com.glaf.wechat.service.WxMenuService;
import com.glaf.test.AbstractTest;

public class WxMenuTest extends AbstractTest {

	protected WxMenuService wxMenuService;

	public WxMenuTest() {
		wxMenuService = getBean("wxMenuService");
	}

	@Test
	public void insertWxMenu() {
		for (int i = 0; i < 10; i++) {
			WxMenu wxMenu = new WxMenu();
			wxMenu.setParentId(100L);
			wxMenu.setName("Name");
			wxMenu.setType("Type");
			wxMenu.setKey("Key");
			wxMenu.setUrl("Url");
			wxMenu.setSort(1);
			wxMenu.setUuid("Uuid");
			wxMenu.setCreateBy("CreateBy");
			wxMenu.setCreateDate(new Date());

			wxMenuService.save(wxMenu);
		}
	}

	@Test
	public void list() {
		WxMenuQuery wxMenuQuery = new WxMenuQuery();

		logger.debug("---------------------total----------------------");
		int total = wxMenuService.getWxMenuCountByQueryCriteria(wxMenuQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxMenu> list = wxMenuService.getWxMenusByQueryCriteria(0, 10,
					wxMenuQuery);
			logger.debug(list);
		}
	}

}
