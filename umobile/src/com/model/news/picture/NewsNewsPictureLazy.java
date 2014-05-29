package com.model.news.picture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NewsNewsPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_news_picture", catalog = "umobile")
public class NewsNewsPictureLazy implements java.io.Serializable {

	// Fields

	public NewsNewsPictureLazy(Long id, Long pid, String description,
			Integer orders, String path, String isCover, String createTime,
			String publisher) {
		super();
		this.id = id;
		this.pid = pid;
		this.description = description;
		this.orders = orders;
		this.path = path;
		this.isCover = isCover;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	private Long id;
	private Long pid;
	private String description;
	private Integer orders;
	private String path;

	private String isCover;

	private String createTime;
	private String publisher;
	/** default constructor */
	public NewsNewsPictureLazy() {
	}
	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	// Constructors

	@Column(name = "description")
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

	@Column(name = "isCover", nullable = false, length = 2)
	public String getIsCover() {
		return this.isCover;
	}

	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	@Column(name = "path", nullable = false, length = 50)
	public String getPath() {
		return this.path;
	}

	@Column(name = "pid", nullable = false)
	public Long getPid() {
		return pid;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
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

	public void setIsCover(String isCover) {
		this.isCover = isCover;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}