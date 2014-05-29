package com.dao;

import java.util.List;

import com.model.course.CourseCourseDelete;
import com.model.course.CourseCourseGrid;
import com.model.course.CourseCourseLazy;

public interface CourseCourseDAO {


	List<CourseCourseGrid> findCourseCourseGrids(int i, int rows,
			String sort, String order);

	long findTotal();

	void saveCourseCourseGrid(CourseCourseGrid courseCourseGrid);

	CourseCourseLazy findCourseCourseLazyById(Long id);

	void deleteCourseCourseLazy(CourseCourseLazy courseCourseLazy);

	CourseCourseGrid findCourseCourseGridById(
			Long id);

	void mergeCourseCourseLazy(CourseCourseLazy courseCourseLazy);

	void mergeCourseCourseGrid(CourseCourseGrid courseCourseNew);

	void saveCourseCourseLazy(CourseCourseLazy courseCourseLazy);

	void updateCourseCourseLazyStatusByIds(String ids, String status);

	CourseCourseDelete findCourseCourseById(long id);

	void deleteCourseCourse(CourseCourseDelete courseCourse);

	void updateCourseCourseCover(Long id, String coverPath);

}