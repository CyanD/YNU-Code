package com.action.news;


import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.news.picture.NewsNewsPictureLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.NewsService;
import com.util.Sys;
import com.vo.bean.NewsNewsPictureVO;
import com.ynu.zjx.SuperAction;
@Component("newsNewsPictureAction")
@Scope("prototype")
public class NewsNewsPictureAction extends SuperAction implements ModelDriven<NewsNewsPictureVO>{
	private NewsNewsPictureVO vo = new NewsNewsPictureVO();
	private NewsService newsService;
	public NewsService getNewsService() {
		return newsService;
	}

	@Override
	public NewsNewsPictureVO getModel() {
		return this.vo;
	}
	public NewsNewsPictureVO getVo() {
		return vo;
	}
	public void setVo(NewsNewsPictureVO vo) {
		this.vo = vo;
	}
	@Resource
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public String show() throws Exception {
		outputJson(this.newsService.findNewsNewsPictureLazyByPage(vo),true);
		//outputJson(this.newsService.findNewsNewsPictureLazyByPid(vo),true);
		return null;
	}
	public String add() throws Exception {
		try{
			NewsNewsPictureLazy modelLazy = new NewsNewsPictureLazy();
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
			String name = "";
			String path="";
			String imgType="";
			int max=10;
			int fileTotal= vo.getFiledata().size();
			boolean isFirst = false;
			Long modelLazyTotal = this.newsService.findNewsNewsPictureLazyTotalByPid(vo.getPid());
			if((fileTotal+modelLazyTotal)>max){
				resultJson.setSuccess(false);
				resultJson.setMsg("上传文件数超出限制，每个新闻最多能附带"+max+"张图片！！");
			}else{
				Long modelLazyCoverTotal =this.newsService.findNewsNewsPictureCoverTotalByPid(vo.getPid());
				if(modelLazyCoverTotal==0){
					isFirst=true;
				}
				for (int i = 0; i < vo.getFiledata().size(); i++) {
					name = vo.getFiledataFileName().get(i);
					boolean isAllow =false;
					if (name.toLowerCase().endsWith(".jpg")||name.toLowerCase().endsWith(".jpeg")) {
						imgType = "JPEG";
						isAllow =true;
					}else if (name.toLowerCase().endsWith(".png")) {
						imgType = "PNG";
						isAllow =true;
					}else if(name.toLowerCase().endsWith(".gif")){
						imgType = "GIF";
						isAllow =true;
					}else if(name.toLowerCase().endsWith(".bmp")){
						imgType = "BMP";
						isAllow =true;
					}
					if(isAllow){
						path = Sys.NEWSNEWSPICTUREBASEPATH+System.currentTimeMillis()+".png";
						vo.setPublisher(loginUser.getName());
						modelLazy = vo.getNewsNewsPictureLazy();
						modelLazy.setPath(path);
						if(isFirst){
							modelLazy.setIsCover("是");
						}
						this.newsService.saveNewsNewsPictureLazyByModel(vo.getFiledata().get(i), modelLazy,imgType);
					}else{
						resultJson.setSuccess(false);
						resultJson.setMsg("图片不是规定格式，只能上传.jpg.png.gif.bmp格式的图片。");
						break;
					}
				}
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
		NewsNewsPictureLazy modelLazy = new NewsNewsPictureLazy();
		String[] ids = vo.getIds().split(",");
		int failNumbers=0;
		try {
			for(int i=0;i<ids.length;i++){
				try {
					modelLazy = this.newsService.findNewsNewsPictureLazyById(Long.parseLong(ids[i]));
					this.newsService.deleteNewsNewsPictureLazyByModel(modelLazy);
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
		try {
			vo.setLoginUser(loginUser);
			this.newsService.updateNewsNewsPictureLazy(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
	public String editDescription() throws Exception {
		try {
			vo.setLoginUser(loginUser);
			this.newsService.updateNewsNewsPictureDescription(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
	public String editIsCorver() throws Exception {
		try {
			vo.setLoginUser(loginUser);
			this.newsService.updateNewsNewsPictureIsCorver(this.vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
	public String initBatchEdit() throws Exception {
		return "initBatchEdit";
	}
	public String batchEdit() throws Exception {
		NewsNewsPictureLazy modelLazy = new NewsNewsPictureLazy();
		int failNumbers=0;
		String msg="";
		try {
				JSONArray deletesJsonArray = JSONArray.fromObject(vo.getDeletes());
				@SuppressWarnings("unchecked")
				List<NewsNewsPictureLazy> deletes = (List<NewsNewsPictureLazy>)JSONArray.toCollection(deletesJsonArray, NewsNewsPictureLazy.class);
			try {
				for(NewsNewsPictureLazy deleteModelLazy : deletes){
					try {
						modelLazy = this.newsService.findNewsNewsPictureLazyById(deleteModelLazy.getId());
						this.newsService.deleteNewsNewsPictureLazyByModel(modelLazy);
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
				msg+="成功删除"+(deletes.size()-failNumbers)+"个图片，失败"+failNumbers+"个！";
			}
			failNumbers=0;
			JSONArray editsJsonArray = JSONArray.fromObject(vo.getEdits());
			@SuppressWarnings("unchecked")
			List<NewsNewsPictureLazy> edits = (List<NewsNewsPictureLazy>)JSONArray.toCollection(editsJsonArray, NewsNewsPictureLazy.class);
			try {
				for(NewsNewsPictureLazy editModelLazy:edits){
					try {
						modelLazy = this.newsService.findNewsNewsPictureLazyById(editModelLazy.getId());
						if(vo.getId()==modelLazy.getId()){
							if(!"是".equals(modelLazy.getIsCover())){
								this.newsService.updateNewsNewsCoverPathById(modelLazy.getPid(), modelLazy.getPath());
							}
							modelLazy.setIsCover("是");
						}else{
							modelLazy.setIsCover("否");
						}
						modelLazy.setOrders(editModelLazy.getOrders());
						modelLazy.setDescription(editModelLazy.getDescription());
						modelLazy.setPublisher(loginUser.getName());
						this.newsService.updateNewsNewsPictureLazyByModel(modelLazy);
					} catch (Exception e) {
						failNumbers++;
						e.printStackTrace();
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				msg+="<br>成功编辑"+(edits.size() -failNumbers)+"个图片，失败"+failNumbers+"个！";
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			resultJson.setSuccess(true);
			resultJson.setMsg(msg);
			this.jsonString = toJsonString(resultJson);
			this.outputJson(this.jsonString,true);
		}
		return null;
	}
}
