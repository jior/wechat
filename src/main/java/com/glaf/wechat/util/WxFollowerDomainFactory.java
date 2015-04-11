package com.glaf.wechat.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.glaf.core.base.DataRequest;
import com.glaf.core.base.DataRequest.FilterDescriptor;
import com.glaf.core.domain.ColumnDefinition;
import com.glaf.core.domain.TableDefinition;
import com.glaf.core.util.DBUtils;

/**
 * 
 * 实体数据工厂类
 *
 */
public class WxFollowerDomainFactory {

	public static final String TABLENAME_PREFIX = "WX_FOLLOWER_";

	public static final ConcurrentMap<String, String> columnMap = new ConcurrentHashMap<String, String>();

	public static final ConcurrentMap<String, String> javaTypeMap = new ConcurrentHashMap<String, String>();

	static {
		columnMap.put("id", "ID_");
		columnMap.put("accountId", "ACCOUNTID_");
		columnMap.put("actorId", "ACTORID_");
		columnMap.put("sourceId", "SOURCEID_");
		columnMap.put("openId", "OPENID_");
		columnMap.put("nickName", "NICKNAME_");
		columnMap.put("sex", "SEX_");
		columnMap.put("mobile", "MOBILE_");
		columnMap.put("mail", "MAIL_");
		columnMap.put("telephone", "TELEPHONE_");
		columnMap.put("headimgurl", "HEADIMGURL_");
		columnMap.put("province", "PROVINCE_");
		columnMap.put("city", "CITY_");
		columnMap.put("country", "COUNTRY_");
		columnMap.put("language", "LANGUAGE_");
		columnMap.put("locked", "LOCKED_");
		columnMap.put("remark", "REMARK");
		columnMap.put("subscribeTime", "SUBSCRIBETIME_");
		columnMap.put("subscribeYear", "SUBSCRIBEYEAR_");
		columnMap.put("subscribeMonth", "SUBSCRIBEMONTH_");
		columnMap.put("subscribeDay", "SUBSCRIBEDAY_");
		columnMap.put("unsubscribeTime", "UNSUBSCRIBETIME_");
		columnMap.put("createDate", "CREATEDATE_");
		columnMap.put("lastModified", "LASTMODIFIED_");

		javaTypeMap.put("id", "Long");
		javaTypeMap.put("accountId", "Long");
		javaTypeMap.put("actorId", "String");
		javaTypeMap.put("sourceId", "String");
		javaTypeMap.put("openId", "String");
		javaTypeMap.put("nickName", "String");
		javaTypeMap.put("sex", "String");
		javaTypeMap.put("mobile", "String");
		javaTypeMap.put("mail", "String");
		javaTypeMap.put("telephone", "String");
		javaTypeMap.put("headimgurl", "String");
		javaTypeMap.put("province", "String");
		javaTypeMap.put("city", "String");
		javaTypeMap.put("country", "String");
		javaTypeMap.put("language", "String");
		javaTypeMap.put("locked", "Integer");
		javaTypeMap.put("remark", "String");
		javaTypeMap.put("subscribeTime", "Long");
		javaTypeMap.put("subscribeYear", "Integer");
		javaTypeMap.put("subscribeMonth", "Integer");
		javaTypeMap.put("subscribeDay", "Integer");
		javaTypeMap.put("unsubscribeTime", "Long");
		javaTypeMap.put("createDate", "Date");
		javaTypeMap.put("lastModified", "Long");
	}

	public static Map<String, String> getColumnMap() {
		return columnMap;
	}

	public static Map<String, String> getJavaTypeMap() {
		return javaTypeMap;
	}

	public static TableDefinition getTableDefinition(String tableName) {
		tableName = tableName.toUpperCase();
		TableDefinition tableDefinition = new TableDefinition();
		tableDefinition.setTableName(tableName);
		tableDefinition.setName("WxFollower");

		ColumnDefinition idColumn = new ColumnDefinition();
		idColumn.setName("id");
		idColumn.setColumnName("ID_");
		idColumn.setJavaType("Long");
		tableDefinition.setIdColumn(idColumn);

		ColumnDefinition accountId = new ColumnDefinition();
		accountId.setName("accountId");
		accountId.setColumnName("ACCOUNTID_");
		accountId.setJavaType("Long");
		tableDefinition.addColumn(accountId);

		ColumnDefinition actorId = new ColumnDefinition();
		actorId.setName("actorId");
		actorId.setColumnName("ACTORID_");
		actorId.setJavaType("String");
		actorId.setLength(50);
		tableDefinition.addColumn(actorId);

		ColumnDefinition sourceId = new ColumnDefinition();
		sourceId.setName("sourceId");
		sourceId.setColumnName("SOURCEID_");
		sourceId.setJavaType("String");
		sourceId.setLength(200);
		tableDefinition.addColumn(sourceId);

		ColumnDefinition openId = new ColumnDefinition();
		openId.setName("openId");
		openId.setColumnName("OPENID_");
		openId.setJavaType("String");
		openId.setLength(200);
		tableDefinition.addColumn(openId);

		ColumnDefinition nickName = new ColumnDefinition();
		nickName.setName("nickName");
		nickName.setColumnName("NICKNAME_");
		nickName.setJavaType("String");
		nickName.setLength(200);
		tableDefinition.addColumn(nickName);

		ColumnDefinition sex = new ColumnDefinition();
		sex.setName("sex");
		sex.setColumnName("SEX_");
		sex.setJavaType("String");
		sex.setLength(1);
		tableDefinition.addColumn(sex);

		ColumnDefinition mobile = new ColumnDefinition();
		mobile.setName("mobile");
		mobile.setColumnName("MOBILE_");
		mobile.setJavaType("String");
		mobile.setLength(20);
		tableDefinition.addColumn(mobile);

		ColumnDefinition mail = new ColumnDefinition();
		mail.setName("mail");
		mail.setColumnName("MAIL_");
		mail.setJavaType("String");
		mail.setLength(100);
		tableDefinition.addColumn(mail);

		ColumnDefinition telephone = new ColumnDefinition();
		telephone.setName("telephone");
		telephone.setColumnName("TELEPHONE_");
		telephone.setJavaType("String");
		telephone.setLength(50);
		tableDefinition.addColumn(telephone);

		ColumnDefinition headimgurl = new ColumnDefinition();
		headimgurl.setName("headimgurl");
		headimgurl.setColumnName("HEADIMGURL_");
		headimgurl.setJavaType("String");
		headimgurl.setLength(500);
		tableDefinition.addColumn(headimgurl);

		ColumnDefinition province = new ColumnDefinition();
		province.setName("province");
		province.setColumnName("PROVINCE_");
		province.setJavaType("String");
		province.setLength(100);
		tableDefinition.addColumn(province);

		ColumnDefinition city = new ColumnDefinition();
		city.setName("city");
		city.setColumnName("CITY_");
		city.setJavaType("String");
		city.setLength(100);
		tableDefinition.addColumn(city);

		ColumnDefinition country = new ColumnDefinition();
		country.setName("country");
		country.setColumnName("COUNTRY_");
		country.setJavaType("String");
		country.setLength(100);
		tableDefinition.addColumn(country);

		ColumnDefinition language = new ColumnDefinition();
		language.setName("language");
		language.setColumnName("LANGUAGE_");
		language.setJavaType("String");
		language.setLength(50);
		tableDefinition.addColumn(language);

		ColumnDefinition locked = new ColumnDefinition();
		locked.setName("locked");
		locked.setColumnName("LOCKED_");
		locked.setJavaType("Integer");
		tableDefinition.addColumn(locked);

		ColumnDefinition remark = new ColumnDefinition();
		remark.setName("remark");
		remark.setColumnName("REMARK");
		remark.setJavaType("String");
		remark.setLength(250);
		tableDefinition.addColumn(remark);

		ColumnDefinition subscribeTime = new ColumnDefinition();
		subscribeTime.setName("subscribeTime");
		subscribeTime.setColumnName("SUBSCRIBETIME_");
		subscribeTime.setJavaType("Long");
		tableDefinition.addColumn(subscribeTime);

		ColumnDefinition subscribeYear = new ColumnDefinition();
		subscribeYear.setName("subscribeYear");
		subscribeYear.setColumnName("SUBSCRIBEYEAR_");
		subscribeYear.setJavaType("Integer");
		tableDefinition.addColumn(subscribeYear);

		ColumnDefinition subscribeMonth = new ColumnDefinition();
		subscribeMonth.setName("subscribeMonth");
		subscribeMonth.setColumnName("SUBSCRIBEMONTH_");
		subscribeMonth.setJavaType("Integer");
		tableDefinition.addColumn(subscribeMonth);

		ColumnDefinition subscribeDay = new ColumnDefinition();
		subscribeDay.setName("subscribeDay");
		subscribeDay.setColumnName("SUBSCRIBEDAY_");
		subscribeDay.setJavaType("Integer");
		tableDefinition.addColumn(subscribeDay);

		ColumnDefinition unsubscribeTime = new ColumnDefinition();
		unsubscribeTime.setName("unsubscribeTime");
		unsubscribeTime.setColumnName("UNSUBSCRIBETIME_");
		unsubscribeTime.setJavaType("Long");
		tableDefinition.addColumn(unsubscribeTime);

		ColumnDefinition createDate = new ColumnDefinition();
		createDate.setName("createDate");
		createDate.setColumnName("CREATEDATE_");
		createDate.setJavaType("Date");
		tableDefinition.addColumn(createDate);

		ColumnDefinition lastModified = new ColumnDefinition();
		lastModified.setName("lastModified");
		lastModified.setColumnName("LASTMODIFIED_");
		lastModified.setJavaType("Long");
		tableDefinition.addColumn(lastModified);

		return tableDefinition;
	}

	public static TableDefinition createOrAlterTable(Long accountId) {
		String tableName = TABLENAME_PREFIX + accountId;
		TableDefinition tableDefinition = getTableDefinition(tableName);
		if (!DBUtils.tableExists(tableName)) {
			DBUtils.createTable(tableDefinition);
		} else {
			DBUtils.alterTable(tableDefinition);
		}
		return tableDefinition;
	}

	public static TableDefinition createOrAlterTable(String tableName) {
		TableDefinition tableDefinition = getTableDefinition(tableName);
		if (!DBUtils.tableExists(tableName)) {
			DBUtils.createTable(tableDefinition);
		} else {
			DBUtils.alterTable(tableDefinition);
		}
		return tableDefinition;
	}

	public static void processDataRequest(DataRequest dataRequest) {
		if (dataRequest != null) {
			if (dataRequest.getFilter() != null) {
				if (dataRequest.getFilter().getField() != null) {
					dataRequest.getFilter().setColumn(
							columnMap.get(dataRequest.getFilter().getField()));
					dataRequest.getFilter()
							.setJavaType(
									javaTypeMap.get(dataRequest.getFilter()
											.getField()));
				}

				List<FilterDescriptor> filters = dataRequest.getFilter()
						.getFilters();
				for (FilterDescriptor filter : filters) {
					filter.setParent(dataRequest.getFilter());
					if (filter.getField() != null) {
						filter.setColumn(columnMap.get(filter.getField()));
						filter.setJavaType(javaTypeMap.get(filter.getField()));
					}

					List<FilterDescriptor> subFilters = filter.getFilters();
					for (FilterDescriptor f : subFilters) {
						f.setColumn(columnMap.get(f.getField()));
						f.setJavaType(javaTypeMap.get(f.getField()));
						f.setParent(filter);
					}
				}
			}
		}
	}

	private WxFollowerDomainFactory() {

	}

}
