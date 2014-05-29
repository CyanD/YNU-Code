package com.vo.bean;

import java.io.File;
import java.util.List;

import javax.persistence.Column;

import com.model.addressbook.work.AddressbookWorkLazy;
import com.ynu.zjx.PageBean;


public class AddressbookWorkVO extends PageBean{
	private Long id;
	private Long deptId;
	private Long departmentId;
	private String partisan;
	private String canmsg;
	public String getCanmsg() {
		return canmsg;
	}
	public void setCanmsg(String canmsg) {
		this.canmsg = canmsg;
	}
	public String getPartisan() {
		return partisan;
	}
	public void setPartisan(String partisan) {
		this.partisan = partisan;
	}
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
	private String isLeader;
	private String department;
	private Integer orders;
	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public AddressbookWorkLazy getAddressbookWorkLazy(){
		return new AddressbookWorkLazy(id, deptId, departmentId,jobTitle, position, jobNumber, password, name, sex, mobilePhone, homePhone, officePhone, address, fax, birthday, email, remark, isLeader,orders,partisan,canmsg);
	}
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	private List<File> Filedata;
	
	public List<File> getFiledata() {
		return Filedata;
	}
	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}
	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}
	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}
	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}
	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	public String getAddress() {
		return address;
	}
	public String getBirthday() {
		return birthday;
	}
	public String getEmail() {
		return email;
	}
	public String getFax() {
		return fax;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public Long getId() {
		return id;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public String getName() {
		return name;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public String getPassword() {
		return password;
	}
	public String getPosition() {
		return position;
	}
	public String getRemark() {
		return remark;
	}
	public String getSex() {
		return sex;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Long getDeptId() {
		return deptId;
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
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
