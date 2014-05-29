package com.model.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CourseCourse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course_course", catalog = "umobile")
public class CourseCourseLazy implements java.io.Serializable {

	// Fields

	public CourseCourseLazy(Long id, Long categoryId, String title,
			String lecturer, String coverPath, String content,
			Integer visitors, String status,Integer orders) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.lecturer = lecturer;
		this.coverPath = coverPath;
		this.content = content;
		this.visitors = visitors;
		this.status = status;
		this.orders=orders;
	}

	private Long id;
	private Long categoryId;
	private String title;
	private String lecturer;
	private String coverPath;
	private String content;
	private Integer visitors;
	private String status;
	private String createTime;
	private String publisher;
	private Integer orders;
	@Column(name = "orders", nullable = false)
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public CourseCourseLazy() {
	}
	@Column(name = "categoryId")
	public Long getCategoryId() {
		return categoryId;
	}

	// Constructors

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}



	@Column(name = "coverPath", length = 100)
	public String getCoverPath() {
		return this.coverPath;
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

	@Column(name = "lecturer", nullable = false, length = 50)
	public String getLecturer() {
		return this.lecturer;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	@Column(name = "status", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	@Column(name = "visitors")
	public Integer getVisitors() {
		return this.visitors;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}
	public void setDepartmentId(Object object) {
		// TODO Auto-generated method stub
		
	}

}