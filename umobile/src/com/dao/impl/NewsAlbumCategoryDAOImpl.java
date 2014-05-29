package com.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dao.NewsAlbumCategoryDAO;
import com.model.news.album.NewsAlbumCategoryTree;
import com.model.news.album.NewsAlbumCategoryTreeGrid;

/**
 * A data access object (DAO) providing persistence and search support for
 * NewsAlbumCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.news.album.NewsAlbumCategory
 * @author MyEclipse Persistence Tools
 */
@Component("NewsAlbumCategoryDAO")
public class NewsAlbumCategoryDAOImpl extends TopDAO implements NewsAlbumCategoryDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NewsAlbumCategoryDAOImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PUBLISHER = "publisher";

	@Override
	public long findTreeGridTotal() {
    	return (Long)this.getSession().createQuery("select count(*) from NewsAlbumCategoryTreeGrid").uniqueResult();
    }

	
	@Override
	public List<NewsAlbumCategoryTreeGrid> findNewsAlbumCategoryTreeGrids() {
		
		return this.getSession().createQuery("from NewsAlbumCategoryTreeGrid").list();
	}

	@Override
	public List<NewsAlbumCategoryTreeGrid> findNewsAlbumCategoryTreeGridByPid(Long pid) {
		return this.getSession().createQuery("from NewsAlbumCategoryTreeGrid  where pid = "+pid).list();
	}

	
	@Override
	public void saveTreeGridSet(NewsAlbumCategoryTreeGrid modelLazy) {
		this.getHibernateTemplate().save(modelLazy);
	}

	
	@Override
	public void deleteNewsAlbumCategoryTreeGridById(Long id) {
		this.getSession().createQuery("delete from NewsAlbumCategoryTreeGrid  where id = "+id).executeUpdate();
	}

	@Override
	public void updateTreeGridSet(NewsAlbumCategoryTreeGrid modelLazy) {
		this.getHibernateTemplate().update(modelLazy);
	}

	@Override
	@SuppressWarnings("unchecked")
	
	public List<NewsAlbumCategoryTree> findNewsAlbumCategoryTree() {
		return this.getSession().createQuery("from NewsAlbumCategoryTree").list();
	}
}