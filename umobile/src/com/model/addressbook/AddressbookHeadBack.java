package com.model.addressbook;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AddressbookHeadBack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "addressbook_head_back", catalog = "umobile")
public class AddressbookHeadBack implements java.io.Serializable {

	// Fields

	private Long id;
	private String path;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public AddressbookHeadBack() {
	}

	/** full constructor */
	public AddressbookHeadBack(String path, Timestamp createTime) {
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
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}