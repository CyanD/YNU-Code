package com.model.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CourseCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course_category", catalog = "umobile")
public class CourseCategoryTree implements java.io.Serializable {


	private Long id;
	private Long pid;
	@Column(name = "pid")
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		if(pid==null)pid=0l;
		this.pid = pid;
	}

	private String name;
	public CourseCategoryTree() {
	}
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}