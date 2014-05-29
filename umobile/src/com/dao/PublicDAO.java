package com.dao;

import java.util.List;

public interface PublicDAO {

	public abstract List<?> findListByPage(int i, int rows, String sort,
			String order, String queryString);

	public abstract List<?> findList(String queryString);

	public abstract long findListTotal(String queryString);

	public abstract Object findObjectById(Long id, String object);

	public abstract void deleteObject(Object comContentLazy);

	public abstract void updateObject(Object object);

	public abstract void mergeObject(Object object);

	public abstract void update(String hql);

	Object findByProperty(String object, String propertyName, Object value);

	public abstract Object findObjectByIid(Long id,
			String object);

	public abstract void saveObject(Object object);

	Object findObject(String queryString);

	public abstract List<?> findCagegoryAndCount(String string);

	Object findAnyObject(String queryString);

	void executeUpdateBySQL(String sqlString);

}