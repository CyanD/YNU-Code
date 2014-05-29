package com.dao;

import java.util.List;

import com.model.news.news.NewsNewsCategory;
import com.model.news.news.NewsNewsCategoryTreeGet;
import com.model.news.news.NewsNewsCategoryTreeGridGet;
import com.model.news.news.NewsNewsCategoryTreeGridSet;

public interface NewsNewsCategoryDAO {

	public abstract void save(NewsNewsCategory transientInstance);

	public abstract void delete(NewsNewsCategory persistentInstance);

	public abstract NewsNewsCategory findById(java.lang.Long id);

	public abstract List<NewsNewsCategory> findByExample(
			NewsNewsCategory instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<NewsNewsCategory> findByName(Object name);

	public abstract List<NewsNewsCategory> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract NewsNewsCategory merge(NewsNewsCategory detachedInstance);

	public abstract void attachDirty(NewsNewsCategory instance);

	public abstract void attachClean(NewsNewsCategory instance);

	/* (non-Javadoc)
	 * @see com.dao.impl.NewsNewsCategoryDAO#findLazyTotal()
	 */

	public abstract long findTreeGridGetTotal();

	public abstract List<NewsNewsCategoryTreeGridGet> findAllTreeGridGet();

	public abstract List<NewsNewsCategoryTreeGridGet> findTreeGridGetByPid(
			Long pid);

	public abstract void saveTreeGridSet(NewsNewsCategoryTreeGridSet modelLazy);

	public abstract void deleteTreeGridSetById(Long id);

	public abstract void updateTreeGridSet(NewsNewsCategoryTreeGridSet modelLazy);

	@SuppressWarnings("unchecked")
	public abstract List<NewsNewsCategoryTreeGet> findTreeGet();

}