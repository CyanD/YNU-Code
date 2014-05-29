package com.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_log", catalog = "umobile")
public class SysLog implements java.io.Serializable {

	// Fields

	private Long id;
	private String userName;
	private Long userId;
	private String userIp;
	private String userDept;
	private String oper;
	private String operTable;
	private Long operObjId;
	private String operDetail;
	private String operTime;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(String userName, String userDept, String oper,
			String operTable, String operTime) {
		this.userName = userName;
		this.userDept = userDept;
		this.oper = oper;
		this.operTable = operTable;
		this.operTime = operTime;
	}

	/** full constructor */
	public SysLog(String userName, Long userId, String userIp, String userDept,
			String oper, String operTable, Long operObjId, String operDetail,
			String operTime) {
		this.userName = userName;
		this.userId = userId;
		this.userIp = userIp;
		this.userDept = userDept;
		this.oper = oper;
		this.operTable = operTable;
		this.operObjId = operObjId;
		this.operDetail = operDetail;
		this.operTime = operTime;
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

	@Column(name = "userName", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "userIp", length = 50)
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Column(name = "userDept", nullable = false, length = 20)
	public String getUserDept() {
		return this.userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	@Column(name = "oper", nullable = false)
	public String getOper() {
		return this.oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	@Column(name = "operTable", nullable = false, length = 20)
	public String getOperTable() {
		return this.operTable;
	}

	public void setOperTable(String operTable) {
		this.operTable = operTable;
	}

	@Column(name = "operObjId")
	public Long getOperObjId() {
		return this.operObjId;
	}

	public void setOperObjId(Long operObjId) {
		this.operObjId = operObjId;
	}

	@Column(name = "operDetail")
	public String getOperDetail() {
		return this.operDetail;
	}

	public void setOperDetail(String operDetail) {
		this.operDetail = operDetail;
	}

	@Column(name = "operTime", nullable = false, length = 19)
	public String getOperTime() {
		return this.operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

}