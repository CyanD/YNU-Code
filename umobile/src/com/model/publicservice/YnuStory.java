package com.model.publicservice;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Ynustory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ynustory", catalog = "umobile")
public class YnuStory implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String happenTime;
	private String content;
	private String createTime;
	private String publisher;
	private String headpath;

	// Constructors

	/** default constructor */
	public YnuStory() {
	}

	/** full constructor */
	public YnuStory(String title, String happenTime, String content,
			String createTime, String publisher, String headpath) {
		this.title = title;
		this.happenTime = happenTime;
		this.content = content;
		this.createTime = createTime;
		this.publisher = publisher;
		this.headpath = headpath;
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


	@Column(name = "happenTime", nullable = false, length = 10)
	public String getHappenTime() {
		return this.happenTime;
	}

	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Column(name = "headpath", nullable = false, length = 100)
	public String getHeadpath() {
		return this.headpath;
	}

	public void setHeadpath(String headpath) {
		this.headpath = headpath;
	}

}