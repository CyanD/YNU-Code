package com.vo.bean;

import com.ynu.zjx.PageBean;



public class SysRoleVO extends PageBean{
	private Long id;
	private String name;
	private String sys = "无";
	private String news = "无";
	private String anniversary = "无";
	private String bus = "无";
	private String map = "无";
	private String course = "无";
	private String addressBook = "无";
	private String createTime;
	private String publisher;
	public String getAddressBook() {
		return addressBook;
	}
	public String getAnniversary() {
		return anniversary;
	}
	public String getBus() {
		return bus;
	}
	public String getCourse() {
		return course;
	}
	public String getCreateTime() {
		return createTime;
	}
	public Long getId() {
		return id;
	}
	public String getMap() {
		return map;
	}
	public String getName() {
		return name;
	}
	public String getNews() {
		return news;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getSys() {
		return sys;
	}
	public void setAddressBook(String addressBook) {
		this.addressBook = addressBook;
	}
	public void setAnniversary(String anniversary) {
		this.anniversary = anniversary;
	}
	public void setBus(String bus) {
		this.bus = bus;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setSys(String sys) {
		this.sys = sys;
	}
}
