package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.AddressbookAlumniDAO;
import com.model.addressbook.alumni.AddressbookAlumni;

/**
 * A data access object (DAO) providing persistence and search support for
 * AddressbookAlumni entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.AddressbookAlumni
 * @author MyEclipse Persistence Tools
 */
@Component("addressbookAlumniDAO")
public class AddressbookAlumniDAOImpl extends TopDAO implements AddressbookAlumniDAO {
	@Override
	public List<AddressbookAlumni> findAddressbookAlumnis(int i,
			int rows, String sort, String order) {
		return this.getSession().createQuery("from AddressbookAlumni order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();

	}

	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from AddressbookAlumni").uniqueResult();
	}

	@Override
	public void saveAddressbookAlumni(AddressbookAlumni addressbookAlumni) {
		this.getHibernateTemplate().save(addressbookAlumni);
	}

	@Override
	public AddressbookAlumni findAddressbookAlumniById(Long id) {
		return (AddressbookAlumni) this.getSession().get(AddressbookAlumni.class, id);
	}

	@Override
	public void deleteAddressbookAlumni(AddressbookAlumni addressbookAlumni) {
		this.getHibernateTemplate().delete(addressbookAlumni);
	}


	@Override
	public void mergeAddressbookAlumni(AddressbookAlumni addressbookAlumniNew) {
		this.getHibernateTemplate().merge(addressbookAlumniNew);
	}

}