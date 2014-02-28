package com.glaf.wechat.test;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.glaf.test.AbstractTest;
import com.glaf.wechat.domain.WxFile;
import com.glaf.wechat.query.WxFileQuery;
import com.glaf.wechat.service.WxFileService;

public class WxFileTest extends AbstractTest {

	protected WxFileService wxFileService;

	public WxFileTest() {
		wxFileService = getBean("wxFileService");
	}

	@Test
	public void insertWxFile() {
		List<WxFile> files = new java.util.concurrent.CopyOnWriteArrayList<WxFile>();
		for (int i = 1; i <= 200; i++) {
			WxFile wxFile = new WxFile();
			wxFile.setCategoryId(0L);
			wxFile.setTitle("原始大图片" + i);
			wxFile.setFilename("/wx/small/small.pic" + i + ".jpg");
			wxFile.setOriginalFilename("/wx/images/pic" + i + ".jpg");
			wxFile.setUuid(DigestUtils.md5Hex(wxFile.getOriginalFilename()));
			wxFile.setType("sys_images");
			wxFile.setCreateBy("system");
			wxFile.setCreateDate(new Date());

			files.add(wxFile);
		}

		for (int i = 1; i <= 200; i++) {
			WxFile wxFile = new WxFile();
			wxFile.setCategoryId(0L);
			wxFile.setTitle("大图片" + i);
			wxFile.setFilename("/wx/small/small.pic" + i + ".jpg");
			wxFile.setOriginalFilename("/wx/big/big.pic" + i + ".jpg");
			wxFile.setUuid(DigestUtils.md5Hex(wxFile.getOriginalFilename()));
			wxFile.setType("sys_big_images");
			wxFile.setCreateBy("system");
			wxFile.setCreateDate(new Date());

			files.add(wxFile);
		}

		for (int i = 1; i <= 200; i++) {
			WxFile wxFile = new WxFile();
			wxFile.setCategoryId(0L);
			wxFile.setTitle("中等图片" + i);
			wxFile.setFilename("/wx/small/small.pic" + i + ".jpg");
			wxFile.setOriginalFilename("/wx/medium/medium.pic" + i + ".jpg");
			wxFile.setUuid(DigestUtils.md5Hex(wxFile.getOriginalFilename()));
			wxFile.setType("sys_medium_images");
			wxFile.setCreateBy("system");
			wxFile.setCreateDate(new Date());

			files.add(wxFile);
		}

		for (int i = 1; i <= 200; i++) {
			WxFile wxFile = new WxFile();
			wxFile.setCategoryId(0L);
			wxFile.setTitle("小图片" + i);
			wxFile.setFilename("/wx/small/small.pic" + i + ".jpg");
			wxFile.setOriginalFilename("/wx/small/small.pic" + i + ".jpg");
			wxFile.setUuid(DigestUtils.md5Hex(wxFile.getOriginalFilename()));
			wxFile.setType("sys_small_images");
			wxFile.setCreateBy("system");
			wxFile.setCreateDate(new Date());

			files.add(wxFile);
		}

		wxFileService.saveAll(files);
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
