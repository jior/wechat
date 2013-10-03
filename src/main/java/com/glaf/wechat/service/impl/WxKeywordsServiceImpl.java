package com.glaf.wechat.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.id.*;
import com.glaf.core.dao.*;
import com.glaf.wechat.mapper.*;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.WxKeywordsService;

@Service("wxKeywordsService")
@Transactional(readOnly = true)
public class WxKeywordsServiceImpl implements WxKeywordsService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxKeywordsMapper wxKeywordsMapper;

	public WxKeywordsServiceImpl() {

	}

	public int count(WxKeywordsQuery query) {
		return wxKeywordsMapper.getWxKeywordsCount(query);
	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxKeywordsMapper.deleteWxKeywordsById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxKeywordsMapper.deleteWxKeywordsById(id);
			}
		}
	}

	public WxKeywords getWxKeywords(Long id) {
		if (id == null) {
			return null;
		}
		WxKeywords wxKeywords = wxKeywordsMapper.getWxKeywordsById(id);
		return wxKeywords;
	}

	public int getWxKeywordsCountByQueryCriteria(WxKeywordsQuery query) {
		return wxKeywordsMapper.getWxKeywordsCount(query);
	}

	public List<WxKeywords> getWxKeywordssByQueryCriteria(int start,
			int pageSize, WxKeywordsQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxKeywords> rows = sqlSessionTemplate.selectList("getWxKeywordss",
				query, rowBounds);
		return rows;
	}

	public List<WxKeywords> list(WxKeywordsQuery query) {
		List<WxKeywords> list = wxKeywordsMapper.getWxKeywordss(query);
		return list;
	}

	@Transactional
	public void save(WxKeywords wxKeywords) {
		if (wxKeywords.getId() == null) {
			wxKeywords.setId(idGenerator.nextId());
			wxKeywords.setCreateDate(new Date());
			// wxKeywords.setDeleteFlag(0);
			wxKeywordsMapper.insertWxKeywords(wxKeywords);
		} else {
			wxKeywordsMapper.updateWxKeywords(wxKeywords);
		}
	}

	@Transactional
	public void saveAll(Long categoryId, WxContent content) {
		WxKeywordsQuery query = new WxKeywordsQuery();
		query.categoryId(categoryId);
		query.contentId(content.getId());
		List<WxKeywords> list = wxKeywordsMapper.getWxKeywordss(query);
		if (list != null && !list.isEmpty()) {
			for (WxKeywords model : list) {
				this.deleteById(model.getId());
			}
		}
		String keywords = content.getKeywords();
		if (StringUtils.isNotEmpty(keywords)) {
			StringTokenizer token = new StringTokenizer(keywords);
			while (token.hasMoreTokens()) {
				String str = token.nextToken();
				WxKeywords wxKeywords = new WxKeywords();
				wxKeywords.setId(idGenerator.nextId());
				wxKeywords.setCategoryId(categoryId);
				wxKeywords.setContentId(content.getId());
				wxKeywords.setCreateBy(content.getCreateBy());
				wxKeywords.setCreateDate(new Date());
				wxKeywords.setKeywords(str);
				wxKeywords.setKeywordsMatchType(content.getKeywordsMatchType());
				wxKeywordsMapper.insertWxKeywords(wxKeywords);
			}
		}

	}

	@Resource(name = "myBatisEntityDAO")
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@Resource(name = "myBatisDbIdGenerator")
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Resource
	public void setWxKeywordsMapper(WxKeywordsMapper wxKeywordsMapper) {
		this.wxKeywordsMapper = wxKeywordsMapper;
	}

}
