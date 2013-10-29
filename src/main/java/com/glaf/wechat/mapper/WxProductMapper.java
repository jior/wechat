package com.glaf.wechat.mapper;

import java.util.*;
import org.springframework.stereotype.Component;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Component
public interface WxProductMapper {

	void deleteWxProducts(WxProductQuery query);

	void deleteWxProductById(Long id);

	WxProduct getWxProductById(Long id);

	int getWxProductCount(WxProductQuery query);

	List<WxProduct> getWxProducts(WxProductQuery query);

	void insertWxProduct(WxProduct model);

	void updateWxProduct(WxProduct model);

}
