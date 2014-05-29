package com.dao.impl;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.BusRouteDAO;
import com.model.bus.BusRoute;

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
@Component("busRouteDAO")
public class BusRouteDAOImpl extends TopDAO implements BusRouteDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusRouteDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String STARTSTATION = "startStation";
	public static final String ENDSTATION = "endStation";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#save(com.model.BusRoute)
	 */
	@Override
	public void save(BusRoute transientInstance) {
		log.debug("saving BusRoute instance");
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
	public void delete(BusRoute persistentInstance) {
		log.debug("deleting BusRoute instance");
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
	public BusRoute findById(java.lang.Long id) {
		log.debug("getting BusRoute instance with id: " + id);
		try {
			BusRoute instance = (BusRoute) getSession().get(
					"com.dao.impl.BusRoute", id);
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
	public List<BusRoute> findByExample(BusRoute instance) {
		log.debug("finding BusRoute instance by example");
		try {
			List<BusRoute> results = (List<BusRoute>) getSession()
					.createCriteria("com.dao.impl.BusRoute")
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
		log.debug("finding BusRoute instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BusRoute as model where model."
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
	public List<BusRoute> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findByStartStation(java.lang.Object)
	 */
	@Override
	public List<BusRoute> findByStartStation(Object startStation) {
		return findByProperty(STARTSTATION, startStation);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findByEndStation(java.lang.Object)
	 */
	@Override
	public List<BusRoute> findByEndStation(Object endStation) {
		return findByProperty(ENDSTATION, endStation);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<BusRoute> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusRouteDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BusRoute instances");
		try {
			String queryString = "from BusRoute";
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
	public BusRoute merge(BusRoute detachedInstance) {
		log.debug("merging BusRoute instance");
		try {
			BusRoute result = (BusRoute) getSession().merge(detachedInstance);
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
	public void attachDirty(BusRoute instance) {
		log.debug("attaching dirty BusRoute instance");
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
	public void attachClean(BusRoute instance) {
		log.debug("attaching clean BusRoute instance");
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
		return (Long)this.getSession().createQuery("select count(*) from BusRouteLazy ").uniqueResult();
	}

	@Override
	public List<?> findByPage(int i, int rows, String sort, String order) {
		return (List<?>)this.getSession().createQuery("from BusRouteLazy order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public void deleteByIds(String ids) {
		this.getSession().createQuery("delete from BusRouteLazy br where br.id in ("+ids+")").executeUpdate();
	}
	@Override
	public void update(BusRoute busRoute) {
		
		this.getSession().update(busRoute);
	}	
	
}