package com.model.publicservice;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * YnustoryHeadBack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ynustory_head_back", catalog = "umobile")
public class YnuStoryHeadBack implements java.io.Serializable {

	// Fields

	private Long id;
	private String path;
	private String createTime;

	// Constructors

	/** default constructor */
	public YnuStoryHeadBack() {
	}

	/** full constructor */
	public YnuStoryHeadBack(String path, String createTime) {
		this.path = path;
		this.createTime = createTime;
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

	@Column(name = "path", nullable = false, length = 200)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}