package com.model.bus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BusSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bus_schedule", catalog = "umobile")
public class BusScheduleLazy implements java.io.Serializable {

	// Fields

	private Long id;
	//private BusRoute busRoute;
	private Long pid;
	private String startTime;

	private String costTime;
	private String createTime;
	private String publisher;
	/** default constructor */
	public BusScheduleLazy() {
	}
	/** minimal constructor */
	public BusScheduleLazy(BusRoute busRoute, String startTime,
			String createTime, String publisher) {
		//this.busRoute = busRoute;
		this.startTime = startTime;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	// Constructors

	/** full constructor */
	public BusScheduleLazy(BusRoute busRoute, String startTime, String costTime,
			String createTime, String publisher) {
		//this.busRoute = busRoute;
		this.startTime = startTime;
		this.costTime = costTime;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	@Column(name = "costTime", length = 8)
	public String getCostTime() {
		return this.costTime;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}


	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rid", nullable = false)
	public BusRoute getBusRoute() {
		return this.busRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}*/

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}
	@Column(name = "startTime", nullable = false, length = 8)
	public String getStartTime() {
		return this.startTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Column(name = "pid", nullable = false)
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

}