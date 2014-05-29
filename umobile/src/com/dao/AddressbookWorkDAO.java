package com.dao;

import java.util.List;

import com.model.addressbook.work.AddressbookWorkGrid;
import com.model.addressbook.work.AddressbookWorkLazy;

public interface AddressbookWorkDAO {

	List<AddressbookWorkGrid> findAddressbookWorkGrids(int i, int rows,
			String sort, String order);

	long findTotal();

	void saveAddressbookWorkGrid(AddressbookWorkGrid addressbookWorkGrid);

	AddressbookWorkLazy findAddressbookWorkLazyById(Long id);

	void deleteAddressbookWorkLazy(AddressbookWorkLazy addressbookWorkLazy);

	com.model.addressbook.work.AddressbookWorkGrid findAddressbookWorkGridById(
			Long id);

	void mergeAddressbookWorkLazy(AddressbookWorkLazy addressbookWorkLazy);

	void mergeAddressbookWorkGrid(AddressbookWorkGrid addressbookWorkNew);

	void saveAddressbookWorkLazy(AddressbookWorkLazy addressbookWorkLazy);

	void updateAddressbookWorkLazyJobNumberByName(String name, String jobNumber);

	List<?> findListByPage(int i, int rows, String sort, String order,
			String queryString);

	long findListTotal(String queryString);

}