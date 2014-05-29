package com.vo.bean;

import com.model.anniversary.AnniversaryDonation;
import com.ynu.zjx.PageBean;

public class AnniversaryDonationVO extends PageBean {
	private Long id;
	private String name;
	private String money;
	public AnniversaryDonation getAnniversaryDonation(){
		return new AnniversaryDonation(id, name, money, location, donateTime);
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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDonateTime() {
		return donateTime;
	}

	public void setDonateTime(String donateTime) {
		this.donateTime = donateTime;
	}

	private String location;
	private String donateTime;
}
