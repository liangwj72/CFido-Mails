package com.cfido.snapshot.mvc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * 和时间相关的一些工具
 * 
 * @author liangwj
 * 
 */
public class DateUtil {

	/**
	 * 将一个时间长度（单位：秒）。 变成 1天3小时3分40秒 的表示方法，
	 * 
	 * @param second
	 * @return
	 */
	public static String getDisplayTimeFromSecond(int second) {
		return getDisplayTimeStr(second, TimeUnit.SECONDS, new String[] {
				"秒", "分钟", "小时"
		});
	}

	/**
	 * 将一个时间长度（单位：秒）。 变成 1天3小时3分40秒 的表示方法，
	 * 
	 * @param second
	 * @return
	 */
	public static String getDisplayTimeFromMin(int min) {
		return getDisplayTimeStr(min, TimeUnit.MINUTES, new String[] {
				"秒", "分", "小时"
		});
	}

	/**
	 * 将一个时间长度（单位：秒）。 变成 1天3小时3分40秒 的表示方法，
	 * 
	 * @param second
	 * @param timeUnitStr
	 *            时间单位的数组，长度为4
	 * @return
	 */
	public static String getDisplayTimeStr(int duration, TimeUnit timeUnit, String[] timeUnitStr) {
		if (timeUnitStr.length != 3) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		long second = timeUnit.toSeconds(duration);
		long s = second % 60;
		if (s > 0) {
			sb.append(s).append(timeUnitStr[0]);
		}

		long remain = second / 60;
		if (remain > 0) {
			long m = remain % 60;
			if (m > 0) {
				sb.insert(0, timeUnitStr[1]);
				sb.insert(0, m);
			}
			remain = remain / 60;
			if (remain > 0) {
				sb.insert(0, timeUnitStr[2]);
				sb.insert(0, remain);
			}
		}
		return sb.toString();
	}

	/**
	 * 是否是闰年
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isLeapYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		GregorianCalendar gc = new GregorianCalendar();
		return gc.isLeapYear(year);
	}

	/**
	 * 将时间变为当天的00:00:00 或者 23:59:59，主要用于sql查询时的条件
	 * 
	 * @param millis
	 *            unix型时间
	 * @param begin
	 *            为true时变成00:00:00，否则为23:59:59
	 * @return
	 */
	public static long ceilTimeToDay(long millis, boolean begin) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		if (!begin) {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 99);
		}

		return calendar.getTimeInMillis();
	}

	/**
	 * 将时间变为当天的00:00:00 或者 23:59:59，主要用于sql查询时的条件
	 * 
	 * @param date
	 * @param begin
	 *            为true时变成00:00:00，否则为23:59:59
	 * @return
	 */
	public static Date ceilDateToDay(Date date, boolean begin) {
		long time = ceilTimeToDay(date.getTime(), begin);
		return new Date(time);
	}
	
	/**
	 * 返回当月最后一天日期
	 * 
	 * @param today
	 * @return
	 */
	public static String getMonthLastDate(String today, String format) {
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String tmp = changeStrTimeFormat(today, format, "yyyyMMdd");
			int year = Integer.parseInt(tmp.substring(0, 4));
			int month = Integer.parseInt(tmp.substring(4, 6));
			if (month == 12) {
				year = year + 1;
				month = 0;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, 1, 0, 0, 0);
			result = sdf.format(new Date(
					calendar.getTime().getTime() - 1000 * 60 * 60));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 返回上一个月最后一天日期
	 * 
	 * @param today
	 * @return
	 */
	public static String getPreMonthLastDate(String today, String format) {
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String tmp = changeStrTimeFormat(today, format, "yyyyMMdd");
			int year = Integer.parseInt(tmp.substring(0, 4));
			int month = Integer.parseInt(tmp.substring(4, 6));
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, 1, 0, 0, 0);
			result = sdf.format(new Date(
					calendar.getTime().getTime() - 1000 * 60 * 60));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 返回下一个月第一天日期
	 * 
	 * @param today
	 * @return
	 */
	public static String getNextMonthFirstDate(String today, String format) {
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String tmp = changeStrTimeFormat(today, format, "yyyyMMdd");
			int year = Integer.parseInt(tmp.substring(0, 4));
			int month = Integer.parseInt(tmp.substring(4, 6));
			if (month == 12) {
				year = year + 1;
				month = 0;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, 1, 0, 0, 0);
			result = sdf.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 返回本一个月第一天日期
	 * 
	 * @param today
	 * @return
	 */
	public static String getFirstDateOfTheMonth(String today, String format) {
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String tmp = changeStrTimeFormat(today, format, "yyyyMMdd");
			int year = Integer.parseInt(tmp.substring(0, 4));
			int month = Integer.parseInt(tmp.substring(4, 6));
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, 1, 0, 0, 0);
			result = sdf.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 格式日期转换
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateFormat(Date date, String format) {
		String result = null;
		try {
			if(date == null)
				result = "";
			else{
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 字符串日期转换成Date型日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date strTimeToDate(String date, String format) {
		Date result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 字符串日期格式转换
	 * 
	 * @param date
	 * @param oldFormat
	 * @param newFormat
	 * @return
	 */
	public static String changeStrTimeFormat(String date, String oldFormat,
			String newFormat) {
		String result = null;
		try {
			if (date == null || date.equals(""))
				return "";
			else {
				SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
				Date tmp = sdf.parse(date);
				sdf.applyPattern(newFormat);
				result = sdf.format(tmp);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if (result == null) {
			return "";
		}
		return result;
	}
	/**
	 * 得到当前日期
	**/
	public static String getCurDate( String dateFormat ) 
	{
		java.text.SimpleDateFormat sdf = 
			new java.text.SimpleDateFormat(dateFormat);
		Calendar c1 = Calendar.getInstance(); // today
		return sdf.format(c1.getTime());
	}
	
	/**
	 * 计算从date开始n天以前（以后）的日期
	 * @param date
	 * @param dateCnt
	 * @return
	 */
	public static Date getDateRelateToDate(Date date, int dateCnt){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dateCnt);
		return calendar.getTime();
	}
	
	/**
	 * 获取两个日期之间的天数间隔
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getDaysBetween(Date startDate, Date endDate) {  
        Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);  
  
        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);  
    }  
	
	/**
	 * 将时间字符串类型转换成Timestamp时间类型
	 * 
	 * @param str
	 * @param isStartDate 如果为false，自动添加23:59:59
	 * @return
	 */
	public static Timestamp parserTimestap(String str, boolean isStartDate) {
		Timestamp date = Timestamp.valueOf(dataAddStr(str,isStartDate));
		return date;
	}

	/**
	 * 将Date类型转换成Timestamp时间类型
	 * 
	 * @param str
	 * @param isStartDate 是否为开始时间-如果为false，自动添加23:59:59
	 * @return
	 */
	public static Timestamp parserTimestapForDate(Date date, boolean isStartDate) {
		Timestamp timestamp = Timestamp.valueOf(dataAddStr(dateFormat(date, "yyyy-MM-dd"), isStartDate));
		return timestamp;
	}
	
	/**
	 * 拼接日期后缀
	 * @return
	 */
	private static String dataAddStr(String str,boolean isStartDate){
		if(str.length()==10){
			if(isStartDate){
				return str+" 00:00:00";
			}else{
				return str+" 23:59:59";
			}
		}
		return str;
	}

}
