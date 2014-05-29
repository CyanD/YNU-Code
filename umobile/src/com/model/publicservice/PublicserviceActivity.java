package com.model.publicservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PublicserviceActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "publicservice_activity", catalog = "umobile")
public class PublicserviceActivity implements java.io.Serializable {

	public PublicserviceActivity(Long id, String title, String beginDate,
			String endDate, String timePoint, String location, String content,String category) {
		super();
		this.id = id;
		this.title = title;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.timePoint = timePoint;
		this.location = location;
		this.content = content;
		this.category=category;
	}

	// Fields

	private Long id;
	private String title;
	private String beginDate;
	private String endDate;
	private String timePoint;
	private String location;
	private String content;
	private String createTime;
	private String publisher;
	private String category;
	

	// Constructors

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/** default constructor */
	public PublicserviceActivity() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "title", nullable = false, length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "beginDate", nullable = false, length = 10)
	public String getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "endDate", nullable = false, length = 10)
	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "timePoint", nullable = false, length = 100)
	public String getTimePoint() {
		return this.timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}

	@Column(name = "location", nullable = false, length = 200)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "content", length = 65535)
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

}