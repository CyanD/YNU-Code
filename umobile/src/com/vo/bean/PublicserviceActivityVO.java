package com.vo.bean;

import com.model.publicservice.PublicserviceActivity;
import com.ynu.zjx.PageBean;


public class PublicserviceActivityVO extends PageBean{
	private Long id;
	private String title;
	private String beginDate;
	private String endDate;
	private String timePoint;
	private String location;
	private String content;
	private String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public PublicserviceActivity getPublicserviceActivity(){
		return new PublicserviceActivity(id, title, beginDate, endDate, timePoint, location, content,category);
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
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
