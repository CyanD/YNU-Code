package com.action.publicservice;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.publicservice.PublicserviceActivity;
import com.opensymphony.xwork2.ModelDriven;
import com.service.PublicserviceActivityService;
import com.vo.bean.PublicserviceActivityVO;
import com.ynu.zjx.SuperAction;

@Component("publicserviceActivityAction")
@Scope("prototype")
public class PublicserviceActivityAction extends SuperAction implements ModelDriven<PublicserviceActivityVO>{
	private PublicserviceActivityVO vo = new PublicserviceActivityVO();
	private PublicserviceActivityService publicserviceActivityService;
	private PublicserviceActivity modelLazy;

	public PublicserviceActivity getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(PublicserviceActivity modelLazy) {
		this.modelLazy = modelLazy;
	}
	public PublicserviceActivityService getPublicserviceActivityService() {
		return publicserviceActivityService;
	}
	@Resource
	public void setPublicserviceActivityService(
			PublicserviceActivityService publicserviceActivityService) {
		this.publicserviceActivityService = publicserviceActivityService;
	}

	@Override
	public PublicserviceActivityVO getModel() {
		return this.vo;
	}


	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.publicserviceActivityService.findAllActivity(vo);
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
			this.jsonString=this.publicserviceActivityService.saveActivity(vo);
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
			this.jsonString =this.publicserviceActivityService.deleteActivity(vo);
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
			this.jsonString = this.publicserviceActivityService.updateActivity(vo);
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
        
		modelLazy=this.publicserviceActivityService.findPublicserviceActivityById(vo);
		return "showDetail";
		//return toJsonString(modelLazy);
	}
}

