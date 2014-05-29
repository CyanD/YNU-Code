package com.vo.bean;

import com.model.addressbook.AddressbookPublicTreeGrid;
import com.ynu.zjx.PageBean;

public class AddressbookPublicVO extends PageBean{
	 private Long id;
     private Long _parentId;
     private String name;
     private String tel;
     private String address;
     private Integer orders;
 	public Integer getOrders() {
 		return this.orders;
 	}

 	public void setOrders(Integer orders) {
 		this.orders = orders;
 	}
     public AddressbookPublicTreeGrid getAddressbookPublicTreeGrid(){
    	 return new AddressbookPublicTreeGrid(id, _parentId, name, tel, address,orders);
     }
     public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String createTime;
     private String publisher;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
}
