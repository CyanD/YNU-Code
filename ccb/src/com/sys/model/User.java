package com.sys.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * @版权：
 * @作者：韩强
 * @创建时间:2013年12月12日
 * @模块功能：
 * @修改记录： 
 */

@Entity
@Table(name = "user", catalog = "ccb")
public class User implements java.io.Serializable {

	// Fields

	private Long id;
	private Long rolId;
	private Long comId;
	private String userName;
	private String password;
	private String cardType;
	private String idnum;
	private Date failureDate;
	private String customerStatus;
	private String email;
	private String cellphone;
	private String tel;
	private String address;
	private String zipCode;
	private String sex;
	private Date birthday;
	private String educationalBackground;
	private String occupation;
	private String remarks;
	private String customerLev;
	private String loyalty;
	

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Long comId, String userName, String password,
			String cardType, String idnum, Date failureDate,
			String customerStatus, String educationalBackground,
			String occupation) {
		this.comId = comId;
		this.userName = userName;
		this.password = password;
		this.cardType = cardType;
		this.idnum = idnum;
		this.failureDate = failureDate;
		this.customerStatus = customerStatus;
		this.educationalBackground = educationalBackground;
		this.occupation = occupation;
	}

	/** full constructor */
	public User(Long rolId, Long comId, String userName, String password,
			String cardType, String idnum, Date failureDate,
			String customerStatus, String email, String cellphone, String tel,
			String address, String zipCode, String sex, Date birthday,
			String educationalBackground, String occupation, String remarks,
			String customerLev, String loyalty) {
		this.comId = comId;
		this.rolId = rolId;
		this.userName = userName;
		this.password = password;
		this.cardType = cardType;
		this.idnum = idnum;
		this.failureDate = failureDate;
		this.customerStatus = customerStatus;
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
		this.customerLev = customerLev;
		this.loyalty = loyalty;
		
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "com_id", nullable = false)
	public Long getComId() {
		return this.comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}
	
	@Column(name = "rol_id")
	public Long getRolId() {
		return this.rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	@Column(name = "userName", nullable = false, length = 30)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "cardType", nullable = false, length = 3)
	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Column(name = "IDNum", nullable = false, length = 20)
	public String getIdnum() {
		return this.idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "failureDate", nullable = false, length = 10)
	public Date getFailureDate() {
		return this.failureDate;
	}

	public void setFailureDate(Date failureDate) {
		this.failureDate = failureDate;
	}

	@Column(name = "customerStatus", nullable = false, length = 3)
	public String getCustomerStatus() {
		return this.customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	@Column(name = "email", length = 20)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cellphone", length = 11)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "tel", length = 13)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "address", length = 30)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zipCode", length = 10)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "educationalBackground", nullable = false, length = 3)
	public String getEducationalBackground() {
		return this.educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	@Column(name = "occupation", nullable = false, length = 20)
	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Column(name = "remarks", length = 100)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "customerLev")
	public String getCustomerLev() {
		return this.customerLev;
	}

	public void setCustomerLev(String customerLev) {
		this.customerLev = customerLev;
	}

	@Column(name = "loyalty")
	public String getLoyalty() {
		return this.loyalty;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}

	

}