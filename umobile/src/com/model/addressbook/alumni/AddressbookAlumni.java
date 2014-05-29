package com.model.addressbook.alumni;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AddressbookAlumni entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "addressbook_alumni", catalog = "umobile")
public class AddressbookAlumni implements java.io.Serializable {


	// Fields

	public AddressbookAlumni(Long id, String yearIn, String major,
			String name, String sex, String mobilePhone, String homePhone,
			String officePhone, String address, String fax, String birthday,
			String email, String city, String remark, String company,
			String country) {
		super();
		this.id = id;
		this.yearIn = yearIn;
		this.major = major;
		this.name = name;
		this.sex = sex;
		this.mobilePhone = mobilePhone;
		this.homePhone = homePhone;
		this.officePhone = officePhone;
		this.address = address;
		this.fax = fax;
		this.birthday = birthday;
		this.email = email;
		this.city = city;
		this.remark = remark;
		this.company = company;
		this.country = country;
	}

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
	private String city;
	private String remark;
	private String createTime;
	private String publisher;
	private String company;
	private String country;
	@Column(name = "company", length = 30)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "country", length = 30)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	// Constructors

	/** default constructor */
	public AddressbookAlumni() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "yearIn", length = 50)
	public String getYearIn() {
		return this.yearIn;
	}

	public void setYearIn(String yearIn) {
		this.yearIn = yearIn;
	}

	@Column(name = "major", length = 50)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
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
		this.birthday = birthday;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "city", length = 20)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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