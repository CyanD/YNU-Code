package com.dao;

import java.util.List;

import com.model.sys.SysVersion;

public interface SysVersionDAO {

	public abstract void save(SysVersion transientInstance);

	public abstract void delete(SysVersion persistentInstance);

	public abstract SysVersion findById(java.lang.Long id);

	public abstract List<SysVersion> findByExample(SysVersion instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<SysVersion> findByVersion(Object version);

	public abstract List<SysVersion> findByName(Object name);

	public abstract List findAll();

	public abstract SysVersion merge(SysVersion detachedInstance);

	public abstract void attachDirty(SysVersion instance);

	public abstract void attachClean(SysVersion instance);

}