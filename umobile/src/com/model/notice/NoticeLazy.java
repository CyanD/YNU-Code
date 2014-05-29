package com.model.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NewsNotice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice", catalog = "umobile")
public class NoticeLazy implements java.io.Serializable {

	// Fields

	public NoticeLazy(Long id, Long categoryId, String title,
			String deptName, String author, String keywords, String abstracts,
			String content, Integer visitors, String status) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.deptName = deptName;
		this.author = author;
		this.keywords = keywords;
		this.abstracts = abstracts;
		this.content = content;
		this.visitors = visitors;
		this.status = status;
	}
	public NoticeLazy(){}
	private Long id;
	private Long categoryId;
	private String title;

	private String deptName;

	private String author;
	private String keywords;
	private String abstracts;
	private String content;
	private Integer visitors;
	private String status;
	private String createTime;
	private String publisher;
	@Column(name = "abstracts", nullable = false, length = 100)
	public String getAbstracts() {
		return this.abstracts;
	}
	@Column(name = "author", nullable = false, length = 50)
	public String getAuthor() {
		return this.author;
	}

	@Column(name = "categoryId")
	public Long getCategoryId() {
		return categoryId;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}


	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	@Column(name = "deptName", length = 50)
	public String getDeptName() {
		return this.deptName;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	@Column(name = "keywords", length = 100)
	public String getKeywords() {
		return this.keywords;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	@Column(name = "status", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	@Column(name = "visitors")
	public Integer getVisitors() {
		return this.visitors;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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


}