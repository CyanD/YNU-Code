package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.CalendarCategoryDAO;
import com.dao.SchoolCalendarDAO;
import com.dao.VersionsDAO;
import com.model.calendar.CalendarCategory;
import com.model.calendar.SchoolCalendar;
import com.model.calendar.SchoolCalendarLazy;
import com.model.calendar.SchoolCalendarSubLazy;
import com.model.calendar.Versions;



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
@Component("calendarCategoryDAO")
public class CalendarCategoryDAOImpl extends TopDAO implements CalendarCategoryDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CalendarCategoryDAOImpl.class);
	// property constants
	public static final String TITLE = "title";
	public static final String BEGINDATE = "beginDate";
	public static final String PUBLISHER = "publisher";
	public static final String WEEKNUM = "weeknum";

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#save(com.model.BusRoute)
	 */
	@Override
	public void save(CalendarCategory transientInstance) {
		log.debug("saving CalendarCategory instance");
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
	public void delete(CalendarCategory persistentInstance) {
		log.debug("deleting CalendarCategory instance");
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
	public CalendarCategory findById(java.lang.Long id) {
		log.debug("getting SchoolCalender instance with id: " + id);
		try {
			CalendarCategory instance = (CalendarCategory) getSession().get(
					"com.dao.impl.CalendarCategory", id);
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
	public List<CalendarCategory> findByExample(CalendarCategory instance) {
		log.debug("finding CalendarCategory instance by example");
		try {
			List<CalendarCategory> results = (List<CalendarCategory>) getSession()
					.createCriteria("com.dao.impl.CalendarCategory")
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
		log.debug("finding CalendarCategory instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CalendarCategory as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}

	
	@Override
	public List<CalendarCategory> findByBeginDate(Object beginDate) {
		return findByProperty(BEGINDATE, beginDate);
	}
	
	@Override
	public List<CalendarCategory> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}
	
	@Override
	public List<CalendarCategory> findByWeeknum(Object weeknum) {
		return findByProperty(WEEKNUM, weeknum);
	}
	


	@Override
	public List<CalendarCategory> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all CalendarCategory instances");
		try {
			String queryString = "from CalendarCategory";
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
	public CalendarCategory merge(CalendarCategory detachedInstance) {
		log.debug("merging CalendarCategory instance");
		try {
			CalendarCategory result = (CalendarCategory) getSession().merge(detachedInstance);
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
	public void attachDirty(CalendarCategory instance) {
		log.debug("attaching dirty CalendarCategory instance");
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
	public void attachClean(CalendarCategory instance) {
		log.debug("attaching clean CalendarCategory instance");
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
		return (Long)this.getSession().createQuery("select count(*) from CalendarCategoryLazy ").uniqueResult();
	}

	@Override
	public List<?> findByPage(int i, int rows, String sort, String order) {
		return (List<?>)this.getSession().createQuery("from CalendarCategoryLazy order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public void deleteByIds(String ids) {
		this.getSession().createQuery("delete from CalendarCategoryLazy br where br.id in ("+ids+")").executeUpdate();
	}
	@Override
	public void update(CalendarCategory calendarCategory) {
		
		this.getSession().update(calendarCategory);
	}

	
}