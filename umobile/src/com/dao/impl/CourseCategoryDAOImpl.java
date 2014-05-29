package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.CourseCategoryDAO;
import com.model.course.CourseCategoryTree;
import com.model.course.CourseCategoryTreeGrid;

@Component("courseCategoryDAO")
public class CourseCategoryDAOImpl extends TopDAO implements CourseCategoryDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseCategoryTreeGrid> findCourseCategoryTreeGrid() {
		return this.getSession().createQuery("from CourseCategoryTreeGrid ").list();
	}

	@Override
	public void saveCourseCategoryTreeGrid(
			CourseCategoryTreeGrid addressbookDepartment) {
		this.getHibernateTemplate().save(addressbookDepartment);
	}

	@Override
	public CourseCategoryTreeGrid findCourseCategoryTreeGridById(Long id) {
		return (CourseCategoryTreeGrid) this.getSession().get(CourseCategoryTreeGrid.class, id);
	}

	@Override
	public void deleteCourseCategoryTreeGrid(
			CourseCategoryTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().delete(addressbookDepartmentTreeGrid);
	}

	@Override
	public void mergeCourseCategoryTreeGrid(
			CourseCategoryTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().merge(addressbookDepartmentTreeGrid);
		
	}

	@Override
	public void updateCourseCategoryTreeGrid(
			CourseCategoryTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().update(addressbookDepartmentTreeGrid);
	}

	@Override
	public List<CourseCategoryTree> findCourseCategoryTree() {
		return this.getSession().createQuery("from CourseCategoryTree").list();
	}

	@Override
	public CourseCategoryTree findCourseCategoryTreeById(Long id) {
		return (CourseCategoryTree) this.getSession().get(CourseCategoryTree.class, id);
	}

	@Override
	public Long findchildrenTotalByPid(Long pid) {
		return (Long)getSession().createQuery("select count(*) from CourseCategoryTreeGrid where pid = "+pid).uniqueResult();
	}
}