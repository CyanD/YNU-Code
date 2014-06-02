package com.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "viewpurchase")
public class Viewpurchase implements java.io.Serializable{
	private static final long serialVersionUID = 3436465887404172412L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	
	private Long pinfoid;
	
	@Column(name = "producttypeid")
	private Long producttypeid;
	
	@Column(name = "userid")
	private Long userid;
	
	@Column(name = "phead")
	private String phead;
	
	@Column(name = "pdetail")
	private String pdetail;
	
	@Column(name = "producttypename")
	private String producttypename;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "position")
	private String position;
	
	public Viewpurchase(){
	}
	
	public Viewpurchase(Long pinfoid,Long producttypeid,Long userid,String phead,
			String pdetail,String producttypename,String username,String telephone,
			String position){
		super();
		this.pinfoid = pinfoid;
		this.producttypeid = producttypeid;
		this.userid = userid;
		this.phead = phead;
		this.pdetail = pdetail;
		this.producttypename = producttypename;
		this.username = username;
		this.telephone = telephone;
		this.position = position;
	}

	public Long getPinfoid() {
		return pinfoid;
	}

	public void setPinfoid(Long pinfoid) {
		this.pinfoid = pinfoid;
	}

	public Long getProducttypeid() {
		return producttypeid;
	}

	public void setProducttypeid(Long producttypeid) {
		this.producttypeid = producttypeid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getPhead() {
		return phead;
	}

	public void setPhead(String phead) {
		this.phead = phead;
	}

	public String getPdetail() {
		return pdetail;
	}

	public void setPdetail(String pdetail) {
		this.pdetail = pdetail;
	}

	public String getProducttypename() {
		return producttypename;
	}

	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPosition() {
		return position;
	} 

	public void setPosition(String position) {
		this.position = position;
	}
	

}
