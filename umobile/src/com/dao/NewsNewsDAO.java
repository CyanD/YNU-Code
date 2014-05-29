package com.dao;

import java.util.List;

import com.model.news.news.NewsNews;
import com.model.news.news.NewsNewsGridGet;
import com.model.news.news.NewsNewsLazy;

public interface NewsNewsDAO {

	public abstract void save(NewsNews transientInstance);

	public abstract void delete(NewsNews persistentInstance);

	public abstract NewsNews findById(java.lang.Long id);

	public abstract List<NewsNews> findByExample(NewsNews instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<NewsNews> findByTitle(Object title);

	public abstract List<NewsNews> findByDept(Object dept);

	public abstract List<NewsNews> findByAuthor(Object author);

	public abstract List<NewsNews> findByContent(Object content);

	public abstract List<NewsNews> findByVisitors(Object visitors);

	public abstract List<NewsNews> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract NewsNews merge(NewsNews detachedInstance);

	public abstract void attachDirty(NewsNews instance);

	public abstract void attachClean(NewsNews instance);

	public abstract long findLazyTotal();

	public abstract List<NewsNewsGridGet> findLazyByPage(int i, int rows, String sort,
			String order, Long deptId);

	public abstract void saveLazy(NewsNewsLazy newsNewsLazy);

	public abstract void deleteLazyByIds(String ids);

	public abstract void updateLazy(NewsNewsLazy newsNewsLazy);

	List<NewsNews> findByAbstracts(Object abstracts);

	List<NewsNews> findByStatus(Object status);

	public abstract void updateLazyCoverPathById(Long id, String coverPath);

	public abstract void updateStatusByIds(String ids, String status);

	public abstract List<?> findAfterLazyById(Long id, int rows);

	public abstract NewsNewsLazy findLazyDetailById(Long id);

	List<NewsNews> findByCategoryName(Object categoryName);

	List<NewsNews> findByKeywords(Object keywords);

	List<NewsNews> findByCoverPath(Object coverPath);

	public abstract long findLazyTotalByPid(Long deptId);


}