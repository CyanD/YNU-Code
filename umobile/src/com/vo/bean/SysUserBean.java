package com.vo.bean;

import java.sql.Timestamp;

public class SysUserBean{
	private java.math.BigInteger id;
	private String roleName;
	private java.math.BigInteger roleId;
	private java.math.BigInteger deptId;
	private String deptName;
	private String account;
	private String password;
	private String name;
	private String tel;
	private String email;
	private Timestamp createTime;
	private String status;
	private String publisher;
	public String getAccount() {
		return account;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public String getDeptName() {
		return deptName;
	}
	public String getEmail() {
		return email;
	}
	public java.math.BigInteger getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getRoleName() {
		return roleName;
	}
	public String getStatus() {
		return status;
	}
	public String getTel() {
		return tel;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(java.math.BigInteger id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public java.math.BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(java.math.BigInteger roleId) {
		this.roleId = roleId;
	}
	public java.math.BigInteger getDeptId() {
		return deptId;
	}
	public void setDeptId(java.math.BigInteger deptId) {
		this.deptId = deptId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
