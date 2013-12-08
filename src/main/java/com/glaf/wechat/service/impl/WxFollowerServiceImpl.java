package com.glaf.wechat.service.impl;

import java.util.*;

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
import com.glaf.wechat.service.WxFollowerService;

@Service("wxFollowerService")
@Transactional(readOnly = true)
public class WxFollowerServiceImpl implements WxFollowerService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected WxFollowerMapper wxFollowerMapper;

	public WxFollowerServiceImpl() {

	}

	@Transactional
	public void deleteById(Long id) {
		if (id != null) {
			wxFollowerMapper.deleteWxFollowerById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (Long id : ids) {
				wxFollowerMapper.deleteWxFollowerById(id);
			}
		}
	}

	public int count(WxFollowerQuery query) {
		query.ensureInitialized();
		return wxFollowerMapper.getWxFollowerCount(query);
	}

	public List<WxFollower> list(WxFollowerQuery query) {
		query.ensureInitialized();
		List<WxFollower> list = wxFollowerMapper.getWxFollowers(query);
		return list;
	}

	public int getWxFollowerCountByQueryCriteria(WxFollowerQuery query) {
		return wxFollowerMapper.getWxFollowerCount(query);
	}

	public List<WxFollower> getWxFollowersByQueryCriteria(int start,
			int pageSize, WxFollowerQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<WxFollower> rows = sqlSessionTemplate.selectList("getWxFollowers",
				query, rowBounds);
		return rows;
	}

	public WxFollower getWxFollower(Long id) {
		if (id == null) {
			return null;
		}
		WxFollower wxFollower = wxFollowerMapper.getWxFollowerById(id);
		return wxFollower;
	}

	@Transactional
	public void save(WxFollower wxFollower) {
		if (wxFollower.getId() == null) {
			wxFollower.setId(idGenerator.nextId());
			wxFollower.setCreateDate(new Date());
			wxFollowerMapper.insertWxFollower(wxFollower);
		} else {
			wxFollowerMapper.updateWxFollower(wxFollower);
		}
	}

	@Transactional
	public void insertAll(List<WxFollower> followers) {
		if (followers != null && !followers.isEmpty()) {
			for (WxFollower follower : followers) {
				follower.setId(idGenerator.nextId());
				follower.setCreateDate(new Date());
				wxFollowerMapper.insertWxFollower(follower);
			}
		}
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
	public void setWxFollowerMapper(WxFollowerMapper wxFollowerMapper) {
		this.wxFollowerMapper = wxFollowerMapper;
	}

	@javax.annotation.Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
