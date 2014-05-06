package com.glaf.wechat.service;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Transactional(readOnly = true)
public interface WxKeywordsService {

	/**
	 * 根据主键删除记录
	 * 
	 * @return
	 */
	@Transactional
	void deleteById(Long id);

	/**
	 * 根据主键删除多条记录
	 * 
	 * @return
	 */
	@Transactional
	void deleteByIds(List<Long> ids);

	/**
	 * 根据主键获取一条记录
	 * 
	 * @return
	 */
	WxKeywords getWxKeywords(Long id);

	/**
	 * 根据查询参数获取记录总数
	 * 
	 * @return
	 */
	int getWxKeywordsCountByQueryCriteria(WxKeywordsQuery query);

	/**
	 * 根据查询参数获取一页的数据
	 * 
	 * @return
	 */
	List<WxKeywords> getWxKeywordssByQueryCriteria(int start, int pageSize,
			WxKeywordsQuery query);

	/**
	 * 根据查询参数获取记录列表
	 * 
	 * @return
	 */
	List<WxKeywords> list(WxKeywordsQuery query);

	/**
	 * 保存一条记录
	 * 
	 * @return
	 */
	@Transactional
	void save(WxKeywords wxKeywords);
	
	/**
	 * 保存关键字引用
	 * @param categoryId
	 * @param content
	 */
	@Transactional
	void saveAll(Long categoryId, WxContent content);

}
