package com.action.course;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.CourseService;
import com.vo.bean.CourseCategoryVO;
import com.ynu.zjx.SuperAction;
@Component("courseCategoryAction")
@Scope("prototype")
public class CourseCategoryAction extends SuperAction implements ModelDriven<CourseCategoryVO>{
	private CourseCategoryVO vo = new CourseCategoryVO();
	private CourseService courseService;
	public CourseService getCourseService() {
		return courseService;
	}

	@Override
	public CourseCategoryVO getModel() {
		return this.vo;
	}
	@Resource
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String show() throws Exception {
		try {
			this.jsonString = this.courseService.findCourseCategoryTreeGrid(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputJson(this.jsonString,true);
		return null;
	}
	
	public String add() throws Exception {
		try {
			vo.setLoginUser(this.loginUser);//后用session
			this.jsonString=this.courseService.saveCourseCategoryTreeGrid(vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("保存成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("保存失败！请稍后重试！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,false);
		}
		return null;
		
	}
	
	public String remove() throws Exception {
		try {
			this.jsonString =this.courseService.deleteCourseCategoryTreeGrid(vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("删除成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！该院系已被使用！不能删除！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,true);
		}
		return null;
	}
	
	public String edit() throws Exception {
		print(vo);
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.courseService.updateCourseCategoryTreeGrid(this.vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("修改成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
}
