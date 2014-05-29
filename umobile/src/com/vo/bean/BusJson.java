package com.vo.bean;

import java.sql.Time;

import com.ynu.zjx.PageBean;


public class BusJson extends PageBean{
	private Long rId;
	private String rName;
	private String rStartStation;
	private String rEndStation;
	private String rCreateTime;
	private String rPublisher;
	private Long sId;
	private Time sStartTime;
	private Time sCostTime;
	private String sScreateTime;
	public Long getrId() {
		return rId;
	}
	public void setrId(Long rId) {
		this.rId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrStartStation() {
		return rStartStation;
	}
	public void setrStartStation(String rStartStation) {
		this.rStartStation = rStartStation;
	}
	public String getrEndStation() {
		return rEndStation;
	}
	public void setrEndStation(String rEndStation) {
		this.rEndStation = rEndStation;
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
	public Time getsStartTime() {
		return sStartTime;
	}
	public void setsStartTime(Time sStartTime) {
		this.sStartTime = sStartTime;
	}
	public Time getsCostTime() {
		return sCostTime;
	}
	public void setsCostTime(Time sCostTime) {
		this.sCostTime = sCostTime;
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
	private String sPublisher;


}
