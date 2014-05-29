package com.vo.bean;

import com.model.addressbook.alumni.AddressbookAlumni;
import com.ynu.zjx.PageBean;


public class AddressbookAlumniVO extends PageBean{
	private Long id;
	private String yearIn;
	private String major;
	private String name;
	private String sex;
	private String mobilePhone;
	private String homePhone;
	private String officePhone;
	private String address;
	private String fax;
	private String birthday;
	private String email;
	private String company;
	private String country;
	private String city;
	private String remark;
	public AddressbookAlumni getAddressbookAlumni(){
		return new AddressbookAlumni(id, yearIn, major, name, sex, mobilePhone, homePhone, officePhone, address, fax, birthday, email, city, remark, company, country);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getYearIn() {
		return yearIn;
	}
	public void setYearIn(String yearIn) {
		this.yearIn = yearIn;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
