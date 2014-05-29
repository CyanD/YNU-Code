package com.action.news;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.SysService;
import com.vo.bean.NewsNewsCategoryVO;
import com.ynu.zjx.SuperAction;
@Component("newsNewsCategoryAction")
@Scope("prototype")
public class NewsNewsCategoryAction extends SuperAction implements ModelDriven<NewsNewsCategoryVO>{
	private NewsNewsCategoryVO vo = new NewsNewsCategoryVO();
	private SysService sysService;
	public SysService getSysService() {
		return sysService;
	}

	@Override
	public NewsNewsCategoryVO getModel() {
		return this.vo;
	}
	public NewsNewsCategoryVO getVo() {
		return vo;
	}
	public void setVo(NewsNewsCategoryVO vo) {
		this.vo = vo;
	}
	@Resource
	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	public String show() throws Exception {
		this.jsonString = this.sysService.findNewsNewsCategoryTreeGridGet(vo);
		outputJson(this.jsonString,true);
		return null;
	}
	public String add() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			vo.setPublisher(loginUser.getName());//后用session
			this.sysService.saveNewsNewsCategoryTreeGridSet(vo);
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
			this.sysService.deleteNewsNewsCategoryTreeGridSetById(vo);
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
			this.sysService.updateNewsNewsCategoryTreeGridSet(this.vo);
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
