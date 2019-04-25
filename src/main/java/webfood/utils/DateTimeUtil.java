package webfood.utils;

import java.text.*;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.*;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * </p>
 * <p>
 * Company: SunFlower
 * </p>
 * 
 * @author Michael Hou
 * @version 1.0
 */

public class DateTimeUtil {
	private static Logger log = Logger.getLogger(DateTimeUtil.class.getName());

	/**
	 *函数名称：取得当前日期、时间
	 * 
	 * @return 时间串，格式：14位时间yyyyMMddHHmmss来表示XXXX年XX月XX日XX时XX分XX秒
	 */
	public static String getDateTimeNow() {
		return getDateTimeNow("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 *函数名称：取得当前日期、时间
	 * 
	 * @param format
	 *            格式串 如：YYYY-MM-DD HHMMSS
	 *@return 时间串
	 */

	public static String getDateTimeNow(String format) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		GregorianCalendar cal = new GregorianCalendar();
		return bartDateFormat.format(cal.getTime());
	}
	
	public static Date getDateTimeNow1() throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = bartDateFormat.parse(getDateTimeNow());  
		return date;
	}

	public static String getDateTime(String format, long time) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		return bartDateFormat.format(new Date(time));

	}

	/**
	 *函数名称：取得当前日期
	 * 
	 * @return 时间串，格式：YYYYMMDD
	 */

	public static String getDateNow() {
		return getDateTimeNow("yyyy-MM-dd");
	}

	/**
	 *函数名称：取得当前时间
	 * 
	 * @return 时间串，格式：HHMMSS
	 */
	public static String getTimeNow() {
		return getDateTimeNow("HH:mm:ss");
	}

	/**
	 * 加上secondOffset秒后（可以为负）的新日期
	 * 
	 * @param format
	 *            String
	 * @param secondOffset
	 *            int
	 * @return String
	 */
	public static String getDateTimeOther(String format, int secondOffset) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.SECOND, secondOffset);
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		return bartDateFormat.format(cal.getTime());
	}

	/**
	 * 传入日期参数上加上secondOffset秒后（可以为负）的新日期
	 * 
	 * @param theDate
	 * @param format
	 * @param secondOffset
	 * @return
	 */
	/*
	 * public static String getDateTimeOther(String date, String format, int
	 * secondOffset){ try { Date theDate = strToDateTime(date, format);
	 * 
	 * GregorianCalendar cal = new GregorianCalendar(theDate.getYear() + 1900,
	 * theDate.getMonth(), theDate.getDate(), theDate.getHours(),
	 * theDate.getMinutes(), theDate.getSeconds()); cal.add(Calendar.SECOND,
	 * secondOffset); SimpleDateFormat bartDateFormat = new
	 * SimpleDateFormat(format); return bartDateFormat.format(cal.getTime()); }
	 * catch (Exception ex) { return null; } }
	 */
	/**
	 * 日期类型转换成字符串类型
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateTimeToStr(Date date, String format) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		return bartDateFormat.format(date);
	}

	/**
	 * 字符串类型转换成日期类型
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDateTime(String str, String format)
			throws ParseException {
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
			return bartDateFormat.parse(str);
		} catch (ParseException ex) {
			throw ex;
		}
	}

	/**
	 * 字符串类型转换成long时间
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static long strToLong(String str, String format)
			throws ParseException {
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
			Date d = bartDateFormat.parse(str);
			return d.getTime();
		} catch (ParseException ex) {
			throw ex;
		}
	}

	/**
	 * 两个日期之间相差的秒数
	 * 
	 * @param beginDate
	 *            String
	 * @param endDate
	 *            String
	 * @return int
	 */
	public static int secsBetweenDate(String beginDate, String endDate) {
		try {
			int interval = 0;
			Date bDate = strToDateTime(beginDate, "yyyy-MM-dd HH:mm:ss");
			Date eDate = strToDateTime(endDate, "yyyy-MM-dd HH:mm:ss");
			interval += (eDate.getTime() - bDate.getTime()) / 1000;
			return interval;
		} catch (ParseException ex) {
		}
		return -1;
	}

	/**
	 * 两个日期之间相差的天数
	 * 
	 * @param beginDate
	 *            String
	 * @param endDate
	 *            String
	 * @return int
	 */
	public static int daysBetweenDate(String beginDate, String endDate) {
		try {
			int interval = 0;
			Date bDate = strToDateTime(beginDate, "yyyy-MM-dd");
			Date eDate = strToDateTime(endDate, "yyyy-MM-dd");
			interval += (eDate.getTime() - bDate.getTime())
					/ (24 * 60 * 60 * 1000);
			return interval;
		} catch (ParseException ex) {
		}
		return -1;
	}

	/**
	 * 两个日期之间相差的年数，计算年龄
	 * 
	 * @param beginDate
	 *            String
	 * @param endDate
	 *            String
	 * @return int
	 */
	public static int yearsBetweenDate(String beginDate, String endDate) {
		int interval = 0;
		String b = org.apache.commons.lang.StringUtils.substring(beginDate, 0,
				4);
		String e = org.apache.commons.lang.StringUtils.substring(endDate, 0, 4);
		interval = Integer.parseInt(e) - Integer.parseInt(b);
		return interval;
	}

	/**
	 * 两个日期之间相差的月数
	 * 
	 * @param beginDate
	 *            String
	 * @param endDate
	 *            String
	 * @return int
	 */
	public static int monthsBetweenDate(String beginDate, String endDate) {
		int interval = 0;
		String b = org.apache.commons.lang.StringUtils.substring(beginDate, 0,
				4);
		String e = org.apache.commons.lang.StringUtils.substring(endDate, 0, 4);
		interval = (Integer.parseInt(e) - Integer.parseInt(b)) * 12;
		b = org.apache.commons.lang.StringUtils.substring(beginDate, 5, 7);
		e = org.apache.commons.lang.StringUtils.substring(endDate, 5, 7);
		interval += (Integer.parseInt(e) - Integer.parseInt(b));
		return interval;
	}

	/**
	 * 两个日期之间相差的月数零天数
	 * 
	 * @param beginDate
	 *            String
	 * @param endDate
	 *            String
	 * @return int
	 */
	public static int[] monthsDaysBetweenDate(String beginDate, String endDate) {
		int [] monthsDays = {0,0};
		try {
			int months = 0;
			String format = "yyyy-MM-dd";
			int oldDate = Integer.parseInt(StringUtils.substring(beginDate, 8,10));
			Date begin = strToDateTime(beginDate, format);
			Calendar beginCal = Calendar.getInstance();
			beginCal.setTime(begin);
			
			Date end = strToDateTime(endDate, format);
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(end);
			if(endCal.after(beginCal)){
				while(endCal.after(beginCal)){
					beginCal.setTime(begin);
					months ++;
					beginCal.add(Calendar.MONTH, months);
				}
			}
			long days = 0;
			long millis = beginCal.getTimeInMillis() - endCal.getTimeInMillis();
			long dayMillis = 24*60*60*1000L;//一天的毫秒数
			if(millis > dayMillis){
				months --;
				beginCal.setTime(begin);
				beginCal.add(Calendar.MONTH, months);
				//SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
				//System.out.println("日期:"+bartDateFormat.format(beginCal.getTime()));
				days = (endCal.getTimeInMillis() - beginCal.getTimeInMillis())/(24*60*60*1000)+1;
			}else if(millis < dayMillis){//此时只会出现=0的情况
				System.out.println("本月日期:"+beginCal.get(Calendar.DATE));
				if(beginCal.get(Calendar.DATE) >= oldDate)
					days ++;
			}else{
				//刚刚好是一个整月 如果日期没有调整的话（比如1.31日加一个月后会变为2.28日）;如果调整了，如何计算？
				
			}
			
			//System.out.println("间隔:"+interval+"月  "+days+"天");
			monthsDays[ 0 ] = months;
			monthsDays[ 1 ] = (int)days;
			return monthsDays;
		} catch (Exception ex) {
			return monthsDays;
		}
	}

	/**
	 * 得到两个日期之间的时间差，以秒为单位
	 * 
	 * @param beginDate
	 *            Date
	 * @param endDate
	 *            Date
	 * @return int
	 */
	public static int secBetweenDate(Date beginDate, Date endDate) {
		int interval = 0;
		interval += (endDate.getTime() - beginDate.getTime()) / 1000;
		return interval;
	}

	public static String millisecondToStr(long millisecond) {
		if (millisecond < 0)
			return millisecond + "毫秒";
		if (millisecond < 1000)
			return "1秒";
		long second = millisecond / 1000;
		String result = "";
		long day = second / (3600 * 24);
		if (day > 0)
			result += (day + "天 ");
		second %= 3600 * 24;
		long hour = second / 3600;
		if (hour > 0)
			result += (hour + "小时 ");
		second %= 3600;
		long minute = second / 60;
		if (minute > 0)
			result += (minute + "分钟 ");
		second %= 60;
		if (second > 0)
			result += (second + "秒");
		return result;
	}

	/**
	 * 日期字符串格式相互转换
	 * 
	 * @param str
	 *            被转换字符串
	 * @param fmtSrc
	 *            源日期格式
	 * @param fmtDst
	 *            目标日期格式
	 * @return
	 * @throws ParseException
	 */
	public static String dateStrConvert(String str, String fmtSrc, String fmtDst) {
		try {
			Date date = strToDateTime(str, fmtSrc);
			return dateTimeToStr(date, fmtDst);
		} catch (ParseException e) {
			return str;
		}
	}

	public static String toShortDate(String dateTimeStr) {
		if (dateTimeStr != null)
			return dateStrConvert(dateTimeStr, "yyyy-MM-dd HH:mm:ss",
					"yyyy-MM-dd");
		else
			return null;
	}

	/**
	 * 取此基准日期偏移 n 天后的日期
	 * 
	 * @param date
	 *            基准日期
	 * @param format
	 *            基准日期格式
	 * @param day
	 *            偏移天数，可以为负
	 * @param destFormat
	 *            结果日期格式 yyyy-MM-dd
	 * @return
	 */

	public static String getDateOther(String date, String format, int day,
			String destFormat) {
		try {
			if (StringUtils.isEmpty(date))
				date = getDateNow();
			Date theDate = strToDateTime(date, format);
			// Log.log.debug("原日期："+date);
			// Log.log.debug("日期："+(theDate.getYear()+1900)+" "+(theDate.getMonth()+1)+" "+theDate.getDate());
			// GregorianCalendar cal = new
			// GregorianCalendar(theDate.getYear()+1900,theDate.getMonth(),theDate.getDate());
			// GregorianCalendar cal = new
			// GregorianCalendar(theDate.getYear()+1900,theDate.getMonth(),theDate.getDate());
			Calendar cal = Calendar.getInstance();
			cal.setTime(theDate);
			cal.add(Calendar.DAY_OF_MONTH, day);
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(destFormat);
			return bartDateFormat.format(cal.getTime());
		} catch (Exception ex) {
		}
		return null;
	}

	public static String getDateOther(int day) {
		try {
			Date theDate = new Date();
			// GregorianCalendar cal = new
			// GregorianCalendar(theDate.getYear()+1900,theDate.getMonth(),theDate.getDate());
			Calendar cal = Calendar.getInstance();
			cal.setTime(theDate);
			cal.add(Calendar.DAY_OF_MONTH, day);
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return bartDateFormat.format(cal.getTime());
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 判断date字符串是否是符合fmt的日期字符串
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static boolean isDate(String date, String fmt) {
		boolean result = false;
		if (date == null || fmt == null)
			return result;
		try {
			Date d = strToDateTime(date, fmt);
			String newDate = dateTimeToStr(d, fmt);
			if (newDate.equals(date))
				result = true;
		} catch (ParseException ex) {
			log.warn("日期格式不对：" + date + " 格式" + fmt);
		}
		return result;
	}

	public static boolean isDate(String date) {
		boolean result = false;
		result = isDate(date, "yyyy-MM-dd");
		return result;
	}

	public DateTimeUtil() {
	}

	/**
	 * 获取本月最后一天的日期
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static String getMonthLastDay(String yearMonth) {
		if (yearMonth == null)
			return null;
		yearMonth = yearMonth + "-01";
		Date date = null;
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = bartDateFormat.parse(yearMonth);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			return bartDateFormat.format(calendar.getTime());
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * 获取与本月相隔i月的本日日期 如果目标月没有本日(如8.31的目标月为2月，则返回当年度2月的最后一天)，则返回当目标月的最后一天
	 * 
	 * @param i
	 * @return
	 */
	public static String getOtherMonth(int i) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, i);
		return bartDateFormat.format(calendar.getTime());
	}

	/**
	 * 同上一方法，命名有修改 获取与本月相隔i月的本日日期
	 * 如果目标月没有本日(如8.31的目标月为2月，则返回当年度2月的最后一天)，则返回当目标月的最后一天
	 * 
	 * @param i
	 * @return
	 */
	public static String getOtherMonthDate(int i) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, i);
		return bartDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取与本月相隔i月的本日日期 如果目标月没有本日(如8.31的目标月为2月，则返回当年度2月的最后一天)，则返回当目标月的最后一天
	 * 
	 * @param i
	 * @return
	 */
	public static String getOtherMonthDate(String date, int i) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date1 = null;
		try {
			date1 = strToDateTime(date, "yyyy-MM-dd");
		} catch (Exception ex) {
			return null;
		}
		calendar.setTime(date1);

		calendar.add(Calendar.MONTH, i);
		return bartDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取本月的下一个月
	 * 
	 * @param yearMonth
	 * @param i
	 * @return
	 */
	/**
	 * 获取本月的下n个月
	 * 
	 * @param yearMonth 格式如“2010-10”
	 * @param n
	 * @return
	 */
	public static String getOtherMonth(String yearMonth, int n) {
		if (yearMonth == null)
			return null;
		if (yearMonth.length() != 7){
			System.out.println("yearMonth=["+yearMonth+"]不符合格式要求，请输入格式如“2010-01”");
			return null;
		}
		yearMonth = yearMonth + "-01";
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		try {
			date = strToDateTime(yearMonth, "yyyy-MM-dd");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return bartDateFormat.format(calendar.getTime());
	}

	/**
	 * 取相隔i年后的今天
	 * 
	 * @param i
	 * @return
	 */
	public static String getOtherYearDate(int i) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, i);
		return bartDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取与本月相隔i年的本日日期
	 * 
	 * @param i
	 * @return
	 */
	public static String getOtherYearDate(String date, int i) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date1 = null;
		try {
			date1 = strToDateTime(date, "yyyy-MM-dd");
		} catch (Exception ex) {
			return null;
		}
		calendar.setTime(date1);

		calendar.add(Calendar.YEAR, i);
		return bartDateFormat.format(calendar.getTime());
	}

	/**
	 * 判断yearMonth是否合法，yearMonth限制在1000年到9999年
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static boolean isYearMonthValid(String yearMonth) {
		if (yearMonth == null)
			return false;
		if (yearMonth.length() != 6)
			return false;
		int year = 0;
		int month = 0;
		try {
			year = Integer.parseInt(yearMonth.substring(0, 4));
			month = Integer.parseInt(yearMonth.substring(4, 6));
			if (year < 1000 || year > 9999)
				return false;
			if (month < 1 || month > 12)
				return false;
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * 取此基准日期偏移 n天工作日后的日期
	 * 
	 * @param date
	 *            基准日期
	 * @param day
	 *            偏移天数
	 * @param weekDay
	 *            星期几
	 * @return 默认为yyyy-MM-dd格式
	 */

	public static String getWorkDay(String date, int day) {
		int weekDay = 0;
		int days = 0;
		int t = 0;
		int i = 0;
		// 根据日期取星期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1;
		try {
			date1 = sdf.parse(date);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);
			weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;// 取出的值比实际要大一所以减一为实际星期
			// System.out.println(date+"是星期"+weekDay);
			// 如果被7取模为6或0的双休日,则工作日i不加1
			while (i < day) {
				t = weekDay % 7;
				if (t != 6 && t != 0) {
					i++;
				}
				weekDay++;
				days++;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println(days+"天中工作了"+day+"天");
		return getDateOther(date, "yyyy-MM-dd", days, "yyyy-MM-dd");
	}

	public static String now() {
		return getDateTimeNow();
	}

	public static String date() {
		return getDateNow();
	}

	public static String date(int days) {
		try {
			Date theDate = new Date();
			// GregorianCalendar cal = new GregorianCalendar(theDate.getYear() +
			// 1900, theDate.getMonth(), theDate.getDate());
			Calendar cal = Calendar.getInstance();
			cal.setTime(theDate);
			cal.add(Calendar.DAY_OF_MONTH, days);
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return bartDateFormat.format(cal.getTime());
		} catch (Exception ex) {
		}
		return getDateNow();
	}

	public static String date(String format) {
		return getDateTimeNow(format);
	}

	public static void main(String[] args) {
		String test = "asdfasdf;asdfasdf;adfasdf";
		log.debug(test);
		log.debug(test.substring(0, test.indexOf(";")));
		test = test.substring(test.indexOf(";") + 1);
		log.debug(test);
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = bartDateFormat.getCalendar();
		log.error(cal.get(Calendar.YEAR) + "");
		log.error(cal.get(Calendar.MONTH) + "");
		/*
		 * GregorianCalendar cal = new GregorianCalendar(theDate.getYear() +
		 * 1900, theDate.getMonth(), theDate.getDate(), theDate.getHours(),
		 * theDate.getMinutes(), theDate.getSeconds()); cal.add(Calendar.SECOND,
		 * secondOffset); SimpleDateFormat bartDateFormat = new
		 * SimpleDateFormat(format); return
		 * bartDateFormat.format(cal.getTime());
		 */
		try {
			// Log.log.warn(""+isDate("20051328","yyyyMMdd"));
			String yearMonth = "200512";
			log.warn(yearMonth + "的最后一天为：" + getMonthLastDay(yearMonth));
			String date = getOtherMonth(6);
			log.warn(date);
			String t = "952148236234234234324542152451";
			// long t1 = Long.parseLong(t);
			double d = Double.parseDouble(t);
			log.warn(t + "  " + t.length() + "   " + d);
			log.warn(yearMonth + "是否合法：" + isYearMonthValid(yearMonth));
			int i = -1;
			log.warn(yearMonth + "  " + i + "个月后是"
					+ getOtherMonth(yearMonth, i));
			// String d1 = "20050926010101";
			// Log.log.warn(d1+"加上3分钟后是"+DateTimeUtil.getDateTimeOther(d1,"yyyyMMddHHmmss",3*60));
			log.warn(getDateTimeNow("yyyy-M-d"));
			log.warn(dateStrConvert("20051002", "yyyyMMdd", "yyyy-M-d"));
			log.warn(millisecondToStr(2104646513));
			log.warn(getDateOther("2010-03-19", "yyyy-MM-dd", 3, "yyyy-M-d"));
			log.warn(getDateOther(-3));
			// Log.log.warn(t+ "  "+t.length()+"   "+t1);
			/*
			 * Log.log.debug(getDateTimeOther("20050302","yyyyMMdd",-3,"yyyy-MM-dd"
			 * )); String str = new GregorianCalendar().getTime().toString();
			 * System.out.println(str);
			 * 
			 * BOCDateTimeUtil hsDateTime1 = new BOCDateTimeUtil();
			 * System.out.println(hsDateTime1.getDateTimeNow("yyyy-MM-dd"));
			 * System.out.println(hsDateTime1.getDateTimeNow("HH:mm:ss"));
			 * GregorianCalendar cal = new GregorianCalendar(); Date d =
			 * cal.getTime(); System.out.println(dateTimeToStr(d,
			 * "yyyy-mm-dd HH:mm:ss"));
			 * 
			 * d = strToDateTime("20080809", "yyyyMMdd"); //d =
			 * strToDateTime("20050223120044","yyyyMMddHHmmss");
			 * System.out.println(dateTimeToStr(d, "yyyy-MM-DD "));
			 * 
			 * System.out.println(dateStrConvert("2003-09-09"));
			 * //System.out.println(dateStrConvert("200309-09")); //raise
			 * excepiton System.out.println(dateStrConvert("20030909",
			 * "yyyymmdd", "yyyy年MM月dd日"));
			 * 
			 * 
			 * System.out.println(BOCDateTimeUtil.getDateTimeNow("yyyy年MM月dd日"));
			 * System.out.println(BOCDateTimeUtil.getDateTimeNow("HH:mm:ss"));
			 * 
			 * System.out.println(BOCDateTimeUtil.getDateTime(
			 * "yyyy年MM月dd日 HH时mm分ss秒", System.currentTimeMillis()));
			 * System.out.println(BOCDateTimeUtil.getDateTimeOther(
			 * "yyyy年MM月dd日 HH时mm分ss秒", -3600 * 24 * 3));
			 * System.out.println(BOCDateTimeUtil.secBetweenDate(new Date(1903,
			 * 2, 1), new Date(1902, 2, 1)));
			 * System.out.println(BOCDateTimeUtil.strToLong("200502230910",
			 * "yyyyMMddHHmm"));
			 * System.out.println(BOCDateTimeUtil.dateStrConvert("20050229",
			 * "yyyyMMdd","yyyyMMdd"));
			 */
			System.out.println(DateTimeUtil.monthsDaysBetweenDate("2010-01-28",
					"2010-02-26"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
