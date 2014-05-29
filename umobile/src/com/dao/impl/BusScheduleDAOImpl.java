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

import com.dao.BusScheduleDAO;
import com.model.bus.BusSchedule;
import com.model.bus.BusScheduleLazy;

/**
 * A data access object (DAO) providing persistence and search support for
 * BusSchedule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.SchoolCalendarSub.model.bus.BusSchedule
 * @author MyEclipse Persistence Tools
 */
@Component("busScheduleDAO")
public class BusScheduleDAOImpl extends TopDAO implements BusScheduleDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusScheduleDAOImpl.class);
	// property constants
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.BusScheduleDAO#save(com.model.BusSchedule)
	 */
	@Override
	public void save(BusSchedule transientInstance) {
		log.debug("saving BusSchedule instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusScheduleDAO#delete(com.model.BusSchedule)
	 */
	@Override
	public void delete(BusSchedule persistentInstance) {
		log.debug("deleting BusSchedule instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusScheduleDAO#findById(java.lang.Long)
	 */
	@Override
	public BusSchedule findById(java.lang.Long id) {
		log.debug("getting BusSchedule instance with id: " + id);
		try {
			BusSchedule instance = (BusSchedule) getSession().get(
					"com.dao.impl.BusSchedule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusScheduleDAO#findByExample(com.model.BusSchedule)
	 */
	@Override
	public List<BusSchedule> findByExample(BusSchedule instance) {
		log.debug("finding BusSchedule instance by example");
		try {
			List<BusSchedule> results = (List<BusSchedule>) getSession()
					.createCriteria("com.dao.impl.BusSchedule")
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
	 * @see com.dao.impl.BusScheduleDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BusSchedule instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BusSchedule as model where model."
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
	 * @see com.dao.impl.BusScheduleDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<BusSchedule> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusScheduleDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BusSchedule instances");
		try {
			String queryString = "from BusSchedule";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusScheduleDAO#merge(com.model.BusSchedule)
	 */
	@Override
	public BusSchedule merge(BusSchedule detachedInstance) {
		log.debug("merging BusSchedule instance");
		try {
			BusSchedule result = (BusSchedule) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusScheduleDAO#attachDirty(com.model.BusSchedule)
	 */
	@Override
	public void attachDirty(BusSchedule instance) {
		log.debug("attaching dirty BusSchedule instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.BusScheduleDAO#attachClean(com.model.BusSchedule)
	 */
	@Override
	public void attachClean(BusSchedule instance) {
		log.debug("attaching clean BusSchedule instance");
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
		
		return (List<?>)this.getSession().createQuery("from BusScheduleLazy bs where bs.pid = " + pid + " order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public long findTotal(long pid) {
		
		return (Long)this.getSession().createQuery("select count(*) from BusScheduleLazy bs where bs.pid = "+pid).uniqueResult();
	}

	@Override
	public void saveLazy(BusScheduleLazy busScheduleLazy) {
		this.getHibernateTemplate().save(busScheduleLazy);
	}

	@Override
	public void deleteByIds(String ids) {
		this.getSession().createQuery("delete from BusScheduleLazy bsl where bsl.id in ("+ids+")").executeUpdate();
	}

	@Override
	public void updateLazy(BusScheduleLazy busScheduleLazy) {
		this.getHibernateTemplate().update(busScheduleLazy);
	}
}