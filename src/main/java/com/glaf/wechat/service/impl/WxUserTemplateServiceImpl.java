package com.glaf.wechat.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.id.*;
import com.glaf.core.dao.*;
import com.glaf.wechat.mapper.*;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.WxUserTemplateService;

@Service("wxUserTemplateService")
@Transactional(readOnly = true)
public class WxUserTemplateServiceImpl implements WxUserTemplateService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxUserTemplateMapper wxUserTemplateMapper;

	public WxUserTemplateServiceImpl() {

	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxUserTemplateMapper.deleteWxUserTemplateById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxUserTemplateMapper.deleteWxUserTemplateById(id);
			}
		}
	}

	public int count(WxUserTemplateQuery query) {
		return wxUserTemplateMapper.getWxUserTemplateCount(query);
	}

	public List<WxUserTemplate> list(WxUserTemplateQuery query) {
		List<WxUserTemplate> list = wxUserTemplateMapper
				.getWxUserTemplates(query);
		return list;
	}

	public int getWxUserTemplateCountByQueryCriteria(WxUserTemplateQuery query) {
		return wxUserTemplateMapper.getWxUserTemplateCount(query);
	}

	public List<WxUserTemplate> getWxUserTemplatesByQueryCriteria(int start,
			int pageSize, WxUserTemplateQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxUserTemplate> rows = sqlSessionTemplate.selectList(
				"getWxUserTemplates", query, rowBounds);
		return rows;
	}

	public WxUserTemplate getWxUserTemplate(Long id) {
		if (id == null) {
			return null;
		}
		WxUserTemplate wxUserTemplate = wxUserTemplateMapper
				.getWxUserTemplateById(id);
		return wxUserTemplate;
	}

	/**
	 * 获取某个栏目指定类型的模板实例
	 * 
	 * @param createBy
	 * @param type
	 * @param categoryId
	 * @return
	 */
	public WxUserTemplate getWxUserTemplate(String createBy, String type,
			Long categoryId) {
		WxUserTemplate wxUserTemplate = null;
		WxUserTemplateQuery query = new WxUserTemplateQuery();
		query.createBy(createBy);
		query.type(type);
		query.categoryId(categoryId);
		List<WxUserTemplate> list = wxUserTemplateMapper
				.getWxUserTemplates(query);
		if (list != null && !list.isEmpty()) {
			wxUserTemplate = list.get(0);
		}
		return wxUserTemplate;
	}

	@Transactional
	public void save(WxUserTemplate wxUserTemplate) {
		WxUserTemplate model = this.getWxUserTemplate(
				wxUserTemplate.getCreateBy(), wxUserTemplate.getType(),
				wxUserTemplate.getCategoryId());
		if (model != null) {
			this.deleteById(model.getId());
		}
		wxUserTemplate.setId(idGenerator.nextId());
		wxUserTemplate.setCreateDate(new Date());
		wxUserTemplateMapper.insertWxUserTemplate(wxUserTemplate);
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
	public void setWxUserTemplateMapper(
			WxUserTemplateMapper wxUserTemplateMapper) {
		this.wxUserTemplateMapper = wxUserTemplateMapper;
	}

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
