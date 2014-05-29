package com.model.addressbook.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AddressbookWork entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "addressbook_work", catalog = "umobile")
public class AddressbookWorkLazy implements java.io.Serializable {

	// Fields


	private Long id;
	private Long deptId;
	private Long departmentId;
	@Column(name = "departmentId")
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	private String jobTitle;
	
	private String position;

	private String jobNumber;

	private String password;
	private String name;
	private String sex;
	private String mobilePhone;
	private String homePhone;
	private String officePhone;
	private String address;
	private String fax;
	private String birthday;
	private String email;
	private String remark;
	private String createTime;
	private String publisher;
	private String partisan;
	private String canmsg;
	@Column(name = "canmsg", length = 3)
	public String getCanmsg() {
		return this.canmsg;
	}

	public void setCanmsg(String canmsg) {
		this.canmsg = canmsg;
	}

	@Column(name = "partisan", length = 100)
	public String getPartisan() {
		return this.partisan;
	}

	public void setPartisan(String partisan) {
		this.partisan = partisan;
	}
	private Integer orders;
	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	private String isLeader;
	/** default constructor */
	public AddressbookWorkLazy() {
	}
	public AddressbookWorkLazy(Long id, Long deptId,Long departmentId,
			String jobTitle, String position, String jobNumber,
			String password, String name, String sex, String mobilePhone,
			String homePhone, String officePhone, String address, String fax,
			String birthday, String email, String remark, String isLeader,Integer orders,String partisan,String canmsg) {
		super();
		this.id = id;
		this.deptId = deptId;
		this.departmentId = departmentId;
		this.jobTitle = jobTitle;
		this.position = position;
		this.jobNumber = jobNumber;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.mobilePhone = mobilePhone;
		this.homePhone = homePhone;
		this.officePhone = officePhone;
		this.address = address;
		this.fax = fax;
		this.birthday = birthday;
		this.email = email;
		this.remark = remark;
		this.isLeader = isLeader;
		this.orders=orders;
		this.partisan=partisan;
		this.canmsg=canmsg;
	}
	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}
	@Column(name = "birthday", length = 20)
	public String getBirthday() {
		return this.birthday;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	// Constructors

	@Column(name = "deptId")
	public Long getDeptId() {
		return deptId;
	}


	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	@Column(name = "fax", length = 15)
	public String getFax() {
		return this.fax;
	}

	@Column(name = "homePhone", length = 15)
	public String getHomePhone() {
		return this.homePhone;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	@Column(name = "isLeader", length = 2)
	public String getIsLeader() {
		return this.isLeader;
	}

	@Column(name = "jobNumber", length = 20)
	public String getJobNumber() {
		return this.jobNumber;
	}

	@Column(name = "jobTitle", length = 20)
	public String getJobTitle() {
		return this.jobTitle;
	}

	@Column(name = "mobilePhone", length = 15)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	@Column(name = "officePhone", length = 15)
	public String getOfficePhone() {
		return this.officePhone;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	@Column(name = "position", length = 20)
	public String getPosition() {
		return this.position;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	@Column(name = "remark", length = 50)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}