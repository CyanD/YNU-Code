package com.model.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * NewsNoticeAccessories entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice_accessories", catalog = "umobile")
public class NoticeAccessoriesDelete implements java.io.Serializable {

	// Fields

	private Long id;
	private NoticeDelete newsNotice;
	private String name;
	private String path;

	// Constructors

	/** default constructor */
	public NoticeAccessoriesDelete() {
	}

	/** full constructor */
	public NoticeAccessoriesDelete(NoticeDelete newsNotice, String name, String path) {
		this.newsNotice = newsNotice;
		this.name = name;
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
	public NoticeDelete getNewsNotice() {
		return this.newsNotice;
	}

	public void setNewsNotice(NoticeDelete newsNotice) {
		this.newsNotice = newsNotice;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "path", nullable = false, length = 50)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}