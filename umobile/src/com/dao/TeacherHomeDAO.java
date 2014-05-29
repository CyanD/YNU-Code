package com.dao;

import java.util.List;

import com.model.publicservice.TeacherHomeKindTree;
import com.model.publicservice.TeacherHome;
import com.model.publicservice.TeacherHomeLazy;

public interface TeacherHomeDAO {
	List<TeacherHome> findAllTeacherHome(int i, int rows, String sort, String order);

	long findTotal();

	void saveTeacherHome(TeacherHomeLazy activity);

	TeacherHome findTeacherHomeById(Long id);

	void deleteTeacherHome(TeacherHome activity);

	void mergeTeacherHome(TeacherHomeLazy activity);
	
	List<TeacherHomeLazy> searchAllTeacherHome(int i, int rows, String sort, String order,Long key);
	
	long searchTotal(Long key);
	
	TeacherHomeKindTree findKindTree(Long id);
	
	TeacherHomeLazy findTeacherHomeLazyById(Long id);
	
}