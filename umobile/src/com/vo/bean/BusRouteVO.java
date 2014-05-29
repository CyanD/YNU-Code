package com.vo.bean;

import com.ynu.zjx.PageBean;


public class BusRouteVO extends PageBean{
	private Long id;
	private String ids;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	private String name;
	private String startStation;
	private String endStation;
	private String createTime;
	private String publisher;
	private String byCarPlace;
	private String viaStation;
	public String getByCarPlace() {
		return this.byCarPlace;
	}

	public void setByCarPlace(String byCarPlace) {
		this.byCarPlace = byCarPlace;
	}

	public String getViaStation() {
		return this.viaStation;
	}

	public void setViaStation(String viaStation) {
		this.viaStation = viaStation;
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
