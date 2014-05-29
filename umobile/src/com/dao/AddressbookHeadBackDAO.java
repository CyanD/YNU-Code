package com.dao;

import java.util.List;

import com.model.addressbook.AddressbookHeadBack;

public interface AddressbookHeadBackDAO {

	public abstract void save(AddressbookHeadBack transientInstance);

	public abstract void delete(AddressbookHeadBack persistentInstance);

	public abstract AddressbookHeadBack findById(java.lang.Long id);

	public abstract List<AddressbookHeadBack> findByExample(
			AddressbookHeadBack instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<AddressbookHeadBack> findByPath(Object path);

	public abstract List findAll();

	public abstract AddressbookHeadBack merge(
			AddressbookHeadBack detachedInstance);

	public abstract void attachDirty(AddressbookHeadBack instance);

	public abstract void attachClean(AddressbookHeadBack instance);

	public abstract void deleteByPath(String headpath);

	void saveByPath(String path);

}