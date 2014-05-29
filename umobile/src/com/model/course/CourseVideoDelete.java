package com.model.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CourseVideo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course_video", catalog = "umobile")
public class CourseVideoDelete implements java.io.Serializable {

	// Fields

	private Long id;
	private CourseCourseDelete courseCourse;
	private String name;
	private Integer orders;
	private String path;

	// Constructors

	/** default constructor */
	public CourseVideoDelete() {
	}

	/** minimal constructor */
	public CourseVideoDelete(CourseCourseDelete courseCourse, String name, String path) {
		this.courseCourse = courseCourse;
		this.name = name;
		this.path = path;
	}

	/** full constructor */
	public CourseVideoDelete(CourseCourseDelete courseCourse, String name, Integer orders,
			String path) {
		this.courseCourse = courseCourse;
		this.name = name;
		this.orders = orders;
		this.path = path;
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
	public CourseCourseDelete getCourseCourse() {
		return this.courseCourse;
	}

	public void setCourseCourse(CourseCourseDelete courseCourse) {
		this.courseCourse = courseCourse;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	@Column(name = "path", nullable = false, length = 100)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}