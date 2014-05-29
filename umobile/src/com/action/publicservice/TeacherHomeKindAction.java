package com.action.publicservice;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ynu.zjx.SuperAction;
import com.model.publicservice.TeacherHomeKind;
import com.opensymphony.xwork2.ModelDriven;
import com.service.TeacherHomeKindService;
import com.vo.bean.TeacherHomeKindVO;

@Component("teacherHomeKindAction")
@Scope("prototype")
public class TeacherHomeKindAction extends SuperAction implements ModelDriven<TeacherHomeKindVO>{
	private TeacherHomeKindVO vo = new TeacherHomeKindVO();
	private TeacherHomeKindService teacherHomeKindService;
	private TeacherHomeKind modelLazy;
	
	public TeacherHomeKindService getTeacherHomeKindService() {
		return teacherHomeKindService;
	}
	@Resource
	public void setTeacherHomeKindService(
			TeacherHomeKindService teacherHomeKindService) {
		this.teacherHomeKindService = teacherHomeKindService;
	}

	public TeacherHomeKind getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(TeacherHomeKind modelLazy) {
		this.modelLazy = modelLazy;
	}

	@Override
	public TeacherHomeKindVO getModel() {
		return this.vo;
	}


	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.teacherHomeKindService.findAllTeacherHomeKind(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputJson(this.jsonString,true);
		return null;
	}
	public String add() throws Exception {
		print(vo);
		try {
			vo.setLoginUser(this.loginUser);//后用session
			this.jsonString=this.teacherHomeKindService.saveTeacherHomeKind(vo);
			print(jsonString);
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
			this.jsonString =this.teacherHomeKindService.deleteTeacherHomeKind(vo);
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
			this.jsonString = this.teacherHomeKindService.updateTeacherHomeKind(vo);
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

