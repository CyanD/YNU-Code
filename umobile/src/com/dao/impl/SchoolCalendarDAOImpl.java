package com.dao.impl;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.SchoolCalendarDAO;
import com.model.calendar.SchoolCalendar;
import com.model.calendar.SchoolCalendarLazy;
import com.model.calendar.SchoolCalendarSubLazy;



/**
 * A data access object (DAO) providing persistence and search support for
 * BusRoute entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.SchoolCalendarAction.model.bus.BusRoute
 * @author MyEclipse Persistence Tools
 */
@Component("schoolCalendarDAO")
public class SchoolCalendarDAOImpl extends TopDAO implements SchoolCalendarDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SchoolCalendarDAOImpl.class);
	// property constants
	public static final String ACTTIME = "acttime";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#save(com.model.BusRoute)
	 */
	@Override
	public void save(SchoolCalendar transientInstance) {
		log.debug("saving SchoolCalender instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#delete(com.model.BusRoute)
	 */
	@Override
	public void delete(SchoolCalendar persistentInstance) {
		log.debug("deleting SchoolCalender instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findById(java.lang.Long)
	 */
	@Override
	public SchoolCalendar findById(java.lang.Long id) {
		log.debug("getting SchoolCalender instance with id: " + id);
		try {
			SchoolCalendar instance = (SchoolCalendar) getSession().get(
					"com.dao.impl.SchoolCalender", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findByExample(com.model.BusRoute)
	 */
	@Override
	public List<SchoolCalendar> findByExample(SchoolCalendar instance) {
		log.debug("finding SchoolCalender instance by example");
		try {
			List<SchoolCalendar> results = (List<SchoolCalendar>) getSession()
					.createCriteria("com.dao.impl.SchoolCalender")
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
	 * @see com.dao.impl.BusRouteDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SchoolCalender instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SchoolCalender as model where model."
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
	 * @see com.dao.impl.BusRouteDAO#findByName(java.lang.Object)
	 */
	@Override
	public List<SchoolCalendar> findByActtime(Object acttime) {
		return findByProperty(ACTTIME, acttime);
	}


	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<SchoolCalendar> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all SchoolCalendar instances");
		try {
			String queryString = "from SchoolCalendar";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#merge(com.model.BusRoute)
	 */
	@Override
	public SchoolCalendar merge(SchoolCalendar detachedInstance) {
		log.debug("merging SchoolCalendar instance");
		try {
			SchoolCalendar result = (SchoolCalendar) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#attachDirty(com.model.BusRoute)
	 */
	@Override
	public void attachDirty(SchoolCalendar instance) {
		log.debug("attaching dirty SchoolCalendar instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#attachClean(com.model.BusRoute)
	 */
	@Override
	public void attachClean(SchoolCalendar instance) {
		log.debug("attaching clean SchoolCalendar instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from SchoolCalendarLazy ").uniqueResult();
	}

	@Override
	public List<?> findByPage(long cid,int i, int rows, String sort, String order) {
		return (List<?>)this.getSession().createQuery("from SchoolCalendarLazy sl where sl.cid = " + cid + "order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public void deleteByIds(String ids) {
		this.getSession().createQuery("delete from SchoolCalendarLazy br where br.id in ("+ids+")").executeUpdate();
	}
	@Override
	public void update(SchoolCalendar schoolCalender) {
		
		this.getSession().update(schoolCalender);
	}	
	
	
	@Override
	public long findTotal(long cid) {
		
		return (Long)this.getSession().createQuery("select count(*) from SchoolCalendarLazy bs where bs.cid = "+cid).uniqueResult();
	}

	
	@Override
	public void saveLazy(SchoolCalendarLazy schoolCalendarLazy) {
		this.getHibernateTemplate().save(schoolCalendarLazy);
	}
	
	@Override
	public void updateLazy(SchoolCalendarLazy schoolCalendarLazy) {
		this.getHibernateTemplate().update(schoolCalendarLazy);
	}
	
}