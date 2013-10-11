package com.glaf.wechat.mapper;

import java.util.*;
import org.springframework.stereotype.Component;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Component
public interface WxUserTemplateMapper {

	void deleteWxUserTemplates(WxUserTemplateQuery query);

	void deleteWxUserTemplateById(Long id);

	WxUserTemplate getWxUserTemplateById(Long id);

	int getWxUserTemplateCount(WxUserTemplateQuery query);

	List<WxUserTemplate> getWxUserTemplates(WxUserTemplateQuery query);

	void insertWxUserTemplate(WxUserTemplate model);

	void updateWxUserTemplate(WxUserTemplate model);

}
