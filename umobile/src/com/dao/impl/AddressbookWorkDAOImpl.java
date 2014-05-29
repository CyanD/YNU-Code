package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.AddressbookWorkDAO;
import com.model.addressbook.work.AddressbookWorkGrid;
import com.model.addressbook.work.AddressbookWorkLazy;
import com.model.sys.SysUserView;
@Component("addressbookWorkDAO")
public class AddressbookWorkDAOImpl extends TopDAO implements AddressbookWorkDAO {

	@Override
	public List<AddressbookWorkGrid> findAddressbookWorkGrids(int i,
			int rows, String sort, String order) {
		return this.getSession().createQuery("from AddressbookWorkGrid order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();

	}

	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from AddressbookWorkGrid").uniqueResult();
	}

	@Override
	public void saveAddressbookWorkGrid(AddressbookWorkGrid addressbookWorkGrid) {
		this.getHibernateTemplate().save(addressbookWorkGrid);
	}

	@Override
	public AddressbookWorkLazy findAddressbookWorkLazyById(Long id) {
		return (AddressbookWorkLazy) this.getSession().get(AddressbookWorkLazy.class, id);
	}

	@Override
	public void deleteAddressbookWorkLazy(AddressbookWorkLazy addressbookWorkLazy) {
		this.getHibernateTemplate().delete(addressbookWorkLazy);
	}

	@Override
	public AddressbookWorkGrid findAddressbookWorkGridById(Long id) {
		return (AddressbookWorkGrid) this.getSession().get(AddressbookWorkGrid.class, id);
	}

	@Override
	public void mergeAddressbookWorkLazy(AddressbookWorkLazy addressbookWorkLazy) {
		this.getHibernateTemplate().merge(addressbookWorkLazy);
	}

	@Override
	public void mergeAddressbookWorkGrid(AddressbookWorkGrid addressbookWorkNew) {
		this.getHibernateTemplate().merge(addressbookWorkNew);
	}

	@Override
	public void saveAddressbookWorkLazy(AddressbookWorkLazy addressbookWorkLazy) {
		this.getHibernateTemplate().save(addressbookWorkLazy);
	}

	@Override
	public void updateAddressbookWorkLazyJobNumberByName(String name,
			String jobNumber) {
		this.getSession().createQuery("update AddressbookWorkLazy set jobNumber = '"+jobNumber+"' where name = '"+name+"'").executeUpdate();
		
	}
	@Override
	public List<?> findListByPage(int i, int rows, String sort, String order,
			String queryString) {
		return this.getSession().createQuery("from " + queryString +" order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public long findListTotal(String queryString) {
		return (Long)this.getSession().createQuery("select count(*) from "+queryString).uniqueResult();
	}
}