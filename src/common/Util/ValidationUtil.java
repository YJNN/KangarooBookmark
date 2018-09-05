/*
 *  기능: 상품 및 회원 등록시 유효성 검사 기능 구현
 * 
 */
package common.Util;

public class ValidationUtil {

	// 숫자 검사
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

	// 영문자,숫자 검사
	public static boolean checkAlphaDigit(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			// 영문
			if ((c >= 0x61 && c <= 0x7A) || (c >= 0x41 && c <= 0x5A)) {
				// 숫자
			} else if (c >= 0x30 && c <= 0x39) {

			} else {
				return false;
			}
		}
		return true;
	}

	// 필수
	public static boolean checkRequired(String data) {
		boolean result = false;
		if (data != null && data.length() != 0) {
			result = true;
		}
		return result;
	}

	// 길이 제한
	public static boolean lessLength(String data, int len) {
		boolean result = false;
		if (data != null && data.length() < len) {
			result = true;
		}
		return result;
	}

	// 길이 제한
	public static boolean equalLength(String data, int len) {
		boolean result = false;
		if (data != null && data.length() == len) {
			result = true;
		}
		return result;
	}
}
