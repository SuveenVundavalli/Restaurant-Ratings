package com.ts.us.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ts.us.exception.UrbanspoonException;

public class DateUtility {

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	public static Date convertStringToDate(String dateString) throws UrbanspoonException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
		try {
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new UrbanspoonException(e.toString());
		}

	}

}
