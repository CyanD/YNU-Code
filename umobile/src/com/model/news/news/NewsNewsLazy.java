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
public class NewsNewsLazy implements java.io.Serializable {

	// Fields

	private Long id;

	private Long deptId;
	private Long categoryId;
	private String categoryName;
	private String title;

	private String deptName;
	private String author;

	private String photographer;

	private String keywords;
	private String headline;
	private String abstracts;
	private String coverPath;
	private String content;
	private Integer visitors;
	private String status;
	private String createTime;
	private String publisher;
	/** default constructor */
	public NewsNewsLazy() {
	}
	public NewsNewsLazy(Long id, Long deptId, Long categoryId,
			String categoryName, String title, String deptName, String author,
			String photographer, String keywords, String headline,
			String abstracts, String coverPath, String content,
			Integer visitors, String status, 
			String createTime, String publisher) {
		this.id = id;
		this.deptId = deptId;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.title = title;
		this.deptName = deptName;
		this.author = author;
		this.photographer = photographer;
		this.keywords = keywords;
		this.headline = headline;
		this.abstracts = abstracts;
		this.coverPath = coverPath;
		this.content = content;
		this.visitors = visitors;
		this.status = status;
		this.createTime = createTime;
		this.publisher = publisher;
	}
	@Column(name = "abstracts", nullable = false, length = 100)
	public String getAbstracts() {
		return this.abstracts;
	}
	@Column(name = "author", nullable = false, length = 20)
	public String getAuthor() {
		return this.author;
	}
	@Column(name = "categoryId")
	public Long getCategoryId() {
		return categoryId;
	}

	// Constructors

	@Column(name = "categoryName", length = 30)
	public String getCategoryName() {
		return this.categoryName;
	}



	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	@Column(name = "coverPath", length = 50)
	public String getCoverPath() {
		return this.coverPath;
	}


	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	@Column(name = "deptId")
	public Long getDeptId() {
		return deptId;
	}

	@Column(name = "deptName", length = 100)
	public String getDeptName() {
		return this.deptName;
	}

	@Column(name = "headline", length = 2)
	public String getHeadline() {
		return this.headline;
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

	@Column(name = "photographer", length = 20)
	public String getPhotographer() {
		return this.photographer;
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

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setPhotographer(String photographer) {
		this.photographer = photographer;
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