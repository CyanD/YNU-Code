package com.vo.bean;

import com.model.addressbook.work.AddressbookDepartmentTreeGrid;
import com.ynu.zjx.PageBean;



public class AddressbookDepartmentVO extends PageBean{
	private Long id;
    private Long _parentId;
    private String name;
	private String queryString;
	private Integer orders;
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	private String remark;
	public AddressbookDepartmentTreeGrid getAddressbookDepartmentTreeGrid(){
		return new AddressbookDepartmentTreeGrid(id, _parentId, name, remark,orders);
	}
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}