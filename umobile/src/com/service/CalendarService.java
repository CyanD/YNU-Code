package com.service;

import java.util.List;

import com.model.calendar.CalendarCategory;
import com.model.calendar.SchoolCalendarLazy;
import com.model.calendar.SchoolCalendarSubLazy;

public interface CalendarService {

	long findCalendarCategoryTotal();
	List<?> findCalendarCategoryByPage(int i, int rows, String sort, String order);
	void saveCalendarCategory(CalendarCategory calendarCategory);
	void deleteCalendarCategoryByIds(String ids);
	void updateCalendarCategory(CalendarCategory calendarCategory);
	
	
	
	List<?> findschoolCalendarByPage(long cid,int i, int rows, String sort, String order);
	long findschoolCalendarTotal(long cid);
	void saveschoolCalendarLazy(SchoolCalendarLazy schoolCalendarLazy);
	void deleteschoolCalendarByIds(String ids);
	void updateschoolCalendarLazy(SchoolCalendarLazy schoolCalendarLazy);
	
	
	List<?> findschoolCalendarSubByPage(long pid,int i, int rows,String sort, String order);
	long findschoolCalendarSubTotal(long pid);
	void saveschoolCalendarSubLazy(SchoolCalendarSubLazy schoolCalendarSub);
	void deleteschoolCalendarSubByIds(String ids);
	void updateschoolCalendarSubLazy(SchoolCalendarSubLazy schoolCalendarSubLazy);

}
