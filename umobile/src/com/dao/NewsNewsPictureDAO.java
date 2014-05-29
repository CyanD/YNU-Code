package com.dao;

import java.util.List;

import com.model.news.picture.NewsNewsPicture;
import com.model.news.picture.NewsNewsPictureLazy;

public interface NewsNewsPictureDAO {

	public abstract void save(NewsNewsPicture transientInstance);

	public abstract void delete(NewsNewsPicture persistentInstance);

	public abstract NewsNewsPicture findById(java.lang.Long id);

	public abstract List<NewsNewsPicture> findByExample(NewsNewsPicture instance);

	public abstract List findByProperty(String propertyName, Object value);


	public abstract List<NewsNewsPicture> findByPath(Object path);

	public abstract List<NewsNewsPicture> findByIsCover(Object isCover);

	public abstract List<NewsNewsPicture> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract NewsNewsPicture merge(NewsNewsPicture detachedInstance);

	public abstract void attachDirty(NewsNewsPicture instance);

	public abstract void attachClean(NewsNewsPicture instance);

	public abstract List<?> findLazyByPage(long pid, int i, int rows,
			String sort, String order);

	public abstract long findLazyTotal(long pid);

	public abstract void saveLazy(NewsNewsPictureLazy newsNewsPictureLazy);

	public abstract void deleteByIds(String ids);

	public abstract void updateLazy(NewsNewsPictureLazy newsNewsPictureLazy);

	List<NewsNewsPicture> findByDescription(Object description);

	NewsNewsPictureLazy findLazyById(Long id);

	void deleteLazy(NewsNewsPictureLazy persistentInstance);

	public abstract NewsNewsPictureLazy findLazyCoverByPid(Long pid);

	Long findLazyCoverTotalByPid(Long pid);

	public abstract void deleteLazyById(Long id);

	public abstract void updateCoverByPid(Long pid);

	List<NewsNewsPicture> findByOrder(Object order);

	public abstract void updateDescriptionById(Long id, String description);

	public abstract List<NewsNewsPictureLazy> findAllByPid(Long pid);

	public abstract void updateDescriptionAndOrdersById(Long id,
			Integer orders, String description);

	List<NewsNewsPictureLazy> findLazyByPid(Long pid, String sort, String order);

}