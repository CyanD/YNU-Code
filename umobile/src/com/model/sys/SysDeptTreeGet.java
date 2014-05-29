package com.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_dept", catalog = "umobile")
public class SysDeptTreeGet implements java.io.Serializable {

	// Fields

	private Long id;
	private Long _parentId;
	private String name;
	public SysDeptTreeGet() {
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

	
	
	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}
	@Column(name = "pid")
	public Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(Long _parentId) {
		if(_parentId==null)_parentId=0l;
		this._parentId = _parentId;
	}

	public void setName(String name) {
		this.name = name;
	}
}