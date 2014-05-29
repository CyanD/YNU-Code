package com.vo.bean;

import java.sql.Time;

import com.ynu.zjx.PageBean;


public class CalendarJson extends PageBean{
	private Long cId;
	private String cTitle;
	private String cBeginDate;
	private String cWeeknum;
	private String cCreateTime;
	private String cPublisher;
	private Long rId;
	private String rActtime;
	private String rCreateTime;
	private String rPublisher;
	private Long sId;
	private String sCategory;
	private String sTitle;
	private String sScreateTime;
	private String sPublisher;
	

	public Long getcId() {
		return cId;
	}
	public void setcId(Long cId) {
		this.cId = cId;
	}
	public String getcTitle() {
		return cTitle;
	}
	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}
	public String getcBeginDate() {
		return cBeginDate;
	}
	public void setcBeginDate(String cBeginDate) {
		this.cBeginDate = cBeginDate;
	}
	public String getcWeeknum() {
		return cWeeknum;
	}
	public void setcWeeknum(String cWeeknum) {
		this.cWeeknum = cWeeknum;
	}
	public String getcCreateTime() {
		return cCreateTime;
	}
	public void setcCreateTime(String cCreateTime) {
		this.cCreateTime = cCreateTime;
	}
	public String getcPublisher() {
		return cPublisher;
	}
	public void setcPublisher(String cPublisher) {
		this.cPublisher = cPublisher;
	}
	
	
	
	
	
	public Long getrId() {
		return rId;
	}
	public void setrId(Long rId) {
		this.rId = rId;
	}
	public String getrActtime() {
		return rActtime;
	}
	public void setrActtime(String rActtime) {
		this.rActtime = rActtime;
	}
	public String getrCreateTime() {
		return rCreateTime;
	}
	public void setrCreateTime(String rCreateTime) {
		this.rCreateTime = rCreateTime;
	}
	public String getrPublisher() {
		return rPublisher;
	}
	public void setrPublisher(String rPublisher) {
		this.rPublisher = rPublisher;
	}
	
	
	
	
	
	public Long getsId() {
		return sId;
	}
	public void setsId(Long sId) {
		this.sId = sId;
	}
	public String getsCategory() {
		return sCategory;
	}
	public void setsCategory(String sCategory) {
		this.sCategory = sCategory;
	}
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getsScreateTime() {
		return sScreateTime;
	}
	public void setsScreateTime(String sScreateTime) {
		this.sScreateTime = sScreateTime;
	}
	public String getsPublisher() {
		return sPublisher;
	}
	public void setsPublisher(String sPublisher) {
		this.sPublisher = sPublisher;
	}


}
