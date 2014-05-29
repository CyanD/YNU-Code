package com.dao;

import java.util.List;

import com.model.addressbook.alumni.AddressbookAlumni;


public interface AddressbookAlumniDAO {

	List<AddressbookAlumni> findAddressbookAlumnis(int i, int rows,
			String sort, String order);

	long findTotal();

	void saveAddressbookAlumni(AddressbookAlumni addressbookAlumni);

	AddressbookAlumni findAddressbookAlumniById(Long id);

	void deleteAddressbookAlumni(AddressbookAlumni addressbookAlumni);

	void mergeAddressbookAlumni(AddressbookAlumni addressbookAlumni);

}