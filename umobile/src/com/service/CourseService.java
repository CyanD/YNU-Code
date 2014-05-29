package com.service;

import com.model.course.CourseCourseGrid;
import com.model.course.CourseCourseLazy;
import com.model.course.CourseVideoLazy;
import com.vo.bean.CourseCategoryVO;
import com.vo.bean.CourseCourseVO;
import com.vo.bean.CourseVideoVO;

public interface CourseService {

	String findCourseCategoryTreeGrid(CourseCategoryVO vo);

	String saveCourseCategoryTreeGrid(CourseCategoryVO vo);

	String deleteCourseCategoryTreeGrid(CourseCategoryVO vo);

	String updateCourseCategoryTreeGrid(CourseCategoryVO vo);

	String findCourseCourseGrids(CourseCourseVO vo);

	String saveCourseCourseLazy(CourseCourseVO vo);

	String deleteCourseCourseLazy(CourseCourseVO vo);

	String updateCourseCourseLazy(CourseCourseVO vo);

	String deleteCourseCourseLazyByIds(CourseCourseVO vo) throws Exception;

	String findCourseVideoLazyByPage(CourseVideoVO vo);

	void saveCourseVideoLazy(CourseVideoVO vo);

	CourseVideoLazy findCourseVideoLazyById(long id);

	void deleteCourseVideoLazy(CourseVideoLazy courseVideoLazy);

	String updateCourseVideoLazy(CourseVideoVO vo);

	String updateCourseCourseLazyStatusByIds(CourseCourseVO vo);

	CourseCourseLazy findCourseCourseLazyDetailById(CourseCourseVO vo);

	CourseCourseGrid findCourseCourseGridDetailById(CourseCourseVO vo);

	CourseCourseLazy findCourseCourseLazyById(Long id);

	String updateCourseCourseCover(CourseCourseVO vo);



}