package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sys {
	public static final String LOGINUSER = "loginUser";
	public static final String SECURITYCODE="securityCode";
	//public static final String BASEPATH="/usr/local/data/";
	public static final String BASEPATH="e:/";
	//public static final String VIDEOBASEPATH="/usr/local/movie/movie/";
	public static final String VIDEOBASEPATH="e:/";
	public static final String ADDRESSBOOKHEADBASEPATH="upload/images/addressbookhead/";
	public static final String YNUSTORYHEADBASEPATH="upload/images/ynustoryhead/";
	public static final String NEWSNEWSPICTUREBASEPATH="upload/images/newsnewspicture/";
	public static final String NEWSALBUMPICTUREBASEPATH="upload/images/newsalbumpicture/";
	public static final String COURSEPICTUREBASEPATH="upload/images/coursecover/";
	public static final String COURSEVIDEOBASEPATH="upload/video/";
	public static final String NOTICEACCESSORIESBASEPATH="upload/accessories/";
	public static final String DEFAULTPICTUREPATH ="upload/images/newsnewspicture/Lighthouse.png";
	public static final String DEFAULTADDRESSBOOKHEADPATH ="upload/images/addressbookhead/default.png";
	public static final String ERRORMSG = "数据不同步，刷新后再试！";
	
	public static final String KEPICTUREPATH="attached/image/";
	public static final int KEMINIPICTUREWIDTH=300;
	public static final int KEMINIPICTUREHIGHT=300;
	
	public static int getAge(String birthday) {
		if(birthday==null||birthday.trim().isEmpty()){
			return 0;
		}
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd"); 
		int age = 0;
		Date date=new Date();       
		Date mydate = null;
		try {
			mydate = myFormatter.parse(birthday);
			long day=(date.getTime()-mydate.getTime())/(24*60*60*1000) + 1;
			age=(int)day/365;
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return age;
	}
}