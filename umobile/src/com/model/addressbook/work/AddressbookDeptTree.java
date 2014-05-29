package com.model.addressbook.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AddressbookDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "addressbook_dept", catalog = "umobile")
public class AddressbookDeptTree implements java.io.Serializable {

	// Fields

	private Long id;
	private Long pid;
	private String name;
	private String isSystem;

	// Constructors

	/** default constructor */
	public AddressbookDeptTree() {
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
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		if(pid==null)pid=0l;
		this.pid = pid;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "isSystem", length = 2)
	public String getIsSystem() {
		return this.isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}


}