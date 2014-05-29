package com.dao;

import java.util.List;

import com.model.news.album.NewsAlbumDelete;
import com.model.news.album.NewsAlbumGrid;
import com.model.news.album.NewsAlbumLazy;

public interface NewsAlbumDAO {

	public abstract void save(NewsAlbumLazy transientInstance);

	public abstract void delete(NewsAlbumLazy persistentInstance);

	public abstract NewsAlbumLazy findById(java.lang.Long id);

	public abstract List<NewsAlbumLazy> findByExample(NewsAlbumLazy instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<NewsAlbumLazy> findByTitle(Object title);

	public abstract List<NewsAlbumLazy> findByPhotographer(Object photographer);

	public abstract List<NewsAlbumLazy> findByKeywords(Object keywords);

	public abstract List<NewsAlbumLazy> findByCoverPath(Object coverPath);

	public abstract List<NewsAlbumLazy> findByDescription(Object description);

	public abstract List<NewsAlbumLazy> findByVisitors(Object visitors);

	public abstract List<NewsAlbumLazy> findByStatus(Object status);

	public abstract List<NewsAlbumLazy> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract NewsAlbumLazy merge(NewsAlbumLazy detachedInstance);

	public abstract void attachDirty(NewsAlbumLazy instance);

	public abstract void attachClean(NewsAlbumLazy instance);

	public abstract List<NewsAlbumGrid> findNewsAlbums(int i, int rows,
			String sort, String order);

	public abstract long findTotal();

	long findLazyTotal();

	void saveLazy(NewsAlbumLazy newsNewsLazy);

	void deleteLazyByIds(String ids);

	void updateLazy(NewsAlbumLazy newsNewsLazy);

	void updateLazyCoverPathById(Long id, String coverPath);

	void updateStatusByIds(String ids, String status);

	NewsAlbumLazy findLazyDetailById(Long id);

	long findLazyTotalByPid(Long deptId);

	public abstract NewsAlbumDelete findNewsAlbumById(long id);

	public abstract void deleteNewsAlbum(NewsAlbumDelete newsAlbum);


}