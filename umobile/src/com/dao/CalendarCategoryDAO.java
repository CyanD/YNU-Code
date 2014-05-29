package com.dao;

import java.util.List;

import com.model.calendar.CalendarCategory;
import com.model.calendar.Versions;

public interface CalendarCategoryDAO {
	public abstract void save(CalendarCategory transientInstance);

	public abstract void delete(CalendarCategory persistentInstance);

	public abstract CalendarCategory findById(java.lang.Long id);

	public abstract List<CalendarCategory> findByExample(CalendarCategory instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<CalendarCategory> findByTitle(Object title);

	public abstract List<CalendarCategory> findByBeginDate(Object beginDate);
	
	public abstract List<CalendarCategory> findByWeeknum(Object weeknum);
	
	public abstract List<CalendarCategory> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract CalendarCategory merge(CalendarCategory detachedInstance);

	public abstract void attachDirty(CalendarCategory instance);

	public abstract void attachClean(CalendarCategory instance);

	public abstract long findTotal();

	public abstract List<?> findByPage(int i, int rows, String sort,
			String order);

	public abstract void deleteByIds(String ids);

	void update(CalendarCategory CalendarCategory);
	
	
	
	
}
