package com.vo.bean;

import com.model.calendar.SchoolCalendar;

public class SchoolCalendarBean {
	private Long id;
	private String acttime;
	private String createTime;
	private String publisher;
	public SchoolCalendarBean(){}
	public SchoolCalendarBean(SchoolCalendar schoolCalender){
		this.id= schoolCalender.getId();
		this.acttime = schoolCalender.getActtime();
		this.createTime = schoolCalender.getCreateTime();
		this.publisher= schoolCalender.getPublisher();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActtime() {
		return acttime;
	}
	public void setActtime(String acttime) {
		this.acttime = acttime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
}
