package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.SysRoleDAO;
import com.model.sys.SysRole;
import com.model.sys.SysRoleCombogrid;
import com.model.sys.SysRoleLazy;

/**
 * A data access object (DAO) providing persistence and search support for
 * SysRole entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.sys.SysRole
 * @author MyEclipse Persistence Tools
 */
@Component("SysRoleDAO")
public class SysRoleDAOImpl extends TopDAO implements SysRoleDAO {
	private static final Logger log = LoggerFactory.getLogger(SysRoleDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String SYS = "sys";
	public static final String NEWS = "news";
	public static final String ANNIVERSARY = "anniversary";
	public static final String BUS = "bus";
	public static final String MAP = "map";
	public static final String COURSE = "course";
	public static final String ADDRESS_BOOK = "addressBook";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#save(com.model.SysRole)
	 */
	@Override
	public void save(SysRole transientInstance) {
		log.debug("saving SysRole instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#delete(com.model.SysRole)
	 */
	@Override
	public void delete(SysRole persistentInstance) {
		log.debug("deleting SysRole instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findById(java.lang.Long)
	 */
	@Override
	public SysRole findById(java.lang.Long id) {
		log.debug("getting SysRole instance with id: " + id);
		try {
			SysRole instance = (SysRole) getSession().get("com.model.SysRole",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByExample(com.model.SysRole)
	 */
	@Override
	public List<SysRole> findByExample(SysRole instance) {
		log.debug("finding SysRole instance by example");
		try {
			List<SysRole> results = (List<SysRole>) getSession()
					.createCriteria("com.model.SysRole").add(create(instance))
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
	 * @see com.dao.impl.SysRoleDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SysRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SysRole as model where model."
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
	 * @see com.dao.impl.SysRoleDAO#findByName(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByDescription(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findBySys(java.lang.Object)
	 */
	@Override
	public List<SysRole> findBySys(Object sys) {
		return findByProperty(SYS, sys);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByNews(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByNews(Object news) {
		return findByProperty(NEWS, news);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByAnniversary(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByAnniversary(Object anniversary) {
		return findByProperty(ANNIVERSARY, anniversary);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByBus(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByBus(Object bus) {
		return findByProperty(BUS, bus);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByMap(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByMap(Object map) {
		return findByProperty(MAP, map);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByCourse(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByCourse(Object course) {
		return findByProperty(COURSE, course);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByAddressBook(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByAddressBook(Object addressBook) {
		return findByProperty(ADDRESS_BOOK, addressBook);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<SysRole> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all SysRole instances");
		try {
			String queryString = "from SysRole";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#merge(com.model.SysRole)
	 */
	@Override
	public SysRole merge(SysRole detachedInstance) {
		log.debug("merging SysRole instance");
		try {
			SysRole result = (SysRole) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#attachDirty(com.model.SysRole)
	 */
	@Override
	public void attachDirty(SysRole instance) {
		log.debug("attaching dirty SysRole instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysRoleDAO#attachClean(com.model.SysRole)
	 */
	@Override
	public void attachClean(SysRole instance) {
		log.debug("attaching clean SysRole instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public long findLazyTotal() {
		return (Long)this.getSession().createQuery("select count(*) from SysRoleLazy").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysRoleLazy> findAllLazy() {
		return (List<SysRoleLazy>)this.getSession().createQuery("from SysRoleLazy").list();
	}
	@Override
	public List<SysRoleCombogrid> findAllSysRoleCombogrid() {
		return (List<SysRoleCombogrid>)this.getSession().createQuery("from SysRoleCombogrid").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysRoleLazy> findLazyByPage(int i, int rows, String sort,
			String order) {
		return (List<SysRoleLazy>)this.getSession().createQuery("from SysRoleLazy order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public void saveLazy(SysRoleLazy modelLazy) {
		this.getHibernateTemplate().save(modelLazy);
	}

	@Override
	public void updateLazy(SysRoleLazy modelLazy) {
		this.getHibernateTemplate().update(modelLazy);
	}

	@Override
	public SysRoleLazy findLazyById(Long id) {
		SysRoleLazy instance = (SysRoleLazy) getSession().get("com.model.SysRoleLazy",
				id);
		return instance;
	}

	@Override
	public void deleteLazy(SysRoleLazy modelLazy) {
		this.getHibernateTemplate().delete(modelLazy);
		
	}
}