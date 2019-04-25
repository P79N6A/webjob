package webfood.utils;

import java.math.BigDecimal;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author Yolanda
 * @version 1.0
 */

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 */

public class Arith
{

	// 默认除法运算精度

	private static final int DEF_DIV_SCALE = 10;

	// 这个类不能实例化

	private Arith()
	{

	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */

	public static double add(double v1, double v2)
	{

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.add(b2).doubleValue();

	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */

	public static double sub(double v1, double v2)
	{

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.subtract(b2).doubleValue();

	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */

	public static double mul(double v1, double v2)
	{

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.multiply(b2).doubleValue();

	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2)
	{

		return div(v1, v2, DEF_DIV_SCALE);

	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2, int scale)
	{

		if (scale < 0)
		{

			throw new IllegalArgumentException(

					"The scale must be a positive integer or zero");

		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */

	public static double round(double v, int scale)
	{

		if (scale < 0)
		{

			throw new IllegalArgumentException(

					"The scale must be a positive integer or zero");

		}

		BigDecimal b = new BigDecimal(Double.toString(v));

		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	// 格式化 scale 小数位数
	public static String format(double v, int scale)
	{
		System.out.println(v);
		if (scale < 0)
		{

			throw new IllegalArgumentException(

					"The scale must be a positive integer or zero");

		}
		StringBuffer sb = new StringBuffer("#0");
		if (scale > 0)
		{
			sb.append(".");
			for (int i = 0; i < scale; i++)
				sb.append("0");
		}
		String format = sb.toString();
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		// System.out.println(v+" 格式化 参数["+sb.toString()+"] 值:"+df.format(v));
		return df.format(v);
	}

	// showZero 如果小数点后都是0，是否显示
	public static String format(double v, int scale, boolean showZero)
	{
		// System.out.println(v);
		if (scale < 0)
		{

			throw new IllegalArgumentException(

					"The scale must be a positive integer or zero");

		}
		StringBuffer sb = null;
		if (scale == 0)
			sb = new StringBuffer("#0");
		else
			sb = new StringBuffer("#0.");
		for (int i = 0; i < scale; i++)
			if (showZero)
				sb.append("0");
			else
				sb.append("#");
		String format = sb.toString();
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		// System.out.println(v+" 格式化 参数["+sb.toString()+"] 值:"+df.format(v));
		// String output = df.format(v);
		return df.format(v);
	}

	// showZero 如果小数点后都是0，是否显示
	public static String formatJe(double v)
	{
		return formatJe(String.valueOf(v), false);
	}

	// showZero 如果小数点后都是0，是否显示
	public static String formatJe(String v)
	{
		return formatJe(v, false);
	}

	// showZero 如果小数点后都是0，是否显示
	public static String formatJe(String v, boolean showZero)
	{
		StringBuffer sb = new StringBuffer("#0.");
		for (int i = 0; i < 2; i++)
			if (showZero)
				sb.append("0");
			else
				sb.append("#");
		String format = sb.toString();
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(Double.parseDouble(v));
	}

	// showZero 如果小数点后都是0，是否显示
	public static String format(String v, int scale, boolean showZero)
	{
		// System.out.println(v);
		if (scale < 0)
		{

			throw new IllegalArgumentException(

					"The scale must be a positive integer or zero");

		}
		StringBuffer sb = new StringBuffer("#0");
		if (scale > 0)
		{
			sb.append(".");
			for (int i = 0; i < scale; i++)
				if (showZero)
					sb.append("0");
				else
					sb.append("#");
		}
		String format = sb.toString();
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		// System.out.println(v+" 格式化 参数["+sb.toString()+"] 值:"+df.format(Double.parseDouble(v)));
		// String output = df.format(v);
		return df.format(Double.parseDouble(v));
	}

	public static String formatCentJe(int v, boolean showZero)
	{
		return formatCentJe(String.valueOf(v), showZero);
	}

	public static String formatCentJe(int v)
	{
		return formatCentJe(String.valueOf(v), false);
	}

	public static String formatCentJe(String v)
	{
		return formatCentJe(v, false);
	}

	public static String formatCentJe(String v, boolean showZero)
	{
		return formatCentInt(v, showZero);
	}

	public static String formatCentInt(int v, boolean showZero)
	{
		return formatCentJe(String.valueOf(v), showZero);
	}

	public static String formatCentInt(int v)
	{
		return formatCentJe(String.valueOf(v), false);
	}

	public static String formatCentInt(String v)
	{
		return formatCentInt(v, false);
	}

	public static String formatCentInt(String v, boolean showZero)
	{
		long f = Long.parseLong(v);
		StringBuffer sb = new StringBuffer("#0.");
		for (int i = 0; i < 2; i++)
			if (showZero)
				sb.append("0");
			else
				sb.append("#");
		String format = sb.toString();
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(Arith.div(f, 100, 2));
	}

	public static String formatMilliInt(int v, boolean showZero)
	{
		return formatMilliInt(String.valueOf(v), showZero);
	}

	public static String formatMilliInt(int v)
	{
		return formatMilliInt(String.valueOf(v), false);
	}

	public static String formatMilliInt(String v)
	{
		return formatMilliInt(v, false);
	}

	public static String formatMilliInt(String v, boolean showZero)
	{
		long f = Long.parseLong(v);
		StringBuffer sb = new StringBuffer("#0.");
		for (int i = 0; i < 3; i++)
			if (showZero)
				sb.append("0");
			else
				sb.append("#");
		String format = sb.toString();
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(Arith.div(f, 1000, 3));
	}

	public static String formatMacroInt(int v, boolean showZero)
	{
		return formatMacroInt(String.valueOf(v), showZero);
	}

	public static String formatMacroInt(int v)
	{
		return formatMacroInt(String.valueOf(v), false);
	}

	public static String formatMacroInt(String v)
	{
		return formatMacroInt(v, false);
	}

	public static String formatMacroInt(String v, boolean showZero)
	{
		long f = Long.parseLong(v);
		StringBuffer sb = new StringBuffer("#0.");
		for (int i = 0; i < 6; i++)
			if (showZero)
				sb.append("0");
			else
				sb.append("#");
		String format = sb.toString();
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(Arith.div(f, 1000000, 6));
	}

	public static void main(String[] argv)
	{
		double d1 = 0.9999990001;
		double d2 = 234.123001;

		System.out.println("" + new BigDecimal(0.1));
		System.out.println(new BigDecimal(Double.toString(0.1)));
		System.out.println("double+double=" + add(d1, d2));
		System.out.println("double-double=" + sub(d1, d2));
		System.out.println("double*double=" + mul(d1, d2));
		System.out.println("double/double=" + div(600, mul(365, 100)));
		System.out.println("double/double=" + div(d1, d2, 5));
		System.out.println("round(double)=" + round(d1, 2));

		double d3 = -214522;
		double d4 = 13123.135646656;
		String format = "#0.00";
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		System.out.println("格式:" + format + " 数据:" + d1 + "  结果:" + df.format(d1));
		System.out.println("格式:" + format + " 数据:" + d2 + "  结果:" + df.format(d2));
		System.out.println("格式:" + format + " 数据:" + d3 + "  结果:" + df.format(d3));
		System.out.println("格式:" + format + " 数据:" + d4 + "  结果:" + df.format(d4));
		System.out.println("格式:" + format + " 数据:" + d4 + "  结果:" + Arith.format(d4, 2, false));
		int i1 = -111;
		int i2 = 234234;
		int i3 = 9090324;
		int i4 = 435435;
		System.out.println("格式:金额 数据:" + i1 + "  结果:" + Arith.formatCentJe(String.valueOf(i1), true));
		System.out.println("格式:金额 数据:" + i2 + "  结果:" + Arith.formatCentJe(String.valueOf(i2), true));
		System.out.println("格式:金额 数据:" + i3 + "  结果:" + Arith.formatCentJe(String.valueOf(i3), true));
		System.out.println("格式:金额 数据:" + i4 + "  结果:" + Arith.formatCentJe(String.valueOf(i4), true));

		float t = 369784.8f;
		format = "#0.00";
		System.out.println("格式:" + format + " 数据:" + d4 + "  结果:" + df.format(t));
		String a = "243222500";
		System.out.println(a + "格式后:" + Arith.formatMilliInt(a));
		System.out.println(a + "格式后:" + Arith.formatMilliInt(a, true));
		try
		{
			Process process = Runtime.getRuntime().exec("d:/a.bat");
			process.waitFor();
		}
		catch (Exception e)
		{
			System.out.println("aaa");
			e.printStackTrace();
		}
	}

}
