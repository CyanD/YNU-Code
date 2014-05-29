package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.AddressbookDepartmentDAO;
import com.model.addressbook.work.AddressbookDepartmentTree;
import com.model.addressbook.work.AddressbookDepartmentTreeGrid;

/**
 * A data access object (DAO) providing persistence and search support for
 * AddressbookDepartment entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.AddressbookDepartmentTreeGrid
 * @author MyEclipse Persistence Tools
 */
@Component("addressbookDepartmentDAO")
public class AddressbookDepartmentDAOImpl extends TopDAO implements AddressbookDepartmentDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<AddressbookDepartmentTreeGrid> findAddressbookDepartmentTreeGrid() {
		return this.getSession().createQuery("from AddressbookDepartmentTreeGrid ").list();
	}

	@Override
	public void saveAddressbookDepartmentTreeGrid(
			AddressbookDepartmentTreeGrid addressbookDepartment) {
		this.getHibernateTemplate().save(addressbookDepartment);
	}

	@Override
	public AddressbookDepartmentTreeGrid findAddressbookDepartmentTreeGridById(Long id) {
		return (AddressbookDepartmentTreeGrid) this.getSession().get(AddressbookDepartmentTreeGrid.class, id);
	}

	@Override
	public void deleteAddressbookDepartmentTreeGrid(
			AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().delete(addressbookDepartmentTreeGrid);
	}

	@Override
	public void mergeAddressbookDepartmentTreeGrid(
			AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().merge(addressbookDepartmentTreeGrid);
		
	}

	@Override
	public void updateAddressbookDepartmentTreeGrid(
			AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid) {
		this.getHibernateTemplate().update(addressbookDepartmentTreeGrid);
	}

	@Override
	public List<AddressbookDepartmentTree> findAddressbookDepartmentTree() {
		return this.getSession().createQuery("from AddressbookDepartmentTree").list();
	}

	@Override
	public AddressbookDepartmentTree findAddressbookDepartmentTreeById(Long id) {
		return (AddressbookDepartmentTree) this.getSession().get(AddressbookDepartmentTree.class, id);
	}

	@Override
	public Long findchildrenTotalByPid(Long pid) {
		return (Long)getSession().createQuery("select count(*) from AddressbookDepartmentTreeGrid where pid = "+pid).uniqueResult();
	}
}