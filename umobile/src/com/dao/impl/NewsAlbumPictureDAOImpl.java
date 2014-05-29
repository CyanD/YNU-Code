package com.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.NewsAlbumPictureDAO;
import com.model.news.album.NewsAlbumPictureLazy;

/**
 * A data access object (DAO) providing persistence and search support for
 * NewsAlbumPicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.news.album.NewsAlbumPictureLazyLazy
 * @author MyEclipse Persistence Tools
 */
@Component("newsAlbumPictureDAO")
public class NewsAlbumPictureDAOImpl extends TopDAO implements NewsAlbumPictureDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NewsAlbumPictureDAOImpl.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String ORDERS = "orders";
	public static final String PATH = "path";
	public static final String IS_COVER = "isCover";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#save(com.model.news.album.NewsAlbumPictureLazy)
	 */
	@Override
	public void save(NewsAlbumPictureLazy transientInstance) {
		log.debug("saving NewsAlbumPicture instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#delete(com.model.news.album.NewsAlbumPictureLazy)
	 */
	@Override
	public void delete(NewsAlbumPictureLazy persistentInstance) {
		log.debug("deleting NewsAlbumPicture instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#findById(java.lang.Long)
	 */
	@Override
	public NewsAlbumPictureLazy findById(java.lang.Long id) {
		log.debug("getting NewsAlbumPicture instance with id: " + id);
		try {
			NewsAlbumPictureLazy instance = (NewsAlbumPictureLazy) getSession().get(
					"com.model.news.album.NewsAlbumPictureLazy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#findByExample(com.model.news.album.NewsAlbumPictureLazy)
	 */
	@Override
	public List<NewsAlbumPictureLazy> findByExample(NewsAlbumPictureLazy instance) {
		log.debug("finding NewsAlbumPicture instance by example");
		try {
			List<NewsAlbumPictureLazy> results = (List<NewsAlbumPictureLazy>) getSession()
					.createCriteria("com.model.news.album.NewsAlbumPictureLazy")
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
	 * @see com.dao.impl.NewsAlbumPictureDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding NewsAlbumPicture instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NewsAlbumPicture as model where model."
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
	 * @see com.dao.impl.NewsAlbumPictureDAO#findByDescription(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumPictureLazy> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#findByOrders(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumPictureLazy> findByOrders(Object orders) {
		return findByProperty(ORDERS, orders);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#findByPath(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumPictureLazy> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#findByIsCover(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumPictureLazy> findByIsCover(Object isCover) {
		return findByProperty(IS_COVER, isCover);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<NewsAlbumPictureLazy> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all NewsAlbumPicture instances");
		try {
			String queryString = "from NewsAlbumPicture";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#merge(com.model.news.album.NewsAlbumPictureLazy)
	 */
	@Override
	public NewsAlbumPictureLazy merge(NewsAlbumPictureLazy detachedInstance) {
		log.debug("merging NewsAlbumPicture instance");
		try {
			NewsAlbumPictureLazy result = (NewsAlbumPictureLazy) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#attachDirty(com.model.news.album.NewsAlbumPictureLazy)
	 */
	@Override
	public void attachDirty(NewsAlbumPictureLazy instance) {
		log.debug("attaching dirty NewsAlbumPicture instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsAlbumPictureDAO#attachClean(com.model.news.album.NewsAlbumPictureLazy)
	 */
	@Override
	public void attachClean(NewsAlbumPictureLazy instance) {
		log.debug("attaching clean NewsAlbumPicture instance");
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
	public List<NewsAlbumPictureLazy> findLazyByPage(Long pid, int i, int rows,
			String sort, String order) {
		return this.getSession().createQuery("from NewsAlbumPictureLazy  where pid = "+pid+" order by " +sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public long findTotalByPid(Long pid) {
		return (Long)this.getSession().createQuery("select count(*) from NewsAlbumPictureLazy  where pid = "+pid).uniqueResult();
	}
	@Override
	public List<NewsAlbumPictureLazy> findLazyByPid(Long pid,String sort,String order) {
		return (List<NewsAlbumPictureLazy>)this.getSession().createQuery("from NewsAlbumPictureLazy nnpl where nnpl.pid = "+pid+" order by " +sort+" "+order).list();
	}
	@Override
	public long findLazyTotal(long pid) {
		return (Long)this.getSession().createQuery("select count(*) from NewsAlbumPictureLazy nnpl where nnpl.pid = "+pid).uniqueResult();
	}

	@Override
	public void saveLazy(NewsAlbumPictureLazy newsNewsPictureLazy) {
		this.getHibernateTemplate().save(newsNewsPictureLazy);
	}

	@Override
	public void deleteByIds(String ids) {
		this.getSession().createQuery("delete from NewsAlbumPictureLazy nnpl where nnpl.id in ("+ids+")").executeUpdate();
	}

	@Override
	public void updateLazy(NewsAlbumPictureLazy newsNewsPictureLazy) {
		this.getHibernateTemplate().update(newsNewsPictureLazy);
	}
	@Override
	public NewsAlbumPictureLazy findLazyById(Long id){
		log.debug("getting NewsAlbumPicture instance with id: " + id);
		try {
			NewsAlbumPictureLazy instance = (NewsAlbumPictureLazy) getSession().get(
					"com.model.news.album.NewsAlbumPictureLazy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	@Override
	public void deleteLazy(NewsAlbumPictureLazy persistentInstance) {
		log.debug("deleting NewsAlbumPicture instance");
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
	public NewsAlbumPictureLazy findLazyCoverByPid(Long pid) {
		return (NewsAlbumPictureLazy)this.getSession().createQuery("from NewsAlbumPictureLazy nnpl where nnpl.pid = "+pid+" and nnpl.isCover = '是' ").uniqueResult();
	}
	@Override
	public Long findLazyCoverTotalByPid(Long pid) {
		return (Long)this.getSession().createQuery("select count(*) from NewsAlbumPictureLazy nnpl where nnpl.pid = "+pid+" and nnpl.isCover = '是' ").uniqueResult();
	}

	@Override
	public void deleteLazyById(Long id) {
		this.getSession().createQuery("delete from NewsAlbumPictureLazy nnpl where nnpl.id = "+id);
	}

	@Override
	public void updateCoverByPid(Long pid) {
		this.getSession().createQuery("update NewsAlbumPictureLazy nnpl set nnpl.isCover = '否' where nnpl.pid = "+pid+" and nnpl.isCover = '是'").executeUpdate();
	}

	@Override
	public void updateDescriptionById(Long id,String description) {
		this.getSession().createQuery("update NewsAlbumPictureLazy nnpl set nnpl.description = '"+description+"' where nnpl.id = "+id).executeUpdate();
	}

	@Override
	public List<NewsAlbumPictureLazy> findAllByPid(Long pid) {
		return this.getSession().createQuery("from NewsAlbumPictureLazy where pid = "+pid).list();
	}

	@Override
	public void updateDescriptionAndOrdersById(Long id, Integer orders,
			String description) {
		this.getSession().createQuery("update NewsAlbumPictureLazy set orders = "+orders+" ,description = '"+description+"' where id = "+id).executeUpdate();
	}

}