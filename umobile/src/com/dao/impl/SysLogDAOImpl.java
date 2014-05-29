package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.SysLogDAO;
import com.model.sys.SysLog;

/**
 * A data access object (DAO) providing persistence and search support for
 * SysLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.sys.SysLog
 * @author MyEclipse Persistence Tools
 */
@Component("SysLogDAO")
public class SysLogDAOImpl extends TopDAO implements SysLogDAO {
	private static final Logger log = LoggerFactory.getLogger(SysLogDAOImpl.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String USER_ID = "userId";
	public static final String USER_IP = "userIp";
	public static final String USER_DEPT = "userDept";
	public static final String OPER = "oper";
	public static final String OPER_TABLE = "operTable";
	public static final String OPER_OBJ_ID = "operObjId";
	public static final String OPER_DETAIL = "operDetail";

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#save(com.model.SysLog)
	 */
	@Override
	public void save(SysLog transientInstance) {
		log.debug("saving SysLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#delete(com.model.SysLog)
	 */
	@Override
	public void delete(SysLog persistentInstance) {
		log.debug("deleting SysLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findById(java.lang.Long)
	 */
	@Override
	public SysLog findById(java.lang.Long id) {
		log.debug("getting SysLog instance with id: " + id);
		try {
			SysLog instance = (SysLog) getSession().get("com.model.SysLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findByExample(com.model.SysLog)
	 */
	@Override
	public List<SysLog> findByExample(SysLog instance) {
		log.debug("finding SysLog instance by example");
		try {
			List<SysLog> results = (List<SysLog>) getSession()
					.createCriteria("com.model.SysLog").add(create(instance))
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
	 * @see com.dao.impl.SysLogDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SysLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SysLog as model where model."
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
	 * @see com.dao.impl.SysLogDAO#findByUserName(java.lang.Object)
	 */
	@Override
	public List<SysLog> findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findByUserId(java.lang.Object)
	 */
	@Override
	public List<SysLog> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findByUserIp(java.lang.Object)
	 */
	@Override
	public List<SysLog> findByUserIp(Object userIp) {
		return findByProperty(USER_IP, userIp);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findByUserDept(java.lang.Object)
	 */
	@Override
	public List<SysLog> findByUserDept(Object userDept) {
		return findByProperty(USER_DEPT, userDept);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findByOper(java.lang.Object)
	 */
	@Override
	public List<SysLog> findByOper(Object oper) {
		return findByProperty(OPER, oper);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findByOperTable(java.lang.Object)
	 */
	@Override
	public List<SysLog> findByOperTable(Object operTable) {
		return findByProperty(OPER_TABLE, operTable);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findByOperObjId(java.lang.Object)
	 */
	@Override
	public List<SysLog> findByOperObjId(Object operObjId) {
		return findByProperty(OPER_OBJ_ID, operObjId);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findByOperDetail(java.lang.Object)
	 */
	@Override
	public List<SysLog> findByOperDetail(Object operDetail) {
		return findByProperty(OPER_DETAIL, operDetail);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all SysLog instances");
		try {
			String queryString = "from SysLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#merge(com.model.SysLog)
	 */
	@Override
	public SysLog merge(SysLog detachedInstance) {
		log.debug("merging SysLog instance");
		try {
			SysLog result = (SysLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#attachDirty(com.model.SysLog)
	 */
	@Override
	public void attachDirty(SysLog instance) {
		log.debug("attaching dirty SysLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.SysLogDAO#attachClean(com.model.SysLog)
	 */
	@Override
	public void attachClean(SysLog instance) {
		log.debug("attaching clean SysLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}