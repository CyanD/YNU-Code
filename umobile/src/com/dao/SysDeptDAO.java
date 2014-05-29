package com.dao;

import java.util.List;

import com.model.sys.SysDept;
import com.model.sys.SysDeptTreeGet;
import com.model.sys.SysDeptTreeGridGet;
import com.model.sys.SysDeptTreeGridSet;

public interface SysDeptDAO {

	public abstract void save(SysDept transientInstance);

	public abstract void delete(SysDept persistentInstance);

	public abstract SysDept findById(java.lang.Long id);

	public abstract List<SysDept> findByExample(SysDept instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<SysDept> findByName(Object name);

	public abstract List<SysDept> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract SysDept merge(SysDept detachedInstance);

	public abstract void attachDirty(SysDept instance);

	public abstract void attachClean(SysDept instance);

	long findTreeGridGetTotal();
	List<SysDeptTreeGridGet> findAllTreeGridGet();
	List<SysDeptTreeGridGet> findTreeGridGetByPid(Long pid);
	void deleteTreeGridSetById(Long id);

	void updateTreeGridSet(SysDeptTreeGridSet modelLazy);

	public abstract List<SysDeptTreeGet> findTreeGet();

	public abstract void saveTreeGridSet(SysDeptTreeGridSet modelLazy);

}