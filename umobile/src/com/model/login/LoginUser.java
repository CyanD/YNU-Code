package com.model.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user", catalog = "umobile")
public class LoginUser implements java.io.Serializable {

	// Fields

	private Long id;
	private LoginRole loginRole;
	private LoginDept loginDept;
	private String account;
	private String password;
	private String name;
	private String tel;
	private String email;
	private String createTime;
	private String status;
	private String publisher;

	// Constructors

	/** default constructor */
	public LoginUser() {
	}

	/** minimal constructor */
	public LoginUser(LoginRole loginRole, LoginDept loginDept, String account,
			String password, String name, String createTime, String status,
			String publisher) {
		this.loginRole = loginRole;
		this.loginDept = loginDept;
		this.account = account;
		this.password = password;
		this.name = name;
		this.createTime = createTime;
		this.status = status;
		this.publisher = publisher;
	}

	/** full constructor */
	public LoginUser(LoginRole loginRole, LoginDept loginDept, String account,
			String password, String name, String tel, String email,
			String createTime, String status, String publisher) {
		this.loginRole = loginRole;
		this.loginDept = loginDept;
		this.account = account;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.createTime = createTime;
		this.status = status;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleId", nullable = false)
	public LoginRole getLoginRole() {
		return this.loginRole;
	}

	public void setLoginRole(LoginRole loginRole) {
		this.loginRole = loginRole;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptId", nullable = false)
	public LoginDept getLoginDept() {
		return this.loginDept;
	}

	public void setLoginDept(LoginDept loginDept) {
		this.loginDept = loginDept;
	}

	@Column(name = "account", nullable = false, length = 30)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tel", length = 20)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}