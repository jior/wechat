package com.glaf.wechat.service;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Transactional(readOnly = true)
public interface WxUserTemplateService {

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
	List<WxUserTemplate> list(WxUserTemplateQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getWxUserTemplateCountByQueryCriteria(WxUserTemplateQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<WxUserTemplate> getWxUserTemplatesByQueryCriteria(int start,
			int pageSize, WxUserTemplateQuery query);

	/**
	 * ����������ȡһ����¼
	 * 
	 * @return
	 */
	WxUserTemplate getWxUserTemplate(Long id);
	
	/**
	 * ��ȡĳ����Ŀָ�����͵�ģ��ʵ��
	 * @param createBy
	 * @param type
	 * @param categoryId
	 * @return
	 */
	WxUserTemplate getWxUserTemplate(String createBy, String type, Long categoryId);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void save(WxUserTemplate wxUserTemplate);

}
