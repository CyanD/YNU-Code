package com.vo.bean;

import com.model.calendar.CalendarCategory;

public class CalendarCategoryBean {
	private  Long id;
	private  String title;
	private  String beginDate;
	private  String weeknum;
	private  String publisher;
	private  String createTime;
	public CalendarCategoryBean(){
		
	}
	public CalendarCategoryBean(CalendarCategory calendarCategory){
		this.id = calendarCategory.getId();
		this.title = calendarCategory.getTitle();
		this.beginDate = calendarCategory.getBeginDate();
		this.weeknum = calendarCategory.getWeeknum();
		this.createTime = calendarCategory.getCreateTime();
		this.publisher = calendarCategory.getPublisher();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getWeeknum() {
		return weeknum;
	}
	public void setWeeknum(String weeknum) {
		this.weeknum = weeknum;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
