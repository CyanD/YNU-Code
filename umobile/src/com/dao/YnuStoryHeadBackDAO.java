package com.dao;

import java.util.List;

import com.model.publicservice.YnuStoryHeadBack;

public interface YnuStoryHeadBackDAO {

	public abstract void save(YnuStoryHeadBack transientInstance);

	public abstract void delete(YnuStoryHeadBack persistentInstance);

	public abstract YnuStoryHeadBack findById(java.lang.Long id);

	public abstract List<YnuStoryHeadBack> findByExample(
			YnuStoryHeadBack instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<YnuStoryHeadBack> findByPath(Object path);

	public abstract List findAll();

	public abstract YnuStoryHeadBack merge(
			YnuStoryHeadBack detachedInstance);

	public abstract void attachDirty(YnuStoryHeadBack instance);

	public abstract void attachClean(YnuStoryHeadBack instance);

	public abstract void deleteByPath(String headpath);

	void saveByPath(String path);

}