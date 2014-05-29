package com.actions.convertions;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class DateConverter {
	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("yyyy-MM-dd HH"),
			// new SimpleDateFormat("dd/MM/yyyy"),
			new SimpleDateFormat("yyyy-MM-dd")//,
	// new SimpleDateFormat("yyyy/MM/dd")
	}; // 支持转换的日期格式

	@SuppressWarnings("rawtypes")
	public Object convertValue(Map context, Object value, Class toType) {
		if (toType == Timestamp.class) { // 浏览器向服务器提交时，进行String to Date的转换
			String dateString = null;
			String[] params = (String[]) value;
			dateString = params[0];// 获取日期的字符串
			for (DateFormat format : ACCEPT_DATE_FORMATS) {
				try {
					return format.parse(dateString);
				} catch (Exception e) {
					continue;
				}
			}
			return null;
		} else if (toType == String.class) { // 服务器向浏览器输出时，进行Date to String的类型转换
			Date date = (Date) value;
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);// 输出的格式是yyyy-MM-dd HH:mm
		}

		return null;
	}

}
