package com.model.news.album;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NewsAlbum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_album", catalog = "umobile")
public class NewsAlbumLazy implements java.io.Serializable {


	private Long id;
	private Long categoryId;
	private String title;
	private String photographer;
	private String coverPath;
	private String description;
	private Integer visitors;
	private String status;
	private String createTime;
	private String publisher;
	/** default constructor */
	public NewsAlbumLazy() {
	}
	public NewsAlbumLazy(Long id, Long categoryId, String title,
			String photographer,String coverPath,
			String description, Integer visitors, String status) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.photographer = photographer;
		this.coverPath = coverPath;
		this.description = description;
		this.visitors = visitors;
		this.status = status;
	}
	@Column(name = "categoryId")
	public Long getCategoryId() {
		return categoryId;
	}

	// Constructors

	@Column(name = "coverPath", length = 50)
	public String getCoverPath() {
		return this.coverPath;
	}



	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
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

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
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