package com.model.news.album;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * NewsAlbumPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_album_picture", catalog = "umobile")
public class NewsAlbumPictureDelete implements java.io.Serializable {

	// Fields

	private Long id;
	private NewsAlbumDelete newsAlbum;
	private String description;
	private Integer orders;
	private String path;
	private String isCover;
	private Timestamp createTime;
	private String publisher;

	// Constructors

	/** default constructor */
	public NewsAlbumPictureDelete() {
	}

	/** minimal constructor */
	public NewsAlbumPictureDelete(NewsAlbumDelete newsAlbum, String path, String isCover,
			Timestamp createTime, String publisher) {
		this.newsAlbum = newsAlbum;
		this.path = path;
		this.isCover = isCover;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	/** full constructor */
	public NewsAlbumPictureDelete(NewsAlbumDelete newsAlbum, String description,
			Integer orders, String path, String isCover, Timestamp createTime,
			String publisher) {
		this.newsAlbum = newsAlbum;
		this.description = description;
		this.orders = orders;
		this.path = path;
		this.isCover = isCover;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid", nullable = false)
	public NewsAlbumDelete getNewsAlbum() {
		return this.newsAlbum;
	}

	public void setNewsAlbum(NewsAlbumDelete newsAlbum) {
		this.newsAlbum = newsAlbum;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	@Column(name = "path", nullable = false, length = 50)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "isCover", nullable = false, length = 2)
	public String getIsCover() {
		return this.isCover;
	}

	public void setIsCover(String isCover) {
		this.isCover = isCover;
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

}