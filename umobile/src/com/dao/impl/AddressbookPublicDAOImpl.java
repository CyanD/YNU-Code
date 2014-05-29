package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.AddressbookPublicDAO;
import com.model.addressbook.AddressbookPublicTree;
import com.model.addressbook.AddressbookPublicTreeGrid;
@Component("addressbookPublicDAO")
public class AddressbookPublicDAOImpl extends TopDAO implements AddressbookPublicDAO  {
    @Override
    public long findLazyTotal() {
    	return (Long)this.getSession().createQuery("select count(*) from AddressbookPublicTreeGrid").uniqueResult();
    }

	@Override
	public List<AddressbookPublicTreeGrid> findAllLazy() {
		
		return this.getSession().createQuery("from AddressbookPublicTreeGrid").list();
	}

	@Override
	public List<AddressbookPublicTreeGrid> findLazyByPid(Long pid) {
		return this.getSession().createQuery("from AddressbookPublicTreeGrid aplg where aplg.pid = "+pid).list();
	}

	@Override
	public List<AddressbookPublicTree> findAllTree() {
		return this.getSession().createQuery("from AddressbookPublicTree").list();
	}

	@Override
	public List<AddressbookPublicTree> findTreeRootByPid(Long pid) {
		return this.getSession().createQuery("from AddressbookPublicTree apt where apt.pid = "+pid).list();

	}

	@Override
	public void saveLazy(AddressbookPublicTreeGrid modelLazy) {
		this.getHibernateTemplate().save(modelLazy);
	}

	@Override
	public void deleteById(Long id) {
		this.getSession().createQuery("delete from AddressbookPublicTreeGrid apls where apls.id = "+id).executeUpdate();
	}

	@Override
	public void updateLazy(AddressbookPublicTreeGrid modelLazy) {
		this.getHibernateTemplate().update(modelLazy);
	}

	@Override
	public AddressbookPublicTreeGrid findAddressbookPublicTreeGridByName(
			String name) {
		return (AddressbookPublicTreeGrid) this.getSession().createQuery("from AddressbookPublicTreeGrid where name = '"+name+"'").uniqueResult();
	}

}