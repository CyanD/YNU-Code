package com.model.addressbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AddressbookPublic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "addressbook_public", catalog = "umobile")
public class AddressbookPublicTreeGrid implements java.io.Serializable {

	public AddressbookPublicTreeGrid(Long id, Long _parentId, String name,
			String tel, String address,Integer orders) {
		super();
		this.id = id;
		if(_parentId==0l)_parentId=null;
		this._parentId = _parentId;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.orders=orders;
	}

	// Fields

	private Long id;
	private Long _parentId;
	private String name;
	private String tel;
	private Integer orders;
	@Column(name = "orders", nullable = false)
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	private String address;
	private String createTime;
	private String publisher;
	
	public AddressbookPublicTreeGrid() {
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}
	@Column(name = "pid")
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		if(_parentId==null)_parentId=0l;
		this._parentId = _parentId;
	}
	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}
	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "tel", length = 30)
	public String getTel() {
		return this.tel;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}