package com.dao;

import java.util.List;

import com.model.publicservice.StudentFieldKindTree;
import com.model.publicservice.StudentField;
import com.model.publicservice.StudentFieldLazy;

public interface StudentFieldDAO {
	List<StudentField> findAllStudentField(int i, int rows, String sort, String order);

	long findTotal();

	void saveStudentField(StudentFieldLazy activity);

	StudentField findStudentFieldById(Long id);

	void deleteStudentField(StudentField activity);

	void mergeStudentField(StudentFieldLazy activity);
	
	List<StudentFieldLazy> searchAllStudentField(int i, int rows, String sort, String order,Long key);
	
	long searchTotal(Long key);
	
	StudentFieldKindTree findKindTree(Long id);
	
	StudentFieldLazy findStudentFieldLazyById(Long id);

}
