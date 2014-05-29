package com.model.news.album;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * NewsAlbum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_album", catalog = "umobile")
public class NewsAlbumGrid implements java.io.Serializable {

	public NewsAlbumGrid(Long id, String title, String photographer, String coverPath, String description,
			Integer visitors, String status) {
		super();
		this.id = id;
		this.title = title;
		this.photographer = photographer;
		this.coverPath = coverPath;
		this.description = description;
		this.visitors = visitors;
		this.status = status;
	}

	// Fields

	private Long id;
	private NewsAlbumCategoryTree newsAlbumCategory;
	private String title;
	private String photographer;
	private String coverPath;
	private String description;
	private Integer visitors;
	private String status;
	private String createTime;
	private String publisher;

	// Constructors

	/** default constructor */
	public NewsAlbumGrid() {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	public NewsAlbumCategoryTree getNewsAlbumCategoryTree() {
		return this.newsAlbumCategory;
	}

	public void setNewsAlbumCategoryTree(NewsAlbumCategoryTree newsAlbumCategory) {
		this.newsAlbumCategory = newsAlbumCategory;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "photographer", length = 20)
	public String getPhotographer() {
		return this.photographer;
	}

	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}

	@Column(name = "coverPath", length = 50)
	public String getCoverPath() {
		return this.coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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