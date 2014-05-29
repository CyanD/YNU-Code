package com.action.news;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.news.news.NewsNewsLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.NewsService;
import com.vo.bean.NewsNewsVO;
import com.ynu.zjx.SuperAction;
@Component("newsNewsAction")
@Scope("prototype")
public class NewsNewsAction extends SuperAction implements ModelDriven<NewsNewsVO>{
	private NewsNewsVO vo = new NewsNewsVO();
	private NewsService newsService;
	private NewsNewsLazy modelLazy;
	public NewsNewsLazy getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(NewsNewsLazy modelLazy) {
		this.modelLazy = modelLazy;
	}
	public NewsService getNewsService() {
		return newsService;
	}

	@Override
	public NewsNewsVO getModel() {
		return this.vo;
	}
	@Resource
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public String show() throws Exception {
		vo.setLoginUser(loginUser);
        try {
			this.jsonString=this.newsService.findNewsNewsLazyByPage(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.outputJson(this.jsonString,true);
		
		return null;
	}
	public String showDetail() throws Exception {
        
		modelLazy=this.newsService.findNewsNewsLazyDetailById(vo);
		return "showDetail";
	}
	
	public String add() throws Exception {
		try {
			vo.setLoginUser(loginUser);
			this.newsService.saveNewsNewsLazy(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("保存成功！");
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
	public String remove() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.newsService.deleteNewsNewsLazyByIds(this.vo);
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请稍后重试！");
			this.jsonString = this.toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString, true);
		}
		return null;
	}	
	public String initEdit() throws Exception {
		try {
			modelLazy = this.newsService.findNewsNewsLazyDetailById(vo);
			this.jsonString = toJsonString(modelLazy);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			outputJson(this.jsonString, false);
		}
		return null;
	}
	public String edit() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			NewsNewsLazy newsNewsLazy=this.newsService.findNewsNewsLazyDetailById(vo);
			vo.setVisitors(newsNewsLazy.getVisitors());
			this.newsService.updateNewsNewsLazy(vo);
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
	public String editStatus() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.newsService.updateNewsNewsLazyStatusByIds(this.vo);
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
}
