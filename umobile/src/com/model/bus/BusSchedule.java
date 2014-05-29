package com.model.bus;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BusSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bus_schedule", catalog = "umobile")
public class BusSchedule implements java.io.Serializable {

	// Fields

	private Long id;
	private BusRoute busRoute;
	private String startTime;
	private String costTime;
	private String createTime;
	private String publisher;

	// Constructors

	/** default constructor */
	public BusSchedule() {
	}

	/** minimal constructor */
	public BusSchedule(BusRoute busRoute, String startTime, String createTime,
			String publisher) {
		this.busRoute = busRoute;
		this.startTime = startTime;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	/** full constructor */
	public BusSchedule(BusRoute busRoute, String startTime, String costTime,
			String createTime, String publisher) {
		this.busRoute = busRoute;
		this.startTime = startTime;
		this.costTime = costTime;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid", nullable = false)
	public BusRoute getBusRoute() {
		return this.busRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}

	@Column(name = "startTime", nullable = false, length = 8)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "costTime", length = 8)
	public String getCostTime() {
		return this.costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "publisher", nullable = false, length = 50)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}