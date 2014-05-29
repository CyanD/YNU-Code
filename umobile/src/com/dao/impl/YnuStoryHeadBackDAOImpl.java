package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.YnuStoryHeadBackDAO;
import com.model.publicservice.YnuStoryHeadBack;

/**
 * A data access object (DAO) providing persistence and search support for
 * AddressbookHeadBack entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.AddressbookHeadBack
 * @author MyEclipse Persistence Tools
 */
@Component("ynuStoryHeadBackDAO")
public class YnuStoryHeadBackDAOImpl extends TopDAO implements YnuStoryHeadBackDAO {
	private static final Logger log = LoggerFactory.getLogger(YnuStoryHeadBackDAOImpl.class);
	// property constants
	public static final String PATH = "path";

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#save(com.model.addressbook.AddressbookHeadBack)
	 */
	@Override
	public void save(YnuStoryHeadBack transientInstance) {
		log.debug("saving YnuStoryHeadBack instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public void saveByPath(String path) {
		YnuStoryHeadBack ynuStoryHeadBack = new YnuStoryHeadBack();
		ynuStoryHeadBack.setPath(path);
		getSession().save(ynuStoryHeadBack);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#delete(com.model.addressbook.AddressbookHeadBack)
	 */
	@Override
	public void delete(YnuStoryHeadBack persistentInstance) {
		log.debug("deleting YnuStoryHeadBack instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#findById(java.lang.Long)
	 */
	@Override
	public YnuStoryHeadBack findById(java.lang.Long id) {
		log.debug("getting YnuStoryHeadBack instance with id: " + id);
		try {
			YnuStoryHeadBack instance = (YnuStoryHeadBack) getSession()
					.get("com.model.YnuStoryHeadBack", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#findByExample(com.model.addressbook.AddressbookHeadBack)
	 */
	@Override
	public List<YnuStoryHeadBack> findByExample(YnuStoryHeadBack instance) {
		log.debug("finding YnuStoryHeadBack instance by example");
		try {
			List<YnuStoryHeadBack> results = (List<YnuStoryHeadBack>) getSession()
					.createCriteria("com.model.YnuStoryHeadBack")
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
	 * @see com.dao.impl.AddressbookHeadBackDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding YnuStoryHeadBack instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from YnuStoryHeadBack as model where model."
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
	 * @see com.dao.impl.AddressbookHeadBackDAO#findByPath(java.lang.Object)
	 */
	@Override
	public List<YnuStoryHeadBack> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all YnuStoryHeadBack instances");
		try {
			String queryString = "from YnuStoryHeadBack";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#merge(com.model.addressbook.AddressbookHeadBack)
	 */
	@Override
	public YnuStoryHeadBack merge(YnuStoryHeadBack detachedInstance) {
		log.debug("merging YnuStoryHeadBack instance");
		try {
			YnuStoryHeadBack result = (YnuStoryHeadBack) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#attachDirty(com.model.addressbook.AddressbookHeadBack)
	 */
	@Override
	public void attachDirty(YnuStoryHeadBack instance) {
		log.debug("attaching dirty YnuStoryHeadBack instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#attachClean(com.model.addressbook.AddressbookHeadBack)
	 */
	@Override
	public void attachClean(YnuStoryHeadBack instance) {
		log.debug("attaching clean YnuStoryHeadBack instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByPath(String headpath) {
		this.getSession().createQuery("delete from YnuStoryHeadBack where path ='"+headpath+"'").executeUpdate();
	}
}