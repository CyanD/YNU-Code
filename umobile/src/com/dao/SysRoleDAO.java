package com.dao;

import java.util.List;

import com.model.sys.SysRole;
import com.model.sys.SysRoleCombogrid;
import com.model.sys.SysRoleLazy;

public interface SysRoleDAO {

	public abstract void save(SysRole transientInstance);

	public abstract void delete(SysRole persistentInstance);

	public abstract SysRole findById(java.lang.Long id);

	public abstract List<SysRole> findByExample(SysRole instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<SysRole> findByName(Object name);

	public abstract List<SysRole> findByDescription(Object description);

	public abstract List<SysRole> findBySys(Object sys);

	public abstract List<SysRole> findByNews(Object news);

	public abstract List<SysRole> findByAnniversary(Object anniversary);

	public abstract List<SysRole> findByBus(Object bus);

	public abstract List<SysRole> findByMap(Object map);

	public abstract List<SysRole> findByCourse(Object course);

	public abstract List<SysRole> findByAddressBook(Object addressBook);

	public abstract List<SysRole> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract SysRole merge(SysRole detachedInstance);

	public abstract void attachDirty(SysRole instance);

	public abstract void attachClean(SysRole instance);

	public abstract long findLazyTotal();

	public abstract List<SysRoleLazy> findAllLazy();

	public abstract List<SysRoleLazy> findLazyByPage(int i, int rows,
			String sort, String order);

	public abstract void saveLazy(SysRoleLazy modelLazy);

	public abstract void updateLazy(SysRoleLazy modelLazy);

	public abstract SysRoleLazy findLazyById(Long id);

	public abstract void deleteLazy(SysRoleLazy modelLazy);

	List<SysRoleCombogrid> findAllSysRoleCombogrid();

}