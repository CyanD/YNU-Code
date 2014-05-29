package com.model.addressbook.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AddressbookDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "addressbook_department", catalog = "umobile")
public class AddressbookDepartmentTree implements java.io.Serializable {

	// Fields


	private Long id;
	private Long pid;
	private String name;
	public AddressbookDepartmentTree() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}
	@Column(name = "pid")
	public Long getPid() {
		return pid;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPid(Long pid) {
		if(pid==null)pid=0l;
		this.pid = pid;
	}

}