package com.model.anniversary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AnniversaryDonation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "anniversary_donation", catalog = "umobile")
public class AnniversaryDonation implements java.io.Serializable {

	// Fields

	public AnniversaryDonation(Long id, String name, String money,
			String location, String donateTime) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
		this.location = location;
		this.donateTime = donateTime;
	}

	private Long id;
	private String name;
	private String createTime;
	private String publisher;
	private String money;
	private String location;
	private String donateTime;

	// Constructors

	/** default constructor */
	public AnniversaryDonation() {
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

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "money", nullable = false, length = 50)
	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name = "location", length = 50)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "donateTime", length = 10)
	public String getDonateTime() {
		return this.donateTime;
	}

	public void setDonateTime(String donateTime) {
		this.donateTime = donateTime;
	}

}