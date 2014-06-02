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
	private Long userid;
	
	
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "userrole")
	private String userrole;
	
	@Column(name = "userphotourl")
	private String userphotourl;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "position")
	private String position;
	
	
	// Constructors
	/** default constructor */
	public LoginUser() {
	}

	/** minimal constructor */
	public LoginUser( String username, String password,
			String userrole) {
		this.username = username;
		this.setPassword(password);
		this.setUserrole(userrole);
	}

	/** full constructor */
	public LoginUser( String username, String password,
			String userrole, String userphotourl, 
			String telephone, String email, String position) {
		this.username = username;
		this.setPassword(password);
		this.setUserrole(userrole);
		this.setUserphotourl(userphotourl);
		this.setTelephone(telephone);
		this.setEmail(email);
		this.setPosition(position);
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getUserphotourl() {
		return userphotourl;
	}

	public void setUserphotourl(String userphotourl) {
		this.userphotourl = userphotourl;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	

}