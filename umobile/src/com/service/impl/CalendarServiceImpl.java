package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.CalendarCategoryDAO;
import com.dao.SchoolCalendarDAO;
import com.dao.SchoolCalendarSubDAO;
import com.dao.VersionsDAO;
import com.model.calendar.CalendarCategory;
import com.model.calendar.SchoolCalendarLazy;
import com.model.calendar.SchoolCalendarSubLazy;
import com.model.calendar.Versions;
import com.service.CalendarService;


@Component("calendarService")
public class CalendarServiceImpl implements CalendarService{
	private SchoolCalendarDAO schoolCalendarDAO;
	private SchoolCalendarSubDAO schoolCalendarSubDAO;
    private CalendarCategoryDAO calendarCategoryDAO;
   
    
	public CalendarCategoryDAO getCalendarCategoryDAO() {
		return calendarCategoryDAO;
	}

	@Resource
	public void setCalendarCategoryDAO(CalendarCategoryDAO calendarCategoryDAO) {
		this.calendarCategoryDAO = calendarCategoryDAO;
	}

	public SchoolCalendarDAO getSchoolCalendarDAO() {
		return schoolCalendarDAO;
	}
	
	@Resource
	public void setSchoolCalendarDAO(SchoolCalendarDAO schoolCalendarDAO) {
		this.schoolCalendarDAO = schoolCalendarDAO;
	}

	public SchoolCalendarSubDAO getSchoolCalendarSubDAO() {
		return schoolCalendarSubDAO;
	}
	@Resource
	public void setSchoolCalendarSubDAO(SchoolCalendarSubDAO schoolCalendarSubDAO) {
		this.schoolCalendarSubDAO = schoolCalendarSubDAO;
	}

	
	@Override
	public long findCalendarCategoryTotal() {
		return calendarCategoryDAO.findTotal();
	}

	@Override
	public List<?> findCalendarCategoryByPage(int i, int rows, String sort,
			String order) {
		return calendarCategoryDAO.findByPage(i,rows, sort,order);
	}
	@Override
	public void saveCalendarCategory(CalendarCategory calendarCategory) {
		this.calendarCategoryDAO.save(calendarCategory);
		
	}
	@Override
	public void deleteCalendarCategoryByIds(String ids) {
		this.calendarCategoryDAO.deleteByIds(ids);
	}
	@Override
	public void updateCalendarCategory(CalendarCategory calendarCategory) {
		this.calendarCategoryDAO.update(calendarCategory);
	}
	
	
	
	
	
	@Override
	public List<?> findschoolCalendarByPage(long cid, int i,int rows, String sort,
			String order) {
		return schoolCalendarDAO.findByPage(cid, i,rows, sort,order);
	}
	@Override
	public void saveschoolCalendarLazy(SchoolCalendarLazy schoolCalendarLazy) {
		this.schoolCalendarDAO.saveLazy(schoolCalendarLazy);
		
	}
	@Override
	public void deleteschoolCalendarByIds(String ids) {
		this.schoolCalendarDAO.deleteByIds(ids);
	}
	@Override
	public void updateschoolCalendarLazy(SchoolCalendarLazy schoolCalendarLazy) {
		this.schoolCalendarDAO.updateLazy(schoolCalendarLazy);
	}

	@Override
	public long findschoolCalendarTotal(long cid) {
		return this.schoolCalendarDAO.findTotal(cid);
	}
	
	
	@Override
	public List<?> findschoolCalendarSubByPage(long pid, int i, int rows,
			String sort, String order) {
		
		return this.schoolCalendarSubDAO.findByPage(pid,i,rows, sort,order);
	}
	@Override
	public long findschoolCalendarSubTotal(long pid) {
		
		return this.schoolCalendarSubDAO.findTotal(pid);
	}
	@Override
	public void saveschoolCalendarSubLazy(SchoolCalendarSubLazy schoolCalendarSubLazy) {
		this.schoolCalendarSubDAO.saveLazy(schoolCalendarSubLazy);
	}
	@Override
	public void deleteschoolCalendarSubByIds(String ids) {
		this.schoolCalendarSubDAO.deleteByIds(ids);
	}
	@Override
	public void updateschoolCalendarSubLazy(SchoolCalendarSubLazy schoolCalendarSubLazy) {
		this.schoolCalendarSubDAO.updateLazy(schoolCalendarSubLazy);
	}

	

}
