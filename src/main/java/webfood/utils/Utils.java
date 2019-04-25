package webfood.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @ClassName: Utils
 * @Description: 将11,22,32,55 分割构造成   Liss<Integer>={11,22,32,55}
 * @author: Daijie
 * @date: 2016年1月8日 下午4:00:37
 *
 */
public class Utils {

	//Sting字符串通过","分割   得到的子串再去掉第一个字符  转为Integer
	public  static List<Integer>  StringSplitToIntegerList(String str)
	{
		String[] strs=str.split(",");
		List <Integer>  list=new LinkedList<Integer>();		
		for(String s:strs)
		{
			Integer it=Integer.parseInt(s);
			list.add(it);
		}
		return list;
	}
	
	
	public  static void  main(String[] agrs)
	{
		System.out.println(StringSplitToIntegerList("21,22,23").toString());
		
		
	}
}
