package com.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role", catalog = "umobile")
public class SysRoleCombogrid implements java.io.Serializable {

	// Fields

	private Long roleId;
	private String name;
	private String sys;
	private String news;
	private String anniversary;
	private String bus;
	private String map;
	private String course;
	private String addressBook;
	public SysRoleCombogrid() {
	}

	/** minimal constructor */
	public SysRoleCombogrid(String name, String sys, String news, String anniversary,
			String bus, String map, String course, String addressBook) {
		this.name = name;
		this.sys = sys;
		this.news = news;
		this.anniversary = anniversary;
		this.bus = bus;
		this.map = map;
		this.course = course;
		this.addressBook = addressBook;
	}

	

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sys", nullable = false, length = 2)
	public String getSys() {
		return this.sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	@Column(name = "news", nullable = false, length = 2)
	public String getNews() {
		return this.news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	@Column(name = "anniversary", nullable = false, length = 2)
	public String getAnniversary() {
		return this.anniversary;
	}

	public void setAnniversary(String anniversary) {
		this.anniversary = anniversary;
	}

	@Column(name = "bus", nullable = false, length = 2)
	public String getBus() {
		return this.bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	@Column(name = "map", nullable = false, length = 2)
	public String getMap() {
		return this.map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	@Column(name = "course", nullable = false, length = 2)
	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Column(name = "addressBook", nullable = false, length = 2)
	public String getAddressBook() {
		return this.addressBook;
	}

	public void setAddressBook(String addressBook) {
		this.addressBook = addressBook;
	}
}