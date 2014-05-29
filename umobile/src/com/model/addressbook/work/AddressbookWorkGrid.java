package com.model.addressbook.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.util.Sys;

/**
 * AddressbookWork entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "addressbook_work", catalog = "umobile")
public class AddressbookWorkGrid implements java.io.Serializable {



	private Long id;
	private AddressbookDeptTree addressbookDept;
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
	private String isLeader;
	private Integer orders;
	private String canmsg;
	@Column(name = "canmsg", length = 3)
	public String getCanmsg() {
		return this.canmsg;
	}

	public void setCanmsg(String canmsg) {
		this.canmsg = canmsg;
	}
	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	private Integer age;
	private String partisan;
	@Transient
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "partisan", length = 100)
	public String getPartisan() {
		return this.partisan;
	}

	public void setPartisan(String partisan) {
		this.partisan = partisan;
	}
	private AddressbookDepartmentTree addressbookDepartment;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departmentId")
	public AddressbookDepartmentTree getAddressbookDepartment() {
		return this.addressbookDepartment;
	}

	public void setAddressbookDepartment(
			AddressbookDepartmentTree addressbookDepartment) {
		this.addressbookDepartment = addressbookDepartment;
	}

	@Column(name = "isLeader", length = 2)
	public String getIsLeader() {
		return this.isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	// Constructors

	/** default constructor */
	public AddressbookWorkGrid() {
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
	@JoinColumn(name = "deptid")
	public AddressbookDeptTree getAddressbookDept() {
		return this.addressbookDept;
	}

	public void setAddressbookDept(AddressbookDeptTree addressbookDept) {
		this.addressbookDept = addressbookDept;
	}

	@Column(name = "jobTitle", length = 20)
	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "position", length = 20)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "jobNumber", length = 20)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
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

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "mobilePhone", length = 15)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "homePhone", length = 15)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "officePhone", length = 15)
	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "fax", length = 15)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "birthday", length = 20)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.age=Sys.getAge(birthday);
		this.birthday = birthday;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "remark", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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