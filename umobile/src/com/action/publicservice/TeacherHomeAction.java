package com.action.publicservice;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ynu.zjx.SuperAction;
import com.model.publicservice.TeacherHome;
import com.opensymphony.xwork2.ModelDriven;
import com.service.TeacherHomeService;
import com.vo.bean.TeacherHomeVO;

@Component("teacherHomeAction")
@Scope("prototype")
public class TeacherHomeAction extends SuperAction implements ModelDriven<TeacherHomeVO>{
	private TeacherHomeVO vo = new TeacherHomeVO();
	private TeacherHomeService teacherHomeService;
	private TeacherHome modelLazy;
	
	public TeacherHomeService getTeacherHomeService() {
		return teacherHomeService;
	}
	@Resource
	public void setTeacherHomeService(TeacherHomeService teacherHomeService) {
		this.teacherHomeService = teacherHomeService;
	}

	public TeacherHome getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(TeacherHome modelLazy) {
		this.modelLazy = modelLazy;
	}

	@Override
	public TeacherHomeVO getModel() {
		return this.vo;
	}


	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.teacherHomeService.findAllTeacherHome(vo);
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
			this.jsonString=this.teacherHomeService.saveTeacherHome(vo);
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
			this.jsonString =this.teacherHomeService.deleteTeacherHome(vo);
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
			this.jsonString = this.teacherHomeService.updateTeacherHome(vo);
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
        
		modelLazy=this.teacherHomeService.findTeacherHomeById(vo);
		return "showDetail";
	}
	
	public String search() throws Exception {
		try {
			this.jsonString = this.teacherHomeService.searchTeacherHome(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputJson(this.jsonString,true);
		return null;
	}
	
	public String editKindConfirm(){
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.teacherHomeService.updateEditKind(vo);
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

