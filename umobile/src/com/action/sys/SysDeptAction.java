package com.action.sys;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.SysService;
import com.vo.bean.SysDeptVO;
import com.ynu.zjx.SuperAction;
@Component("sysDeptAction")
@Scope("prototype")
public class SysDeptAction extends SuperAction implements ModelDriven<SysDeptVO>{
	private SysDeptVO vo = new SysDeptVO();
	private SysService sysService;
	public SysService getSysService() {
		return sysService;
	}

	@Override
	public SysDeptVO getModel() {
		return this.vo;
	}
	public SysDeptVO getVo() {
		return vo;
	}
	public void setVo(SysDeptVO vo) {
		this.vo = vo;
	}
	@Resource
	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	public String show() throws Exception {
		this.jsonString = this.sysService.findSysDeptTreeGridGet(vo);
		outputJson(this.jsonString,true);
		return null;
	}
	public String add() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			vo.setPublisher(loginUser.getName());//后用session
			this.sysService.saveSysDeptTreeGridSet(vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("保存失败！请稍后重试！");
		}finally{
			this.jsonString = this.toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;
		
	}
	public String remove() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.sysService.deleteSysDeptTreeGridSetById(vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请稍后重试！");
		}finally{
			this.jsonString = this.toJsonString(resultJson);
			this.outputJson(this.jsonString,true);
		}
		return null;
	}
	public String edit() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.sysService.updateSysDeptTreeGridSet(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
		}finally{
			this.jsonString = this.toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
}
