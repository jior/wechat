package com.glaf.wechat.test;

import java.util.*;

import org.junit.Test;

import com.glaf.wechat.domain.WxFile;
import com.glaf.wechat.query.WxFileQuery;
import com.glaf.wechat.service.WxFileService;
import com.glaf.test.AbstractTest;

public class WxFileTest extends AbstractTest {

	protected WxFileService wxFileService;

	public WxFileTest() {
		wxFileService = getBean("wxFileService");
	}

	@Test
	public void insertWxFile() {
		for (int i = 0; i < 10; i++) {
			WxFile wxFile = new WxFile();
			wxFile.setCategoryId(100L);
			wxFile.setTitle("Title");
			wxFile.setFilename("Filename");
			wxFile.setPath("Path");
			wxFile.setUuid("Uuid");
			wxFile.setCreateBy("CreateBy");
			wxFile.setCreateDate(new Date());

			wxFileService.save(wxFile);
		}
	}

	@Test
	public void list() {
		WxFileQuery wxFileQuery = new WxFileQuery();

		logger.debug("---------------------total----------------------");
		int total = wxFileService.getWxFileCountByQueryCriteria(wxFileQuery);

		logger.debug("row count:" + total);

		logger.debug("----------------------list---------------------");

		if (total > 0) {
			List<WxFile> list = wxFileService.getWxFilesByQueryCriteria(0, 10,
					wxFileQuery);
			logger.debug(list);
		}
	}

}
