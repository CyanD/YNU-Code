package com.model.bus;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * BusRoute entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bus_route", catalog = "umobile")
public class BusRoute implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String startStation;
	private String endStation;
	private String createTime;
	private String publisher;
	private String byCarPlace;
	private String viaStation;
	@Column(name = "byCarPlace", length = 30)
	public String getByCarPlace() {
		return this.byCarPlace;
	}

	public void setByCarPlace(String byCarPlace) {
		this.byCarPlace = byCarPlace;
	}

	@Column(name = "viaStation", length = 300)
	public String getViaStation() {
		return this.viaStation;
	}

	public void setViaStation(String viaStation) {
		this.viaStation = viaStation;
	}

	private Set<BusSchedule> busSchedules = new HashSet<BusSchedule>(0);

	// Constructors

	/** default constructor */
	public BusRoute() {
	}

	/** minimal constructor */
	public BusRoute(String startStation, String endStation,
			String createTime, String publisher) {
		this.startStation = startStation;
		this.endStation = endStation;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	/** full constructor */
	public BusRoute(String name, String startStation, String endStation,
			String createTime, String publisher,
			Set<BusSchedule> busSchedules) {
		this.name = name;
		this.startStation = startStation;
		this.endStation = endStation;
		this.createTime = createTime;
		this.publisher = publisher;
		this.busSchedules = busSchedules;
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

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "startStation", nullable = false, length = 20)
	public String getStartStation() {
		return this.startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	@Column(name = "endStation", nullable = false, length = 20)
	public String getEndStation() {
		return this.endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "busRoute")
	public Set<BusSchedule> getBusSchedules() {
		return this.busSchedules;
	}

	public void setBusSchedules(Set<BusSchedule> busSchedules) {
		this.busSchedules = busSchedules;
	}

}