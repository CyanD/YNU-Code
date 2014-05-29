package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.NoticeAccessoriesDAO;
import com.model.notice.NoticeAccessoriesLazy;

/**
 * A data access object (DAO) providing persistence and search support for
 * NoticeAccessories entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.NoticeAccessoriesLazy
 * @author MyEclipse Persistence Tools
 */
@Component("noticeAccessoriesDAO")
public class NoticeAccessoriesDAOImpl extends TopDAO implements NoticeAccessoriesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NoticeAccessoriesDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PATH = "path";

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#save(com.model.course.NoticeAccessories)
	 */
	@Override
	public void save(NoticeAccessoriesLazy transientInstance) {
		log.debug("saving NoticeAccessories instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#delete(com.model.course.NoticeAccessories)
	 */
	@Override
	public void delete(NoticeAccessoriesLazy persistentInstance) {
		log.debug("deleting NoticeAccessories instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#findById(java.lang.Long)
	 */
	@Override
	public NoticeAccessoriesLazy findById(java.lang.Long id) {
		log.debug("getting NoticeAccessories instance with id: " + id);
		try {
			NoticeAccessoriesLazy instance = (NoticeAccessoriesLazy) getSession().get(
					NoticeAccessoriesLazy.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#findByExample(com.model.course.NoticeAccessories)
	 */
	@Override
	public List<NoticeAccessoriesLazy> findByExample(NoticeAccessoriesLazy instance) {
		log.debug("finding NoticeAccessories instance by example");
		try {
			List<NoticeAccessoriesLazy> results = (List<NoticeAccessoriesLazy>) getSession()
					.createCriteria(NoticeAccessoriesLazy.class)
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
	 * @see com.dao.impl.NoticeAccessoriesDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding NoticeAccessories instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NoticeAccessories as model where model."
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
	 * @see com.dao.impl.NoticeAccessoriesDAO#findByName(java.lang.Object)
	 */
	@Override
	public List<NoticeAccessoriesLazy> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#findByPath(java.lang.Object)
	 */
	@Override
	public List<NoticeAccessoriesLazy> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all NoticeAccessories instances");
		try {
			String queryString = "from NoticeAccessories";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#merge(com.model.course.NoticeAccessories)
	 */
	@Override
	public NoticeAccessoriesLazy merge(NoticeAccessoriesLazy detachedInstance) {
		log.debug("merging NoticeAccessories instance");
		try {
			NoticeAccessoriesLazy result = (NoticeAccessoriesLazy) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#attachDirty(com.model.course.NoticeAccessories)
	 */
	@Override
	public void attachDirty(NoticeAccessoriesLazy instance) {
		log.debug("attaching dirty NoticeAccessories instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NoticeAccessoriesDAO#attachClean(com.model.course.NoticeAccessories)
	 */
	@Override
	public void attachClean(NoticeAccessoriesLazy instance) {
		log.debug("attaching clean NoticeAccessories instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<NoticeAccessoriesLazy> findLazyByPage(Long pid, int i, int rows,
			String sort, String order) {
		return this.getSession().createQuery("from NoticeAccessoriesLazy  where pid = "+pid+" order by " +sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public long findTotalByPid(Long pid) {
		return (Long)this.getSession().createQuery("select count(*) from NoticeAccessoriesLazy  where pid = "+pid).uniqueResult();
	}

	@Override
	public void updateNoticeAccessoriesLazyOrders(Long id, Integer orders) {
		this.getSession().createQuery("update NoticeAccessoriesLazy set orders = "+orders+" where id = "+id).executeUpdate();
	}
}