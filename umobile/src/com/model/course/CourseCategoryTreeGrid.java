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
public class CourseCategoryTreeGrid implements java.io.Serializable {


	public CourseCategoryTreeGrid(Long id, Long _parentId, String name,
			String remark,Integer orders) {
		super();
		this.id = id;
		if(_parentId==0l)_parentId=null;
		this._parentId = _parentId;
		this.name = name;
		this.remark = remark;
		this.orders=orders;
	}

	// Fields


	private Long id;
	private Long _parentId;
	@Column(name = "pid")
	private Integer orders;
	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	@Column(name="pid")
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		if(_parentId==null)_parentId=0l;
		this._parentId = _parentId;
	}

	private String name;
	private String remark;
	private String createTime;
	private String publisher;
	public CourseCategoryTreeGrid() {
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


	@Column(name = "remark", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "publisher", length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}