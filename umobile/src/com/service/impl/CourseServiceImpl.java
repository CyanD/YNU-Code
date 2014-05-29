package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.CourseCategoryDAO;
import com.dao.CourseCourseDAO;
import com.dao.CourseVideoDAO;
import com.model.course.CourseCategoryTreeGrid;
import com.model.course.CourseCourseDelete;
import com.model.course.CourseCourseGrid;
import com.model.course.CourseCourseLazy;
import com.model.course.CourseVideoDelete;
import com.model.course.CourseVideoLazy;
import com.service.CourseService;
import com.util.MyUploadTool;
import com.util.Sys;
import com.vo.bean.CourseCategoryVO;
import com.vo.bean.CourseCourseVO;
import com.vo.bean.CourseVideoVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.ResultJson;
import com.ynu.zjx.SuperJson;
@Component("courseService")
public class CourseServiceImpl implements CourseService {
	private CourseVideoDAO courseVideoDAO;
	private CourseCourseDAO courseCourseDAO;
	public CourseCourseDAO getCourseCourseDAO() {
		return courseCourseDAO;
	}
	@Resource
	public void setCourseCourseDAO(CourseCourseDAO courseCourseDAO) {
		this.courseCourseDAO = courseCourseDAO;
	}
	public CourseVideoDAO getCourseVideoDAO() {
		return courseVideoDAO;
	}
	@Resource
	public void setCourseVideoDAO(CourseVideoDAO courseVideoDAO) {
		this.courseVideoDAO = courseVideoDAO;
	}
	private CourseCategoryDAO courseCategoryDAO;
	public CourseCategoryDAO getCourseCategoryDAO() {
		return courseCategoryDAO;
	}
	@Resource
	public void setCourseCategoryDAO(CourseCategoryDAO courseCategoryDAO) {
		this.courseCategoryDAO = courseCategoryDAO;
	}
	@Override
	public String findCourseCategoryTreeGrid(CourseCategoryVO vo) {
		List<CourseCategoryTreeGrid> rows = this.courseCategoryDAO.findCourseCategoryTreeGrid();
		long total = 0l; 
		SuperJson<CourseCategoryTreeGrid> superJson = new SuperJson<CourseCategoryTreeGrid>();
		superJson.setRows(rows);
	    superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveCourseCategoryTreeGrid(CourseCategoryVO vo) {
		CourseCategoryTreeGrid courseCategoryTreeGrid = vo.getCourseCategoryTreeGrid();
		courseCategoryTreeGrid.setPublisher(vo.getLoginUser().getName());
		this.courseCategoryDAO.saveCourseCategoryTreeGrid(courseCategoryTreeGrid);
		return null;
	}
	@Override
	public String deleteCourseCategoryTreeGrid(CourseCategoryVO vo) {
		if(vo.getId()<=3){
			ResultJson resultJson = new ResultJson();
			resultJson.setSuccess(false);
			resultJson.setMsg("该资源为系统保留！不能删除！");
			return MyUtil.toJson(resultJson);
		}
		CourseCategoryTreeGrid courseCategoryTreeGrid = this.courseCategoryDAO.findCourseCategoryTreeGridById(vo.getId());
		this.courseCategoryDAO.deleteCourseCategoryTreeGrid(courseCategoryTreeGrid);
		return null;
	}
	@Override
	public String updateCourseCategoryTreeGrid(CourseCategoryVO vo) {
		if(vo.getId()<=3&&vo.get_parentId()!=0){
			ResultJson resultJson = new ResultJson();
			resultJson.setSuccess(false);
			resultJson.setMsg("该资源为系统保留！不能修改父节点！");
			return MyUtil.toJson(resultJson);
		}
		CourseCategoryTreeGrid courseCategoryTreeGridNew = vo.getCourseCategoryTreeGrid();
		courseCategoryTreeGridNew.setPublisher(vo.getLoginUser().getName());
		courseCategoryTreeGridNew.setCreateTime(null);
		System.out.println(MyUtil.toJson(courseCategoryTreeGridNew));
		this.courseCategoryDAO.updateCourseCategoryTreeGrid(courseCategoryTreeGridNew);
		return null;
	}
	@Override
	public String findCourseCourseGrids(CourseCourseVO vo) {
		List<CourseCourseGrid> rows = this.courseCourseDAO.findCourseCourseGrids(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = courseCourseDAO.findTotal(); 
        SuperJson<CourseCourseGrid> superJson = new SuperJson<CourseCourseGrid>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveCourseCourseLazy(CourseCourseVO vo) {
		CourseCourseLazy courseCourseLazy = vo.getCourseCourseLazy();
		courseCourseLazy.setCoverPath(Sys.DEFAULTPICTUREPATH);
		courseCourseLazy.setStatus("未发布");
		courseCourseLazy.setVisitors(0);
		courseCourseLazy.setPublisher(vo.getLoginUser().getName());
		this.courseCourseDAO.saveCourseCourseLazy(courseCourseLazy);
		return null;
	}
	@Override
	public String deleteCourseCourseLazy(CourseCourseVO vo) {
		this.courseCourseDAO.deleteCourseCourseLazy(vo.getCourseCourseLazy());
		return null;
	}
	@Override
	public String updateCourseCourseLazy(CourseCourseVO vo) {
		CourseCourseLazy courseCourseNew = vo.getCourseCourseLazy();
		courseCourseNew.setPublisher(vo.getLoginUser().getName());
		courseCourseNew.setCreateTime(null);
		this.courseCourseDAO.mergeCourseCourseLazy(courseCourseNew);
		return null;
	}
	@Override
	public String deleteCourseCourseLazyByIds(CourseCourseVO vo) throws Exception{
		String[] ids = vo.getIds().split(",");
		for(int i=0;i<ids.length;i++){
			CourseCourseDelete courseCourse = this.courseCourseDAO.findCourseCourseById(Long.parseLong(ids[i]));
			for(CourseVideoDelete courseVideo :courseCourse.getCourseVideos()){
				MyUploadTool.deleteFile(courseVideo.getPath());
			}
			if(!Sys.DEFAULTPICTUREPATH.equals(courseCourse.getCoverPath())){
				MyUploadTool.deleteImage(courseCourse.getCoverPath());
			};
			this.courseCourseDAO.deleteCourseCourse(courseCourse);
		}
		return null;
	}
	@Override
	public String findCourseVideoLazyByPage(CourseVideoVO vo) {
		List<CourseVideoLazy> rows = (List<CourseVideoLazy>)this.courseVideoDAO.findLazyByPage(vo.getPid(),vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = this.courseVideoDAO.findTotalByPid(vo.getPid()); 
        SuperJson<CourseVideoLazy> superJson = new SuperJson<CourseVideoLazy>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public void saveCourseVideoLazy(CourseVideoVO vo) {
		this.courseVideoDAO.save(vo.getCourseVideoLazy());
	}
	@Override
	public CourseVideoLazy findCourseVideoLazyById(long id) {
		return this.courseVideoDAO.findById(id);
	}
	@Override
	public void deleteCourseVideoLazy(CourseVideoLazy courseVideoLazy) {
		this.courseVideoDAO.delete(courseVideoLazy);
	}
	@Override
	public String updateCourseVideoLazy(CourseVideoVO vo) {
		System.out.println(vo.getOrders());
		this.courseVideoDAO.updateCourseVideoLazyOrders(vo.getId(),vo.getOrders());
		return null;
	}
	@Override
	public String updateCourseCourseLazyStatusByIds(CourseCourseVO vo) {
		this.courseCourseDAO.updateCourseCourseLazyStatusByIds(vo.getIds(),vo.getStatus());
		return null;
	}
	@Override
	public CourseCourseLazy findCourseCourseLazyDetailById(CourseCourseVO vo) {
		return this.courseCourseDAO.findCourseCourseLazyById(vo.getId());
	}
	@Override
	public CourseCourseGrid findCourseCourseGridDetailById(CourseCourseVO vo) {
		return this.courseCourseDAO.findCourseCourseGridById(vo.getId());
	}
	@Override
	public CourseCourseLazy findCourseCourseLazyById(Long id) {
		return this.courseCourseDAO.findCourseCourseLazyById(id);
	}
	@Override
	public String updateCourseCourseCover(CourseCourseVO vo) {
		this.courseCourseDAO.updateCourseCourseCover(vo.getId(),vo.getCoverPath());
		return null;
	}
}
