package com.service;

import com.vo.bean.AddressbookAlumniVO;
import com.vo.bean.AddressbookDepartmentVO;
import com.vo.bean.AddressbookDeptVO;
import com.vo.bean.AddressbookPublicVO;
import com.vo.bean.AddressbookWorkVO;

public interface AddressbookService {

	String findAddressbookPublicLazys(AddressbookPublicVO vo);

	String findAddressbookPublicLazy(AddressbookPublicVO vo);

	String findAddressbookPublicTree(AddressbookPublicVO vo);

	String saveAddressbookPublicLazy(AddressbookPublicVO vo);

	String deleteAddressbookPublicLazyById(AddressbookPublicVO vo);

	String updateAddressbookPublicLazy(AddressbookPublicVO vo);

	String findAddressbookDeptTreeGrid(AddressbookDeptVO vo);

	String saveAddressbookDeptTreeGrid(AddressbookDeptVO vo);

	String deleteAddressbookDeptTreeGrid(AddressbookDeptVO vo);

	String updateAddressbookDeptTreeGrid(AddressbookDeptVO vo);
	String findAddressbookWorkGrids(AddressbookWorkVO vo);

	String saveAddressbookWorkLazy(AddressbookWorkVO vo);

	String deleteAddressbookWorkLazy(AddressbookWorkVO vo);

	String updateAddressbookWorkLazy(AddressbookWorkVO vo);

	String findAddressbookDepartmentTreeGrid(AddressbookDepartmentVO vo);

	String saveAddressbookDepartmentTreeGrid(AddressbookDepartmentVO vo);

	String deleteAddressbookDepartmentTreeGrid(AddressbookDepartmentVO vo);

	String updateAddressbookDepartmentTreeGrid(AddressbookDepartmentVO vo);

	String findAddressbookAlumnis(AddressbookAlumniVO vo);

	String saveAddressbookAlumni(AddressbookAlumniVO vo);

	String deleteAddressbookAlumni(AddressbookAlumniVO vo);

	String updateAddressbookAlumni(AddressbookAlumniVO vo);

}