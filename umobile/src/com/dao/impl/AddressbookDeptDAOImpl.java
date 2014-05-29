package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.AddressbookDeptDAO;
import com.model.addressbook.work.AddressbookDeptTree;
import com.model.addressbook.work.AddressbookDeptTreeGrid;

/**
 	* A data access object (DAO) providing persistence and search support for AddressbookDept entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.model.addressbook.work.AddressbookDeptTreeGrid
  * @author MyEclipse Persistence Tools 
 */
@Component("addressbookDeptDAO")
public class AddressbookDeptDAOImpl extends TopDAO implements AddressbookDeptDAO  {

		@SuppressWarnings("unchecked")
		@Override
		public List<AddressbookDeptTreeGrid> findAddressbookDeptTreeGrid(String queryString) {
			return this.getSession().createQuery("from AddressbookDeptTreeGrid " +queryString).list();
		}

		@Override
		public void saveAddressbookDeptTreeGrid(
				AddressbookDeptTreeGrid addressbookDept) {
			this.getHibernateTemplate().save(addressbookDept);
		}

		@Override
		public AddressbookDeptTreeGrid findAddressbookDeptTreeGridById(Long id) {
			return (AddressbookDeptTreeGrid) this.getSession().get(AddressbookDeptTreeGrid.class, id);
		}

		@Override
		public void deleteAddressbookDeptTreeGrid(
				AddressbookDeptTreeGrid addressbookDeptTreeGrid) {
			this.getHibernateTemplate().delete(addressbookDeptTreeGrid);
		}

		@Override
		public void mergeAddressbookDeptTreeGrid(
				AddressbookDeptTreeGrid addressbookDeptTreeGrid) {
			this.getHibernateTemplate().merge(addressbookDeptTreeGrid);
			
		}

		@Override
		public void updateAddressbookDeptTreeGrid(
				AddressbookDeptTreeGrid addressbookDeptTreeGrid) {
			this.getHibernateTemplate().update(addressbookDeptTreeGrid);
		}

		@Override
		public List<AddressbookDeptTree> findAddressbookDeptTree() {
			return this.getSession().createQuery("from AddressbookDeptTree").list();
		}

		@Override
		public AddressbookDeptTree findAddressbookDeptTreeById(Long id) {
			return (AddressbookDeptTree) this.getSession().get(AddressbookDeptTree.class, id);
		}
}