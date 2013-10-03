package com.glaf.wechat.mapper;

import java.util.*;
import org.springframework.stereotype.Component;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Component
public interface WxKeywordsMapper {

	void deleteWxKeywordss(WxKeywordsQuery query);

	void deleteWxKeywordsById(Long id);

	WxKeywords getWxKeywordsById(Long id);

	int getWxKeywordsCount(WxKeywordsQuery query);

	List<WxKeywords> getWxKeywordss(WxKeywordsQuery query);

	void insertWxKeywords(WxKeywords model);

	void updateWxKeywords(WxKeywords model);

}
