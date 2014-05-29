package com.action.news.notice;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.NoticeService;
import com.vo.bean.NoticeCategoryVO;
import com.ynu.zjx.SuperAction;
@Component("noticeCategoryAction")
@Scope("prototype")
public class NoticeCategoryAction extends SuperAction implements ModelDriven<NoticeCategoryVO>{
	private NoticeCategoryVO vo = new NoticeCategoryVO();
	private NoticeService noticeService;
	public NoticeService getNoticeService() {
		return noticeService;
	}

	@Override
	public NoticeCategoryVO getModel() {
		return this.vo;
	}
	@Resource
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public String show() throws Exception {
		try {
			this.jsonString = this.noticeService.findNoticeCategoryTreeGrid(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputJson(this.jsonString,true);
		return null;
	}
	
	public String add() throws Exception {
		try {
			vo.setLoginUser(this.loginUser);//后用session
			this.jsonString=this.noticeService.saveNoticeCategoryTreeGrid(vo);
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
			this.jsonString =this.noticeService.deleteNoticeCategoryTreeGrid(vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("删除成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！该院系已被使用！不能删除！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,true);
		}
		return null;
	}
	
	public String edit() throws Exception {
		print(vo);
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.noticeService.updateNoticeCategoryTreeGrid(this.vo);
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
