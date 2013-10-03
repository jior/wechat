package com.glaf.wechat.service;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Transactional(readOnly = true)
public interface WxKeywordsService {

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
	 * ����������ȡһ����¼
	 * 
	 * @return
	 */
	WxKeywords getWxKeywords(Long id);

	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getWxKeywordsCountByQueryCriteria(WxKeywordsQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<WxKeywords> getWxKeywordssByQueryCriteria(int start, int pageSize,
			WxKeywordsQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼�б�
	 * 
	 * @return
	 */
	List<WxKeywords> list(WxKeywordsQuery query);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void save(WxKeywords wxKeywords);
	
	/**
	 * ����ؼ�������
	 * @param categoryId
	 * @param content
	 */
	@Transactional
	void saveAll(Long categoryId, WxContent content);

}
