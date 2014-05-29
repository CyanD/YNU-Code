package com.dao;

import java.util.List;

import com.model.course.CourseVideoLazy;

public interface CourseVideoDAO {

	public abstract void save(CourseVideoLazy transientInstance);

	public abstract void delete(CourseVideoLazy persistentInstance);

	public abstract CourseVideoLazy findById(java.lang.Long id);

	public abstract List<CourseVideoLazy> findByExample(CourseVideoLazy instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<CourseVideoLazy> findByName(Object name);

	public abstract List<CourseVideoLazy> findByPath(Object path);

	public abstract List findAll();

	public abstract CourseVideoLazy merge(CourseVideoLazy detachedInstance);

	public abstract void attachDirty(CourseVideoLazy instance);

	public abstract void attachClean(CourseVideoLazy instance);

	List<CourseVideoLazy> findLazyByPage(Long pid, int i, int rows,
			String sort, String order);

	long findTotalByPid(Long pid);

	public abstract void updateCourseVideoLazyOrders(Long id, Integer orders);

}