package com.model.news.album;

import java.sql.Timestamp;
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
 * NewsAlbum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_album", catalog = "umobile")
public class NewsAlbumDelete implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String photographer;
	private String coverPath;
	private String description;
	private Integer visitors;
	private String status;
	private Timestamp createTime;
	private String publisher;
	private Set<NewsAlbumPictureDelete> newsAlbumPictures = new HashSet<NewsAlbumPictureDelete>(
			0);

	// Constructors

	/** default constructor */
	public NewsAlbumDelete() {
	}

	/** minimal constructor */
	public NewsAlbumDelete(String title, String description, String status,
			Timestamp createTime, String publisher) {
		this.title = title;
		this.description = description;
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
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "newsAlbum")
	public Set<NewsAlbumPictureDelete> getNewsAlbumPictures() {
		return this.newsAlbumPictures;
	}

	public void setNewsAlbumPictures(Set<NewsAlbumPictureDelete> newsAlbumPictures) {
		this.newsAlbumPictures = newsAlbumPictures;
	}

}