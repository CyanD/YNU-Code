package com.action.publicservice;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ynu.zjx.SuperAction;
import com.model.publicservice.StudentFieldKind;
import com.opensymphony.xwork2.ModelDriven;
import com.service.StudentFieldKindService;
import com.vo.bean.StudentFieldKindVO;

@Component("studentFieldKindAction")
@Scope("prototype")
public class StudentFieldKindAction extends SuperAction implements ModelDriven<StudentFieldKindVO>{
	private StudentFieldKindVO vo = new StudentFieldKindVO();
	private StudentFieldKindService studentFieldKindService;
	private StudentFieldKind modelLazy;
	
	public StudentFieldKindService getStudentFieldKindService() {
		return studentFieldKindService;
	}
	@Resource
	public void setStudentFieldKindService(
			StudentFieldKindService studentFieldKindService) {
		this.studentFieldKindService = studentFieldKindService;
	}
	public StudentFieldKind getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(StudentFieldKind modelLazy) {
		this.modelLazy = modelLazy;
	}
	@Override
	public StudentFieldKindVO getModel() {
		return this.vo;
	}
	
	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.studentFieldKindService.findAllStudentFieldKind(vo);
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
			this.jsonString=this.studentFieldKindService.saveStudentFieldKind(vo);
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
			this.jsonString =this.studentFieldKindService.deleteStudentFieldKind(vo);
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
			this.jsonString = this.studentFieldKindService.updateStudentFieldKind(vo);
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
