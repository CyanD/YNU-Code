package com.model.news.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NewsNews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_news", catalog = "umobile")
public class NewsNewsGridGet implements java.io.Serializable {

	// Fields

	private Long id;
	private Long deptId;
	private String title;
	private Integer visitors;
	private String status;
	private String createTime;
	/** default constructor */
	public NewsNewsGridGet() {
	}
	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}
	

	// Constructors

	@Column(name = "deptId")
	public Long getDeptId() {
		return deptId;
	}

	
	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	@Column(name = "status", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	@Column(name = "title", nullable = false, length = 30)
	public String getTitle() {
		return this.title;
	}

	@Column(name = "visitors")
	public Integer getVisitors() {
		return this.visitors;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setId(Long id) {
		this.id = id;
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

}