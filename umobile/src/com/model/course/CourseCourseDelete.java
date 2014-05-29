package com.model.course;

import java.sql.Timestamp;
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
 * CourseCourse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course_course", catalog = "umobile")
public class CourseCourseDelete implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String lecturer;
	private String coverPath;
	private String content;
	private Integer visitors;
	private String status;
	private Timestamp createTime;
	private String publisher;
	private Integer orders;
	@Column(name = "orders", nullable = false)
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	private Set<CourseVideoDelete> courseVideos = new HashSet<CourseVideoDelete>(0);

	// Constructors

	/** default constructor */
	public CourseCourseDelete() {
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


	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "lecturer", nullable = false, length = 50)
	public String getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	@Column(name = "coverPath", length = 100)
	public String getCoverPath() {
		return this.coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "visitors")
	public Integer getVisitors() {
		return this.visitors;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}

	@Column(name = "status", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courseCourse")
	public Set<CourseVideoDelete> getCourseVideos() {
		return this.courseVideos;
	}

	public void setCourseVideos(Set<CourseVideoDelete> courseVideos) {
		this.courseVideos = courseVideos;
	}

}