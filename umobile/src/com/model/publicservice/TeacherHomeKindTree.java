package com.model.publicservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PublicserviceTeacherhomeKind entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacherhome_kind", catalog = "umobile")
public class TeacherHomeKindTree implements java.io.Serializable {
	
	// Fields

	private Long id;
	private Long pid;
	private String name;

	// Property accessors
	public TeacherHomeKindTree() {
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

	@Column(name = "pid")
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		if(pid==null)pid=0l;
		this.pid = pid;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}