package com.model.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CourseVideo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course_video", catalog = "umobile")
public class CourseVideoLazy implements java.io.Serializable {

	// Fields

	public CourseVideoLazy(Long id, Long pid, String name, String path,
			Integer orders) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.path = path;
		this.orders = orders;
	}

	private Long id;
	private Long pid;
	private String name;
	private String path;
	private Integer orders;
	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public CourseVideoLazy() {
	}
	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	// Constructors

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	@Column(name = "path", nullable = false, length = 100)
	public String getPath() {
		return this.path;
	}

	@Column(name = "pid", nullable = false)
	public Long getPid() {
		return pid;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

}