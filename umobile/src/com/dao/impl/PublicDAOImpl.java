package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.dao.PublicDAO;
@Component("publicDAO")
public class PublicDAOImpl extends TopDAO implements PublicDAO{
	@Override
	public List<?> findListByPage(int i, int rows, String sort, String order,
			String queryString) {
		return this.getSession().createQuery("from " + queryString +" order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public List<?> findList(String queryString) {
		return this.getSession().createQuery("from " + queryString).list();
	}
	@Override
	public long findListTotal(String queryString) {
		return (Long)this.getSession().createQuery("select count(*) from "+queryString).uniqueResult();
	}

	@Override
	public Object findByProperty(String object,String propertyName, Object value) {
		Query queryObject =  this.getSession().createQuery("from " + object + " where "+ propertyName + "= ? ");
		queryObject.setParameter(0, value);
		return queryObject.uniqueResult();
	}
	@Override
	public Object findObjectById(Long id,String object) {
		return this.getSession().createQuery("from " + object + " where id = "+id).uniqueResult();
	}

	@Override
	public void deleteObject(Object object) {
		this.getHibernateTemplate().delete(object);
	}
	@Override
	public void updateObject(Object object) {
		this.getHibernateTemplate().update(object);
	}

	@Override
	public void mergeObject(Object object) {
		this.getHibernateTemplate().merge(object);
	}

	@Override
	public void update(String hql) {
		this.getSession().createQuery(" update " + hql).executeUpdate();
	}
	@Override
	public Object findAnyObject(String queryString) {
		return this.getSession().createQuery(queryString).uniqueResult();
	}
	@Override
	public Object findObject(String queryString) {
		return this.getSession().createQuery("from " + queryString).uniqueResult();
	}
	@Override
	public Object findObjectByIid(Long id, String object) {
		return this.getSession().createQuery("from " + object + " where iid = "+id).uniqueResult();
	}

	@Override
	public void saveObject(Object object) {
		this.getHibernateTemplate().save(object);
	}

	@Override
	public List<?> findCagegoryAndCount(String string) {
			return getHibernateTemplate().find(string);
	}
	@Override
	public void executeUpdateBySQL(String sqlString){
		this.getSession().createSQLQuery(sqlString).executeUpdate();
	}
}