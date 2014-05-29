package com.action.news.notice;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.notice.NoticeGrid;
import com.model.notice.NoticeLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.NoticeService;
import com.vo.bean.NoticeVO;
import com.ynu.zjx.SuperAction;
@Component("noticeAction")
@Scope("prototype")
public class NoticeAction extends SuperAction implements ModelDriven<NoticeVO>{
	private NoticeVO vo = new NoticeVO();
	private NoticeService noticeService;
	private NoticeGrid modelLazy;
	public NoticeGrid getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(NoticeGrid modelLazy) {
		this.modelLazy = modelLazy;
	}
	public NoticeService getNoticeService() {
		return noticeService;
	}

	@Override
	public NoticeVO getModel() {
		return this.vo;
	}
	@Resource
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public String show() throws Exception {
		try {
			this.jsonString = this.noticeService.findNoticeGrids(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		print(this.jsonString);
		outputJson(this.jsonString,true);
		return null;
	}
	public String add() throws Exception {
		try {
			vo.setLoginUser(this.loginUser);//后用session
			this.jsonString=this.noticeService.saveNoticeLazy(vo);
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
			this.jsonString =this.noticeService.deleteNoticeLazyByIds(vo);
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
			NoticeLazy noticeLazy = this.noticeService.findNoticeLazyDetailById(vo);
			vo.setVisitors(noticeLazy.getVisitors());
			vo.setStatus(noticeLazy.getStatus());
			this.jsonString = this.noticeService.updateNoticeLazy(this.vo);
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
	
	
/*	public String initEdit() throws Exception {
		try {
			modelLazy = this.noticeService.findNoticeLazyDetailById(vo);
			this.jsonString = toJsonString(modelLazy);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			outputJson(this.jsonString, false);
		}
		return null;
	}*/
	public String editStatus() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.noticeService.updateNoticeLazyStatusByIds(this.vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("操作成功！请稍后重试！");
				this.jsonString = this.toJsonString(resultJson);
			}
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
	public String showDetail() throws Exception {
    
		modelLazy=this.noticeService.findNoticeGridDetailById(vo);
		return "showDetail";
	}
}
