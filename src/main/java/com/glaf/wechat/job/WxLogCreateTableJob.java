package com.glaf.wechat.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.glaf.core.util.DateUtils;
import com.glaf.wechat.util.WxLogTableUtils;

public class WxLogCreateTableJob implements Job {
	protected final static Log logger = LogFactory
			.getLog(WxLogCreateTableJob.class);

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String jobName = context.getJobDetail().getKey().getName();
		logger.info("Executing job: " + jobName + " executing at "
				+ DateUtils.getDateTime(new Date()));
		Date date = DateUtils.getDateAfter(new Date(), 0);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int daysOfMonth = DateUtils.getYearMonthDays(year, month + 1);

		calendar.set(year, month, daysOfMonth);

		int begin = getYearMonthDay(date);
		int end = getYearMonthDay(calendar.getTime());

		for (int i = begin; i <= end; i++) {
			try {
				WxLogTableUtils.createTable("WX_LOG_" + i);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex);
			}
		}

	}

	public static int getYearMonthDay(Date date) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		returnStr = f.format(date);
		return Integer.parseInt(returnStr);
	}

	public static void main(String[] args) {
		Date date = DateUtils.getDateAfter(new Date(), 0);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int daysOfMonth = DateUtils.getYearMonthDays(year, month + 1);

		calendar.set(year, month, daysOfMonth);

		int begin = getYearMonthDay(date);
		int end = getYearMonthDay(calendar.getTime());

		for (int i = begin; i <= end; i++) {
			logger.debug(i);
			try {
				WxLogTableUtils.createTable("WX_LOG_" + i);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex);
			}
		}

	}

}
