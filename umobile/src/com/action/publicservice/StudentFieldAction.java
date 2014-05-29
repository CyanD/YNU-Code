package com.action.publicservice;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ynu.zjx.SuperAction;
import com.model.publicservice.StudentField;
import com.opensymphony.xwork2.ModelDriven;
import com.service.StudentFieldService;
import com.vo.bean.StudentFieldVO;;

@Component("studentFieldAction")
@Scope("prototype")
public class StudentFieldAction extends SuperAction implements ModelDriven<StudentFieldVO>{
	private StudentFieldVO vo = new StudentFieldVO();
	private StudentFieldService studentFieldService;
	private StudentField modelLazy;
	
	public StudentFieldService getStudentFieldService() {
		return studentFieldService;
	}
	@Resource
	public void setStudentFieldService(StudentFieldService studentFieldService) {
		this.studentFieldService = studentFieldService;
	}
	public StudentField getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(StudentField modelLazy) {
		this.modelLazy = modelLazy;
	}

	@Override
	public StudentFieldVO getModel() {
		return this.vo;
	}


	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.studentFieldService.findAllStudentField(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputJson(this.jsonString,true);
		return null;
	}
	public String add() throws Exception {
		print(vo);
		try {
			vo.setContent( (vo.getContent()).replaceAll("\"", "'").replaceAll("src='/umobile", "src='/ynumobile") );
			//vo.setContent( (vo.getContent()).replaceAll("\"", "'"));
			vo.setLoginUser(this.loginUser);//后用session
			this.jsonString=this.studentFieldService.saveStudentField(vo);
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
			this.jsonString =this.studentFieldService.deleteStudentField(vo);
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
			vo.setContent( (vo.getContent()).replaceAll("\"", "'").replaceAll("src='/umobile", "src='/ynumobile") );
			this.jsonString = this.studentFieldService.updateStudentField(vo);
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
	
	public String showDetail() throws Exception {
        
		modelLazy=this.studentFieldService.findStudentFieldById(vo);
		return "showDetail";
		//return toJsonString(modelLazy);
	}
	
	public String search() throws Exception {
		try {
			this.jsonString = this.studentFieldService.searchStudentField(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputJson(this.jsonString,true);
		return null;
	}
	
	public String editKindConfirm(){
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.studentFieldService.updateEditKind(vo);
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

