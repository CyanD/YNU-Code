package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.SysDeptDAO;
import com.model.addressbook.AddressbookPublicTreeGrid;
import com.model.addressbook.AddressbookPublicTreeGrid;
import com.model.addressbook.AddressbookPublicTree;
import com.model.sys.SysDept;
import com.model.sys.SysDeptTreeGet;
import com.model.sys.SysDeptTreeGridGet;
import com.model.sys.SysDeptTreeGridSet;

/**
 * A data access object (DAO) providing persistence and search support for
 * SysDept entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.sys.SysDept
 * @author MyEclipse Persistence Tools
 */
@Component("SysDeptDAO")
public class SysDeptDAOImpl extends TopDAO implements SysDeptDAO {
	private static final Logger log = LoggerFactory.getLogger(SysDeptDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#save(com.model.SysDept)
	 */
	@Override
	public void save(SysDept transientInstance) {
		log.debug("saving SysDept instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#delete(com.model.SysDept)
	 */
	@Override
	public void delete(SysDept persistentInstance) {
		log.debug("deleting SysDept instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#findById(java.lang.Long)
	 */
	@Override
	public SysDept findById(java.lang.Long id) {
		log.debug("getting SysDept instance with id: " + id);
		try {
			SysDept instance = (SysDept) getSession().get("com.model.SysDept",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#findByExample(com.model.SysDept)
	 */
	@Override
	public List<SysDept> findByExample(SysDept instance) {
		log.debug("finding SysDept instance by example");
		try {
			List<SysDept> results = (List<SysDept>) getSession()
					.createCriteria("com.model.SysDept").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SysDept instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SysDept as model where model."
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
	 * @see com.dao.impl.SysDeptDAO#findByName(java.lang.Object)
	 */
	@Override
	public List<SysDept> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<SysDept> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all SysDept instances");
		try {
			String queryString = "from SysDept";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#merge(com.model.SysDept)
	 */
	@Override
	public SysDept merge(SysDept detachedInstance) {
		log.debug("merging SysDept instance");
		try {
			SysDept result = (SysDept) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#attachDirty(com.model.SysDept)
	 */
	@Override
	public void attachDirty(SysDept instance) {
		log.debug("attaching dirty SysDept instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysDeptDAO#attachClean(com.model.SysDept)
	 */
	@Override
	public void attachClean(SysDept instance) {
		log.debug("attaching clean SysDept instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	@Override
    public long findTreeGridGetTotal() {
    	return (Long)this.getSession().createQuery("select count(*) from SysDeptTreeGridGet").uniqueResult();
    }

	@Override
	public List<SysDeptTreeGridGet> findAllTreeGridGet() {
		
		return this.getSession().createQuery("from SysDeptTreeGridGet").list();
	}

	@Override
	public List<SysDeptTreeGridGet> findTreeGridGetByPid(Long pid) {
		return this.getSession().createQuery("from SysDeptTreeGridGet sdtgg where sdtgg.pid = "+pid).list();
	}

	@Override
	public void saveTreeGridSet(SysDeptTreeGridSet modelLazy) {
		this.getHibernateTemplate().save(modelLazy);
	}

	@Override
	public void deleteTreeGridSetById(Long id) {
		this.getSession().createQuery("delete from SysDeptTreeGridSet sdtgs where sdtgs.id = "+id).executeUpdate();
	}

	@Override
	public void updateTreeGridSet(SysDeptTreeGridSet modelLazy) {
		this.getHibernateTemplate().update(modelLazy);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysDeptTreeGet> findTreeGet() {
		return this.getSession().createQuery("from SysDeptTreeGet").list();
	}
}