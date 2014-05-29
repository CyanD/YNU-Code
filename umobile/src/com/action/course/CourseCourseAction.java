package com.action.course;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.course.CourseCourseGrid;
import com.model.course.CourseCourseLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CourseService;
import com.util.MyUploadTool;
import com.util.Sys;
import com.vo.bean.CourseCourseVO;
import com.ynu.zjx.SuperAction;
@Component("courseCourseAction")
@Scope("prototype")
public class CourseCourseAction extends SuperAction implements ModelDriven<CourseCourseVO>{
	private CourseCourseVO vo = new CourseCourseVO();
	private CourseService courseService;
	private CourseCourseGrid modelLazy;
	public CourseCourseGrid getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(CourseCourseGrid modelLazy) {
		this.modelLazy = modelLazy;
	}
	public CourseService getCourseService() {
		return courseService;
	}

	@Override
	public CourseCourseVO getModel() {
		return this.vo;
	}
	@Resource
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.courseService.findCourseCourseGrids(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		print(this.jsonString);
		outputJson(this.jsonString,true);
		return null;
	}
	public String add() throws Exception {
		print(vo);
		try {
			vo.setLoginUser(this.loginUser);//后用session
			this.jsonString=this.courseService.saveCourseCourseLazy(vo);
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
			this.jsonString =this.courseService.deleteCourseCourseLazyByIds(vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("删除成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请刷新后重试！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,true);
		}
		return null;
	}
	
	public String edit() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			CourseCourseLazy courseCourseLazy= this.courseService.findCourseCourseLazyById(vo.getId());
			this.vo.setVisitors(courseCourseLazy.getVisitors());
			vo.setStatus(courseCourseLazy.getStatus());
			vo.setCoverPath(courseCourseLazy.getCoverPath());
			this.jsonString = this.courseService.updateCourseCourseLazy(this.vo);
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
	public String editCover() throws Exception {
		try{
			String name = "";
			String path="";
			String imgType="";
			for (int i = 0; i < vo.getFiledata().size(); i++) {
				name = vo.getFiledataFileName().get(i);
				boolean isAllow =false;
				if (name.toLowerCase().endsWith(".jpg")||name.toLowerCase().endsWith(".jpeg")) {
					imgType = "JPEG";
					isAllow =true;
				}else if (name.toLowerCase().endsWith(".png")) {
					imgType = "PNG";
					isAllow =true;
				}else if(name.toLowerCase().endsWith(".gif")){
					imgType = "GIF";
					isAllow =true;
				}else if(name.toLowerCase().endsWith(".bmp")){
					imgType = "BMP";
					isAllow =true;
				}
				if(isAllow){
					print(vo);
					CourseCourseLazy courseCourseLazy = this.courseService.findCourseCourseLazyById(vo.getId());
					if(!Sys.DEFAULTPICTUREPATH.equals(courseCourseLazy.getCoverPath())){
						MyUploadTool.deleteImage(courseCourseLazy.getCoverPath());
					};
					path = Sys.COURSEPICTUREBASEPATH+System.currentTimeMillis()+".png";
					vo.setCoverPath(path);
					this.jsonString = this.courseService.updateCourseCourseCover(this.vo);
					MyUploadTool.saveImage(vo.getFiledata().get(i), path, imgType);
					if(this.jsonString==null){
						resultJson.setSuccess(true);
						resultJson.setMsg(path);
					}
				}else{
					resultJson.setSuccess(false);
					resultJson.setMsg("图片不是规定格式，只能上传.jpg.png.gif.bmp格式的图片。");
					break;
				}
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
	
	
/*	public String initEdit() throws Exception {
		try {
			modelLazy = this.courseService.findCourseCourseLazyDetailById(vo);
			this.jsonString = toJsonString(modelLazy);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			outputJson(this.jsonString, false);
		}
		return null;
	}*/
	public String editStatus() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.courseService.updateCourseCourseLazyStatusByIds(this.vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("操作成功！请稍后重试！");
				this.jsonString = this.toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("操作失败！请稍后重试！");
			this.jsonString = this.toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString, true);
		}
		return null;
	}	
	public String showDetail() throws Exception {
    
		modelLazy=this.courseService.findCourseCourseGridDetailById(vo);
		return "showDetail";
	}
}