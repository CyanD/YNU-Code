package com.dao;

import java.util.List;

import com.model.calendar.SchoolCalendar;
import com.model.calendar.SchoolCalendarLazy;




public interface SchoolCalendarDAO {

	public abstract void save(SchoolCalendar transientInstance);

	public abstract void delete(SchoolCalendar persistentInstance);

	public abstract SchoolCalendar findById(java.lang.Long id);

	public abstract List<SchoolCalendar> findByExample(SchoolCalendar instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<SchoolCalendar> findByActtime(Object acttime);

	public abstract List<SchoolCalendar> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract SchoolCalendar merge(SchoolCalendar detachedInstance);

	public abstract void attachDirty(SchoolCalendar instance);

	public abstract void attachClean(SchoolCalendar instance);

	public abstract long findTotal();

	public abstract List<?> findByPage(long cid,int i, int rows, String sort,
			String order);

	public abstract void deleteByIds(String ids);

	void update(SchoolCalendar SchoolCalendar);
	
	public abstract void saveLazy(SchoolCalendarLazy schoolCalendarLazy);
	
	public abstract void updateLazy(SchoolCalendarLazy schoolCalendarLazy);
	
	public abstract long findTotal(long cid);

}