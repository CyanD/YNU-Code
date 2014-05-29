package com.model.notice;

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
 * NewsNotice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice", catalog = "umobile")
public class NoticeDelete implements java.io.Serializable {

	// Fields

	private Long id;
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
	private Set<NoticeAccessoriesDelete> noticeAccessorieses = new HashSet<NoticeAccessoriesDelete>(
			0);

	// Constructors
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "newsNotice")
	public Set<NoticeAccessoriesDelete> getNoticeAccessorieses() {
		return noticeAccessorieses;
	}

	public void setNoticeAccessorieses(
			Set<NoticeAccessoriesDelete> noticeAccessorieses) {
		this.noticeAccessorieses = noticeAccessorieses;
	}

	/** default constructor */
	public NoticeDelete() {
	}

	/** minimal constructor */
	public NoticeDelete(String title, String author, String abstracts,
			String content, String status, String createTime,
			String publisher) {
		this.title = title;
		this.author = author;
		this.abstracts = abstracts;
		this.content = content;
		this.status = status;
		this.createTime = createTime;
		this.publisher = publisher;
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


	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "deptName", length = 50)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "author", nullable = false, length = 50)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "keywords", length = 100)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "abstracts", nullable = false, length = 100)
	public String getAbstracts() {
		return this.abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
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


}