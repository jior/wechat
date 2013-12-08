package com.glaf.wechat.mapper;

import java.util.*;
import org.springframework.stereotype.Component;
import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;

@Component
public interface WxFollowerMapper {

	void deleteWxFollowers(WxFollowerQuery query);

	void deleteWxFollowerById(Long id);

	WxFollower getWxFollowerById(Long id);

	int getWxFollowerCount(WxFollowerQuery query);

	List<WxFollower> getWxFollowers(WxFollowerQuery query);

	void insertWxFollower(WxFollower model);

	void updateWxFollower(WxFollower model);

}
