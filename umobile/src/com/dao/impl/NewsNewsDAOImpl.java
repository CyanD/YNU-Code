package com.dao.impl;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.NewsNewsDAO;
import com.model.news.news.NewsNews;
import com.model.news.news.NewsNewsGridGet;
import com.model.news.news.NewsNewsLazy;
@Component("newsNewsDAO")
public class NewsNewsDAOImpl extends TopDAO implements NewsNewsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NewsNewsDAOImpl.class);
	// property constants
	public static final String CATEGORY_NAME = "categoryName";
	public static final String TITLE = "title";
	public static final String DEPT = "dept";
	public static final String AUTHOR = "author";
	public static final String COVER_PATH = "coverPath";
	public static final String KEYWORDS = "keywords";
	public static final String ABSTRACTS = "abstracts";
	public static final String CONTENT = "content";
	public static final String STATUS = "status";
	public static final String VISITORS = "visitors";
	public static final String PUBLISHER = "publisher";

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#save(com.model.NewsNews)
	 */
	@Override
	public void save(NewsNews transientInstance) {
		log.debug("saving NewsNews instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#delete(com.model.NewsNews)
	 */
	@Override
	public void delete(NewsNews persistentInstance) {
		log.debug("deleting NewsNews instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public NewsNews findById(java.lang.Long id) {
		log.debug("getting NewsNews instance with id: " + id);
		try {
			NewsNews instance = (NewsNews) getSession().get(
					NewsNews.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#findByExample(com.model.NewsNews)
	 */
	@Override
	public List<NewsNews> findByExample(NewsNews instance) {
		log.debug("finding NewsNews instance by example");
		try {
			List<NewsNews> results = (List<NewsNews>) getSession()
					.createCriteria("com.dao.impl.NewsNews")
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
	 * @see com.dao.impl.NewsNewsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding NewsNews instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NewsNews as model where model."
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
	 * @see com.dao.impl.NewsNewsDAO#findByTitle(java.lang.Object)
	 */
	@Override
	public List<NewsNews> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#findByDept(java.lang.Object)
	 */
	@Override
	public List<NewsNews> findByDept(Object dept) {
		return findByProperty(DEPT, dept);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#findByAuthor(java.lang.Object)
	 */
	@Override
	public List<NewsNews> findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}
	@Override
	public List<NewsNews> findByKeywords(Object keywords) {
		return findByProperty(KEYWORDS, keywords);
	}
	@Override
	public List<NewsNews> findByCoverPath(Object coverPath) {
		return findByProperty(COVER_PATH, coverPath);
	}
	@Override
	public List<NewsNews> findByAbstracts(Object abstracts) {
		return findByProperty(ABSTRACTS, abstracts);
	}
	@Override
	public List<NewsNews> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}
	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#findByContent(java.lang.Object)
	 */
	@Override
	public List<NewsNews> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}
	
	@Override
	public List<NewsNews> findByCategoryName(Object categoryName) {
		return findByProperty(CATEGORY_NAME, categoryName);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#findByVisitors(java.lang.Object)
	 */
	@Override
	public List<NewsNews> findByVisitors(Object visitors) {
		return findByProperty(VISITORS, visitors);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#findByPublisher(java.lang.Object)
	 */
	@Override
	public List<NewsNews> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all NewsNews instances");
		try {
			String queryString = "from NewsNews";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#merge(com.model.NewsNews)
	 */
	@Override
	public NewsNews merge(NewsNews detachedInstance) {
		log.debug("merging NewsNews instance");
		try {
			NewsNews result = (NewsNews) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#attachDirty(com.model.NewsNews)
	 */
	@Override
	public void attachDirty(NewsNews instance) {
		log.debug("attaching dirty NewsNews instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsDAO#attachClean(com.model.NewsNews)
	 */
	@Override
	public void attachClean(NewsNews instance) {
		log.debug("attaching clean NewsNews instance");
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
		
		return (Long)this.getSession().createQuery("select count(*) from NewsNewsLazy").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsNewsGridGet> findLazyByPage(int i, int rows, String sort, String order,Long deptId) {
		return this.getSession().createQuery("from NewsNewsGridGet where deptId = "+deptId+" order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}

	@Override
	public void saveLazy(NewsNewsLazy newsNewsLazy) {
		this.getHibernateTemplate().save(newsNewsLazy);
	}

	@Override
	public void deleteLazyByIds(String ids) {
		this.getSession().createQuery("delete from NewsNewsLazy nnl where nnl.id in ("+ids+")").executeUpdate();
	}

	@Override
	public void updateLazy(NewsNewsLazy newsNewsLazy) {
		this.getHibernateTemplate().merge(newsNewsLazy);
	}
	@Override
	public void updateLazyCoverPathById(Long id, String coverPath) {
		this.getSession().createQuery("update NewsNewsLazy nnl set nnl.coverPath = '"+coverPath+"' where nnl.id = "  +id).executeUpdate();
	}

	@Override
	public void updateStatusByIds(String ids, String status) {
		this.getSession().createQuery("update NewsNewsLazy nnl set nnl.status = '"+status+"' where nnl.id in ("  +ids+")").executeUpdate();
	}

	@Override
	public List<?> findAfterLazyById(Long id, int rows) {
		return this.getSession().createQuery("from NewsNewsLazy nnl where nnl.id > "+id+" order by id desc").setMaxResults(rows).list();
	}

	@Override
	public NewsNewsLazy findLazyDetailById(Long id) {
		return (NewsNewsLazy) getSession().get(NewsNewsLazy.class, id);
	}

	@Override
	public long findLazyTotalByPid(Long deptId) {
		return (Long)this.getSession().createQuery("select count(*) from NewsNewsLazy where deptId = '" + deptId + "'").uniqueResult();
	}
}