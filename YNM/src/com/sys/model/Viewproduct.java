package com.sys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "viewproduct")
public class Viewproduct  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3436465887404172412L;


	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	private Long productid;
	
	@Column(name = "productpic")
	private String productpic;
	
	@Column(name = "productbinfo")
	private String productbinfo;
	
	@Column(name = "productprice")
	private String productprice;
	
	@Column(name = "productsum")
	private String productsum;
	
	@Column(name = "showtime")
	private Date showtime;
	
	@Column(name = "deletetime")
	private Date deletetime;
	
	@Column(name = "userid")
	private Long userid;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "productname")
	private String productname;
	
	@Column(name = "producttypeid")
	private Long producttypeid;
	
	@Column(name = "producttypename")
	private String producttypename;
	
	
	
	public Viewproduct() {
	}

	public Viewproduct(Long productid, String productpic, String productbinfo,
			String productprice, String productsum, Date showtime,
			Date deletetime, Long userid, String username, String productname,
			Long producttypeid,String producttypename) {
		super();
		this.productid = productid;
		this.productpic = productpic;
		this.productbinfo = productbinfo;
		this.productprice = productprice;
		this.productsum = productsum;
		this.showtime = showtime;
		this.deletetime = deletetime;
		this.userid = userid;
		this.username = username;
		this.productname = productname;
		this.producttypeid = producttypeid;
		this.producttypename = producttypename;
	}
	
	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public String getProductpic() {
		return productpic;
	}

	public void setProductpic(String productpic) {
		this.productpic = productpic;
	}

	public String getProductbinfo() {
		return productbinfo;
	}

	public void setProductbinfo(String productbinfo) {
		this.productbinfo = productbinfo;
	}

	public String getProductprice() {
		return productprice;
	}

	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}

	public String getProductsum() {
		return productsum;
	}

	public void setProductsum(String productsum) {
		this.productsum = productsum;
	}

	public Date getShowtime() {
		return showtime;
	}

	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}

	public Date getDeletetime() {
		return deletetime;
	}

	public void setDeletetime(Date deletetime) {
		this.deletetime = deletetime;
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

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Long getProducttypeid() {
		return producttypeid;
	}

	public void setProducttypeid(Long producttypeid) {
		this.producttypeid = producttypeid;
	}

	public String getProducttypename() {
		return producttypename;
	}

	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}
}
