package common.Util;

public class ValidationUtil {
	
	public static boolean checkDigit(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 0x30 && c <= 0x39) { // 48 ~ 57

			} else {
				return false;
			}
		}// end for
		return true;
	}
	public static boolean checkAlphaDigit(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c >= 0x61 && c <= 0x7A) || (c >= 0x41 && c <= 0x5A)) {
				// 숫자
			} else if (c >= 0x30 && c <= 0x39) {

			} else {
				return false;
			}
		}
		return true;
	}
	public static boolean checkRequired(String data) {
		boolean result = false;
		if (data != null && data.length() != 0) {
			result = true;
		}
		return result;
	}
	public static boolean lessLength(String data, int len) {
		boolean result = false;
		if (data != null && data.length() < len) {
			result = true;
		}
		return result;
	}
	public static boolean equalLength(String data, int len) {
		boolean result = false;
		if (data != null && data.length() == len) {
			result = true;
		}
		return result;
	}
}
