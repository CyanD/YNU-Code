package com.model.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NewsNoticeAccessories entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice_accessories", catalog = "umobile")
public class NoticeAccessoriesLazy implements java.io.Serializable {
	public NoticeAccessoriesLazy(Long id, Long pid, String name, String path) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.path = path;
	}

	private Long id;
	private Long pid;
	private String name;

	private String path;

	/** default constructor */
	public NoticeAccessoriesLazy() {
	}
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	// Constructors

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	@Column(name = "path", nullable = false, length = 50)
	public String getPath() {
		return this.path;
	}

	@Column(name = "pid", nullable = false)
	public Long getPid() {
		return pid;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

}