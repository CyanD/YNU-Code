package com.action.course;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.course.CourseVideoLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CourseService;
import com.util.MyUploadTool;
import com.vo.bean.CourseVideoVO;
import com.ynu.zjx.SuperAction;
@Component("courseVideoAction")
@Scope("prototype")
public class CourseVideoAction extends SuperAction implements ModelDriven<CourseVideoVO>{
	private CourseVideoVO vo = new CourseVideoVO();
	private CourseService courseService;
	public CourseService getCourseService() {
		return courseService;
	}

	@Override
	public CourseVideoVO getModel() {
		return this.vo;
	}
	@Resource
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String show() throws Exception {
		outputJson(this.courseService.findCourseVideoLazyByPage(vo),true);
		return null;
	}
	
	public String add() throws Exception {
		try{
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
			String name = "";
			String path="";
			for (int i = 0; i < vo.getFiledata().size(); i++) {
				name = vo.getFiledataFileName().get(i);
				path = System.currentTimeMillis()+name.substring(name.lastIndexOf("."));
				MyUploadTool.saveVideo(vo.getFiledata().get(i), path);
				vo.setPath(path);
				vo.setName(name);
				this.courseService.saveCourseVideoLazy(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("保存失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;
		
	}
	
	public String remove() throws Exception {
		CourseVideoLazy courseVideoLazy;
		String[] ids = vo.getIds().split(",");
		int failNumbers=0;
		try {
			for(int i=0;i<ids.length;i++){
				try {
					courseVideoLazy = this.courseService.findCourseVideoLazyById(Long.parseLong(ids[i]));
					MyUploadTool.deleteFile(courseVideoLazy.getPath());
					this.courseService.deleteCourseVideoLazy(courseVideoLazy);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					failNumbers++;
					throw(e);
				}
			}
		} catch (Exception e) {
			throw(e);
		}finally{
			if(failNumbers>0){
				resultJson.setSuccess(false);
				resultJson.setMsg("成功删除"+(ids.length -failNumbers)+"个图片，失败"+failNumbers+"个！");
			}else{
				resultJson.setSuccess(true);
				resultJson.setMsg("所选图片全部成功删除！");
			}
			this.jsonString = toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
	public String edit() throws Exception {//暂时没用
		print(vo);
		try {
			this.jsonString=this.courseService.updateCourseVideoLazy(this.vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("设置成功！");
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
