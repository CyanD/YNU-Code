package com.vo.bean;

import com.ynu.zjx.PageBean;



public class BusScheduleVO extends PageBean{
	private long id;
	private long pid;
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	private String ids;
	private String startTime;
	private String costTime;
	private String direction;
	private String createTime;
	private String publisher;
	public String getCostTime() {
		return costTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getDirection() {
		return direction;
	}
	public long getId() {
		return id;
	}
	public String getIds() {
		return ids;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
