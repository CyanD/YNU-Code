package com.service;

import com.model.publicservice.StudentField;
import com.vo.bean.StudentFieldVO;

public interface StudentFieldService {
	public abstract String findAllStudentField(StudentFieldVO vo);

	String saveStudentField(StudentFieldVO vo);

	String deleteStudentField(StudentFieldVO vo);

	String updateStudentField(StudentFieldVO vo);
	
	StudentField findStudentFieldById(StudentFieldVO vo);
	
	String searchStudentField(StudentFieldVO vo);
	
	String updateEditKind(StudentFieldVO vo);
}