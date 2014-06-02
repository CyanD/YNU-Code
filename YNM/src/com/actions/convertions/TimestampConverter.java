package com.actions.convertions;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class TimestampConverter {

	@SuppressWarnings("rawtypes")
	public Object convertValue(Map context, Object value, Class toType) {
		if (toType == Timestamp.class) { // 浏览器向服务器提交时，进行String to Date的转换
			String dateString = null;
			String[] params = (String[]) value;
			dateString = params[0];// 获取日期的字符串
			try {
				Timestamp tsp = Timestamp.valueOf(dateString+":00");// format.parse(dateString);
				return tsp;// 遍历日期支持格式，进行转换
			} catch (Exception e) {
				System.out.println("TimestampConverter,ex:"+e.getMessage());
			}
			return null;
		} else if (toType == String.class) { // 服务器向浏览器输出时，进行Date to String的类型转换
			Timestamp date = (Timestamp) value;
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);// 输出的格式是yyyy-MM-dd
																			// HH:mm
		}

		return null;
	}

}
