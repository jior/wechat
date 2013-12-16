package com.glaf.wechat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.glaf.core.domain.ColumnDefinition;
import com.glaf.core.domain.TableDefinition;
import com.glaf.core.util.DBUtils;
import com.glaf.core.util.DateUtils;

public class WxLogTableUtils {

	private WxLogTableUtils() {

	}

	public static int getYearMonthDay(Date date) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		returnStr = f.format(date);
		return Integer.parseInt(returnStr);
	}

	public static void main(String[] args) {
		Date date = DateUtils.getDateAfter(new Date(), 20);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int daysOfMonth = DateUtils.getYearMonthDays(year, month + 1);

		calendar.set(year, month, daysOfMonth);

		int begin = getYearMonthDay(date);
		int end = getYearMonthDay(calendar.getTime());

		for (int i = begin; i <= end; i++) {
			System.out.println(i);
			try {
				WxLogTableUtils.createTable("wx_log_" + i);
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println(ex);
			}
		}

	}

	public static TableDefinition createTable(String tableName) {
		TableDefinition tableDefinition = new TableDefinition();
		tableDefinition.setTableName(tableName);

		ColumnDefinition idColumn = new ColumnDefinition();
		idColumn.setColumnName("ID_");
		idColumn.setJavaType("Long");
		tableDefinition.setIdColumn(idColumn);

		ColumnDefinition column1 = new ColumnDefinition();
		column1.setColumnName("ACCOUNT_");
		column1.setJavaType("String");
		column1.setLength(50);
		tableDefinition.addColumn(column1);

		ColumnDefinition column2 = new ColumnDefinition();
		column2.setColumnName("IP_");
		column2.setJavaType("String");
		column2.setLength(50);
		tableDefinition.addColumn(column2);

		ColumnDefinition column3 = new ColumnDefinition();
		column3.setColumnName("CREATETIME_");
		column3.setJavaType("Date");
		tableDefinition.addColumn(column3);

		ColumnDefinition column4 = new ColumnDefinition();
		column4.setColumnName("OPERATE_");
		column4.setJavaType("String");
		column4.setLength(500);
		tableDefinition.addColumn(column4);

		ColumnDefinition column5 = new ColumnDefinition();
		column5.setColumnName("FLAG_");
		column5.setJavaType("Integer");
		tableDefinition.addColumn(column5);

		if (!DBUtils.tableExists(tableName)) {
			DBUtils.createTable(tableDefinition);
		}

		return tableDefinition;
	}

}
