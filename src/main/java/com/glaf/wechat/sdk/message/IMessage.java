package com.glaf.wechat.sdk.message;

/**
 * 全部常量定义
 * 
 */
public interface IMessage {

	// 接收的消息类型
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_TEXT = "text";

	// 响应的消息类型
	public static final String MESSAGE_RESPONSE_MUSIC = "music";
	public static final String MESSAGE_RESPONSE_NEWS = "news";
	public static final String MESSAGE_RESPONSE_TEXT = "text";

	// 事件类型
	public static final String EVENT_CLICK = "CLICK";
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";

	// 消息标签
	public static final String TAG_ARTICLECOUNT = "ArticleCount";
	public static final String TAG_ARTICLES = "Articles";
	public static final String TAG_CONTENT = "Content";
	public static final String TAG_CREATETIME = "CreateTime";
	public static final String TAG_DESCRIPTION = "Description";
	public static final String TAG_EVENT = "Event";
	public static final String TAG_EVENTKEY = "EventKey";
	public static final String TAG_FROMUSERNAME = "FromUserName";
	public static final String TAG_FUNCFLAG = "FuncFlag";
	public static final String TAG_HQMUSICURL = "HQMusicUrl";
	public static final String TAG_ITEM = "item";
	public static final String TAG_LABEL = "Label";
	public static final String TAG_LOCATIONX = "Location_X";
	public static final String TAG_LOCATIONY = "Location_Y";
	public static final String TAG_MSGID = "MsgId";
	public static final String TAG_MSGTYPE = "MsgType";
	public static final String TAG_MUSIC = "Music";
	public static final String TAG_MUSICURL = "MusicUrl";
	public static final String TAG_PICURL = "PicUrl";
	public static final String TAG_SCALE = "Scale";
	public static final String TAG_TITLE = "Title";
	public static final String TAG_TOUSERNAME = "ToUserName";
	public static final String TAG_URL = "Url";
	public static final String TAG_XML = "xml";

}
