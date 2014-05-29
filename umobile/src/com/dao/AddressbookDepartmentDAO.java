package com.dao;

import java.util.List;

import com.model.addressbook.work.AddressbookDepartmentTree;
import com.model.addressbook.work.AddressbookDepartmentTreeGrid;

public interface AddressbookDepartmentDAO {

	List<AddressbookDepartmentTreeGrid> findAddressbookDepartmentTreeGrid();

	void saveAddressbookDepartmentTreeGrid(
			AddressbookDepartmentTreeGrid addressbookDepartment);

	AddressbookDepartmentTreeGrid findAddressbookDepartmentTreeGridById(Long id);

	void deleteAddressbookDepartmentTreeGrid(
			AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid);

	void mergeAddressbookDepartmentTreeGrid(
			AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid);

	void updateAddressbookDepartmentTreeGrid(
			AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid);

	List<AddressbookDepartmentTree> findAddressbookDepartmentTree();

	AddressbookDepartmentTree findAddressbookDepartmentTreeById(Long id);

	Long findchildrenTotalByPid(Long pid);


}