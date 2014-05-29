package com.service;

import com.model.publicservice.StudentFieldKind;
import com.vo.bean.StudentFieldKindVO;

public interface StudentFieldKindService {
	public abstract String findAllStudentFieldKind(StudentFieldKindVO vo);

	String saveStudentFieldKind(StudentFieldKindVO vo);

	String deleteStudentFieldKind(StudentFieldKindVO vo);

	String updateStudentFieldKind(StudentFieldKindVO vo);
	
	StudentFieldKind findStudentFieldKindById(StudentFieldKindVO vo);

}
