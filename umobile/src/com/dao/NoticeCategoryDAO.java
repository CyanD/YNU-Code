package com.dao;

import java.util.List;

import com.model.notice.NoticeCategoryTree;
import com.model.notice.NoticeCategoryTreeGrid;

public interface NoticeCategoryDAO {

	List<NoticeCategoryTreeGrid> findNoticeCategoryTreeGrid();

	void saveNoticeCategoryTreeGrid(
			NoticeCategoryTreeGrid addressbookDepartment);

	NoticeCategoryTreeGrid findNoticeCategoryTreeGridById(Long id);

	void deleteNoticeCategoryTreeGrid(
			NoticeCategoryTreeGrid addressbookDepartmentTreeGrid);

	void mergeNoticeCategoryTreeGrid(
			NoticeCategoryTreeGrid addressbookDepartmentTreeGrid);

	void updateNoticeCategoryTreeGrid(
			NoticeCategoryTreeGrid addressbookDepartmentTreeGrid);

	List<NoticeCategoryTree> findNoticeCategoryTree();

	NoticeCategoryTree findNoticeCategoryTreeById(Long id);

	Long findchildrenTotalByPid(Long pid);

}