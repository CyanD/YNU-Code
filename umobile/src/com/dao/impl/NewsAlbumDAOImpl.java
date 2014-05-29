package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.NewsAlbumDAO;
import com.model.news.album.NewsAlbumDelete;
import com.model.news.album.NewsAlbumGrid;
import com.model.news.album.NewsAlbumLazy;

/**
 * A data access object (DAO) providing persistence and search support for
 * NewsAlbumLazy entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.NewsAlbumLazy
 * @author MyEclipse Persistence Tools
 */
@Component("newsAlbumDAO")
public class NewsAlbumDAOImpl extends TopDAO implements NewsAlbumDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NewsAlbumDAOImpl.class);
	// property constants
	public static final String TITLE = "title";
	public static final String PHOTOGRAPHER = "photographer";
	public static final String KEYWORDS = "keywords";
	public static final String COVER_PATH = "coverPath";
	public static final String DESCRIPTION = "description";
	public static final String VISITORS = "visitors";
	public static final String STATUS = "status";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#save(com.model.news.album.NewsAlbumLazy)
	 */
	@Override
	public void save(NewsAlbumLazy transientInstance) {
		log.debug("saving NewsAlbumLazy instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#delete(com.model.news.album.NewsAlbumLazy)
	 */
	@Override
	public void delete(NewsAlbumLazy persistentInstance) {
		log.debug("deleting NewsAlbumLazy instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findById(java.lang.Long)
	 */
	@Override
	public NewsAlbumLazy findById(java.lang.Long id) {
		log.debug("getting NewsAlbumLazy instance with id: " + id);
		try {
			NewsAlbumLazy instance = (NewsAlbumLazy) getSession().get(
					NewsAlbumLazy.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findByExample(com.model.news.album.NewsAlbumLazy)
	 */
	@Override
	public List<NewsAlbumLazy> findByExample(NewsAlbumLazy instance) {
		log.debug("finding NewsAlbumLazy instance by example");
		try {
			List<NewsAlbumLazy> results = (List<NewsAlbumLazy>) getSession()
					.createCriteria("com.model.NewsAlbumLazy")
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
	 * @see com.dao.impl.NewsAlbumDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding NewsAlbumLazy instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NewsAlbumLazy as model where model."
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
	 * @see com.dao.impl.NewsAlbumDAO#findByTitle(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumLazy> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findByPhotographer(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumLazy> findByPhotographer(Object photographer) {
		return findByProperty(PHOTOGRAPHER, photographer);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findByKeywords(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumLazy> findByKeywords(Object keywords) {
		return findByProperty(KEYWORDS, keywords);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findByCoverPath(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumLazy> findByCoverPath(Object coverPath) {
		return findByProperty(COVER_PATH, coverPath);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findByDescription(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumLazy> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findByVisitors(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumLazy> findByVisitors(Object visitors) {
		return findByProperty(VISITORS, visitors);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findByStatus(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumLazy> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumLazy> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all NewsAlbumLazy instances");
		try {
			String queryString = "from NewsAlbumLazy";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#merge(com.model.news.album.NewsAlbumLazy)
	 */
	@Override
	public NewsAlbumLazy merge(NewsAlbumLazy detachedInstance) {
		log.debug("merging NewsAlbumLazy instance");
		try {
			NewsAlbumLazy result = (NewsAlbumLazy) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#attachDirty(com.model.news.album.NewsAlbumLazy)
	 */
	@Override
	public void attachDirty(NewsAlbumLazy instance) {
		log.debug("attaching dirty NewsAlbumLazy instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumDAO#attachClean(com.model.news.album.NewsAlbumLazy)
	 */
	@Override
	public void attachClean(NewsAlbumLazy instance) {
		log.debug("attaching clean NewsAlbumLazy instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<NewsAlbumGrid> findNewsAlbums(int i, int rows, String sort,
			String order) {
		return this.getSession().createQuery("from NewsAlbumGrid order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from NewsAlbumLazy ").uniqueResult();
	}
	@Override
	public long findLazyTotal() {
		
		return (Long)this.getSession().createQuery("select count(*) from NewsAlbumLazy").uniqueResult();
	}

	@Override
	public void saveLazy(NewsAlbumLazy newsNewsLazy) {
		this.getHibernateTemplate().save(newsNewsLazy);
	}

	@Override
	public void deleteLazyByIds(String ids) {
		this.getSession().createQuery("delete from NewsAlbumLazy nnl where nnl.id in ("+ids+")").executeUpdate();
	}

	@Override
	public void updateLazy(NewsAlbumLazy newsNewsLazy) {
		this.getHibernateTemplate().update(newsNewsLazy);
	}
	@Override
	public void updateLazyCoverPathById(Long id, String coverPath) {
		this.getSession().createQuery("update NewsAlbumLazy nnl set nnl.coverPath = '"+coverPath+"' where nnl.id = "  +id).executeUpdate();
	}

	@Override
	public void updateStatusByIds(String ids, String status) {
		this.getSession().createQuery("update NewsAlbumLazy nnl set nnl.status = '"+status+"' where nnl.id in ("  +ids+")").executeUpdate();
	}


	@Override
	public NewsAlbumLazy findLazyDetailById(Long id) {
		return (NewsAlbumLazy) getSession().get(NewsAlbumLazy.class, id);
	}

	@Override
	public long findLazyTotalByPid(Long deptId) {
		return (Long)this.getSession().createQuery("select count(*) from NewsAlbumLazy where deptId = '" + deptId + "'").uniqueResult();
	}

	@Override
	public NewsAlbumDelete findNewsAlbumById(long id) {
		return (NewsAlbumDelete) getSession().get(NewsAlbumDelete.class, id);
	}

	@Override
	public void deleteNewsAlbum(NewsAlbumDelete newsAlbum) {
		this.getHibernateTemplate().delete(newsAlbum);
	}
}