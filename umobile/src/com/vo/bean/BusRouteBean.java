package com.vo.bean;

import com.model.bus.BusRoute;

public class BusRouteBean {
	private Long id;
	private String name;
	private String startStation;
	private String endStation;
	private String createTime;
	private String publisher;
	public BusRouteBean(){}
	public BusRouteBean(BusRoute busRoute){
		this.id= busRoute.getId();
		this.name = busRoute.getName();
		this.startStation = busRoute.getStartStation();
		this.endStation = busRoute.getEndStation();
		this.createTime = busRoute.getCreateTime();
		this.publisher= busRoute.getPublisher();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getEndStation() {
		return endStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
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
