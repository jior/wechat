package com.glaf.wechat.service;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Transactional(readOnly = true)
public interface WxFollowerService {

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
	List<WxFollower> list(WxFollowerQuery query);

	/**
	 * 根据查询参数获取记录总数
	 * 
	 * @return
	 */
	int getWxFollowerCountByQueryCriteria(WxFollowerQuery query);

	/**
	 * 根据查询参数获取一页的数据
	 * 
	 * @return
	 */
	List<WxFollower> getWxFollowersByQueryCriteria(int start, int pageSize,
			WxFollowerQuery query);

	/**
	 * 根据主键获取一条记录
	 * 
	 * @return
	 */
	WxFollower getWxFollower(Long id);

	@Transactional
	void insertAll(List<WxFollower> followers);

	/**
	 * 保存一条记录
	 * 
	 * @return
	 */
	@Transactional
	void save(WxFollower wxFollower);

}
