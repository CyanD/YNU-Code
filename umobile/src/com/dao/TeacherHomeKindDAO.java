package com.dao;

import java.util.List;

import com.model.publicservice.TeacherHomeKind;
import com.model.publicservice.TeacherHomeKindTree;

public interface TeacherHomeKindDAO {
	List<TeacherHomeKind> findAllTeacherHomeKind();
	List<TeacherHomeKindTree> findAllTeacherHomeKindTree();
	
	long findTotal();

	void saveTeacherHomeKind(TeacherHomeKind activity);

	TeacherHomeKind findTeacherHomeKindById(Long id);

	void deleteTeacherHomeKind(TeacherHomeKind activity);

	void mergeTeacherHomeKind(TeacherHomeKind activity);

	void updateTeacherHomeKind(TeacherHomeKind activity);
}