package com.yinxue.computer.util;

import java.sql.Timestamp;

import com.yinxue.computer.constant.ErrorMessage;

public class DateUtil {

	private DateUtil() {
		throw new Error(ErrorMessage.NO_REFLECT);
	}

	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Timestamp getMaxTime() {
		final String time = "9999-12-31 23:59:59.999";
		return Timestamp.valueOf(time);
	}
}
