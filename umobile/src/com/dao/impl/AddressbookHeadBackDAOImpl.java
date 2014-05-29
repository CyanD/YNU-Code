package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.AddressbookHeadBackDAO;
import com.model.addressbook.AddressbookHeadBack;

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
@Component("addressbookHeadBackDAO")
public class AddressbookHeadBackDAOImpl extends TopDAO implements AddressbookHeadBackDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AddressbookHeadBackDAOImpl.class);
	// property constants
	public static final String PATH = "path";

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#save(com.model.addressbook.AddressbookHeadBack)
	 */
	@Override
	public void save(AddressbookHeadBack transientInstance) {
		log.debug("saving AddressbookHeadBack instance");
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
		AddressbookHeadBack addressbookHeadBack = new AddressbookHeadBack();
		addressbookHeadBack.setPath(path);
		getSession().save(addressbookHeadBack);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#delete(com.model.addressbook.AddressbookHeadBack)
	 */
	@Override
	public void delete(AddressbookHeadBack persistentInstance) {
		log.debug("deleting AddressbookHeadBack instance");
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
	public AddressbookHeadBack findById(java.lang.Long id) {
		log.debug("getting AddressbookHeadBack instance with id: " + id);
		try {
			AddressbookHeadBack instance = (AddressbookHeadBack) getSession()
					.get("com.model.AddressbookHeadBack", id);
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
	public List<AddressbookHeadBack> findByExample(AddressbookHeadBack instance) {
		log.debug("finding AddressbookHeadBack instance by example");
		try {
			List<AddressbookHeadBack> results = (List<AddressbookHeadBack>) getSession()
					.createCriteria("com.model.AddressbookHeadBack")
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
		log.debug("finding AddressbookHeadBack instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AddressbookHeadBack as model where model."
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
	public List<AddressbookHeadBack> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.AddressbookHeadBackDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all AddressbookHeadBack instances");
		try {
			String queryString = "from AddressbookHeadBack";
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
	public AddressbookHeadBack merge(AddressbookHeadBack detachedInstance) {
		log.debug("merging AddressbookHeadBack instance");
		try {
			AddressbookHeadBack result = (AddressbookHeadBack) getSession()
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
	public void attachDirty(AddressbookHeadBack instance) {
		log.debug("attaching dirty AddressbookHeadBack instance");
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
	public void attachClean(AddressbookHeadBack instance) {
		log.debug("attaching clean AddressbookHeadBack instance");
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
		this.getSession().createQuery("delete from AddressbookHeadBack where path ='"+headpath+"'").executeUpdate();
	}
}