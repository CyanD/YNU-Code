package com.model.news.album;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NewsAlbumPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_album_picture", catalog = "umobile")
public class NewsAlbumPictureLazy implements java.io.Serializable {

	public NewsAlbumPictureLazy(Long id, Long pid, String description,
			Integer orders, String path, String isCover) {
		super();
		this.id = id;
		this.pid = pid;
		this.description = description;
		this.orders = orders;
		this.path = path;
		this.isCover = isCover;
	}

	// Fields

	private Long id;
	private Long pid;
	@Column(name = "pid", nullable = false)
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	private String description;
	private Integer orders;
	private String path;
	private String isCover;
	private String createTime;
	private String publisher;

	// Constructors

	/** default constructor */
	public NewsAlbumPictureLazy() {
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