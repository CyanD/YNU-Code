package com.dao;

import java.util.List;
import com.model.calendar.SchoolCalendarSub;
import com.model.calendar.SchoolCalendarSubLazy;


public interface SchoolCalendarSubDAO {

	public abstract void save(SchoolCalendarSub transientInstance);

	public abstract void delete(SchoolCalendarSub persistentInstance);

	public abstract SchoolCalendarSub findById(java.lang.Long id);

	public abstract List<SchoolCalendarSub> findByExample(SchoolCalendarSub instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<SchoolCalendarSub> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract SchoolCalendarSub merge(SchoolCalendarSub detachedInstance);

	public abstract void attachDirty(SchoolCalendarSub instance);

	public abstract void attachClean(SchoolCalendarSub instance);

	public abstract List<?> findByPage(long rid, int i, int rows, String sort,
			String order);

	public abstract long findTotal(long rid);

	public abstract void saveLazy(SchoolCalendarSubLazy schoolCalendarSubLazy);

	public abstract void deleteByIds(String ids);

	public abstract void updateLazy(SchoolCalendarSubLazy schoolCalendarSubLazy);

}