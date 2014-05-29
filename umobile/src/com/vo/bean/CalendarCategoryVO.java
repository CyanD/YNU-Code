package com.vo.bean;

import com.ynu.zjx.PageBean;

public class CalendarCategoryVO extends PageBean{
	private  Long id;
	private  String title;
	private  String beginDate;
	private  String weeknum;
	private  String publisher;
	private  String createTime;
	private String ids;
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
