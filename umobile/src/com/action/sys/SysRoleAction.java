package com.action.sys;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.sys.SysRoleLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.SysService;
import com.vo.bean.SysRoleVO;
import com.ynu.zjx.SuperAction;
@Component("sysRoleAction")
@Scope("prototype")
public class SysRoleAction extends SuperAction implements ModelDriven<SysRoleVO>{
	private SysRoleVO vo = new SysRoleVO();
	private SysService sysService;
	private SysRoleLazy modelLazy;
	public SysRoleLazy getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(SysRoleLazy modelLazy) {
		this.modelLazy = modelLazy;
	}
	public SysService getSysService() {
		return sysService;
	}

	@Override
	public SysRoleVO getModel() {
		return this.vo;
	}
	@Resource
	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	public String show() throws Exception {
        
		this.outputJson(this.sysService.findSysRoleLazyByPage(vo),true);
		
		return null;
	}
	
	public String add() throws Exception {
		vo.setPublisher(loginUser.getName());
		try {
			this.sysService.saveSysRoleLazy(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("保存成功！");
			this.jsonString = toJsonString(resultJson);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("上传失败！请稍后重试！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
	public String delete() throws Exception {
		try {
			this.sysService.deleteSysRoleLazyById(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请稍后重试！");
		}finally{
			this.jsonString = this.toJsonString(resultJson);
			this.outputJson(this.jsonString, true);
		}
		return null;
	}	
	public String edit() throws Exception {
		vo.setPublisher(loginUser.getName());//以后用session取
		try {
			this.sysService.updateSysRoleLazy(vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			outputJson(this.jsonString, false);
		}
		return null;
	}
	
}
