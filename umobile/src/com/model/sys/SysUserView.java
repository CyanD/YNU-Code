package com.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysUserViewId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user_view", catalog = "umobile")
public class SysUserView implements java.io.Serializable {

	// Fields

	private Long id;
	private String account;
	private String createTime;
	private String name;
	private Long roleId;
	private Long deptId;
	private String roleName;
	private String deptName;
	private String tel;
	private String email;
	private String status;
	private String password;
	private String publisher;

	// Constructors

	/** default constructor */
	public SysUserView() {
	}

	/** minimal constructor */
	public SysUserView(Long id, String account, String createTime,
			String name, Long roleId, Long deptId, String roleName,
			String deptName, String status, String password, String publisher) {
		this.id = id;
		this.account = account;
		this.createTime = createTime;
		this.name = name;
		this.roleId = roleId;
		this.deptId = deptId;
		this.roleName = roleName;
		this.deptName = deptName;
		this.status = status;
		this.password = password;
		this.publisher = publisher;
	}

	/** full constructor */
	public SysUserView(Long id, String account, String createTime,
			String name, Long roleId, Long deptId, String roleName,
			String deptName, String tel, String email, String status,
			String password, String publisher) {
		this.id = id;
		this.account = account;
		this.createTime = createTime;
		this.name = name;
		this.roleId = roleId;
		this.deptId = deptId;
		this.roleName = roleName;
		this.deptName = deptName;
		this.tel = tel;
		this.email = email;
		this.status = status;
		this.password = password;
		this.publisher = publisher;
	}

	// Property accessors
	@Id
	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "account", nullable = false, length = 30)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "roleId", nullable = false)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "deptId", nullable = false)
	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Column(name = "roleName", nullable = false, length = 20)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "deptName", nullable = false, length = 100)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	@Column(name = "status", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysUserView))
			return false;
		SysUserView castOther = (SysUserView) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getAccount() == castOther.getAccount()) || (this
						.getAccount() != null && castOther.getAccount() != null && this
						.getAccount().equals(castOther.getAccount())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getDeptId() == castOther.getDeptId()) || (this
						.getDeptId() != null && castOther.getDeptId() != null && this
						.getDeptId().equals(castOther.getDeptId())))
				&& ((this.getRoleName() == castOther.getRoleName()) || (this
						.getRoleName() != null
						&& castOther.getRoleName() != null && this
						.getRoleName().equals(castOther.getRoleName())))
				&& ((this.getDeptName() == castOther.getDeptName()) || (this
						.getDeptName() != null
						&& castOther.getDeptName() != null && this
						.getDeptName().equals(castOther.getDeptName())))
				&& ((this.getTel() == castOther.getTel()) || (this.getTel() != null
						&& castOther.getTel() != null && this.getTel().equals(
						castOther.getTel())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null && castOther.getEmail() != null && this
						.getEmail().equals(castOther.getEmail())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getPassword() == castOther.getPassword()) || (this
						.getPassword() != null
						&& castOther.getPassword() != null && this
						.getPassword().equals(castOther.getPassword())))
				&& ((this.getPublisher() == castOther.getPublisher()) || (this
						.getPublisher() != null
						&& castOther.getPublisher() != null && this
						.getPublisher().equals(castOther.getPublisher())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getAccount() == null ? 0 : this.getAccount().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getDeptId() == null ? 0 : this.getDeptId().hashCode());
		result = 37 * result
				+ (getRoleName() == null ? 0 : this.getRoleName().hashCode());
		result = 37 * result
				+ (getDeptName() == null ? 0 : this.getDeptName().hashCode());
		result = 37 * result
				+ (getTel() == null ? 0 : this.getTel().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getPassword() == null ? 0 : this.getPassword().hashCode());
		result = 37 * result
				+ (getPublisher() == null ? 0 : this.getPublisher().hashCode());
		return result;
	}

}