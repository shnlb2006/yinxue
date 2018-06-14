package org.yinxueframework.util;

import java.util.UUID;

/**
 * UUID生成器
 * @author zengjian
 *
 */
public class UUIDUtil {
	
	private UUIDUtil() {
	}
	
	/**
	 * 得到32位数字及字母混合值
	 * @return
	 */
	public static String getToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] args) {
		String s = getToken();
		System.out.println(s);
		System.out.println(s.length());
	}

}
