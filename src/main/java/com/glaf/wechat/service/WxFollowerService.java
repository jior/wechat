package com.glaf.wechat.service;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Transactional(readOnly = true)
public interface WxFollowerService {

	/**
	 * ��������ɾ����¼
	 * 
	 * @return
	 */
	@Transactional
	void deleteById(Long id);

	/**
	 * ��������ɾ��������¼
	 * 
	 * @return
	 */
	@Transactional
	void deleteByIds(List<Long> ids);

	/**
	 * ���ݲ�ѯ������ȡ��¼�б�
	 * 
	 * @return
	 */
	List<WxFollower> list(WxFollowerQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getWxFollowerCountByQueryCriteria(WxFollowerQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<WxFollower> getWxFollowersByQueryCriteria(int start, int pageSize,
			WxFollowerQuery query);

	/**
	 * ����������ȡһ����¼
	 * 
	 * @return
	 */
	WxFollower getWxFollower(Long id);

	@Transactional
	void insertAll(List<WxFollower> followers);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void save(WxFollower wxFollower);

}
