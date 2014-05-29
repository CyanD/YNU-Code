package com.model.sys;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.model.news.news.NewsNews;

/**
 * SysDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_dept", catalog = "umobile")
public class SysDept implements java.io.Serializable {

	// Fields

	private Long id;
	private SysDept sysDept;
	private String name;
	private Timestamp createTime;
	private String publisher;
	private Set<SysDept> sysDepts = new HashSet<SysDept>(0);
	private Set<NewsNews> newsNewses = new HashSet<NewsNews>(0);
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	// Constructors

	/** default constructor */
	public SysDept() {
	}

	/** minimal constructor */
	public SysDept(String name, Timestamp createTime, String publisher) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public SysDept getSysDept() {
		return this.sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysDept")
	public Set<SysDept> getSysDepts() {
		return this.sysDepts;
	}

	public void setSysDepts(Set<SysDept> sysDepts) {
		this.sysDepts = sysDepts;
	}



	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysDept")
	public Set<NewsNews> getNewsNewses() {
		return this.newsNewses;
	}

	public void setNewsNewses(Set<NewsNews> newsNewses) {
		this.newsNewses = newsNewses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysDept")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

}