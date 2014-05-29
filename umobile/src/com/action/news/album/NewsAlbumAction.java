package com.action.news.album;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.NewsAlbumService;
import com.vo.bean.NewsAlbumVO;
import com.ynu.zjx.SuperAction;
@Component("newsAlbumAction")
@Scope("prototype")
public class NewsAlbumAction extends SuperAction implements ModelDriven<NewsAlbumVO>{
	private NewsAlbumVO vo = new NewsAlbumVO();
	private NewsAlbumService newsAlbumService;
	public NewsAlbumService getNewsAlbumService() {
		return newsAlbumService;
	}

	@Override
	public NewsAlbumVO getModel() {
		return this.vo;
	}
	@Resource
	public void setNewsAlbumService(NewsAlbumService newsAlbumService) {
		this.newsAlbumService = newsAlbumService;
	}

	public String show() throws Exception {
		print(vo);
		vo.setLoginUser(loginUser);
        try {
			this.jsonString=this.newsAlbumService.findNewsAlbumGridByPage(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        print(this.jsonString);
		this.outputJson(this.jsonString,true);
		
		return null;
	}
	
	
	public String add() throws Exception {
		try {
			vo.setLoginUser(loginUser);
			this.jsonString=this.newsAlbumService.saveNewsAlbumLazy(this.vo);
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
	
	public String edit() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.newsAlbumService.updateNewsAlbumLazy(vo);
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
			outputJson(this.jsonString, false);
		}
		return null;
	}

	public String remove() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.newsAlbumService.deleteNewsAlbumLazyById(this.vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("删除成功！");
				this.jsonString = toJsonString(resultJson);
			}
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

	public String editStatus() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.newsAlbumService.updateNewsAlbumLazyStatusByIds(this.vo);
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
/*public String showDetail() throws Exception {
    
	modelLazy=this.newsAlbumService.findNewsAlbumLazyDetailById(vo);
	return "showDetail";
}*/
}
