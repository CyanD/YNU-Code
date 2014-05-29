package com.sys.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user")
public class LoginUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3436465887404172412L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	private Long id;
	
	
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "cardType")
	private String cardType;
	
	@Column(name = "idnum")
	private String idnum;
	
	@Column(name = "failureDate")
	private Timestamp failureDate;
	
	@Column(name = "customerStatus")
	private String customerStatus;
	
	@Column(name = "customerLev")
	private String customerLev;
	
	@Column(name = "loyalty")
	private String loyalty;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "cellphone")
	private String cellphone;
	
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "zipCode")
	private String zipCode;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "educationalBackground")
	private String educationalBackground;
	
	@Column(name = "occupation")
	private String occupation;
	
	@Column(name = "remarks")
	private String remarks;

	
	
	// Constructors
	/** default constructor */
	public LoginUser() {
	}

	/** minimal constructor */
	public LoginUser( String userName, String password,
			String cardType, String idnum, Timestamp failureDate,
			String customerStatus, String customerLev, String loyalty,
			String educationalBackground, String occupation) {
		this.userName = userName;
		this.password = password;
		this.cardType = cardType;
		this.idnum = idnum;
		this.failureDate = failureDate;
		this.customerStatus = customerStatus;
		this.customerLev = customerLev;
		this.loyalty = loyalty;
		this.educationalBackground = educationalBackground;
		this.occupation = occupation;
	}

	/** full constructor */
	public LoginUser( String userName, String password,
			String cardType, String idnum, Timestamp failureDate,
			String customerStatus, String customerLev, String loyalty,
			String email, String cellphone, String tel, String address,
			String zipCode, String sex, Date birthday,
			String educationalBackground, String occupation, String remarks) {
		
		this.userName = userName;
		this.password = password;
		this.cardType = cardType;
		this.idnum = idnum;
		this.failureDate = failureDate;
		this.customerStatus = customerStatus;
		this.customerLev = customerLev;
		this.loyalty = loyalty;
		this.email = email;
		this.cellphone = cellphone;
		this.tel = tel;
		this.address = address;
		this.zipCode = zipCode;
		this.sex = sex;
		this.birthday = birthday;
		this.educationalBackground = educationalBackground;
		this.occupation = occupation;
		this.remarks = remarks;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public String getIdnum() {
		return this.idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public Timestamp getFailureDate() {
		return this.failureDate;
	}

	public void setFailureDate(Timestamp failureDate) {
		this.failureDate = failureDate;
	}

	public String getCustomerStatus() {
		return this.customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getCustomerLev() {
		return this.customerLev;
	}

	public void setCustomerLev(String customerLev) {
		this.customerLev = customerLev;
	}

	public String getLoyalty() {
		return this.loyalty;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEducationalBackground() {
		return this.educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

}