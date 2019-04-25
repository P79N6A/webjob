package webfood.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{

	public static final String YEARMONTH = "yyyy-MM";
	public static final String AllDate = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String YearMonthDay = "yyyy年MM月dd日";

	public static String dateFormat(Date date)
	{
		if (null == date)
		{
			return "无";
		}
		return new SimpleDateFormat(YearMonthDay).format(date);
	}

	public static String dateFormat(String format, Date date)
	{
		if (null == date)
		{
			return "无";
		}
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 *
	 * @Title: getMonth
	 * @Description: 取得之后num个月的月份，取得之前num个月的月份
	 * @param calendar 基准日期
	 * @param num  过去负数  未来正数
	 * @return
	 * @return: Calendar
	 * @throws
	 */
	public static Calendar getMonth(Calendar calendar, int num)
	{
		calendar.add(Calendar.MONTH, num);
		//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
		return calendar;

	}

	/**
	 *
	 * @Title: getYear
	 * @Description: 取得之后num个年的年份，取得之前num个年的年份
	 * @param calendar 基准日期
	 * @param num  过去负数  未来正数
	 * @return
	 * @return: int
	 * @throws
	 */
	public static int getYear(Calendar calendar, int num)
	{
		calendar.add(Calendar.YEAR, num);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
		return calendar.get(Calendar.YEAR);
	}

	/**
	 *
	 * @Title: getCalFromDays
	 * @Description: 取得过去num天的日期 或者 未来num天的日期
	 * @param calendar 基准日期
	 * @param num 过去负数  未来正数
	 * @return
	 * @return: Calendar
	 * @throws
	 */
	public static Calendar getCalFromDays(Calendar calendar, int num)
	{
		calendar.add(Calendar.DAY_OF_YEAR, num);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
		return calendar;

	}

	public static boolean compareMonth(Date standard, Date compared)
	{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		System.out.println("1:" + sdf.format(standard) + "--:" + sdf.format(compared));
		return sdf.format(standard).equals(sdf.format(compared));
	}

	public static Calendar getCurrYearFirst()
	{
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		return calendar;
	}

	public static Date StringToDate(String str, String pattern) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(str);
		return date;
	}

	public static String EmailToUsername(String email)
	{
		int index = email.indexOf("@");
		String username = email.substring(0, index);
		return username;
	}

	public static void main(String args[])
	{
		//		System.out.println(getMonth(Calendar.getInstance(), -12));
		//		System.out.println(getYear(Calendar.getInstance(), -1));
		//		System.out.println(getCalFromDays(Calendar.getInstance(),0).getTime());
		//		System.out.println(getCalFromDays(Calendar.getInstance(),-30).getTime());
		//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.getMonth(Calendar.getInstance(),0).getTime()));
		//		System.out.println(DateUtils.getMonth(Calendar.getInstance(),0).get(Calendar.YEAR));
		//		System.out.println(DateUtils.getMonth(Calendar.getInstance(),0).get(Calendar.MONTH)+1);
		//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.getMonth(Calendar.getInstance(),1).getTime()));
		//		System.out.println(DateUtils.getMonth(Calendar.getInstance(),1).get(Calendar.YEAR));
		//		System.out.println(DateUtils.getMonth(Calendar.getInstance(),1).get(Calendar.MONTH)+1);
		//		Calendar ca=Calendar.getInstance();

		System.out.println(getCurrYearFirst().getTime());

		System.out.println(EmailToUsername("@163.com"));
	}

}
