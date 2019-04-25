package webfood.utils;

import java.text.DecimalFormat;
import java.util.UUID;

public class UUIDUtil {
	public UUIDUtil() {

	}

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	public static String getUUID(int number) {
		String s = UUID.randomUUID().toString();
		if (number < 0 || number > 32) {
			return null;
		} else if (number <= 8) {
			return s.substring(0, number);
		} else if (number <= 16) {
			return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, number);
		} else {
			return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
		}
	}

	public static String genProjectCode(int id, String prefix) {
		DecimalFormat format = new DecimalFormat("000");

		return prefix + DateTimeUtil.getDateNow().substring(0, 4) + format.format(id);
	}

	public static void main(String[] args) {

		System.out.println(getUUID(7));
	}
}