package com.dao.impl;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.SchoolCalendarSubDAO;
import com.dao.SchoolCalendarSubDAO;
import com.dao.impl.TopDAO;

import com.model.calendar.SchoolCalendarSub;
import com.model.calendar.SchoolCalendarSubLazy;

/**
 * A data access object (DAO) providing persistence and search support for
 * SchoolCalendarSub entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.SchoolCalendarSub.model.bus.SchoolCalendarSub
 * @author MyEclipse Persistence Tools
 */
@Component("schoolCalendarSubDAO")
public class SchoolCalendarSubDAOImpl extends TopDAO implements SchoolCalendarSubDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SchoolCalendarSubDAOImpl.class);
	// property constants
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#save(com.model.SchoolCalendarSub)
	 */
	@Override
	public void save(SchoolCalendarSub transientInstance) {
		log.debug("saving  SchoolCalendarSub instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#delete(com.model.SchoolCalendarSub)
	 */
	@Override
	public void delete(SchoolCalendarSub persistentInstance) {
		log.debug("deleting SchoolCalendarSub instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#findById(java.lang.Long)
	 */
	@Override
	public SchoolCalendarSub findById(java.lang.Long id) {
		log.debug("getting SchoolCalendarSub instance with id: " + id);
		try {
			SchoolCalendarSub instance = (SchoolCalendarSub) getSession().get(
					"com.dao.impl.SchoolCalendarSub", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#findByExample(com.model.SchoolCalendarSub)
	 */
	@Override
	public List<SchoolCalendarSub> findByExample(SchoolCalendarSub instance) {
		log.debug("finding SchoolCalendarSub instance by example");
		try {
			List<SchoolCalendarSub> results = (List<SchoolCalendarSub>) getSession()
					.createCriteria("com.dao.impl.SchoolCalendarSub")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SchoolCalendarSub instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SchoolCalendarSub as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<SchoolCalendarSub> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all SchoolCalendarSub instances");
		try {
			String queryString = "from SchoolCalendarSub";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#merge(com.model.SchoolCalendarSub)
	 */
	@Override
	public SchoolCalendarSub merge(SchoolCalendarSub detachedInstance) {
		log.debug("merging SchoolCalendarSub instance");
		try {
			SchoolCalendarSub result = (SchoolCalendarSub) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#attachDirty(com.model.SchoolCalendarSub)
	 */
	@Override
	public void attachDirty(SchoolCalendarSub instance) {
		log.debug("attaching dirty SchoolCalendarSub instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SchoolCalendarSubDAO#attachClean(com.model.SchoolCalendarSub)
	 */
	@Override
	public void attachClean(SchoolCalendarSub instance) {
		log.debug("attaching clean SchoolCalendarSub instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<?> findByPage(long pid, int i, int rows, String sort,
			String order) {
		
		return (List<?>)this.getSession().createQuery("from SchoolCalendarSubLazy bs where bs.pid = " + pid + " order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public long findTotal(long pid) {
		
		return (Long)this.getSession().createQuery("select count(*) from SchoolCalendarSubLazy bs where bs.pid = "+pid).uniqueResult();
	}

	@Override
	public void saveLazy(SchoolCalendarSubLazy schoolCalendarSubLazy) {
		this.getHibernateTemplate().save(schoolCalendarSubLazy);
	}

	@Override
	public void deleteByIds(String ids) {
		this.getSession().createQuery("delete from SchoolCalendarSubLazy bsl where bsl.id in ("+ids+")").executeUpdate();
	}

	@Override
	public void updateLazy(SchoolCalendarSubLazy schoolCalendarSubLazy) {
		this.getHibernateTemplate().update(schoolCalendarSubLazy);
	}
}