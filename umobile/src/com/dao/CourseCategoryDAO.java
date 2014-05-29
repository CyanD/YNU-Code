package com.dao;

import java.util.List;

import com.model.course.CourseCategoryTree;
import com.model.course.CourseCategoryTreeGrid;

public interface CourseCategoryDAO {

	List<CourseCategoryTreeGrid> findCourseCategoryTreeGrid();

	void saveCourseCategoryTreeGrid(
			CourseCategoryTreeGrid addressbookDepartment);

	CourseCategoryTreeGrid findCourseCategoryTreeGridById(Long id);

	void deleteCourseCategoryTreeGrid(
			CourseCategoryTreeGrid addressbookDepartmentTreeGrid);

	void mergeCourseCategoryTreeGrid(
			CourseCategoryTreeGrid addressbookDepartmentTreeGrid);

	void updateCourseCategoryTreeGrid(
			CourseCategoryTreeGrid addressbookDepartmentTreeGrid);

	List<CourseCategoryTree> findCourseCategoryTree();

	CourseCategoryTree findCourseCategoryTreeById(Long id);

	Long findchildrenTotalByPid(Long pid);

}