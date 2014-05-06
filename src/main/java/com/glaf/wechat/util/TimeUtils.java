/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.glaf.wechat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimeUtils {
	// 计算多少天后的时间
	private static int after(int curYear, int curMonth, int curDay, int i) {
		int _iCurYear = curYear;
		int _iCurMonth = curMonth;
		int _iCurDay = curDay;
		int _iDays = i;
		// 平年月的天数
		int _aNormMonthDays[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		// 闰年月的天数
		int _aLeapMonthDays[] = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		int[] _aMonthDays = new int[13];
		// 计算下个年度的月份
		while (_iDays != 0) {
			// 是否闰年
			if (isLeapYear(_iCurYear)) {
				_aMonthDays = _aLeapMonthDays;
			} else {
				_aMonthDays = _aNormMonthDays;
			}

			// 是否在当前月中
			int _iDescDays = _aMonthDays[_iCurMonth] - _iCurDay;
			if (_iDays > _iDescDays) {
				_iDays = _iDays - _iDescDays - 1;
				// 设定下月的年月日
				_iCurDay = 1;
				if (_iCurMonth < 12) {
					_iCurMonth++;
				} else {
					_iCurMonth = 1;
					_iCurYear++;
				}
			} else {
				_iCurDay = _iCurDay + _iDays;
				_iDays = 0;
			}
		}

		return _iCurYear * 10000 + _iCurMonth * 100 + _iCurDay;
	}

	private static int before(int curYear, int curMonth, int curDay, int i) {
		int _iCurYear = curYear;
		int _iCurMonth = curMonth;
		int _iCurDay = curDay;
		int _iDays = i;
		_iDays = -_iDays;
		// 平年月的天数
		int _aNormMonthDays[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		// 闰年月的天数
		int _aLeapMonthDays[] = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		int[] _aMonthDays = new int[13];
		// 计算下个年度的月份
		while (_iDays != 0) {
			// 是否在当前月中
			int _iDescDays = _iCurDay - 1;
			if (_iDays > _iDescDays) {
				_iDays = _iDays - _iDescDays - 1;
				// 设定上月的年月日
				if (_iCurMonth > 1) {
					_iCurMonth--;
				} else {
					_iCurMonth = 12;
					_iCurYear--;
				}
				// 是否闰年
				if (isLeapYear(_iCurYear)) {
					_aMonthDays = _aLeapMonthDays;
				} else {
					_aMonthDays = _aNormMonthDays;
				}
				// 上月的月末日期
				_iCurDay = _aMonthDays[_iCurMonth];
			} else {
				_iCurDay = _iCurDay - _iDays;
				_iDays = 0;
			}
		}

		return _iCurYear * 10000 + _iCurMonth * 100 + _iCurDay;
	}

	public static String changeDateFormat(String dateStr) throws ParseException {
		Date d = new SimpleDateFormat("ddMMMyy", Locale.US).parse(dateStr);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(d);
	}

	/**
	 * 校验时间是否合法
	 * 
	 * @param curTime
	 *            录入时间 HHMMSS 或 HMMSS
	 * @return 时间合法则返回true，不合法返回false
	 */
	public static boolean checkTime(int curTime) {
		// 时间的范围
		if ((curTime > 235959) || (curTime < 0)) {
			return false;
		}

		// 获得不同段的时间
		int iHour = curTime / 10000; // 小时
		int iMinute = (curTime - (iHour * 10000)) / 100; // 分钟
		int iSecond = curTime % 100; // 秒

		// 对小时进行校验
		if ((iHour < 0) || (iHour > 23)) {
			return false;
		}
		// 对分钟进行校验
		if ((iMinute < 0) || (iMinute > 59)) {
			return false;
		}
		// 对秒钟进行校验
		if ((iSecond < 0) || (iSecond > 59)) {
			return false;
		}

		return true;
	}

	/**
	 * 校验时间或者日期是否合法
	 * 
	 * @param strDate
	 *            "YYYYMMDD" OR "YYYYMMDDhhmmss"
	 * @return true - 合法； false - 非法
	 */
	public static boolean checkTimeStr(String strDate) {
		// 时间参数:年、月、日、时、分、秒
		int _iYear = 0;
		int _iMonth = 0;
		int _iDay = 0;
		int _iHour = 0;
		int _iMinute = 0;
		int _iSecond = 0;
		boolean _bFlag = false;
		String _strDate = strDate.trim();

		// 合法性的判断
		try {
			if (Long.parseLong(_strDate) > 0) {
				_bFlag = true;
			} else {
				_bFlag = false;
			}
		} catch (NumberFormatException ex2) {
			_bFlag = false;
		}
		if (!_bFlag) {
			return _bFlag;
		}

		// 将年月日转换为整型
		try {
			_iYear = Integer.parseInt(_strDate.substring(0, 4));
			_iMonth = Integer.parseInt(_strDate.substring(4, 6));
			_iDay = Integer.parseInt(_strDate.substring(6, 8));
		} catch (Exception ex) {
			_bFlag = false;
			return _bFlag;
		}

		// 判断年月是否合法
		if ((_iYear > 1900) && (_iDay > 0) && ((_iMonth > 0) && (_iMonth < 13))) {
			_bFlag = true;
		} else {
			_bFlag = false;
		}

		// 判定日期是否合法
		if (_bFlag) {
			// 平年时每月的天数
			int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			int _maxDay = months[_iMonth];
			// 如果是闰年，二月再增加一天
			if (((_iYear % 4 == 0) && (_iYear % 100 != 0))
					|| (_iYear % 400 == 0)) {
				if (_iMonth == 2) {
					_maxDay++;
				}
			}
			_bFlag = (_iDay <= _maxDay);
		}

		if ((_bFlag) && (_strDate.length() == 14)) {
			// 当字符串中含有时间串(hh:mm:ss)
			try {
				_iHour = Integer.parseInt(_strDate.substring(8, 10));
				_iMinute = Integer.parseInt(_strDate.substring(10, 12));
				_iSecond = Integer.parseInt(_strDate.substring(12, 14));
			} catch (Exception ex1) {
				_bFlag = false;
			}
			if (!_bFlag) {
				return _bFlag;
			}

			// 检测时分秒
			if (((_iHour >= 0) && (_iHour <= 23))
					&& ((_iMinute >= 0) && (_iMinute <= 59))
					&& ((_iSecond >= 0) && (_iSecond <= 59))) {
				_bFlag = true;
			} else {
				_bFlag = false;
			}
		}

		return _bFlag;
	}

	/**
	 * 将日期格式精简成年月格式
	 * 
	 * @param date
	 * @return
	 */
	public static int date2Month(int date) {
		int length = String.valueOf(date).length();
		int month = date;
		if (length == 8 && date != 0) {
			month = date / 100;
		}
		return month;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY-MM-DD
	 */
	public static String date2Str(int iDate) {
		String _strDate = null;
		_strDate = String.valueOf(iDate);
		if (_strDate.length() == 8) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			String _strDay = _strDate.substring(6, 8);
			// 整合成YYYY年MM月DD日串
			_strDate = _strYear + "-" + _strMonth + "-" + _strDay;
		} else if (_strDate.length() == 6) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			// 整合成YYYY年MM月串
			_strDate = _strYear + "-" + _strMonth;

		}
		return _strDate;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY-MM-DD
	 */
	public static String date2Str(String iDate) {
		String _strDate = null;
		_strDate = iDate;
		if (_strDate.length() == 8) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			String _strDay = _strDate.substring(6, 8);
			// 整合成YYYY年MM月DD日串
			_strDate = _strYear + "-" + _strMonth + "-" + _strDay;
		} else if (_strDate.length() == 6) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			// 整合成YYYY年MM月串
			_strDate = _strYear + "-" + _strMonth;

		}
		return _strDate;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY/MM/DD
	 */
	public static String date2Str2(int iDate) {
		String _strDate = null;
		_strDate = String.valueOf(iDate);
		if (_strDate.length() == 8) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			String _strDay = _strDate.substring(6, 8);
			// 整合成YYYY/MM/DD串
			_strDate = _strYear + "/" + _strMonth + "/" + _strDay;
		} else if (_strDate.length() == 6) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			// 整合成YYYY/MM串
			_strDate = _strYear + "/" + _strMonth;

		}
		return _strDate;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY/MM/DD
	 */
	public static String date2Str2(String iDate) {
		if (iDate.length() == 8) {
			String _strYear = iDate.substring(0, 4);
			String _strMonth = iDate.substring(4, 6);
			String _strDay = iDate.substring(6, 8);
			// 整合成YYYY/MM/DD串
			iDate = _strYear + "/" + _strMonth + "/" + _strDay;
		} else if (iDate.length() == 6) {
			String _strYear = iDate.substring(0, 4);
			String _strMonth = iDate.substring(4, 6);
			// 整合成YYYY/MM串
			iDate = _strYear + "/" + _strMonth;
		}
		return iDate;
	}

	/**
	 * 传入一个int的日期转换成一个中文的日期 例如20050328 输出二OO五年三月二十八日
	 * 
	 * @param iDate
	 *            int
	 * @return String
	 */
	public static String date2StrCHS(int iDate) {
		String result = "";
		String[] chineseNumber = { "0", "一", "二", "三", "四", "五", "六", "七", "八",
				"九" };
		char[] cDate = String.valueOf(iDate).toCharArray();
		StringBuffer tempDate = new StringBuffer("");
		for (char aCDate : cDate) {
			int chatat = Integer.parseInt(String.valueOf(aCDate));
			tempDate.append(chineseNumber[chatat]);
		}
		result = tempDate.substring(0, 4) + "年";
		int chatM = Integer.parseInt(String.valueOf(cDate[4]));
		if (chatM != 0) {
			if ("0".equals(tempDate.substring(5, 6))) {
				result += "十月";
			} else {
				result += "十" + tempDate.substring(5, 6) + "月";
			}
		} else {
			result += tempDate.substring(5, 6) + "月";
		}
		int chatd = Integer.parseInt(String.valueOf(cDate[6]));
		if (chatd == 0) {
			result += tempDate.substring(7, 8) + "日";
		} else if (chatd == 1) {
			if ("0".equals(tempDate.substring(7, 8))) {
				result += "十日";
			} else {
				result += "十" + tempDate.substring(7, 8) + "日";
			}
		} else {
			if ("0".equals(tempDate.substring(7, 8))) {
				result += tempDate.substring(6, 7) + "十日";
			} else {
				result += tempDate.substring(6, 7) + "十"
						+ tempDate.substring(7, 8) + "日";
			}
		}
		return result;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY年MM月DD日
	 */
	public static String date2StrCN(int iDate) {
		String _strDate = null;
		_strDate = String.valueOf(iDate);
		if (_strDate.length() == 8) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			String _strDay = _strDate.substring(6, 8);
			// 整合成YYYY年MM月DD日串
			_strDate = _strYear + "年" + _strMonth + "月" + _strDay + "日";
		} else if (_strDate.length() == 6) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			// 整合成YYYY年MM月串
			_strDate = _strYear + "年" + _strMonth + "月";

		}
		return _strDate;
	}

	/**
	 * 转换字符串日期为数字型日期
	 * 
	 * @param strDate
	 *            "YYYY-MM-DD"
	 * @return 数字日期
	 */
	public static int dateFromString(String strDate) {
		int intYear = Integer.parseInt(strDate.substring(0, 4));
		int intMonth = Integer.parseInt(strDate.substring(5, 7));
		int intDay = Integer.parseInt(strDate.substring(8, 10));
		return intYear * 10000 + intMonth * 100 + intDay;
	}

	/**
	 * 将时间格式精简成日期格式
	 * 
	 * @param datetime
	 * @return
	 */
	public static int dateTime2Date(long datetime) {
		int length = String.valueOf(datetime).length();
		long date = datetime;
		if (length == 14 && datetime != 0) {
			date = datetime / 1000000;
		} else if (length == 17 && datetime != 0) {
			date = datetime / 1000000000;
		} else if (datetime > Integer.MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		return (int) date;
	}

	/**
	 * 将时间格式精简成年月格式
	 * 
	 * @param datetime
	 * @return
	 */
	public static int dateTime2Month(long datetime) {
		return dateTime2Date(datetime) / 100;
	}

	/**
	 * 转整型日期时间为字符串日期时间
	 * 
	 * @param iDateTime
	 *            整型YYMMDDhhmmss
	 * @return 字符串 YY-MM-DD hh:mm:ss
	 */
	public static String dateTime2Str(long iDateTime) {
		String _strDateTimeTemp = iDateTime + "";
		// 调用date2Str(int iDate)方法获得YY-MM-DD串
		String _strDate = date2Str(Integer.parseInt(_strDateTimeTemp.substring(
				0, 8)));
		String _strTimeTemp = _strDateTimeTemp.substring(8, 14) + "";
		String _strHour = _strTimeTemp.substring(0, 2);
		String _strMinute = _strTimeTemp.substring(2, 4);
		String _strSecond = _strTimeTemp.substring(4, 6);
		// 整合成 hh:ss:mm 串
		String _strTime = _strHour + ":" + _strMinute + ":" + _strSecond;
		return _strDate + " " + _strTime;
	}

	/**
	 * 返回当前日期的前(后)时间
	 * 
	 * @param minute
	 *            15 分钟
	 * @return
	 */
	public static long dateTimeBeforeMinute(long minute) {
		SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date currentTime = new Date();
		long lDate = currentTime.getTime() + (1000L * 60L * minute);
		currentTime = new Date(lDate);
		return Long.parseLong(longDateFormat.format(currentTime));
	}

	public static String formatDate1(String date) throws ParseException {
		Date d = new SimpleDateFormat("yyyy-M-d HH:mm:ss").parse(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(d);
	}

	/**
	 * 
	 * @param date
	 *            2011-02-28 14:37:40
	 * @return 20110228143740
	 */
	public static Long formatStringToLong(String date) {
		return Long.parseLong(date.replace("-", "").replace(" ", "")
				.replace(":", ""));
	}

	/**
	 * 获得系统年月日，返回"YYYYMMDD"整型
	 * 
	 * @return 返回"YYYYMMDD"整型
	 */
	public static Integer getCurrentDate() {
		return Integer.parseInt(timeFormate("yyyyMMdd"));
	}

	/**
	 * 获得系统天数，以整数"DD"返回
	 * 
	 * @return 返回"DD"整数
	 */
	public static int getCurrentDay() {
		return Integer.parseInt(timeFormate("dd"));
	}

	/**
	 * 获得系统年月日时分秒毫秒，以长整型"YYYYMMDDhhmmssSSS"返回
	 * 
	 * @return 返回"YYYYMMDDhhmmssSSS"整型
	 */
	public static long getCurrentDetailTime() {
		return Long.parseLong(timeFormate("yyyyMMddHHmmssSSS"));
	}

	/**
	 * 获得系统时分秒毫秒，以整型"hhmmssSSS"返回
	 * 
	 * @return 返回"hhmmssSSS"整型
	 */
	public static int getCurrentHmsSTime() {
		return Integer.parseInt(timeFormate("HHmmssSSS"));
	}

	/**
	 * 获得系统时分秒，以整型"hhmmss"返回
	 * 
	 * @return 返回"hhmmssSSS"整型
	 */
	public static int getCurrentHmsTime() {
		return Integer.parseInt(timeFormate("HHmmss"));
	}

	/**
	 * 获得系统时分，以整型"hhmm"返回
	 * 
	 * @return 返回"hhmm"整型
	 */
	public static Integer getCurrentHmTime() {
		return Integer.parseInt(timeFormate("HHmm"));
	}

	/**
	 * 获得系统月份，以整数"MM"返回
	 * 
	 * @return 返回"MM"整数
	 */
	public static int getCurrentMonth() {
		return Integer.parseInt(timeFormate("MM"));
	}

	/**
	 * 获得系统年月日时分秒，以整型"YYYYMMDDhhmmss"返回
	 * 
	 * @return 返回"YYYYMMDDhhmmss"整型
	 */
	public static long getCurrentTime() {
		return Long.parseLong(timeFormate("yyyyMMddHHmmss"));
	}

	/**
	 * 获得系统年份，以整数"YYYY"返回
	 * 
	 * @return 返回"YYYY"整数
	 */
	public static int getCurrentYear() {
		return Integer.parseInt(timeFormate("yyyy"));
	}

	/**
	 * 获得系统年月，返回"YYYYMM"整型
	 * 
	 * @return 返回"YYYYMM"整型
	 */
	public static int getCurrentYearMonth() {
		return Integer.parseInt(timeFormate("yyyyMM"));
	}

	// 计算多少天前的时间

	/**
	 * 获取系统星期几
	 * 
	 * @return
	 */
	public static String getCurrenWeekday() {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(Calendar.getInstance().getTime());
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 把YYYY/MM/DD 转换为YYYYMMDD格式
	 * 
	 * @param date
	 *            YYYYDDMM
	 * @return YYYYDDMM
	 */
	public static String getDate(String date) {
		StringBuffer str = new StringBuffer();
		str.append(date.substring(0, 4));
		str.append(date.substring(5, 7));
		str.append(date.substring(8, 10));
		return str.toString();
	}

	/**
	 * 把YYYYMMDD 转换为YYYY/MM/DD格式
	 * 
	 * @param date
	 *            YYYY/DD/MM
	 * @return YYYY/DD/MM
	 */
	public static String getDate1(String date) {
		StringBuffer str = new StringBuffer();
		str.append(date.substring(0, 4)).append("/");
		str.append(date.substring(4, 6)).append("/");
		str.append(date.substring(6, 8));
		return str.toString();
	}

	/**
	 * 获取两个日期间隔天数
	 * 
	 * @param startDate
	 *            开始日期 YYYYMMDD
	 * @param endDate
	 *            结束日期 YYYYMMDD
	 * @return 间隔天数:有正，有负
	 */
	public static int getDateBetween(int startDate, int endDate) {
		int interval = 0;
		int tempDate = 0;

		int orgStartDate = startDate;
		int orgEndDate = endDate;

		if (startDate > endDate) {
			tempDate = startDate;
			startDate = endDate;
			endDate = tempDate;
		}

		// 平年月的天数
		int _monthDays[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		String _startDate = Integer.toString(startDate);
		String _endDate = Integer.toString(endDate);

		int _startYear = Integer.parseInt(_startDate.substring(0, 4));
		int _startMonth = Integer.parseInt(_startDate.substring(4, 6));
		int _startDay = Integer.parseInt(_startDate.substring(6, 8));

		int _endYear = Integer.parseInt(_endDate.substring(0, 4));
		int _endMonth = Integer.parseInt(_endDate.substring(4, 6));
		int _endDay = Integer.parseInt(_endDate.substring(6, 8));

		// 计算开始年度到当年底的天数
		int _passDay = 0; // 当年已过的天数
		if (_startMonth < 3) {
			for (int index = 0; index < _startMonth - 1; index++) {
				_passDay += _monthDays[index];
			}
			_passDay += _startDay - 1;

			if (isLeapYear(_startYear)) {
				interval = 366 - _passDay;
			} else {
				interval = 365 - _passDay;
			}
		} else {
			for (int index = 0; index < _startMonth - 1; index++) {
				_passDay += _monthDays[index];
			}
			_passDay += _startDay - 1;
			interval = 365 - _passDay;
		}

		// 计算开始下一年度到结束前年度的天数
		if (_startYear == _endYear) { // 同一年
			if (isLeapYear(_startYear)) {
				interval -= 366;
			} else {
				interval = interval - 365;
			}
		}
		_startYear++;
		while (_startYear < _endYear) {
			if (isLeapYear(_startYear)) {
				interval += 366;
			} else {
				interval += 365;
			}
			_startYear++;
		}

		// 计算结束年度的天数
		_passDay = 0;
		if (_endMonth > 2) {
			for (int index = 0; index < _endMonth - 1; index++) {
				_passDay += _monthDays[index];
			}
			_passDay += _endDay;

			if (isLeapYear(_endYear)) {
				interval += (_passDay + 1);
			} else {
				interval += _passDay;
			}
		} else {
			for (int index = 0; index < _endMonth - 1; index++) {
				_passDay += _monthDays[index];
			}
			_passDay += _endDay;

			interval += _passDay;
		}
		if (orgStartDate > orgEndDate) {
			interval = -interval;
		}

		return interval;
	}

	/**
	 * 返回指定天数后的日期
	 * 
	 * @param curDate
	 *            时间起点 YYYYMMDD
	 * @param i
	 *            数天前 (Days < 0); Days 数天后 (Days > 0)
	 * @return 数天后的日期 YYYYMMDD
	 */
	public static int getDateLater(int curDate, int i) {
		int _iCurYear = curDate / 10000; // 获得年度
		int _iCurMonth = (curDate - _iCurYear * 10000) / 100; // 获得月份
		int _iCurDay = (curDate) % 100; // 获得日期
		int _iNextDate = 0;
		if (i >= 0) {
			_iNextDate = after(_iCurYear, _iCurMonth, _iCurDay, i);
		} else {
			_iNextDate = before(_iCurYear, _iCurMonth, _iCurDay, i);
		}

		return _iNextDate;

	}

	/**
	 * 取月末最后一天的日期
	 * 
	 * @param YearMonth
	 *            指定的年月
	 * @return 指定年月的最后一天的日期，格式为YYYYMMDD
	 */
	public static int getDateOfMonthEnd(int YearMonth) {
		int _year = YearMonth / 100;
		int _month = YearMonth % 100;
		int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int _endDay = months[_month];
		// 如果是闰年，二月则多一天
		if (isLeapYear(_year) && _month == 2) {
			_endDay++;
		}
		return YearMonth * 100 + _endDay;
	}

	/**
	 * 月末的最后时刻
	 * 
	 * @param yearMonth
	 *            年月 YYYYMM
	 * @return 月末的最后时刻 YYYYMMdd2359999
	 */
	public static String getDateTimeOfMonthEnd(Integer yearMonth) {
		String _lMonthEndDetailTime = "";
		int _year = Integer.parseInt(yearMonth.toString().substring(0, 4));
		int _month = Integer.parseInt(yearMonth.toString().substring(4, 6));
		int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int _endDay = months[_month];
		// 如果是闰年，二月则多一天
		if (isLeapYear(_year) && _month == 2) {
			_endDay++;
		}
		_lMonthEndDetailTime = yearMonth + "" + _endDay + "" + 235959;
		return _lMonthEndDetailTime;
	}

	/**
	 * 得到某年月的最大天数
	 * 
	 * @param strYearMonth
	 *            "YYYYMM"
	 * @return 天数
	 */
	public static int getDaysMaxOfMonth(String strYearMonth) {
		int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int _iMonth = new Integer(strYearMonth.substring(4, 6));
		int _iYear = new Integer(strYearMonth.substring(0, 4));
		int _maxDay = months[_iMonth];
		// 如果是闰年，二月再增加一天
		if (((_iYear % 4 == 0) && (_iYear % 100 != 0)) || (_iYear % 400 == 0)) {
			if (_iMonth == 2) {
				_maxDay++;
			}
		}
		return _maxDay;
	}

	/**
	 * 计算两个年月之间相隔的月份
	 * 
	 * @param startMonth
	 *            开始的年月(int YYYYMM)
	 * @param endMonth
	 *            结束的年月(int YYYYMM)
	 * @return 计算日期相隔的月份
	 */
	public static int getMonthBetween(int startMonth, int endMonth) {
		int _iYearS = startMonth / 100;
		int _iYearE = endMonth / 100;
		int _iMonthS = startMonth - _iYearS * 100;
		int _iMonthE = endMonth - _iYearE * 100;
		int _iMonthSum = 0;
		int _iYearSum = _iYearE - _iYearS;
		if (_iYearSum == 0) {
			_iMonthSum = _iMonthE - _iMonthS;
		}
		if (_iYearSum >= 1) {
			_iMonthSum = (12 - _iMonthS) + _iMonthE + 12 * (_iYearSum - 1);
		}
		return _iMonthSum;
	}

	/**
	 * 计算若干个月之后的年月
	 * 
	 * @param beginYearMonth
	 *            开始年月
	 * @param month
	 *            经过月数
	 * @return
	 */
	public static int getMonthLater(int beginYearMonth, int month) {
		int _year = beginYearMonth / 100;
		int _month = beginYearMonth - _year * 100;

		if (month > 0) {
			int _tempMon = _month + month; // - 1;
			if (_tempMon > 12) {
				_year = _year + _tempMon / 12;
				_tempMon = _tempMon % 12;
				if (_tempMon == 0) {
					_year = _year - 1;
					_tempMon = 12; // _month;
				}
			}
			return _year * 100 + _tempMon;
		} else if (month < 0) {
			_year = _year - Math.abs(month) / 12;
			int _tempMon = _month - Math.abs(month) % 12;
			if (_tempMon <= 0) {
				_year = _year - 1;
				_tempMon = 12 - Math.abs(_tempMon);
			}
			return _year * 100 + _tempMon;
		} else {
			return beginYearMonth;
		}
	}

	/**
	 * 计算年龄月数
	 * 
	 * @param birthday
	 *            出生日期
	 * @param calcDate
	 *            计算日期
	 * @return
	 */
	public static int getMonthOfAge(int birthday, int calcDate) {
		return TimeUtils.getMonthBetween(birthday / 100, calcDate / 100);
	}

	/**
	 * 返回两个年月之间的年月数组
	 * 
	 * @param begin
	 *            开始年月
	 * @param end
	 *            结束年月
	 * @return
	 */
	public static int[] getMonthPeriod(int begin, int end) {
		List<Integer> list = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		for (int _tmpYearMonth = begin; _tmpYearMonth <= end; _tmpYearMonth = getMonthLater(
				_tmpYearMonth, 1)) {
			list.add(_tmpYearMonth);
		}
		int _ary[] = new int[list.size()];
		for (int i = 0; i < _ary.length; i++) {
			_ary[i] = list.get(i);
		}
		return _ary;
	}

	/**
	 * 获得系统年月日，以字符串"YYYY-MM-DD"返回
	 * 
	 * @return 返回"YYYY-MM-DD"字符串
	 */
	public static String getStrCurrentDate() {
		return timeFormate("yyyy年MM月dd日");
	}

	public static String getStrCurrentDate1() {
		return timeFormate("yyyy-M-d");
	}

	/**
	 * 获得系统天数，以字符串"DD"返回
	 * 
	 * @return 返回"DD"字符串
	 */
	public static String getStrCurrentDay() {
		return timeFormate("dd");
	}

	/**
	 * 获得系统年月日时分秒毫秒，以字符串"YYYY-MM-DD hh:mm:ss.SSS"返回
	 * 
	 * @return 返回"YYYY-MM-DD hh:mm:ss.SSS"字符串
	 */
	public static String getStrCurrentDetailTime() {
		return timeFormate("yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获得系统月份，以字符串"MM"返回
	 * 
	 * @return 返回"MM"字符串
	 */
	public static String getStrCurrentMonth() {
		return timeFormate("MM");
	}

	/**
	 * 获得系统年月日时分秒，以字符串"YYYY-MM-DD hh:mm:ss"返回
	 * 
	 * @return 返回"YYYY-MM-DD hh:mm:ss"字符串
	 */
	public static String getStrCurrentTime() {
		return timeFormate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得系统年份，以字符串"YYYY"返回
	 * 
	 * @return 返回"YYYY"字符串
	 */
	public static String getStrCurrentYear() {
		return timeFormate("yyyy");
	}

	/**
	 * 获得系统年月，以字符串"YYYY-MM"返回
	 * 
	 * @return 返回"YYYY-MM"字符串
	 */
	public static String getStrCurYearMonth() {
		return timeFormate("yyyy年MM月");
	}

	/**
	 * 把HH:mm 转换为HHmm格式
	 * 
	 * @param time
	 *            HHmm
	 * @return HHmm
	 */
	public static String getTime(String time) {
		StringBuffer str = new StringBuffer();
		str.append(time.substring(0, 2));
		str.append(time.substring(3, 5));
		return str.toString();
	}

	/**
	 * 把HHmm时间转换为时间HH:mm
	 * 
	 * @param time
	 *            HH:mm
	 * @return HH:mm
	 */
	public static String getTime1(String time) {
		StringBuffer str = new StringBuffer();
		str.append(time.substring(0, 2)).append(":");
		str.append(time.substring(2, 4));
		return str.toString();
	}

	/**
	 * 是否闰年
	 * 
	 * @param CurYear
	 *            年度
	 * @return True - 是闰年； false - 平年
	 */
	public static boolean isLeapYear(int CurYear) {
		boolean _bIsLeap = false;
		// 判定平年闰年
		if (((CurYear % 4 == 0) && (CurYear % 100 != 0))
				|| (CurYear % 400 == 0)) {
			_bIsLeap = true;
		} else {
			_bIsLeap = false;
		}
		return _bIsLeap;
	}

	/**
	 * 校验时间或者日期是否合法:不需要判断2月是否是润年的天数
	 * 
	 * @param strDate
	 *            "YYYYMMDD" OR "YYYYMMDDhhmmss"
	 * @return true - 合法； false - 非法
	 */
	public static boolean isValidate(String strDate) {
		// 时间参数:年、月、日、时、分、秒
		int _iYear = 0;
		int _iMonth = 0;
		int _iDay = 0;
		int _iHour = 0;
		int _iMinute = 0;
		int _iSecond = 0;
		boolean _bFlag;
		String _strDate = strDate.trim();

		// 合法性的判断
		try {
			_bFlag = Long.parseLong(_strDate) > 0;
		} catch (NumberFormatException ex2) {
			_bFlag = false;
		}
		if (!_bFlag) {
			return _bFlag;
		}

		if (_bFlag) {
			// 将年月日转换为整型
			try {
				_iYear = Integer.parseInt(_strDate.substring(0, 4));
				_iMonth = Integer.parseInt(_strDate.substring(4, 6));
				_iDay = Integer.parseInt(_strDate.substring(6, 8));
			} catch (Exception ex) {
				_bFlag = false;
			}
			if (!_bFlag) {
				return _bFlag;
			}
			// 判断年月是否合法
			_bFlag = (_iYear > 1900) && (_iDay > 0)
					&& ((_iMonth > 0) && (_iMonth < 13));

			// 判定日期是否合法
			if (_bFlag) {
				// 平年时每月的天数
				int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
						31 };
				int _maxDay = months[_iMonth];
				// 如果是闰年，二月再增加一天
				// if (((_iYear % 4 == 0) && (_iYear % 100 != 0)) || (_iYear %
				// 400 == 0)) {
				if (_iMonth == 2) {
					_maxDay++;
				}
				// }
				_bFlag = (_iDay <= _maxDay);
			}

			if (_bFlag && _strDate.length() == 14) {
				// 当字符串中含有时间串(hh:mm:ss)
				try {
					_iHour = Integer.parseInt(_strDate.substring(8, 10));
					_iMinute = Integer.parseInt(_strDate.substring(10, 12));
					_iSecond = Integer.parseInt(_strDate.substring(12, 14));
				} catch (Exception ex1) {
					_bFlag = false;
				}
				if (!_bFlag) {
					return _bFlag;
				}

				// 检测时分秒
				_bFlag = ((_iHour >= 0) && (_iHour <= 23))
						&& ((_iMinute >= 0) && (_iMinute <= 59))
						&& ((_iSecond >= 0) && (_iSecond <= 59));
			}
		}
		return _bFlag;
	}

	/**
	 * 把整数型日期转换成字符串形式返回
	 * 
	 * @param dateint
	 *            日期
	 * @return
	 */
	public static String spliteWith(int dateint, char spliter) {
		String dateStr = dateint + "";
		return dateStr.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1" + spliter
				+ "$2" + spliter + "$3");
	}

	/**
	 * 将时间转换为hh:mm格式
	 * 
	 * @param time
	 *            "yyyMMdd/hhmm"
	 * @return
	 */
	public static String time2Str(String time) {
		String tempStr = "";
		if (time != null && !"".equals(time)) {
			tempStr = time.substring(time.indexOf('/') + 1, time.length());
			tempStr = tempStr.substring(0, 2) + ":" + tempStr.substring(2, 4);
		}
		return tempStr;
	}

	// 日期格式
	private synchronized static String timeFormate(String format) {
		SimpleDateFormat _dateFormat = new SimpleDateFormat(format);
		// 获得当前的时间
		Date _currentDate = Calendar.getInstance().getTime();
		return _dateFormat.format(_currentDate);
	}

	/**
	 * 将XLM通用时间日期型转换为日期 2010-01-12T13:00:00+08:00 -> 20100112
	 * 
	 * @param dateTime
	 *            String
	 * @return int
	 */
	public static String xmlDateTime2Date(String dateTime) {
		// 2010-01-12T13:00:00+08:00
		return dateTime.replaceFirst("T.*", "").replace("-", "");
	}

	public static int getCurrentUnixTimestamp() {
		Date date = new Date();
		return (int) (date.getTime() / 1000);
	}

	/**
	 * 将UTC-second类型时间转化为hh:mm:ss格式
	 * 
	 * @return
	 */
	public String getStringTime(int nowtime) {
		int hour = (nowtime % 86400) / 3600;
		int min = (nowtime % 3600) / 60;
		int second = nowtime % 60;
		return hour + ":" + (min < 10 ? "0" + min : min) + ":"
				+ (second < 10 ? "0" + second : second);
	}
}
