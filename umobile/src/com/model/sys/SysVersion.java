package com.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * SysVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_version", catalog = "umobile")
public class SysVersion implements java.io.Serializable {

	// Fields

	private Long id;
	private Long version;
	private String name;

	// Constructors

	/** default constructor */
	public SysVersion() {
	}

	/** full constructor */
	public SysVersion(String name) {
		this.name = name;
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

	@Version
	@Column(name = "version", nullable = false)
	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}