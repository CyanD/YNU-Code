package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.NewsNewsCategoryDAO;
import com.model.news.news.NewsNewsCategory;
import com.model.news.news.NewsNewsCategoryTreeGet;
import com.model.news.news.NewsNewsCategoryTreeGridGet;
import com.model.news.news.NewsNewsCategoryTreeGridSet;

/**
 * A data access object (DAO) providing persistence and search support for
 * NewsNewsCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.news.news.NewsNewsCategory
 * @author MyEclipse Persistence Tools
 */
@Component("newsNewsCategoryDAO")
public class NewsNewsCategoryDAOImpl extends TopDAO implements NewsNewsCategoryDAO{
	private static final Logger log = LoggerFactory
			.getLogger(NewsNewsCategoryDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#save(com.model.NewsNewsCategory)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#save(com.model.NewsNewsCategory)
	 */
	@Override
	public void save(NewsNewsCategory transientInstance) {
		log.debug("saving NewsNewsCategory instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#delete(com.model.NewsNewsCategory)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#delete(com.model.NewsNewsCategory)
	 */
	@Override
	public void delete(NewsNewsCategory persistentInstance) {
		log.debug("deleting NewsNewsCategory instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findById(java.lang.Long)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findById(java.lang.Long)
	 */
	@Override
	public NewsNewsCategory findById(java.lang.Long id) {
		log.debug("getting NewsNewsCategory instance with id: " + id);
		try {
			NewsNewsCategory instance = (NewsNewsCategory) getSession().get(
					"com.model.NewsNewsCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findByExample(com.model.NewsNewsCategory)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findByExample(com.model.NewsNewsCategory)
	 */
	@Override
	public List<NewsNewsCategory> findByExample(NewsNewsCategory instance) {
		log.debug("finding NewsNewsCategory instance by example");
		try {
			List<NewsNewsCategory> results = (List<NewsNewsCategory>) getSession()
					.createCriteria("com.model.NewsNewsCategory")
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
	 * @see com.dao.impl.NewsNewsCategoryDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding NewsNewsCategory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NewsNewsCategory as model where model."
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
	 * @see com.dao.impl.NewsNewsCategoryDAO#findByName(java.lang.Object)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findByName(java.lang.Object)
	 */
	@Override
	public List<NewsNewsCategory> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findByPublisher(java.lang.Object)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<NewsNewsCategory> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findAll()
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all NewsNewsCategory instances");
		try {
			String queryString = "from NewsNewsCategory";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#merge(com.model.NewsNewsCategory)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#merge(com.model.NewsNewsCategory)
	 */
	@Override
	public NewsNewsCategory merge(NewsNewsCategory detachedInstance) {
		log.debug("merging NewsNewsCategory instance");
		try {
			NewsNewsCategory result = (NewsNewsCategory) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#attachDirty(com.model.NewsNewsCategory)
	 */
	@Override
	public void attachDirty(NewsNewsCategory instance) {
		log.debug("attaching dirty NewsNewsCategory instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#attachClean(com.model.NewsNewsCategory)
	 */
	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#attachClean(com.model.NewsNewsCategory)
	 */
	@Override
	public void attachClean(NewsNewsCategory instance) {
		log.debug("attaching clean NewsNewsCategory instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
    /* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findLazyTotal()
	 */
	
    /* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findTreeGridGetTotal()
	 */
    @Override
	public long findTreeGridGetTotal() {
    	return (Long)this.getSession().createQuery("select count(*) from NewsNewsCategoryTreeGridGet").uniqueResult();
    }

	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findAllTreeGridGet()
	 */
	@Override
	public List<NewsNewsCategoryTreeGridGet> findAllTreeGridGet() {
		
		return this.getSession().createQuery("from NewsNewsCategoryTreeGridGet").list();
	}

	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findTreeGridGetByPid(java.lang.Long)
	 */
	@Override
	public List<NewsNewsCategoryTreeGridGet> findTreeGridGetByPid(Long pid) {
		return this.getSession().createQuery("from NewsNewsCategoryTreeGridGet sdtgg where sdtgg.pid = "+pid).list();
	}

	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#saveTreeGridSet(com.model.NewsNewsCategoryTreeGridSet)
	 */
	@Override
	public void saveTreeGridSet(NewsNewsCategoryTreeGridSet modelLazy) {
		this.getHibernateTemplate().save(modelLazy);
	}

	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#deleteTreeGridSetById(java.lang.Long)
	 */
	@Override
	public void deleteTreeGridSetById(Long id) {
		this.getSession().createQuery("delete from NewsNewsCategoryTreeGridSet sdtgs where sdtgs.id = "+id).executeUpdate();
	}

	
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#updateTreeGridSet(com.model.NewsNewsCategoryTreeGridSet)
	 */
	@Override
	public void updateTreeGridSet(NewsNewsCategoryTreeGridSet modelLazy) {
		this.getHibernateTemplate().update(modelLazy);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findTreeGet()
	 */
	@Override
	@SuppressWarnings("unchecked")
	
	public List<NewsNewsCategoryTreeGet> findTreeGet() {
		return this.getSession().createQuery("from NewsNewsCategoryTreeGet").list();
	}
}