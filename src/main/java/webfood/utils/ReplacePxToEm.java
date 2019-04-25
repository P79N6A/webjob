package webfood.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplacePxToEm {
	/**
	 * 将 px 折算成 em
	 * @param strs
	 * @return
	 */
	public static String replacePxToEm(String strs) {//
		if(strs==null){
			strs = "";
		}
		
		StringBuilder str = new StringBuilder(strs);

		String base = "(\\d+)\\s*px";
		String[] begins = { "height:", "width:", "font-size:" };
		String zz = "";
		for (int i = 0; i < begins.length; i++) {
			zz += begins[i] + base;
			if (i != begins.length - 1) {
				zz += "|";
			}
		}

		Pattern pattern = Pattern.compile(zz);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			int beginIndex = 0;
			int groupIndex = 0;
			String match0 = matcher.group(0);
			for (int i = 0; i < begins.length; i++) {
				if (match0.startsWith(begins[i])) {
					beginIndex = begins[i].length();
					groupIndex = i + 1;
					break;
				}
			}

			String match = matcher.group(groupIndex);
			System.out.println("match:" + match + "-" + beginIndex + "-" + matcher.groupCount() + "-g1:"
					+ matcher.group(1) + "-g2:" + matcher.group(2) + "-g3:" + matcher.group(3));
			if (StringUtil.isNum(match)) {// 如果是数字 则替换
				match = Arith.format(Double.parseDouble(match) / 16, 3, false);
			}
			match += "em";
			System.out.println(matcher.start() + "--" + matcher.end());
			str = str.replace(matcher.start() + beginIndex, matcher.end(), match);
		}
		return str.toString();
	}

	public static void main(String[] strs) {
		String str = "<p><span style=\"font-size:18px\"><img alt=\"\" src=\"/upload/3f57b17c347a42..jpg\" style=\"height:533px; width:400px\" /></span></p>"
				+ "<p><img alt=\"\" src=\"/upload/810ac8b3bb1440..jpg\" style=\"height:300px; width:400px\" /></p>"
				+ "<p><img alt=\"\" src=\"/upload/49c470f7bb0547..jpg\" style=\"height:300px; width:400px\" /></p>"
				+ "<p><span style=\"font-size:16px\">土味农情&mdash;&mdash;所有的测试数据、</span></p>"
				+ "<p><span style=\"font-size:16px\"><strong><span style=\"color:#ff0000\">测试数据看一下。</span></strong></span></p>";
		replacePxToEm(str);
	}
}
