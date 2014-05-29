package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.SysUserDAO;
import com.model.login.LoginUser;
import com.model.sys.SysUser;
import com.model.sys.SysUserLazy;
import com.model.sys.SysUserView;

/**
 * A data access object (DAO) providing persistence and search support for
 * SysUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.sys.SysUser
 * @author MyEclipse Persistence Tools
 */
@Component("SysUserDAO")
public class SysUserDAOImpl extends TopDAO implements SysUserDAO {
	private static final Logger log = LoggerFactory.getLogger(SysUserDAOImpl.class);
	// property constants
	public static final String ACCOUNT = "account";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String TEL = "tel";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#save(com.model.SysUser)
	 */
	@Override
	public void save(SysUser transientInstance) {
		log.debug("saving SysUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#delete(com.model.SysUser)
	 */
	@Override
	public void delete(SysUser persistentInstance) {
		log.debug("deleting SysUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findById(java.lang.Long)
	 */
	@Override
	public SysUser findById(java.lang.Long id) {
		log.debug("getting SysUser instance with id: " + id);
		try {
			SysUser instance = (SysUser) getSession().get(SysUser.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findByExample(com.model.SysUser)
	 */
	@Override
	public List<SysUser> findByExample(SysUser instance) {
		log.debug("finding SysUser instance by example");
		try {
			List<SysUser> results = (List<SysUser>) getSession()
					.createCriteria(SysUser.class).add(create(instance))
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
	 * @see com.dao.impl.SysUserDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SysUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SysUser as model where model."
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
	 * @see com.dao.impl.SysUserDAO#findByAccount(java.lang.Object)
	 */
	@Override
	public List<SysUser> findByAccount(Object account) {
		return findByProperty(ACCOUNT, account);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findByPassword(java.lang.Object)
	 */
	@Override
	public List<SysUser> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findByName(java.lang.Object)
	 */
	@Override
	public List<SysUser> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findByTel(java.lang.Object)
	 */
	@Override
	public List<SysUser> findByTel(Object tel) {
		return findByProperty(TEL, tel);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findByEmail(java.lang.Object)
	 */
	@Override
	public List<SysUser> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findByStatus(java.lang.Object)
	 */
	@Override
	public List<SysUser> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<SysUser> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all SysUser instances");
		try {
			String queryString = "from SysUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#merge(com.model.SysUser)
	 */
	@Override
	public SysUser merge(SysUser detachedInstance) {
		log.debug("merging SysUser instance");
		try {
			SysUser result = (SysUser) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#attachDirty(com.model.SysUser)
	 */
	@Override
	public void attachDirty(SysUser instance) {
		log.debug("attaching dirty SysUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysUserDAO#attachClean(com.model.SysUser)
	 */
	@Override
	public void attachClean(SysUser instance) {
		log.debug("attaching clean SysUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUserView> findLazyByPage(int i, int rows, String sort,
			String order) {
		return (List<SysUserView>)this.getSession().createQuery("from SysUserView order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public long findLazyTotal() {
		return (Long)this.getSession().createQuery("select count(*) from SysUserLazy").uniqueResult();
	}

	@Override
	public void saveLazy(SysUserLazy modelLazy) {
		this.getHibernateTemplate().save(modelLazy);
	}

	@Override
	public LoginUser findLoginUser(String account) {
		return (LoginUser)this.getSession().createQuery("from LoginUser where account = '"+account+"'").uniqueResult();
	}

	@Override
	public SysUserLazy findSysUserLazyById(Long id) {
		SysUserLazy instance = (SysUserLazy) getSession().get(SysUserLazy.class,id);
		return instance;
	}
}