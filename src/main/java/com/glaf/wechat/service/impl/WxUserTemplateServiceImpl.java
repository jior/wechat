package com.glaf.wechat.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glaf.core.cache.CacheFactory;
import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.wechat.domain.WxUserTemplate;
import com.glaf.wechat.mapper.WxUserTemplateMapper;
import com.glaf.wechat.query.WxUserTemplateQuery;
import com.glaf.wechat.service.WxUserTemplateService;
import com.glaf.wechat.util.WxUserTemplateJsonFactory;

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

	public int count(WxUserTemplateQuery query) {
		return wxUserTemplateMapper.getWxUserTemplateCount(query);
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
	 * @param type
	 * @param categoryId
	 * @return
	 */
	public WxUserTemplate getWxUserTemplate(Long categoryId, String type) {
		WxUserTemplate wxUserTemplate = null;
		WxUserTemplateQuery query = new WxUserTemplateQuery();
		query.type(type);
		query.categoryId(categoryId);
		List<WxUserTemplate> list = wxUserTemplateMapper
				.getWxUserTemplates(query);
		if (list != null && !list.isEmpty()) {
			wxUserTemplate = list.get(0);
		}
		return wxUserTemplate;
	}

	/**
	 * 获取某个栏目指定类型的模板实例
	 * 
	 * @param accountId
	 * @param categoryId
	 * @param type
	 * @return
	 */
	public WxUserTemplate getWxUserTemplate(Long accountId, Long categoryId,
			String type) {
		String cacheKey = "wx_user_tpl_" + accountId + "_" + categoryId + "_"
				+ type;
		if (CacheFactory.getString(cacheKey) != null) {
			String text = CacheFactory.getString(cacheKey);
			JSONObject json = JSON.parseObject(text);
			WxUserTemplate tpl = WxUserTemplateJsonFactory.jsonToObject(json);
			return tpl;
		}
		WxUserTemplate wxUserTemplate = null;
		WxUserTemplateQuery query = new WxUserTemplateQuery();
		query.type(type);
		query.accountId(accountId);
		query.categoryId(categoryId);
		List<WxUserTemplate> list = wxUserTemplateMapper
				.getWxUserTemplates(query);
		if (list != null && !list.isEmpty()) {
			wxUserTemplate = list.get(0);
			JSONObject json = WxUserTemplateJsonFactory
					.toJsonObject(wxUserTemplate);
			CacheFactory.put(cacheKey, json.toJSONString());
		}
		return wxUserTemplate;
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

	public List<WxUserTemplate> list(WxUserTemplateQuery query) {
		List<WxUserTemplate> list = wxUserTemplateMapper
				.getWxUserTemplates(query);
		return list;
	}

	@Transactional
	public void save(WxUserTemplate wxUserTemplate) {
		WxUserTemplate model = this.getWxUserTemplate(
				wxUserTemplate.getAccountId(), wxUserTemplate.getCategoryId(),
				wxUserTemplate.getType());
		if (model != null) {
			this.deleteById(model.getId());
		}
		wxUserTemplate.setId(idGenerator.nextId());
		wxUserTemplate.setCreateDate(new Date());
		wxUserTemplateMapper.insertWxUserTemplate(wxUserTemplate);
		String cacheKey = "wx_user_tpl_" + wxUserTemplate.getAccountId() + "_"
				+ wxUserTemplate.getCategoryId() + "_"
				+ wxUserTemplate.getType();
		CacheFactory.remove(cacheKey);
	}

	@javax.annotation.Resource
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@javax.annotation.Resource
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@javax.annotation.Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@javax.annotation.Resource
	public void setWxUserTemplateMapper(
			WxUserTemplateMapper wxUserTemplateMapper) {
		this.wxUserTemplateMapper = wxUserTemplateMapper;
	}

}
