package webfood.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentHelper;

/**
 * 这是一个用于处理和校验字符串的类。
 * A set of utility functions for handling string/text.
 * @author alibaba , devinfor group
 * @version 1.0
 */
public class StringUtil
{

	private final static char[] cs = "零一二三四五六七八九".toCharArray();

	/**
	 * @param args
	 */

	/**
	 *
	 * @param number  数字
	 * @return  字符串
	 */
	public static String NumberToString(int number)
	{
		String temp = "";
		while (number > 0)
		{
			temp += cs[number % 10];
			//  System.out.println(temp);
			number /= 10;
		}
		return temp;

	}

	//防止CSS攻击的正则表达式
	static String[] CSS_REGS =
	{ "((\\%3C)|<)((\\%2F)|\\/)*[a-z0-9\\%]+((\\%3E)|>)",
			"((\\%3C)|<)((\\%69)|i|(\\%49))((\\%6D)|m|(\\%4D))((\\%67)|g|(\\%47))[^\n]+((\\%3E)|>)",
			"((\\%3C)|<)[^\n]+((\\%3E)|>)" };
	//检测跨站脚本攻击(CSS)
	//正则表达式
	/*
	  一般 CSS 攻击的正则表达式
	 /((\%3C)|<)((\%2F)|\/)*[a-z0-9\%]+((\%3E)|>)/ix
	 解释:
	 ((\%3C)|<) －检查<和它hex等值
	 ((\%2F)|\/)*－结束标签/或它的 hex等值
	 [a-z0-9\%]+ －检查标签里的字母或它hex等值
	 ((\%3E)|>) －检查>或它的hex等值

	 "<img src" CSS 攻击正则表达式
	 /((\%3C)|<)((\%69)|i|(\%49))((\%6D)|m|(\%4D))((\%67)|g|(\%47))[^\n]+((\%3E)|>)/I

	 解释:
	 (\%3 C)|<) -<或它的hex等值
	 (\%69)|i|(\%49))((\%6D)|m|(\%4D))((\%67)|g|(\%47) -’img’字母或它的大小写hex等值的变化组合
	 [^\n]+ -除了换行符以外的任何跟随<img的字符
	 (\%3E)|>) ->或它的hex等值

	 CSS 攻击的极端的正则表达式
	 /((\%3C)|<)[^\n]+((\%3E)|>)/I
	 解释:
	 这个规则简单寻找<+除换行符外的任何字符+>。由于你的web服务器和web应用程序的构架，这个规则可能产生一些错误。但它能保证捉住任何CCS或者类似CSS的攻击。

	 */

	/**
	 * 检测是否含有css攻击　　根据　http://www.secnumen.com/technology/sql014.htm 开发
	 * @param value String
	 * @return boolean
	 */
	public static boolean cssExist(String value)
	{
		for (String element : CSS_REGS)
		{
			Pattern p = Pattern.compile(element);
			Matcher m = null;
			m = p.matcher(value);
			if (m.find())
			{
				System.out.println("[" + value + "]中含有违法字符串，检验串:[" + element + "]");
				return true;
			}
		}
		return false;
	}

	/*Map中保存着 变量名和变量值  对每个值进行css攻击检测*/
	public static boolean cssExist(Map<String, String> items)
	{
		if (items == null)
		{
			return false;
		}

		for (String element : CSS_REGS)
		{
			Pattern p = Pattern.compile(element);
			Matcher m = null;
			Iterator<String> it = items.keySet().iterator();
			while (it.hasNext())
			{
				String key = it.next();
				String value = items.get(key);
				m = p.matcher(value);
				if (m.find())
				{
					System.out.println("key=[" + key + "] value=[" + value + "]中含有违法字符串，检验串:[" + element + "]");
					return true;
				}
			}
		}

		return false;
	}

	/*Map中保存着 变量名和变量值  生成 k=v&k2=v2这样的queryString*/
	public static String getQueryStringFromMap(Map<String, String> items)
	{
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = items.keySet().iterator();
		int i = 0;
		while (it.hasNext())
		{
			String key = it.next();
			String value = items.get(key);
			if (i != 0)
			{
				sb.append("&");
			}
			sb.append(key + "=" + value);
			i++;
		}
		return sb.toString();
	}

	/*生成<input type='hidden' name='key' value='value'>*/
	public static String getPostHiddenInputs(Map<String, String> items)
	{
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = items.keySet().iterator();
		//int i = 0;
		while (it.hasNext())
		{
			String key = it.next();
			String value = items.get(key);
			sb.append("<input type=\"hidden\" id=\"" + key + "\" name=\"" + key + "\" value=\"" + value + "\" />\r\n");
		}
		return sb.toString();
	}

	public static boolean isMobile(String phone)
	{
		String[] phones = StringUtils.split(phone, "/、,;，；");
		for (String phone2 : phones)
		{
			String str = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
			Pattern p = Pattern.compile(str);
			Matcher m = null;
			m = p.matcher(phone2);
			if (m.find())
			{
				return true;
			}
		}
		return false;
	}

	private static Random random = new Random();

	/**
	 * 字符串进入数据库前的准备工作
	 * @param inStr 输入字符串
	 * @return
	 */
	public static String toDb(String inStr)
	{
		String outStr = inStr;
		if (inStr == null)
		{
			outStr = "";
		}
		else
		{
			// outStr = outStr.trim();
			// outStr = outStr.replaceAll("'", "''"); //单引号替换成两个单引号
			outStr = org.apache.commons.lang.StringEscapeUtils.escapeSql(outStr.trim());
		}
		return outStr;
	}

	public static String toDbNum(String inStr)
	{
		String outStr = StringUtils.trimToEmpty(inStr);
		if (StringUtils.isEmpty(outStr))
		{
			outStr = "0";
		}
		else
		{
			outStr = org.apache.commons.lang.StringEscapeUtils.escapeSql(outStr);
			try
			{
				Double.parseDouble(outStr);
			}
			catch (NumberFormatException ex)
			{
				outStr = "0";
			}
		}
		return outStr;
	}

	/**
	 * 字符串从request中取出，进入数据库前的准备工作
	 * @return
	 */
	public static String req2Db(javax.servlet.http.HttpServletRequest req, String para)
	{
		String outStr = req.getParameter(para);
		if (outStr == null)
		{
			outStr = "";
		}
		else
		{
			//outStr = outStr.trim();
			//outStr = outStr.replaceAll("'", "''"); //单引号替换成两个单引号
			outStr = org.apache.commons.lang.StringEscapeUtils.escapeSql(outStr.trim());
		}
		return outStr;
	}

	/**
	 * HTML文件中的换行标记
	 */
	public static final String HTML_BreakLine = "<BR>";

	/**
	 * HTML文件中的空格标记
	 */
	public static final String HTML_Space = "&nbsp;";
	/**
	 * HTML文件中的大于号标记
	 */
	public static final String HTML_Greater = "&gt;";
	/**
	 * HTML文件中的小于号标记
	 */
	public static final String HTML_Less = "&lt;";

	/**
	 * The SBC table array,the hashtable and vector are low effiency,so we use the array
	 * 中文全角符号表。
	 */
	final static String[] SBC =
	{ //SBC symbol define
			"，", "。", "；", "“", "”", "？", "！", "（", "）", "：", "——", "、" };

	/**
	 * mailto:jasonxu@alibaba-inc.com
	 * The DBC table array.
	 * 英文半角符号表。
	 */
	final static String[] DBC =
	{ //DBC symbol define
			",", ".", ";", "\"", "\"", "?", "!", "(", ")", ":", "_", "," };

	/**
	 * 非法email字符表
	 */
	final static char[] caIllegalEmailChar =
	{ ' ', ',', ';', '!', '#', '$', '%', '^', '&', '*', '(', ')', '[', ']', '{', '}', ':', '\"', '\'', '?', '+', '=',
			'|', '\\' };

	/**
	 * 非法email地址的错误信息提示。
	 */
	final static String[] emailErrMsg =
	{ "No Error!", "Contain illegal char in email address!", "Contain chinese char in email address!",
			"The email address can not start or end with symbol char!", "Only one @ char can be in the email address!",
			"The @ can not be at the beginning or the end of email address!",
			"Two symbol char join together in the email address!",
			"The symbol char can not be at the start and end of email address!",
			"Can not find \".\" or the  \".\" at the previous position of the \"@\"",
			"Null or empty email address string!", "The char \'@\' is required!"

	};

	//---------------------------------------------------------------------------------------------------------------
	//----------------------------------------------unknown----------------------------------------------------------
	/**
	 * Return a string with leading and trailing spaces
	 * trimmed off.
	 * If string is empty, return null.
	 */
	public static String trim(String str)
	{
		if (str == null)
		{
			return null;
		}
		str = str.trim();
		if (str.length() == 0)
		{
			return null;
		}
		return str;
	}

	/**
	 * Return "NULL" when parameter is null
	 * else do the trim and return the trimed string
	 * used to get form value of CSMap for BOPS
	 */
	public static String trimFV(String str)
	{
		if (str == null)
		{
			return "NULL";
		}
		str = str.trim();
		if (str.length() == 0)
		{
			return "";
		}
		return str;
	}

	/**
	 * Return "" when parameter is null
	 * else do the trim and return the trimed string.
	 * @param str
	 * @return
	 */
	public static String nullTrim(String str)
	{
		if (str == null)
		{
			return "";
		}
		else
		{
			return str.trim();
		}
	}

	public static String htmlNullTrim(String str)
	{
		if (str == null || str.equals(""))
		{
			return "&nbsp;";
		}
		else
		{
			return str.trim();
		}
	}

	public static String null2String(Object o)
	{
		if (o == null)
		{
			return "";
		}
		else
		{
			return o.toString();
		}
	}

	/**
	 * Capitalize a word.
	 */
	public static String cap(String str)
	{
		if (str == null)
		{
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(Character.toUpperCase(str.charAt(0)));
		sb.append(str.substring(1).toLowerCase());
		return sb.toString();
	}

	/**
	 * Returns true if the string represents a word,
	 * i.e. every char is either a-z, A-Z, 0-9, _.
	 */
	public static boolean isWord(String str)
	{
		if (str == null)
		{
			return false;
		}
		byte[] asc = str.getBytes();
		for (int i = 0; i < asc.length; i++)
		{
			if (!isVisibleChar(asc[i]))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean isVisibleChar(byte asc)
	{
		if (asc >= 48 && asc <= 57 || asc >= 65 && asc <= 90 || asc >= 97 && asc <= 122 || asc == 95)
		{
			return true;
		}
		return false;
	}

	/**
	 * check homepage_url
	 */
	public static boolean checkHomePageUrl(String str)
	{
		if (str == null)
		{
			return true;
		}
		if (str.length() == 0)
		{
			return true;
		}
		if (str.length() > 7)
		{
			if (str.toLowerCase().indexOf("http://") == 0 && str.indexOf(".") > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if (str.length() == 7 && str.toLowerCase().indexOf("http://") == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	public static boolean isEmailUrl(String str)
	{
		if (str == null || str.length() == 0)
		{
			return false;
		}
		if (str.indexOf('@') > 0 && str.indexOf('@') == str.lastIndexOf('@'))
		{
			if (str.indexOf('.') > 0 && str.lastIndexOf('.') > str.indexOf('@'))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Input is a string and the index of which position you want to get from the dataArray
	 * Return a object: null or object or Vector
	 * index 0 : return a Vector which include all strArray
	 * note : if index out of bouds then return null
	 * usage : (please judge not null) String str = (String)StringUtil.getStringStr("jfas12",1);
	 */
	@SuppressWarnings("unchecked")
	public static Object getStringStr(String str, int index)
	{
		Vector<String> reStr = new Vector<String>();
		Object obj = getStringNumber(str, 0);
		// no number or str is null
		if (obj == null)
		{
			if (index > 1)
			{
				return null;
			}
			return str;
		}
		else
		{
			for (int i = 0; i < ((Vector) obj).size(); i++)
			{
				int indexOfString = str.indexOf((String) ((Vector) obj).elementAt(i));
				if (indexOfString != 0)
				{
					reStr.addElement(str.substring(0, indexOfString));
				}
				str = str.substring(indexOfString + ((String) ((Vector) obj).elementAt(i)).length());
			}
			if (str.length() != 0)
			{
				reStr.addElement(str);
			}

		}

		// return as the index
		if (index <= 0)
		{
			return reStr;
		}
		if (index > reStr.size())
		{
			return null;
		}
		return reStr.elementAt(index - 1);

	}

	/**
	 * Input is a string and the index of which position you want to get from the dataArray
	 * Return a object: null or object or Vector
	 * index 0 : return a Vector which include all intArray
	 * note : if index out of bouds then return null
	 * useage(please first judge not null) : int i = Integer.parseInt((String)StringUtil.getStringNumber("asjfdkla3.asfa4",1));
	 */
	public static Object getStringNumber(String str, int index)
	{
		if (str == null)
		{
			return null;
		}
		char[] ch = str.toCharArray();
		int i;
		String tempStr = "";
		Vector<String> returnNumber = new Vector<String>();

		for (i = 0; i < str.length(); i++)
		{
			if (Character.isDigit(ch[i]))
			{
				tempStr += ch[i];
			}
			else
			{

				if (!tempStr.equals(""))
				{
					returnNumber.addElement(tempStr);
				}
				tempStr = "";
			}
		}
		if (!tempStr.equals(""))
		{
			returnNumber.addElement(tempStr);
		}

		if (returnNumber.isEmpty() || index > returnNumber.size())
		{
			return null;
		}
		else
		{
			if (index <= 0)
			{
				return returnNumber;
			}
			else
			{
				return returnNumber.elementAt(index - 1);
			}
		}

	}

	/**
	 * 检验一个String是否是一个数字（例如：7897897->true,789t67 ->false）。注意：如果是一个负数，将会返回false.
	 * Return true if the string represents a number
	 * i.e. 7897897
	 * @param str 你想校验的字符串。
	 * @return 如果是一个标准的数字，将返回true，否则返回false。
	 */
	public static boolean isNum(String str)
	{
		if (str == null || str.length() <= 0)
		{
			return false;
		}
		char[] ch = str.toCharArray();

		for (int i = 0; i < str.length(); i++)
		{
			if (!Character.isDigit(ch[i]))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * 检验一个String是否是一个正实数（例如：7897.897 or 7465354->true,78.9t67 ->false）。注意：如果是一个负数，将会返回false.
	 * Return true if the string represents a real number
	 * i.e. 3.0897
	 * @param 你想校验的字符串。
	 * @return 如果是一个标准的实数，将返回true，否则返回false。
	 */
	public static boolean isNumEx(String str)
	{
		if (str == null || str.length() <= 0)
		{
			return false;
		}
		char[] ch = str.toCharArray();

		for (int i = 0, comcount = 0; i < str.length(); i++)
		{
			if (!Character.isDigit(ch[i]))
			{
				if (ch[i] != '.')
				{
					return false;
				}
				else if (i == 0 || i == str.length() - 1)
				{
					return false; // .12122 or 423423.  is not a real number
				}
				else if (++comcount > 1)
				{
					return false; // 12.322.23 is not a real number
				}
			}
		}
		return true;
	}

	/**
	 * Return the replaced string
	 * 在一个字符串中替换等长的子串。注意：sOld必须和sNew等长。
	 * For example: strnew = replaceEq("hello the world","he","65"); the strnew now is "65llo t65 world";
	   * Replace the string with another string,these two string's length must equal
	 * @param sReplace 你想进行替换操作的字符串。
	 * @param sOld 将要被替换掉的字符串。
	 * @param sNew 将要替换上去得字符串。
	 * @return 返回替换以后的字符串，如果替换不成功将返回原始的字符串。
	 */
	public static String replaceStrEq(String sReplace, String sOld, String sNew)
	{

		if (sReplace == null || sOld == null || sNew == null)
		{
			return null;
		}

		int iLen = sReplace.length();
		int iLenOldStr = sOld.length();
		int iLenNewStr = sNew.length();
		if (iLen <= 0 || iLenOldStr <= 0 || iLenNewStr <= 0)
		{
			return sReplace;
		}
		if (iLenOldStr != iLenNewStr)
		{
			return sReplace;
		}

		int[] iIndex = new int[iLen];
		iIndex[0] = sReplace.indexOf(sOld, 0);
		if (iIndex[0] == -1)
		{
			return sReplace;
		}

		int iIndexNum = 1; //get the iIndex of all sOld substring
		while (true)
		{
			iIndex[iIndexNum] = sReplace.indexOf(sOld, iIndex[iIndexNum - 1] + iLenOldStr);
			if (iIndex[iIndexNum] == -1)
			{
				break;
			}
			iIndexNum++;
		}
		char[] caReplace = sReplace.toCharArray();
		char[] caNewStr = sNew.toCharArray();
		for (int i = 0; i < iIndexNum; i++)
		{
			for (int j = 0; j < iLenOldStr; j++)
			{
				caReplace[j + iIndex[i]] = caNewStr[j];
			}
		}

		return new String(caReplace);
	}

	/**
	 * Return the replaced string
	 * 在一个字符串中替换子串。
	 * For example: strnew = replaceEq("hello the world","he","good"); the strnew now is "goodllo tgood world";
	   * Replace the string with another string,these two string's length must equal
	 * @param sReplace 你想进行替换操作的字符串。
	 * @param sOld 将要被替换掉的字符串。
	 * @param sNew 将要替换上去的字符串。
	 * @return 返回替换以后的字符串，如果替换不成功将返回原始的字符串。
	 */
	public static String replaceStrEx(String sReplace, String sOld, String sNew, boolean bFirstString)
	{
		if (bFirstString == true)
		{

			if (sReplace == null || sOld == null || sNew == null)
			{
				return null;
			}

			int iLen = sReplace.length();
			int iLenOldStr = sOld.length();
			int iLenNewStr = sNew.length();
			if (iLen <= 0 || iLenOldStr <= 0 || iLenNewStr < 0)
			{
				return sReplace;
			}

			//get the first sOld string index
			int iIndex = sReplace.indexOf(sOld, 0);
			if (iIndex == -1)
			{
				return sReplace;
			}

			//get all sub string split by first sOld,and strored in a vector
			Vector<String> vStore = new Vector<String>();
			String sub = sReplace.substring(0, iIndex);
			if (sub != null)
			{
				vStore.add(sub);
			}
			vStore.add(sReplace.substring(iIndex + iLenOldStr, iLen));

			//contact all sub string with sNew
			StringBuffer sbReplaced = new StringBuffer("");
			int i = 0;

			for (i = 0; i < vStore.size() - 1; i++)
			{
				sbReplaced.append(vStore.get(i) + sNew);
			}
			sbReplaced.append(vStore.get(i));

			return sbReplaced.toString();
		}
		else
		{
			return replaceStrEx(sReplace, sOld, sNew);
		}
	}

	/**
	 * Return the replaced string
	 * 在一个字符串中替换子串。
	 * For example: strnew = replaceEq("hello the world","he","good"); the strnew now is "goodllo tgood world";
	   * Replace the string with another string,these two string's length must equal
	 * @param sReplace 你想进行替换操作的字符串。
	 * @param sOld 将要被替换掉的字符串。
	 * @param sNew 将要替换上去的字符串。
	 * @return 返回替换以后的字符串，如果替换不成功将返回原始的字符串。
	 */
	public static String replaceStrEx(String sReplace, String sOld, String sNew)
	{
		if (sReplace == null || sOld == null || sNew == null)
		{
			return null;
		}

		int iLen = sReplace.length();
		int iLenOldStr = sOld.length();
		int iLenNewStr = sNew.length();
		if (iLen <= 0 || iLenOldStr <= 0 || iLenNewStr < 0)
		{
			return sReplace;
		}

		//get the first sOld string index
		int[] iIndex = new int[iLen];
		iIndex[0] = sReplace.indexOf(sOld, 0);
		if (iIndex[0] == -1)
		{
			return sReplace;
		}

		//get all sOld string index
		int iIndexNum = 1; //get the iIndex of all sOld substring
		while (true)
		{
			iIndex[iIndexNum] = sReplace.indexOf(sOld, iIndex[iIndexNum - 1] + iLenOldStr);
			if (iIndex[iIndexNum] == -1)
			{
				break;
			}
			iIndexNum++;
		}

		//get all sub string split by sOld,and strored in a vector
		Vector<String> vStore = new Vector<String>();
		String sub = sReplace.substring(0, iIndex[0]);
		if (sub != null)
		{
			vStore.add(sub);
		}
		int i = 1;
		for (i = 1; i < iIndexNum; i++)
		{
			vStore.add(sReplace.substring(iIndex[i - 1] + iLenOldStr, iIndex[i]));
		}
		vStore.add(sReplace.substring(iIndex[i - 1] + iLenOldStr, iLen));

		//contact all sub string with sNew
		StringBuffer sbReplaced = new StringBuffer("");
		for (i = 0; i < iIndexNum; i++)
		{
			sbReplaced.append(vStore.get(i) + sNew);
		}
		sbReplaced.append(vStore.get(i));

		return sbReplaced.toString();
	}

	/**
	 * 计算一个string中包含特定的子串的数量，
	 * 例如countSubstring("hello,the world!","he")将返回2。
	 * @param str 要被计算的string
	 * @param subStr 要统计的子串
	 * @return 返回子串的数量
	 */
	public static int countSubstring(String str, String subStr)
	{

		if (str == null || str.length() <= 0 || subStr == null || subStr.length() <= 0)
		{
			return 0;
		}

		int iLen = subStr.length();
		int[] iIndex = new int[str.length()];
		iIndex[0] = str.indexOf(subStr, 0);

		int iIndexNum = 1;
		while (true)
		{
			iIndex[iIndexNum] = str.indexOf(subStr, iIndex[iIndexNum - 1] + iLen);
			if (iIndex[iIndexNum] == -1)
			{
				break;
			}
			iIndexNum++;
		}
		return iIndexNum;
	}

	/**
	 *根据分割符分割字符串。
	 *Return String[],after split
	 *Split a String by a splitter(such as ",","hai",...)
	 * @param sStr 将要被分割的字符串。
	 * @param sSplitter 分割符。
	 * @return 一个含有分割好的字符串的数组。如果分割失败将返回null,如果字符串中没有包含指定的分割符，
	 * 将返回只有一个元素的字符串数组，这个元素就是该字符串本身。如果这个字符串只含有分割符，将返回null。
	 */
	public static String[] splitStr(String sStr, String sSplitter)
	{

		if (sStr == null || sStr.length() <= 0 || sSplitter == null || sSplitter.length() <= 0)
		{
			return null;
		}

		String[] saRet = null;
		int iLen = sSplitter.length();
		//we alloc the max length of int[] need,because a vector may be used more memory
		int[] iIndex = new int[sStr.length()];
		iIndex[0] = sStr.indexOf(sSplitter, 0);
		if (iIndex[0] == -1)
		{
			saRet = new String[1];
			saRet[0] = sStr;
			return saRet;
		}

		int iIndexNum = 1; //get the iIndex of all splitter substring
		while (true)
		{
			iIndex[iIndexNum] = sStr.indexOf(sSplitter, iIndex[iIndexNum - 1] + iLen);
			if (iIndex[iIndexNum] == -1)
			{
				break;
			}
			iIndexNum++;
		}

		Vector<String> vStore = new Vector<String>();

		int i = 0;
		String sub = null;
		for (i = 0; i < iIndexNum + 1; i++)
		{
			if (i == 0)
			{
				sub = sStr.substring(0, iIndex[0]);
			}
			else if (i == iIndexNum)
			{
				sub = sStr.substring(iIndex[i - 1] + iLen, sStr.length());
			}
			else
			{
				sub = sStr.substring(iIndex[i - 1] + iLen, iIndex[i]);
			}

			if (sub != null && sub.length() > 0)
			{
				vStore.add(sub);
			}
		}
		if (vStore.size() <= 0)
		{
			return null; //only split,none string
		}
		saRet = new String[vStore.size()];
		Enumeration<String> e = vStore.elements();
		for (i = 0; e.hasMoreElements(); i++)
		{
			saRet[i] = e.nextElement();
		}

		return saRet;
	}

	/**
	 * 将一个字符串数组中的字符串根据一个连接符连成一个字符串。
	 * Return String,after contact
	 * contact a String[] with a contacter(such as ",")
	 * @param saStr 要进行连接操作的字符串数组。
	 * @param sContacter 连接符。
	 * @return 连接完成后的字符串，字符串最后没有连接符。如果连接失败将返回null。如果该数组只有一个元素，将只返回该元素。
	 */
	public static String contactStr(String[] saStr, String sContacter)
	{
		if (saStr == null || saStr.length <= 0 || sContacter == null || sContacter.length() <= 0)
		{
			return null;
		}
		StringBuffer sRet = new StringBuffer("");
		for (int i = 0; i < saStr.length; i++)
		{
			if (i == saStr.length - 1)
			{
				sRet.append(saStr[i]);
			}
			else
			{
				sRet.append(saStr[i] + sContacter);
			}
		}
		return sRet.toString();
	}

	/**
	 * 将一个整型数组中的数字根据一个连接符连成一个字符串。
	 * Return String after contact
	 * contact a int[] with a contacter(such as ",")
	 * @param saStr 要进行连接操作的整型数组。
	 * @param sContacter 连接符。
	 * @return 连接完成后的字符串，字符串最后没有连接符。如果连接失败将返回null。如果该数组只有一个元素，将只返回该元素。
	 */
	public static String contactStr(int[] saStr, String sContacter)
	{

		if (saStr == null || saStr.length <= 0 || sContacter == null || sContacter.length() <= 0)
		{
			return null;
		}
		StringBuffer sRet = new StringBuffer("");
		for (int i = 0; i < saStr.length; i++)
		{
			if (i == saStr.length - 1)
			{
				sRet.append(new Integer(saStr[i]));
			}
			else
			{
				sRet.append(new Integer(saStr[i]) + sContacter);
			}
		}
		return sRet.toString();
	}

	/**
	 * 将一个字符串数组中的字符串根据长度排序。
	 * Return String[] after sort
	 * Sort string array by string length
	 * @param saSource 需要排序的字符串。
	 * @param bAsc 如果是true,将按升序排序，如果是false,将按降序排列。
	 * @return 排好序的字符串数组。如果排序失败，将会返回null。
	 */
	public static String[] sortByLength(String[] saSource, boolean bAsc)
	{
		if (saSource == null || saSource.length <= 0)
		{
			return null;
		}
		int iLength = saSource.length;
		String[] saDest = new String[iLength];

		//duplicate source string buffer
		for (int i = 0; i < iLength; i++)
		{
			saDest[i] = saSource[i];
		}

		String sTemp = "";
		int j = 0, k = 0;
		//bubble sort
		for (j = 0; j < iLength; j++)
		{
			for (k = 0; k < iLength - j - 1; k++)
			{ //one time bubble sort
				if (saDest[k].length() > saDest[k + 1].length() && bAsc)
				{ //asc sort
					sTemp = saDest[k];
					saDest[k] = saDest[k + 1];
					saDest[k + 1] = sTemp;
				}
				else if (saDest[k].length() < saDest[k + 1].length() && !bAsc)
				{ //desc sort
					sTemp = saDest[k];
					saDest[k] = saDest[k + 1];
					saDest[k + 1] = sTemp;
				}
			}
		}
		return saDest;
	}

	/**
	 * 删除一个字符串中多余的空格。例如： strNew = compactStr("This    is a   test   !"); the strNew will be "This is a test!".
	 * 注意：该函数不会删除句首的空格。如果你要删除句首的空格可以这么做：
	 * strNew = compactStr(strOld); strNew = strNew==null?null:strNew.trim();
	 * Return String after compact blank.
	 * erase the redundance blank between the words
	 * The blanks at the beginning of string will not be erased!
	 * @param str 要进行压缩的字符串。
	 * @return 如果压缩成功，返回压缩的字符串，否则返回null。
	 */
	public static String compactStr(String str)
	{
		if (str == null)
		{
			return null;
		}
		if (str.length() <= 0)
		{
			return "";
		}
		String sDes = new String(str); //make a copy of source string
		int iBlanksAtStart = 0;
		int iLen = str.length();

		while (sDes.charAt(iBlanksAtStart) == ' ')
		{
			if (++iBlanksAtStart >= iLen)
			{
				break;
			}
		}

		String[] saDes = splitStr(sDes.trim(), " ");
		if (saDes == null)
		{
			return null;
		}

		int i = 0;
		for (i = 0; i < saDes.length; i++)
		{
			saDes[i] = saDes[i].trim();
		}

		sDes = contactStr(saDes, " ");
		StringBuffer sBlank = new StringBuffer("");
		for (i = 0; i < iBlanksAtStart; i++)
		{
			sBlank.append(" ");
		}

		return sBlank.toString() + sDes;
	}

	/**
	 * 将一个字符串中的全角符号全部转换成半角符号。
	 * Convert the SBC symbols in source string to DBC symbols
	 * @param sSource 要转换的字符串。
	 * @return 返回转换好以后的字符串，如果转换失败将返回原始字符串。
	 */
	public static String symbolSBCToDBC(String sSource)
	{
		if (sSource == null || sSource.length() <= 0)
		{
			return sSource;
		}
		int iLen = SBC.length < DBC.length ? SBC.length : DBC.length;
		for (int i = 0; i < iLen; i++)
		{
			sSource = replaceStrEx(sSource, SBC[i], DBC[i]);
		}
		return sSource;
	}

	/**
	 * 将一个字符串中的半角符号全部转换成全角符号。
	 * Convert the SBC symbols in source string to DBC symbols
	 * @param sSource 要转换的字符串。
	 * @return 返回转换好以后的字符串，如果转换失败将返回原始字符串。
	 */
	public static String symbolDBCToSBC(String sSource)
	{
		if (sSource == null || sSource.length() <= 0)
		{
			return sSource;
		}
		int iLen = SBC.length < DBC.length ? SBC.length : DBC.length;
		for (int i = 0; i < iLen; i++)
		{
			sSource = replaceStrEx(sSource, DBC[i], SBC[i]);
		}
		return sSource;
	}

	/**
	 * Get the email error message string by error code.
	 * @param iErrCode error code
	 * @return Return the string describe the error,if the error code is unknown  return the "Unkonwn error!" string.
	 */
	public static String getMailErrMsg(int iErrCode)
	{
		if (iErrCode >= 0 && iErrCode < emailErrMsg.length)
		{
			return emailErrMsg[iErrCode];
		}
		return "Unkown error!";
	}

	/**
	 * 校验一个email地址字符串是否合法。
	 * vertify a email address
	 * @param Email地址字符串。
	 * @return 如果这是一个合法的Email地址，将返回0，否则返回错误代码，可以用getEmailErrMsg(int errCode)来得到当前的错误信息。
	 */
	public static int isEmailAddress(String str)
	{
		if (str == null || str.length() <= 0)
		{
			return 10;
		}
		//int iCommonCount = 0;
		int iAltCount = 0;
		int iSymbol = 0;
		int j;
		char[] chEmail = str.trim().toCharArray();
		for (int i = 0; i < chEmail.length; i++)
		{
			j = 0;
			while (j < caIllegalEmailChar.length)
			{
				if (chEmail[i] == caIllegalEmailChar[j])
				{ //find illegal char in email address
					return 1;
				}
				if (chEmail[i] > 127)
				{
					return 2; //find HZ in email address
				}

				j++;
			}
			if (chEmail[i] == '.')
			{ //common at the beginning or end of email address
				if (i == 0 || i == chEmail.length - 1)
				{
					return 3;
				}
			}
			else if (chEmail[i] == '@')
			{ //find '@' more than one
				if (++iAltCount > 1)
				{
					return 4;
				}
				if (i == 0 || i == chEmail.length - 1)
				{ // @ char at the beginning or end of email address
					return 5;
				}
			}
			if (chEmail[i] < 48 || chEmail[i] > 57 && chEmail[i] < 65 || chEmail[i] > 90 && chEmail[i] < 97
					|| chEmail[i] > 122)
			{ //is not number and letter
				if (iSymbol + 1 == i)
				{
					return 6;
				}
				iSymbol = i;
				if (iSymbol == 0 || iSymbol == chEmail.length - 1)
				{ //two symbol join together.
					return 7;
				}
			}
		}
		if (str.indexOf('.') < str.indexOf('@'))
		{
			return 8;
		}

		if (iAltCount == 0)
		{
			return 10;
		}
		return 0;
	}

	/**
	 * print errmsg and exit program.
	 */
	protected static void out()
	{
		final String sUsage = "StringUtil -command [param1] [param2] [param3]";
		System.out.println(sUsage);
		System.exit(0);
	}

	/**
	 * Format Date into which format you define
	 * @param date(java.util.Date)
	 * @param format(String)
	 * @return String
	 * example formatDate(date, "MMMM dd, yyyy") = July 20, 2000
	 */
	public static String formatDate(Date date, String newFormat)
	{
		if (date == null || newFormat == null)
		{
			return null;
		}
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(newFormat);
		return formatter.format(date);
	}

	/**
	 * get the familiar string from a enumeration,
	 * this method is easiely to process the unknown key words from the CGI form
	 * and get the ids array from the form mixed in the key words
	 * @param  e
	 * @param s
	 * @return Enumeration
	 * example getIdsFromEnu(e1, str), it is used by webhosting cs
	 * author Tommy Qian  mailto:tommyq@alibaba-inc.com
	 */
	@SuppressWarnings("unchecked")
	public static Vector getFamiliarString(Enumeration e, String s)
	{
		if (e == null || s == null)
		{
			return null;
		}
		Vector reString = new Vector();
		for (int i = 0; e.hasMoreElements(); i++)
		{
			String tmp = (String) e.nextElement();
			if (tmp.startsWith(s))
			{
				reString.addElement(tmp);
			}
		}

		if (reString == null)
		{
			return null;
		}
		if (reString.isEmpty())
		{
			return null;
		}
		return reString;
	}

	@SuppressWarnings("unchecked")
	public static int[] getIntArray(Vector e)
	{
		if (e == null)
		{
			return null;
		}
		int[] tmp = new int[e.size()];

		for (int i = 0; i < e.size(); i++)
		{
			String[] sTmp = splitStr((String) e.get(i), "_");
			int len = sTmp.length - 1;
			tmp[i] = new Integer(sTmp[len]).intValue();
		}
		if (tmp == null)
		{
			return null;
		}
		if (tmp.length < 1)
		{
			return null;
		}
		return tmp;
	}

	@SuppressWarnings("unchecked")
	public static int[] getIdsFromEnu(Enumeration e, String s)
	{
		return getIntArray(getFamiliarString(e, s));
	}

	/**
	 * 按照英文习惯使字符串自动分行功能, 即找指定长度后的第一个空格进行换行
	 * @param sOld: 传入的字符串
	 * @param iNum: 大概多少字符串为一行
	 * @return String
	 */
	public static String wrapEnStr(String sOld, int iNum)
	{
		if (sOld == null || sOld.length() < iNum)
		{
			return sOld;
		}

		String sSplitter = "\n";
		int iEnd = 0;
		int iLen = 0;
		int iStart = 0;
		boolean bTrue = true;
		int j = 0;
		StringBuffer sbTemp = new StringBuffer();
		StringBuffer sbRtn = new StringBuffer();
		String[] sNew = splitStr(sOld, sSplitter);
		for (String element : sNew)
		{
			iLen = element.length();

			if (iLen <= iNum)
			{
				sbRtn.append(element);
				continue;
			}
			j = 0;
			sbTemp.delete(0, sbTemp.length());
			while (bTrue)
			{
				iEnd = element.indexOf(" ", (j + 1) * iNum);
				if (iEnd > 0)
				{
					sbTemp.append(element.substring(iStart, iEnd)).append("\n");
					iStart = iEnd + 1;
					j++;
				}
				else
				{
					sbTemp.append(element.substring(iStart, iLen)).append("\n");
					iStart = 0;
					bTrue = false;
				}
			}
			bTrue = true;
			sbRtn.append(sbTemp.toString());
		}
		return sbRtn.toString();
	}

	/** this method is to  put Sting array  to int array
	 * @param  sChecked
	 * @return  int[]
	 * author tommy & michael
	 */

	public static int[] str2Int(String[] sChecked)
	{
		if (sChecked == null || sChecked.length <= 0)
		{
			return null;
		}

		int[] iChecked = new int[sChecked.length];

		for (int i = 0; i < sChecked.length; i++)
		{
			iChecked[i] = new Integer(sChecked[i]).intValue();
		}
		return iChecked;
	}

	/**
	 * This function print exception stack trace info to a string writer and
	 * return String value contain the stack tracd info
	 * @param	e	the exception contain the stack trace info
	 * @return	the string value
	 */
	public static String getStackTrace(Exception e)
	{
		java.io.StringWriter sw = new java.io.StringWriter();
		java.io.PrintWriter pw = new java.io.PrintWriter(sw);
		e.printStackTrace(pw);
		sw.flush();
		return sw.toString();
	}

	/**
	 * This function get the UTF encode lenght of a string
	 * @param str the string before UTF encode
	 * @return the length of UTF encoded string
	 */
	public static int getUTFLength(String str)
	{
		int strlen = str.length();
		int utflen = 0;
		char[] charr = new char[strlen];
		int c;//count = 0;

		str.getChars(0, strlen, charr, 0);

		for (int i = 0; i < strlen; i++)
		{
			c = charr[i];
			if (c >= 0x0001 && c <= 0x007F)
			{
				utflen++;
			}
			else if (c > 0x07FF)
			{
				utflen += 3;
			}
			else
			{
				utflen += 2;
			}
		}

		return utflen;
	}

	public static int indexOfColSep(String line)
	{
		return indexOfColSep(line, 0);
	}

	public static int indexOfColSep(String line, int from)
	{
		int index = line.indexOf('\t', from);
		//		if (index < 0) index = line.indexOf(' ', from);
		if (index < 0)
		{
			index = line.length();
		}

		return index;
	}

	public static int indexOfCol(String line)
	{
		return indexOfCol(line, 0);
	}

	public static int indexOfCol(String line, int from)
	{
		int len = line.length();
		if (from >= len || from < 0)
		{
			return -1;
		}

		//int index = -1;
		//int ret;

		char[] charr = line.toCharArray();
		for (; from < len; from++)
		{
			if (charr[from] != '\t' /* && charr[from] != ' '*/)
			{
				break;
			}
		}

		if (from == len)
		{
			return -1;
		}
		return from;
	}

	/**
	 * Get column dates from a line
	 * line format should be this:
	 * column1	column2	column3	...
	 * @param	lineData  string line contain each column of data
	 * @return	lineData  the String array of column data
	 */
	@SuppressWarnings("unchecked")
	public static Vector getColumnData(String lineData)
	{
		if (lineData == null)
		{
			return null;
		}
		int len = lineData.length();
		if (len < 1)
		{
			return null;
		}

		int col = 0;
		int sep = 0;
		Vector v = new Vector();
		while ((sep = indexOfColSep(lineData, col)) < len)
		{
			v.addElement(lineData.substring(col, sep));
			col = indexOfCol(lineData, sep);
			if (col == -1)
			{
				return v;
			}
		}
		v.addElement(lineData.substring(col, sep));

		return v;
	}

	/**
	 * 完成从数组到Hashtable的转变
	 * @param sources
	 * @return Hashtable
	 */
	@SuppressWarnings("unchecked")
	public static Hashtable strArr2Hash(String[] sources)
	{
		if (sources == null)
		{
			return null;
		}
		Hashtable ht = new Hashtable();

		if (sources.length % 2 == 0)
		{
			for (int i = 0; i < sources.length; i++)
			{
				ht.put(sources[i++], sources[i]);
			}
		}
		else
		{
			return null;
		}
		return ht;
	}

	/**
	 * 传入一串以+1+2+3+4+5的格式组成的代表年月日小时分钟的字符串，返回时间
	 * 其中年月日是相对时间，小时分钟是确切的值，如果不符合则返回当前时间；
	 * 例如：-1+2-3+12+30代表的是上一年的下二个月的上三天的12:30分
	 * @param time : 格式化的时间字符串
	 * @return Date
	 */
	public static Date str2Date(String time)
	{
		if (time == null || time.length() == 0)
		{
			return new Date();
		}

		String tmp = time;
		tmp = replaceStrEx(tmp, "+", ";");
		tmp = replaceStrEx(tmp, "-", ";-");
		String[] tmp_array = splitStr(tmp.substring(1), ";");

		if (tmp_array.length < 5)
		{
			return new Date();
		}

		int[] tem_int_array = str2Int(tmp_array);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, tem_int_array[0]);
		cal.add(Calendar.MONTH, tem_int_array[1]);
		cal.add(Calendar.DAY_OF_MONTH, tem_int_array[2]);
		cal.set(Calendar.HOUR_OF_DAY, tem_int_array[3]);
		cal.set(Calendar.MINUTE, tem_int_array[4]);
		return cal.getTime();
	}

	/**
	 * 完成上面方法str2Date反向解析
	 * @param date
	 * @return String
	 */
	public static String date2Str(Date date)
	{
		if (date == null)
		{
			return null;
		}
		Calendar cal_standard = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		StringBuffer sb = new StringBuffer();
		int in = 0;
		String tmp = new String();
		in = cal.get(Calendar.YEAR) - cal_standard.get(Calendar.YEAR);
		tmp = in >= 0 ? "+" + in : String.valueOf(in);
		sb.append(tmp);
		in = cal.get(Calendar.MONTH) - cal_standard.get(Calendar.MONTH);
		tmp = in >= 0 ? "+" + in : String.valueOf(in);
		sb.append(tmp);
		in = cal.get(Calendar.DATE) - cal_standard.get(Calendar.DATE);
		tmp = in >= 0 ? "+" + in : String.valueOf(in);
		sb.append(tmp);
		sb.append("+" + cal.get(Calendar.HOUR_OF_DAY));
		sb.append("+" + cal.get(Calendar.MINUTE));

		return sb.toString();
	}

	/**
	 * This function enCode for webhosting
	 * @param str
	 * @return String
	 */
	public static String encode(String str)
	{

		if (str == null)
		{
			return "";
		}

		try
		{
			return java.net.URLEncoder.encode(str, "UTF-8");//, "UTF-8");
			//return (str); //, "UTF-8");
		}
		catch (Exception e)
		{
			//Log.log.fatal(" encode failed ");
			return str;
		}
	}

	public static String decode(String str)
	{
		System.out.println("字符串:" + str);
		if (str == null)
		{
			return "";
		}

		try
		{
			return java.net.URLDecoder.decode(str, "utf-8");//, "utf-8");
			//return (str); //, "UTF-8");
		}
		catch (Exception e)
		{
			//Log.log.fatal(" encode failed ");
			return str;
		}
	}

	public static String removeLoadingDivStr()
	{
		String t = "<script language=\"javascript\">  var loadingDiv = document.getElementById(\"_jydiv_loading\");  if(loadingDiv)  	loadingDiv.style.display=\"none\";</script>";
		return t;
	}

	public static String ajaxDecode(javax.servlet.http.HttpServletRequest req, String paraName)
	{
		return decode(req.getParameter(paraName));
	}

	/**
	 * 得到随机产生的字符串
	 * @param strLength 所需字符串长度
	 * @return String
	 */
	public static String getRandomStr(int strLength)
	{
		java.util.Random randomobj = random; //new java.util.Random();
		int c;
		String cc = "";
		int i = 0;
		while (i < strLength)
		{
			c = randomobj.nextInt(122);
			if (c >= 97 || c >= 65 && c <= 90 || c <= 57 && c >= 48 || c == 95)
			{ //a:97  z:122  48:0 57:9  65:A 90:Z 95:_
				cc += (char) c;
				i++;
			}
		}
		return cc;
	}

	/**
	 * 得到随机数
	 * @param length int
	 * @return int
	 */
	public static int getRandomInt(int length)
	{
		if (length > 9)
		{
			length = 10;
		}
		//java.util.Random randombj = random;
		int t = (int) Math.pow(10, length);
		t = random.nextInt(t);
		while (t / (int) Math.pow(10, length - 1) == 0)
		{
			t *= 10;
		}
		return t;
	}

	//add by yxsong
	public static String[] splitStr(String sStr, char sSplitter)
	{
		if (sStr == null || sStr.length() <= 0)
		{
			return null;
		}

		String[] saRet = null;
		//we alloc the max length of int[] need,because a vector may be used more memory
		int lenStr = sStr.length();
		int[] iIndex = new int[lenStr];
		iIndex[0] = sStr.indexOf(sSplitter, 0);
		if (iIndex[0] == -1)
		{
			saRet = new String[1];
			saRet[0] = sStr;
			return saRet;
		}

		int iIndexNum = 1; //get the iIndex of all splitter substring
		int numChildStr = 0;
		if (iIndex[0] != 0)
		{
			numChildStr++;
		}
		while (true)
		{
			iIndex[iIndexNum] = sStr.indexOf(sSplitter, iIndex[iIndexNum - 1] + 1);

			if (iIndex[iIndexNum] != iIndex[iIndexNum - 1] + 1 && iIndex[iIndexNum - 1] < lenStr - 1)
			{
				numChildStr++;
			}
			if (iIndex[iIndexNum] == -1)
			{
				break;
			}
			iIndexNum++;

		}

		saRet = new String[numChildStr];
		numChildStr = 0;
		int i = 0;
		String sub = null;
		for (i = 0; i < iIndexNum + 1; i++)
		{
			if (i == 0)
			{
				sub = sStr.substring(0, iIndex[0]);
			}
			else if (i == iIndexNum)
			{
				sub = sStr.substring(iIndex[i - 1] + 1, lenStr);
			}
			else
			{
				sub = sStr.substring(iIndex[i - 1] + 1, iIndex[i]);
			}

			if (sub != null && sub.length() > 0)
			{
				saRet[numChildStr++] = sub;
			}
		}

		return saRet;
	}

	//add by yxsong for ebs
	public static String replaceStrForSearch(String wholeStr, String oldStr, String newStr)
	{
		if (wholeStr == null)
		{
			return wholeStr;
		}
		String[] strArray = splitStr(wholeStr, "\n");

		for (int i = 0; i < strArray.length; i++)
		{

			if (strArray[i].indexOf(oldStr + "=") > -1 && !newStr.equals(""))
			{
				strArray[i] = oldStr + "=" + newStr;

				break;
			}
		}
		wholeStr = contactStr(strArray, "\n");
		return wholeStr;
	}

	/**
	 * 将中文字符串截取一定的数量，不出现问号
	 * @param str   要截取的字符串
	 * @param num   截取位数
	 * @return String   截取完成的字符串
	 */
	public static String getSubStr(String str, int num)
	{
		byte strByte[] = new byte[num];
		int flag = 0;
		String lastStr = null;

		strByte = str.getBytes();

		for (int i = 0; i < num; i++)
		{
			if (strByte[i] >= 32 && strByte[i] <= 128)
			{
				flag++;
			}
			if (flag % 2 == 0)
			{
				lastStr = str.substring(0, num);
			}
			else
			{
				lastStr = str.substring(0, num + 1);
			}
		}
		return lastStr;
	}

	/**
	 *  将文件转成String
	 * @param  fInputFile 传入的文件
	 * @return String 传出的字符串
	 * @deprecated
	 */
	@Deprecated
	public static String fileToString(File fInputFile)
	{
		try
		{
			long nLength = fInputFile.length();

			if (nLength == 0L)
			{
				return "file length is 0";
			}

			FileInputStream is = new FileInputStream(fInputFile);
			byte[] b = new byte[(int) nLength];
			is.read(b);
			is.close();

			return new String(b);
		}
		catch (Exception e)
		{
			//Log.log.fatal("ArticleContent.class is wrong: "+e);
			return null;
		}

	}

	/**
	 * 把传入的字符变成Ascii格式的
	 * @param para 要转换的字符串
	 * @return String
	 */
	public static String toAsciiString(String para)
	{

		if (para == null)
		{
			return null;
		}
		//return  URLEncoder.encode(para);
		return para;
	}

	/**
	 * 把传入的Ascii格式字符转成普通格式的
	 * @param para 要转换的字符串
	 * @return String
	 */
	public static String getAsciiString(String para)
	{
		if (para == null)
		{
			return null;
		}

		//return URLDecoder.decode(para.trim());
		return para.trim();
	}

	/**
	 * 将字符串进行转码.如把"GB2312"编码的字符串转成"ISO8859-1"编码的
	 * @param InterString	需要转换的字符串
	 * @param oldCoding      老的编码方式
	 * @param newCoding      新的编码方式
	 * @return String		转换后的字符串
	 */
	public static String transCoding(String InterString, String oldCoding, String newCoding)
	{

		if (InterString == null || InterString.equals(""))
		{
			return null;
		}

		try
		{
			String last = new String(InterString.getBytes(oldCoding), newCoding);
			return last;
		}
		catch (Exception e)
		{
			//Log.log.fatal(" transCoding failed "+e);
			return InterString;
		}
	}

	/**
	 * 将字符串转换为HTML格式的字符串
	 * @param NormalString	需要转换的字符串
	 * @return String  转换后可被Browser显示的字符串
	 */
	public static String toHTMLString(String NormalString)
	{

		if (NormalString == null || NormalString.equals(""))
		{
			return null;
		}
		int SourceLength = NormalString.length();

		//Initialize the buffer for return value
		StringBuffer HTMLString = new StringBuffer(SourceLength);

		for (int cnt = 0; cnt < SourceLength; cnt++)
		{
			char ch = NormalString.charAt(cnt);
			switch (ch)
			{
				case '\n':
					HTMLString.append(HTML_BreakLine);
					break;
				case '\r':
					break;
				case ' ':
					HTMLString.append(HTML_Space);
					break;
				case '<':
					HTMLString.append(HTML_Less);
					break;
				case '>':
					HTMLString.append(HTML_Greater);
					break;

				default:
					HTMLString.append(ch);
			}
		}

		return HTMLString.toString();

	}

	public static String webcallEncrypt(String s)
	{
		if (s == null)
		{
			return null;
		}
		String result = "";
		for (int i = 0; i < s.length(); i = i + 2)
		{
			int j = Integer.parseInt(s.substring(i, i + 2), 16);
			if (j == 0)
			{
				result = result + (char) Integer.parseInt("1a", 16);
			}
			else
			{
				result = result + (char) j;
			}
		}
		return result;
	}

	public static String URLEncode(String source)
	{
		String newStr = null;
		try
		{
			if (source == null)
			{
				return null;
			}
			else
			{
				newStr = URLEncoder.encode(source, "UTF-8");
			}
			if (newStr.length() > 150)
			{ //中文名超过16
				newStr = new String(source.getBytes(), "ISO8859-1");
			}
		}
		catch (Exception ex)
		{
		}
		return newStr;
	}

	public static String URLDecode(String source)
	{
		try
		{
			if (source == null)
			{
				return null;
			}
			else
			{
				return URLDecoder.decode(source, "UTF-8");
			}
		}
		catch (Exception ex)
		{

		}
		return null;

	}

	/*
	public static String[] getStringParts(String resource, String seperator) {
	if (resource == null)
	  return null;
	if (seperator == null)
	  return null;
	StringTokenizer token = new StringTokenizer(resource, seperator);
	String[] result = new String[token.countTokens()];
	for (int i = 0; i < result.length; i++) {
	  result[i] = token.nextToken();
	}
	return result;
	}

	/**
	 * 获得有固定间隔符隔开的多个字符串组成字串中的第几个部分
	 * 如"11^|^222^|^33333^|^444444^|^5555555"中的5部分中的某一个。
	 * @param resource
	 * @param seperator
	 * @param index
	 * @return
	 */
	/*
	public static String getStr(String resource, String seperator, int index) {
	if (resource == null)
	  return null;
	if (seperator == null)
	  return null;
	StringTokenizer token = new StringTokenizer(resource, seperator);
	String result = null;
	if (token.countTokens() >= index) {
	  for (int i = 1; i < index; i++) {
	    token.nextToken();
	  }
	  result = token.nextToken();
	}
	return result;
	}
	*/
	public static int getStrCount(String resource, String seperator)
	{
		if (resource == null)
		{
			return -1;
		}
		if (seperator == null)
		{
			return -1;
		}
		StringTokenizer token = new StringTokenizer(resource, seperator);

		return token.countTokens();
	}

	/**
	 * main entrance
	 */
	public static void main1(String[] args)
	{
		if (args == null || args.length < 1)
		{
			out();
		}

		if (args[0].equalsIgnoreCase("-replaceex"))
		{
			if (args.length < 4)
			{
				out();
			}

		}
		else if (args[0].equalsIgnoreCase("-compact"))
		{

		}
		else if (args[0].equalsIgnoreCase("-split"))
		{
			if (args.length < 3)
			{
				out();
			}
			System.err.println("test");
			//String[] sa = splitStr(args[1], args[2]);
			//for(int i=0;i<sa.length;i++)
			//Log.log.fatal(sa[i]+"\n");
		}
		else if (args[0].equalsIgnoreCase("-email"))
		{
			if (args.length < 2)
			{
				out();
			}
			System.err.println("email: " + args[1] + " ==>" + getMailErrMsg(isEmailAddress(args[1])));
		}
		else
		{
			//Log.log.fatal("Unknown parameter!");
		}
	}

	/**
	 * 判断date字符串是否是符合fmt的日期字符串
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static boolean isDate(String date, String fmt)
	{
		boolean result = false;
		if (date == null || fmt == null)
		{
			return result;
		}
		try
		{
			Date d = strToDateTime(date, fmt);
			String newDate = dateTimeToStr(d, fmt);
			if (newDate.equals(date))
			{
				result = true;
			}
		}
		catch (ParseException ex)
		{
			//Log.log.warn("日期格式不对：" + date + " 格式" + fmt);
		}
		return result;
	}

	/**
	 * 日期类型转换成字符串类型
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateTimeToStr(Date date, String format)
	{
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		return bartDateFormat.format(date);
	}

	/**
	 * 字符串类型转换成日期类型
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDateTime(String str, String format) throws ParseException
	{
		try
		{
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
			return bartDateFormat.parse(str);
		}
		catch (ParseException ex)
		{
			throw ex;
		}
	}

	/**
	 * 如果是0，则显示空格；否则将数值以指定的format格式化
	 * @param number
	 * @param format
	 * @return
	 */
	public static String numberToStringWebEmpty(String number, String format)
	{
		if (number == null || number.equals("") || number.equals("&nbsp;"))
		{
			return HTML_Space;
		}
		try
		{
			double d = Double.parseDouble(number);
			return numberToStringWebEmpty(d, format);
		}
		catch (Exception ex)
		{
			System.out.println("解析出错:" + number);
			return "解析出错:" + number;
		}
	}

	public static String numberToStringWebEmpty(double number, String format)
	{
		if (Math.abs(number) < 0.000001)
		{
			return HTML_Space;
		}
		else
		{
			java.text.DecimalFormat df = new java.text.DecimalFormat(format);
			return df.format(number);
		}
	}

	/**
	 * 将数值以指定的format格式化
	 * @param number
	 * @param format
	 * @return
	 */
	public static String numberToString(String number, String format)
	{
		//"##0.0000"
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(Double.parseDouble(number));
	}

	/**
	 * 将数值以指定的format格式化
	 * @param number
	 * @param format
	 * @return
	 */
	public static String numberToString(double number, String format)
	{
		//"##0.0000"
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(number);
	}

	/**
	 * 获得指定源串中被seperator分隔开的字符串组成的数组。
	 * @param resource
	 * @param seperator
	 * @return
	 */
	public static String[] getStringParts(String resource, String seperator)
	{
		if (resource == null)
		{
			return null;
		}
		if (seperator == null)
		{
			return null;
		}
		int count = 0;
		int position = 0;
		int lastPosition = 0;
		List<String> list = new ArrayList<String>();
		while (true)
		{
			if (count != 0)
			{
				position = resource.indexOf(seperator, lastPosition + seperator.length());
			}
			else
			{
				position = resource.indexOf(seperator);
			}
			if (position >= 0)
			{
				if (count != 0)
				{
					list.add(resource.substring(lastPosition + seperator.length(), position));
					//Log.log.warn(resource.substring(lastPosition + seperator.length(), position));
				}
				else
				{
					list.add(resource.substring(0, position));
					//Log.log.warn(resource.substring(0,position));
				}
				lastPosition = position;
				count++;
			}
			else
			{
				break;
			}
		}
		if (count > 0)
		{
			count++;//认为最后一个分隔符后还有一个
		}
		list.add(resource.substring(lastPosition + seperator.length(), resource.length()));
		//Log.log.warn(resource.substring(lastPosition+seperator.length(),resource.length()));
		String[] result = null;
		if (count > 0)
		{
			result = new String[count];
			for (int i = 0; i < list.size(); i++)
			{
				result[i] = list.get(i);
			}
		}
		return result;
	}

	/**
	 * 获得有固定间隔符隔开的多个字符串组成字串中的第几个部分，从1开始
	 * 如"11^|^222^|^33333^|^444444^|^5555555"中的5部分中的某一个。
	 * @param resource
	 * @param seperator
	 * @param index
	 * @return
	 */
	public static String getStr(String resource, String seperator, int index)
	{
		if (index < 1)
		{
			return null;
		}
		if (resource == null)
		{
			return null;
		}
		if (seperator == null)
		{
			return null;
		}
		int position = 0;
		int lastPosition = 0;
		List<String> list = new ArrayList<String>();
		while (true)
		{
			if (lastPosition != 0)
			{
				position = resource.indexOf(seperator, lastPosition + seperator.length());
			}
			else
			{
				position = resource.indexOf(seperator);
			}
			if (position > 0)
			{
				if (lastPosition != 0)
				{
					list.add(resource.substring(lastPosition + seperator.length(), position));
					//Log.log.warn(resource.substring(lastPosition + seperator.length(), position));
				}
				else
				{
					list.add(resource.substring(0, position));
					//Log.log.warn(resource.substring(0, position));
				}
				lastPosition = position;
			}
			else
			{
				break;
			}
		}
		list.add(resource.substring(lastPosition + seperator.length(), resource.length()));
		//Log.log.warn(resource.substring(lastPosition + seperator.length(),resource.length()));
		String result = null;
		if (index <= list.size())
		{
			result = list.get(index - 1);
		}
		else
		{
			result = null;
		}

		return result;

		/*
		if(resource == null)
		  return null;
		if(seperator == null)
		  return null;
		StringTokenizer token = new StringTokenizer(resource,seperator);
		String result = null;
		if(token.countTokens() >= index){
		  for(int i=1; i<index; i++){
		token.nextToken();
		  }
		  result = token.nextToken();
		}
		return result;
		*/
	}

	/**
	 * 对request传递过来的参数进行转码，以防止中文乱码的出现
	 * @param inStr
	 * @return
	 */
	public static String[] fromRequest(String[] inStr)
	{
		if (inStr == null)
		{
			return null;
		}
		else
		{
			String[] outStr = inStr;
			for (int i = 0; i < inStr.length; i++)
			{
				inStr[i] = fromRequest(inStr[i]);
			}
			return outStr;
		}
	}

	/**
	 * 对request传递过来的参数进行转码，以防止中文乱码的出现
	 * @param inStr
	 * @return
	 */
	public static String fromRequest(String inStr)
	{
		String outStr;
		try
		{
			if (inStr == null)
			{
				outStr = "";
			}
			else
			{
				outStr = trimNull(inStr);
				outStr = new String(outStr.getBytes("ISO8859_1"), "GBK");
			}
		}
		catch (Exception e)
		{
			outStr = "error!!!";
			System.out.println("中文转码GB2K->ISO8859_1 出错");
		}
		return outStr;
	}

	/**
	 * 将null替换为""，将字符串左右的空白去掉
	 * @param inStr 输入字符串
	 * @return
	 */
	public static String trimNull(String inStr)
	{
		String outStr = inStr;
		if (outStr == null)
		{
			outStr = "";
		}
		else
		{
			outStr = outStr.trim();
		}
		return outStr;
	}

	/**
	 * 支持中英文读数
	 * @param str
	 * @param len
	 * @param append
	 * @return
	 */
	public static String substring(String str, int len, String append)
	{
		len = len * 2;
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if (c < 255)
			{
				counter++;
			}
			else
			{
				counter = counter + 2;
			}
			if (counter > len)
			{
				sb.append(append);
				break;
			}
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 不足位数时可以弥补
	 * @param str
	 * @param len
	 * @param append
	 * @return
	 */
	public static String substring2(String str, int len)
	{
		len = len * 2;
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if (c < 255)
			{
				counter++;
			}
			else
			{
				counter = counter + 2;
			}
			if (counter > len)
			{
				//sb.append(append);
				break;
			}
			sb.append(c);
		}
		if (counter < len)
		{
			while (len - counter > 0)
			{
				counter++;
				sb.append("&nbsp;");
			}
		}
		return sb.toString();
	}

	/**
	 * 对浮点数字符串进行处理，按指定长度保留尾数，四舍五入
	 * 此方法不好，需重写，参照numberToString()
	 * @param floatStr String 浮点数字符串
	 * @param length int 保留小数位数
	 * @param trimZero boolean 是否将末尾的0去除
	 * @return String
	 */
	public static String trimFloatStr(String floatStr, int length, boolean trimZero)
	{
		if (StringUtils.isEmpty(floatStr))
		{
			return "";
		}
		//if(!StringUtils.isNumeric(floatStr))
		//return floatStr;
		int index = StringUtils.indexOf(floatStr, ".");
		//System.out.println(index);
		//是小数
		if (index != -1)
		{
			String t = StringUtils.substring(floatStr, index + 1);
			System.out.println(t);
			//小数位不够要求的
			if (t.length() <= length)
			{
				int diff = length - t.length();
				System.out.println(diff);
				for (int i = 0; i < diff; i++)
				{
					t += "0";
				}
			}
			else
			{
				//小数位多于要求的，要四舍五入
				t = StringUtils.substring(t, 0, length);
				String tmp = StringUtils.substring(t, length, 1);
				if (tmp.compareTo("5") >= 0)
				{
					floatStr = StringUtils.substring(floatStr, 0, index) + "." + t;
					double d = Double.parseDouble(floatStr);
					d += Integer.parseInt(tmp) / Math.pow(10, length);
					floatStr = String.valueOf(d);
				}
			}
			//要去尾数上的0
			if (trimZero)
			{
				if (StringUtils.isNotEmpty(t))
				{
					int value = Integer.parseInt(t);
					if (value != 0)
					{
						while (value % 10 == 0)
						{
							value /= 10;
							//System.out.println(value);
						}
						floatStr = StringUtils.substring(floatStr, 0, index) + "." + value;
					}
					else
					{
						floatStr = StringUtils.substring(floatStr, 0, index);
					}
				}
			}
			else
			{
				floatStr = StringUtils.substring(floatStr, 0, index) + "." + t;
			}
		}
		else
		{
			//不是小数的
			if (!trimZero)
			{
				if (length > 0)
				{
					floatStr += ".";
					for (int i = 0; i < length; i++)
					{
						floatStr += "0";
					}
				}
			}
		}
		return floatStr;
	}

	/**
	 * 用正则表达式检查字符串
	 */
	public static boolean checkStrByZzbds(String str, String pattern)
	{
		Pattern p = Pattern.compile(pattern);
		Matcher m = null;
		m = p.matcher(str);
		if (m.find())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 用于菜单编码和功能编码到顺序号的转换。
	 * 比如1.2.3 转换为1*1000*1000 + 2*1000 *3
	 * @param menuCode String
	 */
	public static int menuCode2Order(String menuCode)
	{
		int order = -2;
		if (menuCode == null)
		{
			return -1;
		}
		String[] digits = StringUtils.split(menuCode, ".");
		if (digits.length == 1)
		{
			int number = Integer.parseInt(digits[0]);
			order = number * 1000 * 1000;
		}
		else if (digits.length == 2)
		{
			int number = Integer.parseInt(digits[1]);
			order = number * 1000;
			number = Integer.parseInt(digits[0]);
			order += number * 1000 * 1000;
		}
		else if (digits.length == 3)
		{
			int number = Integer.parseInt(digits[2]);
			order = number;
			number = Integer.parseInt(digits[1]);
			order += number * 1000;
			number = Integer.parseInt(digits[0]);
			order += number * 1000 * 1000;
		}
		return order;
	}

	/**
	 * 把xml格式化为可读性强的文本
	 * @param xmlStr
	 * @return
	 */
	public static String formatXml(String xmlStr)
	{
		StringWriter outstr = null;
		try
		{
			org.dom4j.io.OutputFormat formate = org.dom4j.io.OutputFormat.createPrettyPrint();
			outstr = new StringWriter();
			org.dom4j.io.XMLWriter writer = new org.dom4j.io.XMLWriter(outstr, formate);
			writer.write(DocumentHelper.parseText(xmlStr));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				outstr.close();
			}
			catch (Exception e2)
			{

			}
		}
		return outstr.toString();
	}

	public static void main(String[] argv)
	{
		String t = "adfadf|adfads|asdfa|fadsa|";
		System.out.println(t + " contains:" + getStrCount(t, "|") + " parts.");
		//int i = 1;
		int j = getStrCount(t, "|");
		System.out.println("有几个:" + j);
		for (int i = 1; i <= j; i++)
		{
			System.out.println("the " + i + " part is:" + getStr(t, "|", i));
		}
		System.out.println(trimFloatStr("234234.250", 4, false));
		System.out.println(numberToString("0.125", "#.##"));
		System.out.println(org.apache.commons.lang.StringUtils.upperCase("jsp"));
		//List<String> list = new ArrayList<String>();
		//list.add();
		try
		{
			System.out.println("解码:" + java.net.URLDecoder.decode("%E5%B0%8F%E6%9D%8E", "UTF-8"));
			System.out.println("转码:" + java.net.URLEncoder.encode("燃气热水器", "UTF-8"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println(StringUtils.isNumericSpace("37683.43"));

		String a = "122,bc-tds，dfsd";
		String[] b = StringUtils.split(a, ",-，");
		for (String element : b)
		{
			System.out.println(element);
		}

		for (int i = 0; i < 120; i++)
		{
			System.out.println(getRandomInt(8));
		}
	}

	public static boolean checkInteger(String str)
	{
		if (StringUtils.isEmpty(str) || !StringUtils.isNumeric(str))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
