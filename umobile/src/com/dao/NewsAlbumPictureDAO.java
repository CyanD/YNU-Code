package com.dao;

import java.util.List;

import com.model.news.album.NewsAlbumPictureLazy;
import com.model.news.picture.NewsNewsPictureLazy;

public interface NewsAlbumPictureDAO {

	public abstract void save(NewsAlbumPictureLazy transientInstance);

	public abstract void delete(NewsAlbumPictureLazy persistentInstance);

	public abstract NewsAlbumPictureLazy findById(java.lang.Long id);

	public abstract List<NewsAlbumPictureLazy> findByExample(
			NewsAlbumPictureLazy instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<NewsAlbumPictureLazy> findByDescription(
			Object description);

	public abstract List<NewsAlbumPictureLazy> findByOrders(Object orders);

	public abstract List<NewsAlbumPictureLazy> findByPath(Object path);

	public abstract List<NewsAlbumPictureLazy> findByIsCover(Object isCover);

	public abstract List<NewsAlbumPictureLazy> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract NewsAlbumPictureLazy merge(
			NewsAlbumPictureLazy detachedInstance);

	public abstract void attachDirty(NewsAlbumPictureLazy instance);

	public abstract void attachClean(NewsAlbumPictureLazy instance);

	public abstract List<NewsAlbumPictureLazy> findLazyByPage(Long pid, int i,
			int rows, String sort, String order);

	public abstract long findTotalByPid(Long pid);


	List<NewsAlbumPictureLazy> findLazyByPid(Long pid, String sort, String order);

	long findLazyTotal(long pid);

	void saveLazy(NewsAlbumPictureLazy newsNewsPictureLazy);

	void deleteByIds(String ids);

	void updateLazy(NewsAlbumPictureLazy newsNewsPictureLazy);

	NewsAlbumPictureLazy findLazyById(Long id);

	void deleteLazy(NewsAlbumPictureLazy persistentInstance);

	NewsAlbumPictureLazy findLazyCoverByPid(Long pid);

	Long findLazyCoverTotalByPid(Long pid);

	void deleteLazyById(Long id);

	void updateCoverByPid(Long pid);

	void updateDescriptionById(Long id, String description);

	List<NewsAlbumPictureLazy> findAllByPid(Long pid);

	void updateDescriptionAndOrdersById(Long id, Integer orders,
			String description);


}