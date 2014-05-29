package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.CourseCourseDAO;
import com.model.course.CourseCourseDelete;
import com.model.course.CourseCourseGrid;
import com.model.course.CourseCourseLazy;

@Component("courseCourseDAO")
public class CourseCourseDAOImpl extends TopDAO implements CourseCourseDAO {
	@Override
	public List<CourseCourseGrid> findCourseCourseGrids(int i,
			int rows, String sort, String order) {
		return this.getSession().createQuery("from CourseCourseGrid order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();

	}

	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from CourseCourseGrid").uniqueResult();
	}

	@Override
	public void saveCourseCourseGrid(CourseCourseGrid courseCourseGrid) {
		this.getHibernateTemplate().save(courseCourseGrid);
	}

	@Override
	public CourseCourseLazy findCourseCourseLazyById(Long id) {
		return (CourseCourseLazy) this.getSession().get(CourseCourseLazy.class, id);
	}

	@Override
	public void deleteCourseCourseLazy(CourseCourseLazy courseCourseLazy) {
		this.getHibernateTemplate().delete(courseCourseLazy);
	}

	@Override
	public CourseCourseGrid findCourseCourseGridById(Long id) {
		return (CourseCourseGrid) this.getSession().get(CourseCourseGrid.class, id);
	}

	@Override
	public void mergeCourseCourseLazy(CourseCourseLazy courseCourseLazy) {
		this.getHibernateTemplate().merge(courseCourseLazy);
	}

	@Override
	public void mergeCourseCourseGrid(CourseCourseGrid courseCourseNew) {
		this.getHibernateTemplate().merge(courseCourseNew);
	}

	@Override
	public void saveCourseCourseLazy(CourseCourseLazy courseCourseLazy) {
		this.getHibernateTemplate().save(courseCourseLazy);
	}

	@Override
	public void updateCourseCourseLazyStatusByIds(String ids, String status) {
		this.getSession().createQuery("update CourseCourseLazy set status = '"+status+"' where id in ("+ids+")").executeUpdate();
	}

	@Override
	public CourseCourseDelete findCourseCourseById(long id) {
		return (CourseCourseDelete) this.getSession().get(CourseCourseDelete.class, id);
	}

	@Override
	public void deleteCourseCourse(CourseCourseDelete courseCourse) {
		this.getSession().delete(courseCourse);
	}

	@Override
	public void updateCourseCourseCover(Long id, String coverPath) {
		this.getSession().createQuery("update CourseCourseLazy set coverPath = '"+ coverPath+"' where id = "+id).executeUpdate();
	}

}