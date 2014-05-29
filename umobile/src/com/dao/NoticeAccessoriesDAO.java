package com.dao;

import java.util.List;

import com.model.notice.NoticeAccessoriesLazy;

public interface NoticeAccessoriesDAO {

	public abstract void save(NoticeAccessoriesLazy transientInstance);

	public abstract void delete(NoticeAccessoriesLazy persistentInstance);

	public abstract NoticeAccessoriesLazy findById(java.lang.Long id);

	public abstract List<NoticeAccessoriesLazy> findByExample(NoticeAccessoriesLazy instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<NoticeAccessoriesLazy> findByName(Object name);

	public abstract List<NoticeAccessoriesLazy> findByPath(Object path);

	public abstract List findAll();

	public abstract NoticeAccessoriesLazy merge(NoticeAccessoriesLazy detachedInstance);

	public abstract void attachDirty(NoticeAccessoriesLazy instance);

	public abstract void attachClean(NoticeAccessoriesLazy instance);

	List<NoticeAccessoriesLazy> findLazyByPage(Long pid, int i, int rows,
			String sort, String order);

	long findTotalByPid(Long pid);

	public abstract void updateNoticeAccessoriesLazyOrders(Long id, Integer orders);

}