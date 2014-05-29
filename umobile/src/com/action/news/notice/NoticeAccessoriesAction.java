package com.action.news.notice;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.notice.NoticeAccessoriesLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.NoticeService;
import com.util.MyUploadTool;
import com.util.Sys;
import com.vo.bean.NoticeAccessoriesVO;
import com.ynu.zjx.SuperAction;
@Component("noticeAccessoriesAction")
@Scope("prototype")
public class NoticeAccessoriesAction extends SuperAction implements ModelDriven<NoticeAccessoriesVO>{
	private NoticeAccessoriesVO vo = new NoticeAccessoriesVO();
	private NoticeService noticeService;
	public NoticeService getNoticeService() {
		return noticeService;
	}

	@Override
	public NoticeAccessoriesVO getModel() {
		return this.vo;
	}
	@Resource
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public String show() throws Exception {
		outputJson(this.noticeService.findNoticeAccessoriesLazyByPage(vo),true);
		return null;
	}
	
	public String add() throws Exception {
		try{
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
			String name = "";
			String path="";
			for (int i = 0; i < vo.getFiledata().size(); i++) {
				name = vo.getFiledataFileName().get(i);
				path = Sys.NOTICEACCESSORIESBASEPATH+System.currentTimeMillis()+name.substring(name.lastIndexOf("."));
				MyUploadTool.saveFile(vo.getFiledata().get(i), path);
				vo.setPath(path);
				vo.setName(name);
				this.noticeService.saveNoticeAccessoriesLazy(vo);
			}
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
		NoticeAccessoriesLazy noticeAccessoriesLazy;
		String[] ids = vo.getIds().split(",");
		int failNumbers=0;
		try {
			for(int i=0;i<ids.length;i++){
				try {
					noticeAccessoriesLazy = this.noticeService.findNoticeAccessoriesLazyById(Long.parseLong(ids[i]));
					MyUploadTool.deleteFile(noticeAccessoriesLazy.getPath());
					this.noticeService.deleteNoticeAccessoriesLazy(noticeAccessoriesLazy);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					failNumbers++;
					throw(e);
				}
			}
		} catch (Exception e) {
			throw(e);
		}finally{
			if(failNumbers>0){
				resultJson.setSuccess(false);
				resultJson.setMsg("成功删除"+(ids.length -failNumbers)+"个图片，失败"+failNumbers+"个！");
			}else{
				resultJson.setSuccess(true);
				resultJson.setMsg("所选图片全部成功删除！");
			}
			this.jsonString = toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
	public String edit() throws Exception {//暂时没用
		print(vo);
		try {
			this.jsonString=this.noticeService.updateNoticeAccessoriesLazy(this.vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("设置成功！");
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
