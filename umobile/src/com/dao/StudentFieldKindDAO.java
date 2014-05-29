package com.dao;

import java.util.List;

import com.model.publicservice.StudentFieldKind;
import com.model.publicservice.StudentFieldKindTree;

public interface StudentFieldKindDAO {
	List<StudentFieldKind> findAllStudentFieldKind();
	List<StudentFieldKindTree> findAllStudentFieldKindTree();
	
	long findTotal();

	void saveStudentFieldKind(StudentFieldKind activity);

	StudentFieldKind findStudentFieldKindById(Long id);

	void deleteStudentFieldKind(StudentFieldKind activity);

	void mergeStudentFieldKind(StudentFieldKind activity);

	void updateStudentFieldKind(StudentFieldKind activity);
}