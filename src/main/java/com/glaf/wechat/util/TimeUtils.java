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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TimeUtils {
    // �����������ʱ��
    private static int after(int curYear, int curMonth, int curDay, int i) {
        int _iCurYear = curYear;
        int _iCurMonth = curMonth;
        int _iCurDay = curDay;
        int _iDays = i;
        //ƽ���µ�����
        int _aNormMonthDays[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //�����µ�����
        int _aLeapMonthDays[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] _aMonthDays = new int[13];
        //�����¸���ȵ��·�
        while (_iDays != 0) {
            //�Ƿ�����
            if (isLeapYear(_iCurYear)) {
                _aMonthDays = _aLeapMonthDays;
            } else {
                _aMonthDays = _aNormMonthDays;
            }

            //�Ƿ��ڵ�ǰ����
            int _iDescDays = _aMonthDays[_iCurMonth] - _iCurDay;
            if (_iDays > _iDescDays) {
                _iDays = _iDays - _iDescDays - 1;
                //�趨���µ�������
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
        //ƽ���µ�����
        int _aNormMonthDays[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //�����µ�����
        int _aLeapMonthDays[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] _aMonthDays = new int[13];
        //�����¸���ȵ��·�
        while (_iDays != 0) {
            //�Ƿ��ڵ�ǰ����
            int _iDescDays = _iCurDay - 1;
            if (_iDays > _iDescDays) {
                _iDays = _iDays - _iDescDays - 1;
                //�趨���µ�������
                if (_iCurMonth > 1) {
                    _iCurMonth--;
                } else {
                    _iCurMonth = 12;
                    _iCurYear--;
                }
                //�Ƿ�����
                if (isLeapYear(_iCurYear)) {
                    _aMonthDays = _aLeapMonthDays;
                } else {
                    _aMonthDays = _aNormMonthDays;
                }
                //���µ���ĩ����
                _iCurDay = _aMonthDays[_iCurMonth];
            } else {
                _iCurDay = _iCurDay - _iDays;
                _iDays = 0;
            }
        }

        return _iCurYear * 10000 + _iCurMonth * 100 + _iCurDay;
    }

    public static String changeDateFormat(String dateStr) throws ParseException{
		Date d = new SimpleDateFormat("ddMMMyy",Locale.US).parse(dateStr);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(d);
	}

    /**
     * У��ʱ���Ƿ�Ϸ�
     *
     * @param curTime ¼��ʱ�� HHMMSS �� HMMSS
     * @return ʱ��Ϸ��򷵻�true�����Ϸ�����false
     */
    public static boolean checkTime(int curTime) {
        //ʱ��ķ�Χ
        if ((curTime > 235959) || (curTime < 0)) {
            return false;
        }

        //��ò�ͬ�ε�ʱ��
        int iHour = curTime / 10000; //Сʱ
        int iMinute = (curTime - (iHour * 10000)) / 100; //����
        int iSecond = curTime % 100; //��

        //��Сʱ����У��
        if ((iHour < 0) || (iHour > 23)) {
            return false;
        }
        //�Է��ӽ���У��
        if ((iMinute < 0) || (iMinute > 59)) {
            return false;
        }
        //�����ӽ���У��
        if ((iSecond < 0) || (iSecond > 59)) {
            return false;
        }


        return true;
    }

    /**
     * У��ʱ����������Ƿ�Ϸ�
     *
     * @param strDate "YYYYMMDD" OR "YYYYMMDDhhmmss"
     * @return true - �Ϸ��� false - �Ƿ�
     */
    public static boolean checkTimeStr(String strDate) {
        //ʱ�����:�ꡢ�¡��ա�ʱ���֡���
        int _iYear = 0;
        int _iMonth = 0;
        int _iDay = 0;
        int _iHour = 0;
        int _iMinute = 0;
        int _iSecond = 0;
        boolean _bFlag = false;
        String _strDate = strDate.trim();

        //�Ϸ��Ե��ж�
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

        //��������ת��Ϊ����
        try {
            _iYear = Integer.parseInt(_strDate.substring(0, 4));
            _iMonth = Integer.parseInt(_strDate.substring(4, 6));
            _iDay = Integer.parseInt(_strDate.substring(6, 8));
        } catch (Exception ex) {
            _bFlag = false;
            return _bFlag;
        }

        //�ж������Ƿ�Ϸ�
        if ((_iYear > 1900) && (_iDay > 0) && ((_iMonth > 0) && (_iMonth < 13))) {
            _bFlag = true;
        } else {
            _bFlag = false;
        }

        //�ж������Ƿ�Ϸ�
        if (_bFlag) {
            //ƽ��ʱÿ�µ�����
            int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int _maxDay = months[_iMonth];
            //��������꣬����������һ��
            if (((_iYear % 4 == 0) && (_iYear % 100 != 0)) || (_iYear % 400 == 0)) {
                if (_iMonth == 2) {
                    _maxDay++;
                }
            }
            _bFlag = (_iDay <= _maxDay);
        }

        if ((_bFlag) && (_strDate.length() == 14)) {
            //���ַ����к���ʱ�䴮(hh:mm:ss)
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

            //���ʱ����
            if (((_iHour >= 0) && (_iHour <= 23)) && ((_iMinute >= 0)
                    && (_iMinute <= 59)) && ((_iSecond >= 0) && (_iSecond <= 59))) {
                _bFlag = true;
            } else {
                _bFlag = false;
            }
        }

        return _bFlag;
    }

    /**
     * �����ڸ�ʽ��������¸�ʽ
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
     * ת��������Ϊ�ַ�������
     *
     * @param iDate ����YYYYMMDD
     * @return �ַ���YYYY-MM-DD
     */
    public static String date2Str(int iDate) {
        String _strDate = null;
        _strDate = String.valueOf(iDate);
        if (_strDate.length() == 8) {
            String _strYear = _strDate.substring(0, 4);
            String _strMonth = _strDate.substring(4, 6);
            String _strDay = _strDate.substring(6, 8);
            //���ϳ�YYYY��MM��DD�մ�
            _strDate = _strYear + "-" + _strMonth + "-" + _strDay;
        } else if (_strDate.length() == 6) {
            String _strYear = _strDate.substring(0, 4);
            String _strMonth = _strDate.substring(4, 6);
            //���ϳ�YYYY��MM�´�
            _strDate = _strYear + "-" + _strMonth;

        }
        return _strDate;
    }

    /**
     * ת��������Ϊ�ַ�������
     *
     * @param iDate ����YYYYMMDD
     * @return �ַ���YYYY-MM-DD
     */
    public static String date2Str(String iDate) {
        String _strDate = null;
        _strDate = iDate;
        if (_strDate.length() == 8) {
            String _strYear = _strDate.substring(0, 4);
            String _strMonth = _strDate.substring(4, 6);
            String _strDay = _strDate.substring(6, 8);
            //���ϳ�YYYY��MM��DD�մ�
            _strDate = _strYear + "-" + _strMonth + "-" + _strDay;
        } else if (_strDate.length() == 6) {
            String _strYear = _strDate.substring(0, 4);
            String _strMonth = _strDate.substring(4, 6);
            //���ϳ�YYYY��MM�´�
            _strDate = _strYear + "-" + _strMonth;

        }
        return _strDate;
    }

    /**
     * ת��������Ϊ�ַ�������
     *
     * @param iDate ����YYYYMMDD
     * @return �ַ���YYYY/MM/DD
     */
    public static String date2Str2(int iDate) {
        String _strDate = null;
        _strDate = String.valueOf(iDate);
        if (_strDate.length() == 8) {
            String _strYear = _strDate.substring(0, 4);
            String _strMonth = _strDate.substring(4, 6);
            String _strDay = _strDate.substring(6, 8);
            //���ϳ�YYYY/MM/DD��
            _strDate = _strYear + "/" + _strMonth + "/" + _strDay;
        } else if (_strDate.length() == 6) {
            String _strYear = _strDate.substring(0, 4);
            String _strMonth = _strDate.substring(4, 6);
            //���ϳ�YYYY/MM��
            _strDate = _strYear + "/" + _strMonth;

        }
        return _strDate;
    }

    /**
     * ת��������Ϊ�ַ�������
     *
     * @param iDate ����YYYYMMDD
     * @return �ַ���YYYY/MM/DD
     */
    public static String date2Str2(String iDate) {
        if (iDate.length() == 8) {
            String _strYear = iDate.substring(0, 4);
            String _strMonth = iDate.substring(4, 6);
            String _strDay = iDate.substring(6, 8);
            //���ϳ�YYYY/MM/DD��
            iDate = _strYear + "/" + _strMonth + "/" + _strDay;
        } else if (iDate.length() == 6) {
            String _strYear = iDate.substring(0, 4);
            String _strMonth = iDate.substring(4, 6);
            //���ϳ�YYYY/MM��
            iDate = _strYear + "/" + _strMonth;
        }
        return iDate;
    }

    /**
     * ����һ��int������ת����һ�����ĵ�����
     * ����20050328
     * �����OO�������¶�ʮ����
     *
     * @param iDate int
     * @return String
     */
    public static String date2StrCHS(int iDate) {
        String result = "";
        String[] chineseNumber = {"0", "һ", "��", "��", "��", "��", "��", "��", "��", "��"};
        char[] cDate = String.valueOf(iDate).toCharArray();
        StringBuffer tempDate = new StringBuffer("");
        for (char aCDate : cDate) {
            int chatat = Integer.parseInt(String.valueOf(aCDate));
            tempDate.append(chineseNumber[chatat]);
        }
        result = tempDate.substring(0, 4) + "��";
        int chatM = Integer.parseInt(String.valueOf(cDate[4]));
        if (chatM != 0) {
            if ("0".equals(tempDate.substring(5, 6))) {
                result += "ʮ��";
            } else {
                result += "ʮ" + tempDate.substring(5, 6) + "��";
            }
        } else {
            result += tempDate.substring(5, 6) + "��";
        }
        int chatd = Integer.parseInt(String.valueOf(cDate[6]));
        if (chatd == 0) {
            result += tempDate.substring(7, 8) + "��";
        } else if (chatd == 1) {
            if ("0".equals(tempDate.substring(7, 8))) {
                result += "ʮ��";
            } else {
                result += "ʮ" + tempDate.substring(7, 8) + "��";
            }
        } else {
            if ("0".equals(tempDate.substring(7, 8))) {
                result += tempDate.substring(6, 7) + "ʮ��";
            } else {
                result += tempDate.substring(6, 7) + "ʮ" + tempDate.substring(7, 8) + "��";
            }
        }
        return result;
    }

    /**
     * ת��������Ϊ�ַ�������
     *
     * @param iDate ����YYYYMMDD
     * @return �ַ���YYYY��MM��DD��
     */
    public static String date2StrCN(int iDate) {
        String _strDate = null;
        _strDate = String.valueOf(iDate);
        if (_strDate.length() == 8) {
            String _strYear = _strDate.substring(0, 4);
            String _strMonth = _strDate.substring(4, 6);
            String _strDay = _strDate.substring(6, 8);
            //���ϳ�YYYY��MM��DD�մ�
            _strDate = _strYear + "��" + _strMonth + "��" + _strDay + "��";
        } else if (_strDate.length() == 6) {
            String _strYear = _strDate.substring(0, 4);
            String _strMonth = _strDate.substring(4, 6);
            //���ϳ�YYYY��MM�´�
            _strDate = _strYear + "��" + _strMonth + "��";

        }
        return _strDate;
    }

    /**
     * ת���ַ�������Ϊ����������
     *
     * @param strDate "YYYY-MM-DD"
     * @return ��������
     */
    public static int dateFromString(String strDate) {
        int intYear = Integer.parseInt(strDate.substring(0, 4));
        int intMonth = Integer.parseInt(strDate.substring(5, 7));
        int intDay = Integer.parseInt(strDate.substring(8, 10));
        return intYear * 10000 + intMonth * 100 + intDay;
    }

    /**
     * ��ʱ���ʽ��������ڸ�ʽ
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
	 * ��ʱ���ʽ��������¸�ʽ
	 * 
	 * @param datetime
	 * @return
	 */
	public static int dateTime2Month(long datetime) {
		return dateTime2Date(datetime) / 100;
	}

    /**
     * ת��������ʱ��Ϊ�ַ�������ʱ��
     *
     * @param iDateTime ����YYMMDDhhmmss
     * @return �ַ��� YY-MM-DD hh:mm:ss
     */
    public static String dateTime2Str(long iDateTime) {
    	String _strDateTimeTemp = iDateTime + "";
    	//����date2Str(int iDate)�������YY-MM-DD��
    	String _strDate = date2Str(Integer.parseInt(_strDateTimeTemp.substring(0, 8)));
    	String _strTimeTemp = _strDateTimeTemp.substring(8, 14) + "";
        String _strHour = _strTimeTemp.substring(0, 2);
        String _strMinute = _strTimeTemp.substring(2, 4);
        String _strSecond = _strTimeTemp.substring(4, 6);
        //���ϳ� hh:ss:mm ��
        String _strTime = _strHour + ":" + _strMinute + ":" + _strSecond;
        return _strDate + " " + _strTime;
    }

    /**
     * ���ص�ǰ���ڵ�ǰ(��)ʱ��
     * @param minute  15 ���� 
     * @return
     */
	public static long dateTimeBeforeMinute(long minute) {
		SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date currentTime = new Date();
		long lDate = currentTime.getTime() + (1000L * 60L * minute);
		currentTime = new Date(lDate);
		return  Long.parseLong(longDateFormat.format(currentTime));
	}

    public static String formatDate1(String date) throws ParseException{
		Date d = new SimpleDateFormat("yyyy-M-d HH:mm:ss").parse(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(d);
    }

    /**
	 * 
	 * @param date 2011-02-28 14:37:40
	 * @return 20110228143740
	 */
	public static Long formatStringToLong(String date){
		return Long.parseLong(date.replace("-", "").replace(" ", "").replace(":", ""));
	}

    /**
     * ���ϵͳ�����գ�����"YYYYMMDD"����
     *
     * @return ����"YYYYMMDD"����
     */
    public static Integer getCurrentDate() {
        return Integer.parseInt(timeFormate("yyyyMMdd"));
    }

    /**
     * ���ϵͳ������������"DD"����
     *
     * @return ����"DD"����
     */
    public static int getCurrentDay() {
        return Integer.parseInt(timeFormate("dd"));
    }
    
    
    /**
     * ���ϵͳ������ʱ������룬�Գ�����"YYYYMMDDhhmmssSSS"����
     *
     * @return ����"YYYYMMDDhhmmssSSS"����
     */
    public static long getCurrentDetailTime() {
        return Long.parseLong(timeFormate("yyyyMMddHHmmssSSS"));
    }
    
    /**
     * ���ϵͳʱ������룬������"hhmmssSSS"����
     *
     * @return ����"hhmmssSSS"����
     */
    public static int getCurrentHmsSTime() {
        return Integer.parseInt(timeFormate("HHmmssSSS"));
    } 
    
    
    /**
     * ���ϵͳʱ���룬������"hhmmss"����
     *
     * @return ����"hhmmssSSS"����
     */
    public static int getCurrentHmsTime() {
        return Integer.parseInt(timeFormate("HHmmss"));
    }
    

    /**
     * ���ϵͳʱ�֣�������"hhmm"����
     *
     * @return ����"hhmm"����
     */
    public static Integer getCurrentHmTime() {
        return Integer.parseInt(timeFormate("HHmm"));
    }

    /**
     * ���ϵͳ�·ݣ�������"MM"����
     *
     * @return ����"MM"����
     */
    public static int getCurrentMonth() {
        return Integer.parseInt(timeFormate("MM"));
    }

    /**
     * ���ϵͳ������ʱ���룬������"YYYYMMDDhhmmss"����
     *
     * @return ����"YYYYMMDDhhmmss"����
     */
    public static long getCurrentTime() {
        return Long.parseLong(timeFormate("yyyyMMddHHmmss"));
    }

    /**
     * ���ϵͳ��ݣ�������"YYYY"����
     *
     * @return ����"YYYY"����
     */
    public static int getCurrentYear() {
        return Integer.parseInt(timeFormate("yyyy"));
    }

    /**
     * ���ϵͳ���£�����"YYYYMM"����
     *
     * @return ����"YYYYMM"����
     */
    public static int getCurrentYearMonth() {
        return Integer.parseInt(timeFormate("yyyyMM"));
    }

    //���������ǰ��ʱ��

    /**
     * ��ȡϵͳ���ڼ�
     *
     * @return  
     */
    public static String getCurrenWeekday() {
        String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(Calendar.getInstance().getTime());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * ��YYYY/MM/DD ת��ΪYYYYMMDD��ʽ
     * @param date YYYYDDMM
     * @return YYYYDDMM
     */
    public static String getDate(String date){
    	StringBuffer str = new StringBuffer();
    	str.append(date.substring(0, 4));
    	str.append(date.substring(5, 7));
    	str.append(date.substring(8, 10));
    	return str.toString();
    }

    /**
     * ��YYYYMMDD ת��ΪYYYY/MM/DD��ʽ
     * @param date YYYY/DD/MM
     * @return YYYY/DD/MM
     */
    public static String getDate1(String date){
    	StringBuffer str = new StringBuffer();
    	str.append(date.substring(0, 4)).append("/");
    	str.append(date.substring(4, 6)).append("/");
    	str.append(date.substring(6, 8));
    	return str.toString();
    }

    /**
     * ��ȡ�������ڼ������
     *
     * @param startDate ��ʼ���� YYYYMMDD
     * @param endDate   �������� YYYYMMDD
     * @return �������:�������и�
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

        //ƽ���µ�����
        int _monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        String _startDate = Integer.toString(startDate);
        String _endDate = Integer.toString(endDate);

        int _startYear = Integer.parseInt(_startDate.substring(0, 4));
        int _startMonth = Integer.parseInt(_startDate.substring(4, 6));
        int _startDay = Integer.parseInt(_startDate.substring(6, 8));

        int _endYear = Integer.parseInt(_endDate.substring(0, 4));
        int _endMonth = Integer.parseInt(_endDate.substring(4, 6));
        int _endDay = Integer.parseInt(_endDate.substring(6, 8));

        //���㿪ʼ��ȵ�����׵�����
        int _passDay = 0; //�����ѹ�������
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

        //���㿪ʼ��һ��ȵ�����ǰ��ȵ�����
        if (_startYear == _endYear) { //ͬһ��
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

        //���������ȵ�����
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
     * ����ָ�������������
     *
     * @param curDate ʱ����� YYYYMMDD
     * @param i       ����ǰ (Days < 0); Days ����� (Days > 0)
     * @return ���������� YYYYMMDD
     */
    public static int getDateLater(int curDate, int i) {
        int _iCurYear = curDate / 10000; //������
        int _iCurMonth = (curDate - _iCurYear * 10000) / 100; //����·�
        int _iCurDay = (curDate) % 100; //�������
        int _iNextDate = 0;
        if (i >= 0) {
            _iNextDate = after(_iCurYear, _iCurMonth, _iCurDay, i);
        } else {
            _iNextDate = before(_iCurYear, _iCurMonth, _iCurDay, i);
        }

        return _iNextDate;

    }

    /**
     * ȡ��ĩ���һ�������
     *
     * @param YearMonth ָ��������
     * @return ָ�����µ����һ������ڣ���ʽΪYYYYMMDD
     */
    public static int getDateOfMonthEnd(int YearMonth) {
        int _year = YearMonth / 100;
        int _month = YearMonth % 100;
        int[] months = {
                0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        int _endDay = months[_month];
        //��������꣬�������һ��
        if (isLeapYear(_year) && _month == 2) {
            _endDay++;
        }
        return YearMonth * 100 + _endDay;
    }

    /**
     * ��ĩ�����ʱ��
     *
     * @param yearMonth ���� YYYYMM
     * @return ��ĩ�����ʱ�� YYYYMMdd2359999
     */
    public static String getDateTimeOfMonthEnd(Integer yearMonth) {
        String _lMonthEndDetailTime = "";
        int _year = Integer.parseInt(yearMonth.toString().substring(0, 4));
        int _month = Integer.parseInt(yearMonth.toString().substring(4, 6));
        int[] months = {
                0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        int _endDay = months[_month];
        //��������꣬�������һ��
        if (isLeapYear(_year) && _month == 2) {
            _endDay++;
        }
        _lMonthEndDetailTime = yearMonth +""+_endDay +""+ 235959;
        return _lMonthEndDetailTime;
    }

    /**
     * �õ�ĳ���µ��������
     *
     * @param strYearMonth "YYYYMM"
     * @return ����
     */
    public static int getDaysMaxOfMonth(String strYearMonth) {
        int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int _iMonth = new Integer(strYearMonth.substring(4, 6));
        int _iYear = new Integer(strYearMonth.substring(0, 4));
        int _maxDay = months[_iMonth];
        //��������꣬����������һ��
        if (((_iYear % 4 == 0) && (_iYear % 100 != 0)) || (_iYear % 400 == 0)) {
            if (_iMonth == 2) {
                _maxDay++;
            }
        }
        return _maxDay;
    }

    /**
     * ������������֮��������·�
     *
     * @param startMonth ��ʼ������(int YYYYMM)
     * @param endMonth   ����������(int YYYYMM)
     * @return ��������������·�
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
     * �������ɸ���֮�������
     *
     * @param beginYearMonth ��ʼ����
     * @param month          ��������
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
                    _tempMon = 12; //_month;
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
     * ������������
     *
     * @param birthday ��������
     * @param calcDate ��������
     * @return
     */
    public static int getMonthOfAge(int birthday, int calcDate) {
        return TimeUtils.getMonthBetween(birthday / 100, calcDate / 100);
    }

    /**
     * ������������֮�����������
     *
     * @param begin ��ʼ����
     * @param end   ��������
     * @return
     */
    public static int[] getMonthPeriod(int begin, int end) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int _tmpYearMonth = begin; _tmpYearMonth <= end;
             _tmpYearMonth = getMonthLater(_tmpYearMonth, 1)) {
            list.add(_tmpYearMonth);
        }
        int _ary[] = new int[list.size()];
        for (int i = 0; i < _ary.length; i++) {
            _ary[i] = list.get(i);
        }
        return _ary;
    }

    /**
     * ���ϵͳ�����գ����ַ���"YYYY-MM-DD"����
     *
     * @return ����"YYYY-MM-DD"�ַ���
     */
    public static String getStrCurrentDate() {
        return timeFormate("yyyy��MM��dd��");
    }


    public static String getStrCurrentDate1() {
        return timeFormate("yyyy-M-d");
    }

    /**
     * ���ϵͳ���������ַ���"DD"����
     *
     * @return ����"DD"�ַ���
     */
    public static String getStrCurrentDay() {
        return timeFormate("dd");
    }

    /**
     * ���ϵͳ������ʱ������룬���ַ���"YYYY-MM-DD hh:mm:ss.SSS"����
     *
     * @return ����"YYYY-MM-DD hh:mm:ss.SSS"�ַ���
     */
    public static String getStrCurrentDetailTime() {
        return timeFormate("yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * ���ϵͳ�·ݣ����ַ���"MM"����
     *
     * @return ����"MM"�ַ���
     */
    public static String getStrCurrentMonth() {
        return timeFormate("MM");
    }
    
    /**
     * ���ϵͳ������ʱ���룬���ַ���"YYYY-MM-DD hh:mm:ss"����
     *
     * @return ����"YYYY-MM-DD hh:mm:ss"�ַ���
     */
    public static String getStrCurrentTime() {
        return timeFormate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * ���ϵͳ��ݣ����ַ���"YYYY"����
     *
     * @return ����"YYYY"�ַ���
     */
    public static String getStrCurrentYear() {
        return timeFormate("yyyy");
    }
    
    /**
     * ���ϵͳ���£����ַ���"YYYY-MM"����
     *
     * @return ����"YYYY-MM"�ַ���
     */
    public static String getStrCurYearMonth() {
        return timeFormate("yyyy��MM��");
    }
    
    /**
     * ��HH:mm ת��ΪHHmm��ʽ
     * @param time HHmm
     * @return HHmm
     */
    public static String getTime(String time){
    	StringBuffer str = new StringBuffer();
    	str.append(time.substring(0, 2));
    	str.append(time.substring(3, 5));
    	return str.toString();
    }
    /**
     * ��HHmmʱ��ת��Ϊʱ��HH:mm
     * @param time HH:mm
     * @return HH:mm
     */
    public static String getTime1(String time){
    	StringBuffer str = new StringBuffer();
    	str.append(time.substring(0, 2)).append(":");
    	str.append(time.substring(2, 4));
    	return str.toString();
    }
    
    /**
     * �Ƿ�����
     *
     * @param CurYear ���
     * @return True - �����ꣻ false - ƽ��
     */
    public static boolean isLeapYear(int CurYear) {
        boolean _bIsLeap = false;
        //�ж�ƽ������
        if (((CurYear % 4 == 0) && (CurYear % 100 != 0)) || (CurYear % 400 == 0)) {
            _bIsLeap = true;
        } else {
            _bIsLeap = false;
        }
        return _bIsLeap;
    }
    
    /**
     * У��ʱ����������Ƿ�Ϸ�:����Ҫ�ж�2���Ƿ������������
     *
     * @param strDate "YYYYMMDD" OR "YYYYMMDDhhmmss"
     * @return true - �Ϸ��� false - �Ƿ�
     */
    public static boolean isValidate(String strDate) {
        //ʱ�����:�ꡢ�¡��ա�ʱ���֡���
        int _iYear = 0;
        int _iMonth = 0;
        int _iDay = 0;
        int _iHour = 0;
        int _iMinute = 0;
        int _iSecond = 0;
        boolean _bFlag;
        String _strDate = strDate.trim();

        //�Ϸ��Ե��ж�
        try {
            _bFlag = Long.parseLong(_strDate) > 0;
        } catch (NumberFormatException ex2) {
            _bFlag = false;
        }
        if (!_bFlag) {
            return _bFlag;
        }

        if (_bFlag) {
            //��������ת��Ϊ����
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
            //�ж������Ƿ�Ϸ�
            _bFlag = (_iYear > 1900) && (_iDay > 0) && ((_iMonth > 0) && (_iMonth < 13));

            //�ж������Ƿ�Ϸ�
            if (_bFlag) {
                //ƽ��ʱÿ�µ�����
                int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                int _maxDay = months[_iMonth];
                //��������꣬����������һ��
                //  if (((_iYear % 4 == 0) && (_iYear % 100 != 0)) || (_iYear % 400 == 0)) {
                if (_iMonth == 2) {
                    _maxDay++;
                }
                // }
                _bFlag = (_iDay <= _maxDay);
            }

            if (_bFlag && _strDate.length() == 14) {
                //���ַ����к���ʱ�䴮(hh:mm:ss)
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

                //���ʱ����
                _bFlag = ((_iHour >= 0) && (_iHour <= 23)) && ((_iMinute >= 0)
                        && (_iMinute <= 59)) && ((_iSecond >= 0) && (_iSecond <= 59));
            }
        }
        return _bFlag;
    }
    /**
     * ������������ת�����ַ�����ʽ����
     *
     * @param dateint ����
     * @return
     */
    public static String spliteWith(int dateint, char spliter) {
        String dateStr = dateint + "";
        return dateStr.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1" + spliter + "$2" + spliter + "$3");
    }
    
    /**
     * ��ʱ��ת��Ϊhh:mm��ʽ
     * @param time "yyyMMdd/hhmm"
     * @return
     */
    public static String time2Str(String time)
    {	String tempStr = "";
    	if(time!=null && !"".equals(time))
    	{
    	tempStr = time.substring(time.indexOf('/')+1,time.length());
    	tempStr = tempStr.substring(0,2)+":"+tempStr.substring(2,4);
    	}
    	return tempStr;
    }
	
	// ���ڸ�ʽ
    private synchronized static String timeFormate(String format) {
        SimpleDateFormat _dateFormat = new SimpleDateFormat(format);
        // ��õ�ǰ��ʱ��
        Date _currentDate = Calendar.getInstance().getTime();
        return _dateFormat.format(_currentDate);
    }
	
	/**
     * ��XLMͨ��ʱ��������ת��Ϊ����
     * 2010-01-12T13:00:00+08:00 -> 20100112
     * @param dateTime String
     * @return int
     */
    public static String xmlDateTime2Date(String dateTime) {
        // 2010-01-12T13:00:00+08:00
        return dateTime.replaceFirst("T.*", "").replace("-", "");
    }
	
	/**
     * ��UTC-second����ʱ��ת��Ϊhh:mm:ss��ʽ
     * @return
     */
    public String getStringTime(int nowtime) {
     int hour = (nowtime % 86400) / 3600;
     int min = (nowtime % 3600) / 60;
     int second = nowtime % 60;
     return hour + ":" + (min < 10 ? "0" + min : min) + ":" + (second < 10 ? "0" + second : second);
    }
}
