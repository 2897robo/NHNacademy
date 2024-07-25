package com.nhnacademy.exam.hotel.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatterImpl {
	public static String convert(LocalDateTime localDateTime) {
		String dateFormat = getDateFormat();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return localDateTime.format(formatter);
	}

	private static String getDateFormat() {
		String profile = System.getProperty("spring.profiles.active", "dev");
		switch (profile) {
			case "dev":
				return "yyyy-MM-dd";
			case "real":
				return "yyyy-MM-dd HH:mm:ss";
			default:
				return "yyyy-MM-dd";
		}
	}
}