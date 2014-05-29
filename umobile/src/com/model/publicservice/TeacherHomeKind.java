package com.model.publicservice;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * PublicserviceTeacherhomeKind entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacherhome_kind", catalog = "umobile")
public class TeacherHomeKind implements java.io.Serializable {
	
	// Fields

	private Long id;
	private Long _parentId;
	private String name;
	private String createTime;
	private String publisher;

	// Constructors

	/** default constructor */
	public TeacherHomeKind() {
	}
	
	/** full constructor */
	public TeacherHomeKind(Long id, Long _parentId, String name, String createTime,
			String publisher) {
		this.id = id;
		if(_parentId==0l)_parentId=null;
		this._parentId = _parentId;
		this.name = name;
		this.createTime = createTime;
		this.publisher = publisher;
	
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

	@Column(name = "pid")
	public Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(Long _parentId) {
		if(_parentId==null)_parentId=0l;
		this._parentId = _parentId;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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