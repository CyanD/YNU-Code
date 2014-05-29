package com.action.sys;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.SysService;
import com.vo.bean.SysUserVO;
import com.ynu.zjx.SuperAction;
@Component("sysUserAction")
@Scope("prototype")
public class SysUserAction extends SuperAction implements ModelDriven<SysUserVO>{
	private SysUserVO vo = new SysUserVO();
	private SysService sysService;
	public SysService getSysService() {
		return sysService;
	}

	@Override
	public SysUserVO getModel() {
		return this.vo;
	}
	@Resource
	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	public String show() throws Exception {
        try {
			this.outputJson(this.sysService.findSysUserLazyByPage(vo),true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public String showSysDeptTree() throws Exception {
        try {
			this.outputJson(this.sysService.findSysDeptTreeGet(vo),true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public String showRoleCombogrid() throws Exception {
        try {
        	this.jsonString=this.sysService.findRoleCombogrid(vo);
			this.outputJson(this.jsonString,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String add() throws Exception {
		vo.setPublisher(loginUser.getName());
		try {
			this.sysService.saveSysUserLazy(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("保存成功！");
			this.jsonString = this.toJsonString(resultJson);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("上传失败！请稍后重试！");
			this.jsonString = this.toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
	/*
	public String delete() throws Exception {
		try {
			this.sysService.deleteSysUserLazyById(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请稍后重试！");
		}finally{
			this.json = this.this.toJsonString(resultJson);
			this.outputJson(this.json, true);
		}
		return null;
	}*/	
	public String edit() throws Exception {
		vo.setPublisher(loginUser.getName());//以后用session取
		try {
			this.sysService.updateSysUserLazy(vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
		}finally{
			jsonString = this.toJsonString(resultJson);
			outputJson(jsonString, false);
		}
		return null;
	}
	
}
