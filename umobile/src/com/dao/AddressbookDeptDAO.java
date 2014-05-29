package com.dao;

import java.util.List;

import com.model.addressbook.work.AddressbookDeptTree;
import com.model.addressbook.work.AddressbookDeptTreeGrid;


public interface AddressbookDeptDAO {

	public abstract List<AddressbookDeptTreeGrid> findAddressbookDeptTreeGrid(String queryString);

	public abstract void saveAddressbookDeptTreeGrid(
			AddressbookDeptTreeGrid addressbookDept);

	public abstract AddressbookDeptTreeGrid findAddressbookDeptTreeGridById(
			Long id);

	public abstract void deleteAddressbookDeptTreeGrid(
			AddressbookDeptTreeGrid addressbookDeptTreeGrid);

	public abstract void mergeAddressbookDeptTreeGrid(
			AddressbookDeptTreeGrid addressbookDeptTreeGrid);

	public abstract void updateAddressbookDeptTreeGrid(
			AddressbookDeptTreeGrid addressbookDeptTreeGrid);

	public abstract List<AddressbookDeptTree> findAddressbookDeptTree();

	public abstract com.model.addressbook.work.AddressbookDeptTree findAddressbookDeptTreeById(
			Long id);


}