package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.NoticeCategoryDAO;
import com.model.notice.NoticeCategoryTree;
import com.model.notice.NoticeCategoryTreeGrid;

@Component("noticeCategoryDAO")
public class NoticeCategoryDAOImpl extends TopDAO implements NoticeCategoryDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<NoticeCategoryTreeGrid> findNoticeCategoryTreeGrid() {
		return this.getSession().createQuery("from NoticeCategoryTreeGrid ").list();
	}

	@Override
	public void saveNoticeCategoryTreeGrid(
			NoticeCategoryTreeGrid addressbookDepartment) {
		this.getHibernateTemplate().save(addressbookDepartment);
	}

	@Override
	public NoticeCategoryTreeGrid findNoticeCategoryTreeGridById(Long id) {
		return (NoticeCategoryTreeGrid) this.getSession().get(NoticeCategoryTreeGrid.class, id);
	}

	@Override
	public void deleteNoticeCategoryTreeGrid(
			NoticeCategoryTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().delete(addressbookDepartmentTreeGrid);
	}

	@Override
	public void mergeNoticeCategoryTreeGrid(
			NoticeCategoryTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().merge(addressbookDepartmentTreeGrid);
		
	}

	@Override
	public void updateNoticeCategoryTreeGrid(
			NoticeCategoryTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().update(addressbookDepartmentTreeGrid);
	}

	@Override
	public List<NoticeCategoryTree> findNoticeCategoryTree() {
		return this.getSession().createQuery("from NoticeCategoryTree").list();
	}

	@Override
	public NoticeCategoryTree findNoticeCategoryTreeById(Long id) {
		return (NoticeCategoryTree) this.getSession().get(NoticeCategoryTree.class, id);
	}

	@Override
	public Long findchildrenTotalByPid(Long pid) {
		return (Long)getSession().createQuery("select count(*) from NoticeCategoryTreeGrid where pid = "+pid).uniqueResult();
	}
}