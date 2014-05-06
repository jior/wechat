package com.glaf.wechat.service;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Transactional(readOnly = true)
public interface WxUserTemplateService {

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
	 * 根据查询参数获取记录列表
	 * 
	 * @return
	 */
	List<WxUserTemplate> list(WxUserTemplateQuery query);

	/**
	 * 根据查询参数获取记录总数
	 * 
	 * @return
	 */
	int getWxUserTemplateCountByQueryCriteria(WxUserTemplateQuery query);

	/**
	 * 根据查询参数获取一页的数据
	 * 
	 * @return
	 */
	List<WxUserTemplate> getWxUserTemplatesByQueryCriteria(int start,
			int pageSize, WxUserTemplateQuery query);

	/**
	 * 根据主键获取一条记录
	 * 
	 * @return
	 */
	WxUserTemplate getWxUserTemplate(Long id);
	
	/**
	 * 获取某个栏目指定类型的模板实例
	 * @param accountId
	 * @param categoryId
	 * @param type
	 * @return
	 */
	WxUserTemplate getWxUserTemplate(Long accountId, Long categoryId, String type);
	
	
	/**
	 * 获取某个栏目指定类型的模板实例
	 * @param type
	 * @param categoryId
	 * @return
	 */
	WxUserTemplate getWxUserTemplate(Long categoryId, String type );

	/**
	 * 保存一条记录
	 * 
	 * @return
	 */
	@Transactional
	void save(WxUserTemplate wxUserTemplate);

}
