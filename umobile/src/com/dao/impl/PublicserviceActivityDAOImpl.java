package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.PublicserviceActivityDAO;
import com.model.addressbook.alumni.AddressbookAlumni;
import com.model.publicservice.PublicserviceActivity;

@Component("publicserviceActivityDAO")
public class PublicserviceActivityDAOImpl extends TopDAO implements PublicserviceActivityDAO{
	
	@Override
	public List<PublicserviceActivity> findAllActivity (int i, int rows, String sort, String order){
		return this.getSession().createQuery("from PublicserviceActivity order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();

	}

	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from PublicserviceActivity").uniqueResult();
	}

	@Override
	public void saveActivity(PublicserviceActivity activity) {
		this.getHibernateTemplate().save(activity);
	}

	@Override
	public PublicserviceActivity findActivityById(Long id) {
		return (PublicserviceActivity) this.getSession().get(PublicserviceActivity.class, id);
	}

	@Override
	public void deleteActivity(PublicserviceActivity activity) {
		this.getHibernateTemplate().delete(activity);
	}


	@Override
	public void mergeActivity(PublicserviceActivity activity) {
		this.getHibernateTemplate().merge(activity);
	}

}
