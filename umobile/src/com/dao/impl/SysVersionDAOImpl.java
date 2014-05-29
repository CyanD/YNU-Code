package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.SysVersionDAO;
import com.model.sys.SysVersion;

/**
 * A data access object (DAO) providing persistence and search support for
 * SysVersion entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.sys.SysVersion
 * @author MyEclipse Persistence Tools
 */
@Component("SysVersionDAO")
public class SysVersionDAOImpl extends TopDAO implements SysVersionDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SysVersionDAOImpl.class);
	// property constants
	public static final String VERSION = "version";
	public static final String NAME = "name";

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#save(com.model.SysVersion)
	 */
	@Override
	public void save(SysVersion transientInstance) {
		log.debug("saving SysVersion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#delete(com.model.SysVersion)
	 */
	@Override
	public void delete(SysVersion persistentInstance) {
		log.debug("deleting SysVersion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#findById(java.lang.Long)
	 */
	@Override
	public SysVersion findById(java.lang.Long id) {
		log.debug("getting SysVersion instance with id: " + id);
		try {
			SysVersion instance = (SysVersion) getSession().get(
					"com.model.SysVersion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#findByExample(com.model.SysVersion)
	 */
	@Override
	public List<SysVersion> findByExample(SysVersion instance) {
		log.debug("finding SysVersion instance by example");
		try {
			List<SysVersion> results = (List<SysVersion>) getSession()
					.createCriteria("com.model.SysVersion")
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
	 * @see com.dao.impl.SysVersionDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SysVersion instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SysVersion as model where model."
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
	 * @see com.dao.impl.SysVersionDAO#findByVersion(java.lang.Object)
	 */
	@Override
	public List<SysVersion> findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#findByName(java.lang.Object)
	 */
	@Override
	public List<SysVersion> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all SysVersion instances");
		try {
			String queryString = "from SysVersion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#merge(com.model.SysVersion)
	 */
	@Override
	public SysVersion merge(SysVersion detachedInstance) {
		log.debug("merging SysVersion instance");
		try {
			SysVersion result = (SysVersion) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#attachDirty(com.model.SysVersion)
	 */
	@Override
	public void attachDirty(SysVersion instance) {
		log.debug("attaching dirty SysVersion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysVersionDAO#attachClean(com.model.SysVersion)
	 */
	@Override
	public void attachClean(SysVersion instance) {
		log.debug("attaching clean SysVersion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}