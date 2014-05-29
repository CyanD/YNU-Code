package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.CourseVideoDAO;
import com.model.course.CourseVideoLazy;

/**
 * A data access object (DAO) providing persistence and search support for
 * CourseVideo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.CourseVideoLazy
 * @author MyEclipse Persistence Tools
 */
@Component("courseVideoDAO")
public class CourseVideoDAOImpl extends TopDAO implements CourseVideoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CourseVideoDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PATH = "path";

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#save(com.model.course.CourseVideo)
	 */
	@Override
	public void save(CourseVideoLazy transientInstance) {
		log.debug("saving CourseVideo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#delete(com.model.course.CourseVideo)
	 */
	@Override
	public void delete(CourseVideoLazy persistentInstance) {
		log.debug("deleting CourseVideo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#findById(java.lang.Long)
	 */
	@Override
	public CourseVideoLazy findById(java.lang.Long id) {
		log.debug("getting CourseVideo instance with id: " + id);
		try {
			CourseVideoLazy instance = (CourseVideoLazy) getSession().get(
					CourseVideoLazy.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#findByExample(com.model.course.CourseVideo)
	 */
	@Override
	public List<CourseVideoLazy> findByExample(CourseVideoLazy instance) {
		log.debug("finding CourseVideo instance by example");
		try {
			List<CourseVideoLazy> results = (List<CourseVideoLazy>) getSession()
					.createCriteria(CourseVideoLazy.class)
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
	 * @see com.dao.impl.CourseVideoDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CourseVideo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CourseVideo as model where model."
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
	 * @see com.dao.impl.CourseVideoDAO#findByName(java.lang.Object)
	 */
	@Override
	public List<CourseVideoLazy> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#findByPath(java.lang.Object)
	 */
	@Override
	public List<CourseVideoLazy> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all CourseVideo instances");
		try {
			String queryString = "from CourseVideo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#merge(com.model.course.CourseVideo)
	 */
	@Override
	public CourseVideoLazy merge(CourseVideoLazy detachedInstance) {
		log.debug("merging CourseVideo instance");
		try {
			CourseVideoLazy result = (CourseVideoLazy) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#attachDirty(com.model.course.CourseVideo)
	 */
	@Override
	public void attachDirty(CourseVideoLazy instance) {
		log.debug("attaching dirty CourseVideo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.CourseVideoDAO#attachClean(com.model.course.CourseVideo)
	 */
	@Override
	public void attachClean(CourseVideoLazy instance) {
		log.debug("attaching clean CourseVideo instance");
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
	public List<CourseVideoLazy> findLazyByPage(Long pid, int i, int rows,
			String sort, String order) {
		return this.getSession().createQuery("from CourseVideoLazy  where pid = "+pid+" order by " +sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public long findTotalByPid(Long pid) {
		return (Long)this.getSession().createQuery("select count(*) from CourseVideoLazy  where pid = "+pid).uniqueResult();
	}

	@Override
	public void updateCourseVideoLazyOrders(Long id, Integer orders) {
		this.getSession().createQuery("update CourseVideoLazy set orders = "+orders+" where id = "+id).executeUpdate();
	}
}