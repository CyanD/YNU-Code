package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.NewsNewsPictureDAO;
import com.model.news.picture.NewsNewsPicture;
import com.model.news.picture.NewsNewsPictureLazy;

/**
 * A data access object (DAO) providing persistence and search support for
 * NewsNewsPicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.com.model.news.picture.NewsNewsPicture
 * @author MyEclipse Persistence Tools
 */
@Component("newsNewsPictureDAO")
public class NewsNewsPictureDAOImpl extends TopDAO implements NewsNewsPictureDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NewsNewsPictureDAOImpl.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String PATH = "path";
	public static final String ORDER = "order";
	public static final String ISCOVER = "isCover";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#save(com.model.NewsNewsPicture)
	 */
	@Override
	public void save(NewsNewsPicture transientInstance) {
		log.debug("saving NewsNewsPicture instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#delete(com.model.NewsNewsPicture)
	 */
	@Override
	public void delete(NewsNewsPicture persistentInstance) {
		log.debug("deleting NewsNewsPicture instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#findById(java.lang.Long)
	 */
	@Override
	public NewsNewsPicture findById(java.lang.Long id) {
		log.debug("getting NewsNewsPicture instance with id: " + id);
		try {
			NewsNewsPicture instance = (NewsNewsPicture) getSession().get(
					"com.model.NewsNewsPicture", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#findByExample(com.model.NewsNewsPicture)
	 */
	@Override
	public List<NewsNewsPicture> findByExample(NewsNewsPicture instance) {
		log.debug("finding NewsNewsPicture instance by example");
		try {
			List<NewsNewsPicture> results = (List<NewsNewsPicture>) getSession()
					.createCriteria("com.model.NewsNewsPicture")
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
	 * @see com.dao.impl.NewsNewsPictureDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding NewsNewsPicture instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NewsNewsPicture as model where model."
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
	 * @see com.dao.impl.NewsNewsPictureDAO#findByPath(java.lang.Object)
	 */
	@Override
	public List<NewsNewsPicture> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#findByMiniPath(java.lang.Object)
	 */
	@Override
	public List<NewsNewsPicture> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#findByIsCover(java.lang.Object)
	 */
	@Override
	public List<NewsNewsPicture> findByIsCover(Object isCover) {
		return findByProperty(ISCOVER, isCover);
	}
	@Override
	public List<NewsNewsPicture> findByOrder(Object order) {
		return findByProperty(ORDER, order);
	}
	

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<NewsNewsPicture> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all NewsNewsPicture instances");
		try {
			String queryString = "from NewsNewsPicture";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#merge(com.model.NewsNewsPicture)
	 */
	@Override
	public NewsNewsPicture merge(NewsNewsPicture detachedInstance) {
		log.debug("merging NewsNewsPicture instance");
		try {
			NewsNewsPicture result = (NewsNewsPicture) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#attachDirty(com.model.NewsNewsPicture)
	 */
	@Override
	public void attachDirty(NewsNewsPicture instance) {
		log.debug("attaching dirty NewsNewsPicture instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsPictureDAO#attachClean(com.model.NewsNewsPicture)
	 */
	@Override
	public void attachClean(NewsNewsPicture instance) {
		log.debug("attaching clean NewsNewsPicture instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<?> findLazyByPage(long pid, int i, int rows, String sort,
			String order) {
		return (List<?>)this.getSession().createQuery("from NewsNewsPictureLazy nnpl where nnpl.pid = "+pid+" order by " +sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}
	@Override
	public List<NewsNewsPictureLazy> findLazyByPid(Long pid,String sort,String order) {
		return (List<NewsNewsPictureLazy>)this.getSession().createQuery("from NewsNewsPictureLazy nnpl where nnpl.pid = "+pid+" order by " +sort+" "+order).list();
	}
	@Override
	public long findLazyTotal(long pid) {
		return (Long)this.getSession().createQuery("select count(*) from NewsNewsPictureLazy nnpl where nnpl.pid = "+pid).uniqueResult();
	}

	@Override
	public void saveLazy(NewsNewsPictureLazy newsNewsPictureLazy) {
		this.getHibernateTemplate().save(newsNewsPictureLazy);
	}

	@Override
	public void deleteByIds(String ids) {
		this.getSession().createQuery("delete from NewsNewsPictureLazy nnpl where nnpl.id in ("+ids+")").executeUpdate();
	}

	@Override
	public void updateLazy(NewsNewsPictureLazy newsNewsPictureLazy) {
		this.getHibernateTemplate().merge(newsNewsPictureLazy);
	}
	@Override
	public NewsNewsPictureLazy findLazyById(Long id){
			NewsNewsPictureLazy instance = (NewsNewsPictureLazy) getSession().get(
					NewsNewsPictureLazy.class, id);
			return instance;
	}
	@Override
	public void deleteLazy(NewsNewsPictureLazy persistentInstance) {
		log.debug("deleting NewsNewsPicture instance");
		try {
			System.out.println(persistentInstance.getId());
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public NewsNewsPictureLazy findLazyCoverByPid(Long pid) {
		return (NewsNewsPictureLazy)this.getSession().createQuery("from NewsNewsPictureLazy nnpl where nnpl.pid = "+pid+" and nnpl.isCover = '是' ").uniqueResult();
	}
	@Override
	public Long findLazyCoverTotalByPid(Long pid) {
		return (Long)this.getSession().createQuery("select count(*) from NewsNewsPictureLazy nnpl where nnpl.pid = "+pid+" and nnpl.isCover = '是' ").uniqueResult();
	}

	@Override
	public void deleteLazyById(Long id) {
		this.getSession().createQuery("delete from NewsNewsPictureLazy nnpl where nnpl.id = "+id);
	}

	@Override
	public void updateCoverByPid(Long pid) {
		this.getSession().createQuery("update NewsNewsPictureLazy nnpl set nnpl.isCover = '否' where nnpl.pid = "+pid+" and nnpl.isCover = '是'").executeUpdate();
	}

	@Override
	public void updateDescriptionById(Long id,String description) {
		this.getSession().createQuery("update NewsNewsPictureLazy nnpl set nnpl.description = '"+description+"' where nnpl.id = "+id).executeUpdate();
	}

	@Override
	public List<NewsNewsPictureLazy> findAllByPid(Long pid) {
		return this.getSession().createQuery("from NewsNewsPictureLazy where pid = "+pid).list();
	}

	@Override
	public void updateDescriptionAndOrdersById(Long id, Integer orders,
			String description) {
		this.getSession().createQuery("update NewsNewsPictureLazy set orders = "+orders+" ,description = '"+description+"' where id = "+id).executeUpdate();
	}

	
}