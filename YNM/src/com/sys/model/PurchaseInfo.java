package com.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "purchaseinfo")
public class PurchaseInfo implements java.io.Serializable{
	private static final long serialVersionUID = 3436465887404172412L;
	@Id   //主键注释为id
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

	public PurchaseInfo(){
		
	}
	public PurchaseInfo(Long pinfoid,Long producttypeid,Long userid,
			String phead,String pdetail){
		super();
		this.pinfoid = pinfoid;
		this.producttypeid = producttypeid;
		this.userid = userid;
		this.phead = phead;
		this.pdetail = pdetail;
		
		
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
	
}
