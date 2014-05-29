package com.action.news.album;


import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.news.album.NewsAlbumPictureLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.NewsAlbumService;
import com.util.Sys;
import com.vo.bean.NewsAlbumPictureVO;
import com.ynu.zjx.SuperAction;
@Component("newsAlbumPictureAction")
@Scope("prototype")
public class NewsAlbumPictureAction extends SuperAction implements ModelDriven<NewsAlbumPictureVO>{
	private NewsAlbumPictureVO vo = new NewsAlbumPictureVO();
	private NewsAlbumService newsAlbumService;
	public NewsAlbumService getNewsAlbumService() {
		return newsAlbumService;
	}

	@Override
	public NewsAlbumPictureVO getModel() {
		return this.vo;
	}
	public NewsAlbumPictureVO getVo() {
		return vo;
	}
	public void setVo(NewsAlbumPictureVO vo) {
		this.vo = vo;
	}
	@Resource
	public void setNewsAlbumService(NewsAlbumService newsAlbumService) {
		this.newsAlbumService = newsAlbumService;
	}

	public String show() throws Exception {
		print(vo);
        try {
			this.jsonString=this.newsAlbumService.findNewsAlbumPictureListByPage(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        print(this.jsonString);
		this.outputJson(this.jsonString,true);
		
		return null;
	}
	public String add() throws Exception {
		try{
			NewsAlbumPictureLazy modelLazy = new NewsAlbumPictureLazy();
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
			String name = "";
			String path="";
			String imgType="";
			int max=20;
			int fileTotal= vo.getFiledata().size();
			boolean isFirst = false;
			Long modelLazyTotal = this.newsAlbumService.findNewsAlbumPictureLazyTotalByPid(vo.getPid());
			if((fileTotal+modelLazyTotal)>max){
				resultJson.setSuccess(false);
				resultJson.setMsg("上传文件数超出限制，每个新闻最多能附带"+max+"张图片！！");
			}else{
				Long modelLazyCoverTotal =this.newsAlbumService.findNewsAlbumPictureCoverTotalByPid(vo.getPid());
				if(modelLazyCoverTotal==0){
					System.out.println(modelLazyCoverTotal);
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
						path = Sys.NEWSALBUMPICTUREBASEPATH+System.currentTimeMillis()+".png";
						modelLazy = vo.getNewsAlbumPictureLazy();
						modelLazy.setPublisher(loginUser.getName());
						modelLazy.setPath(path);
						if(isFirst){
							modelLazy.setIsCover("是");
						}
						this.newsAlbumService.saveNewsAlbumPictureLazyByModel(vo.getFiledata().get(i), modelLazy,imgType);
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
		NewsAlbumPictureLazy modelLazy = new NewsAlbumPictureLazy();
		String[] ids = vo.getIds().split(",");
		int failNumbers=0;
		try {
			for(int i=0;i<ids.length;i++){
				try {
					modelLazy = this.newsAlbumService.findNewsAlbumPictureLazyById(Long.parseLong(ids[i]));
					this.newsAlbumService.deleteNewsAlbumPictureLazyByModel(modelLazy);
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
			this.newsAlbumService.updateNewsAlbumPictureLazy(this.vo);
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
			this.newsAlbumService.updateNewsAlbumPictureDescription(this.vo);
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
			this.newsAlbumService.updateNewsAlbumPictureIsCorver(this.vo);
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
	@SuppressWarnings("unchecked")
	public String batchEdit() throws Exception {
		NewsAlbumPictureLazy modelLazy = new NewsAlbumPictureLazy();
		int failNumbers=0;
		String msg="";
		try {
				JSONArray deletesJsonArray = JSONArray.fromObject(vo.getDeletes());
				List<NewsAlbumPictureLazy> deletes = (List<NewsAlbumPictureLazy>)JSONArray.toCollection(deletesJsonArray, NewsAlbumPictureLazy.class);
			try {
				for(NewsAlbumPictureLazy deleteModelLazy : deletes){
					try {
						modelLazy = this.newsAlbumService.findNewsAlbumPictureLazyById(deleteModelLazy.getId());
						this.newsAlbumService.deleteNewsAlbumPictureLazyByModel(modelLazy);
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
			List<NewsAlbumPictureLazy> edits = (List<NewsAlbumPictureLazy>)JSONArray.toCollection(editsJsonArray, NewsAlbumPictureLazy.class);
			try {
				for(NewsAlbumPictureLazy editModelLazy:edits){
					try {
						modelLazy = this.newsAlbumService.findNewsAlbumPictureLazyById(editModelLazy.getId());
						if(vo.getId()==modelLazy.getId()){
							if(!"是".equals(modelLazy.getIsCover())){
								this.newsAlbumService.updateNewsAlbumCoverPathById(modelLazy.getPid(), modelLazy.getPath());
							}
							modelLazy.setIsCover("是");
						}else{
							modelLazy.setIsCover("否");
						}
						modelLazy.setOrders(editModelLazy.getOrders());
						modelLazy.setDescription(editModelLazy.getDescription());
						modelLazy.setPublisher(loginUser.getName());
						this.newsAlbumService.updateNewsAlbumPictureLazyByModel(modelLazy);
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
