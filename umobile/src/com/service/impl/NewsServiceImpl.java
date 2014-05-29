package com.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Component;

import com.dao.NewsNewsDAO;
import com.dao.NewsNewsPictureDAO;
import com.model.news.news.NewsNews;
import com.model.news.news.NewsNewsGridGet;
import com.model.news.news.NewsNewsLazy;
import com.model.news.picture.NewsNewsPicture;
import com.model.news.picture.NewsNewsPictureLazy;
import com.service.NewsService;
import com.util.MyUploadTool;
import com.vo.bean.NewsNewsPictureVO;
import com.vo.bean.NewsNewsVO;
import com.vo.json.NewsNewsPictureResultJson;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.ResultJson;
import com.ynu.zjx.SuperJson;
@Component("newsService")
public class NewsServiceImpl implements NewsService{
	private NewsNewsDAO newsNewsDAO;
	private NewsNewsPictureDAO newsNewsPictureDAO;
	public NewsNewsPictureDAO getNewsNewsPictureDAO() {
		return newsNewsPictureDAO;
	}
	@Resource
	public void setNewsNewsPictureDAO(NewsNewsPictureDAO newsNewsPictureDAO) {
		this.newsNewsPictureDAO = newsNewsPictureDAO;
	}
	public NewsNewsDAO getNewsNewsDAO() {
		return newsNewsDAO;
	}
	@Resource
	public void setNewsNewsDAO(NewsNewsDAO newsNewsDAO) {
		this.newsNewsDAO = newsNewsDAO;
	}

	@Override
	public long findNewsNewsLazyTotal() {
		return newsNewsDAO.findLazyTotal();
	}

	@Override
	public String findNewsNewsLazyByPage(NewsNewsVO vo) {
		List<NewsNewsGridGet> rows = this.newsNewsDAO.findLazyByPage(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder(),vo.getLoginUser().getLoginDept().getId()); 
        long total = newsNewsDAO.findLazyTotalByPid(vo.getLoginUser().getLoginDept().getId()); 
        SuperJson<NewsNewsGridGet> superJson = new SuperJson<NewsNewsGridGet>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public void saveNewsNewsLazy(NewsNewsVO vo) {
		vo.setVisitors(0);
		vo.setAbstracts(vo.getAbstracts());
		vo.setPublisher(vo.getLoginUser().getName());
		vo.setDeptId(vo.getLoginUser().getLoginDept().getId());
		vo.setCoverPath(MyUploadTool.DEFAULTPICTUREPATH);
		vo.setDeptName(vo.getLoginUser().getLoginDept().getName());
		this.newsNewsDAO.saveLazy(vo.getNewsNewsLazy());
	}
	@Override
	public String deleteNewsNewsLazyByIds(NewsNewsVO vo) throws Exception {
		NewsNews modelLazy =null;
		NewsNewsPictureResultJson resultJson = new NewsNewsPictureResultJson();
		String[] ids = vo.getIds().split(",");
		int failNumbers=0;
		String failNames ="";
		try {
			for(int i=0;i<ids.length;i++){
				try {
					modelLazy = this.newsNewsDAO.findById(Long.parseLong(ids[i]));
					for(NewsNewsPicture cModelLazy :modelLazy.getNewsNewsPictures()){
						MyUploadTool.deleteImage(cModelLazy.getPath());
					}
					this.newsNewsDAO.delete(modelLazy);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					failNumbers++;
					failNames+= modelLazy.getId() +",";
					throw(e);
				}
			}
		} catch (Exception e) {
			throw(e);
		}finally{
			if(failNumbers>0){
				resultJson.setSuccess(false);
				resultJson.setMsg("成功删除"+(ids.length -failNumbers)+"个新闻，失败"+failNumbers+"个,失败新闻编号分别为："+failNames);
			}else{
				resultJson.setSuccess(true);
				resultJson.setMsg("所选新闻全部成功删除");
			}
		}
		return MyUtil.toJson(resultJson);
	}
	@Override
	public String updateNewsNewsLazy(NewsNewsVO vo) {
		vo.setPublisher(vo.getLoginUser().getName());
		vo.setDeptId(vo.getLoginUser().getLoginDept().getId());
		vo.setDeptName(vo.getLoginUser().getLoginDept().getName());
		this.newsNewsDAO.updateLazy(vo.getNewsNewsLazy());
		return null;
	}
	@Override
	public String findNewsNewsPictureLazyByPage(NewsNewsPictureVO vo) {
		@SuppressWarnings("unchecked")
		List<NewsNewsPictureLazy> rows = (List<NewsNewsPictureLazy>)this.newsNewsPictureDAO.findLazyByPage(vo.getPid(),vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = findNewsNewsPictureLazyTotalByPid(vo.getPid()); 
        SuperJson<NewsNewsPictureLazy> superJson = new SuperJson<NewsNewsPictureLazy>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String findNewsNewsPictureLazyByPid(NewsNewsPictureVO vo) {
		List<NewsNewsPictureLazy> rows = (List<NewsNewsPictureLazy>)this.newsNewsPictureDAO.findLazyByPid(vo.getPid(),vo.getSort(),vo.getOrder()); 
        long total = findNewsNewsPictureLazyTotalByPid(vo.getPid()); 
        SuperJson<NewsNewsPictureLazy> superJson = new SuperJson<NewsNewsPictureLazy>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		
		return MyUtil.toJson(superJson);
	}
	@Override
	public long findNewsNewsPictureLazyTotalByPid(long pid) {
		
		return this.newsNewsPictureDAO.findLazyTotal(pid);
	}
	@Override
	public void saveNewsNewsPictureLazyByModel(File fileData ,NewsNewsPictureLazy modelLazy,String imgType) throws Exception{
		if("是".equals(modelLazy.getIsCover())){
			this.newsNewsDAO.updateLazyCoverPathById(modelLazy.getPid(),modelLazy.getPath());
		}
		this.newsNewsPictureDAO.saveLazy(modelLazy);
		MyUploadTool.saveImage(fileData, modelLazy.getPath(),imgType);
	}
	@Override
	public Long findNewsNewsPictureCoverTotalByPid(Long pid) {
		return this.newsNewsPictureDAO.findLazyCoverTotalByPid(pid);
	}
	@Override
	public NewsNewsPictureLazy findNewsNewsPictureLazyById(long id) {
		return this.newsNewsPictureDAO.findLazyById(id);
	}
	@Override
	public void updateNewsNewsPictureLazy(NewsNewsPictureVO vo) {
		NewsNewsPictureLazy modelLazy = this.newsNewsPictureDAO.findLazyById(vo.getId());
		if("是".equals(vo.getIsCover())&&"否".equals(modelLazy.getIsCover())){
			this.newsNewsDAO.updateLazyCoverPathById(modelLazy.getPid(), modelLazy.getPath());
			this.newsNewsPictureDAO.updateCoverByPid(modelLazy.getPid());
		}else if("否".equals(vo.getIsCover())&&"是".equals(modelLazy.getIsCover())){
			this.newsNewsDAO.updateLazyCoverPathById(modelLazy.getPid(), MyUploadTool.DEFAULTPICTUREPATH);
		}
		modelLazy.setPublisher(vo.getLoginUser().getName());
		modelLazy.setIsCover(vo.getIsCover());
		modelLazy.setDescription(vo.getDescription());
		this.newsNewsPictureDAO.updateLazy(modelLazy);
	}
	@Override
	public void deleteNewsNewsPictureLazyByModel(NewsNewsPictureLazy modelLazy)
			throws Exception {
		if("是".equals(modelLazy.getIsCover())){
			this.newsNewsDAO.updateLazyCoverPathById(modelLazy.getPid(),MyUploadTool.DEFAULTPICTUREPATH);
		}
		this.newsNewsPictureDAO.deleteLazy(modelLazy);
		MyUploadTool.deleteImage(modelLazy.getPath());
		
	}
	@Override
	public String updateNewsNewsLazyStatusByIds(NewsNewsVO vo) {
		this.newsNewsDAO.updateStatusByIds(vo.getIds(),vo.getStatus());
		ResultJson resultJson = new ResultJson();
		resultJson.setSuccess(true);
		resultJson.setMsg("操作成功！");
		return MyUtil.toJson(resultJson);
	}
	@Override
	public NewsNewsLazy findNewsNewsLazyDetailById(NewsNewsVO vo) {
		return this.newsNewsDAO.findLazyDetailById(vo.getId());
	}
	@Override
	public String updateNewsNewsPictureDescription(NewsNewsPictureVO vo) {
		NewsNewsPictureLazy modelLazy = this.newsNewsPictureDAO.findLazyById(vo.getId());
		modelLazy.setPublisher(vo.getLoginUser().getName());
		modelLazy.setCreateTime(null);
		modelLazy.setDescription(vo.getDescription());
		this.newsNewsPictureDAO.updateLazy(modelLazy);
		return null;
	}
	@Override
	public String updateNewsNewsPictureIsCorver(NewsNewsPictureVO vo) {
		NewsNewsPictureLazy modelLazy = this.newsNewsPictureDAO.findLazyById(vo.getId());
		if("否".equals(modelLazy.getIsCover())){
			this.newsNewsDAO.updateLazyCoverPathById(modelLazy.getPid(), modelLazy.getPath());
			this.newsNewsPictureDAO.updateCoverByPid(modelLazy.getPid());
			modelLazy.setPublisher(vo.getLoginUser().getName());
			modelLazy.setIsCover("是");
			modelLazy.setCreateTime(null);
			this.newsNewsPictureDAO.updateLazy(modelLazy);
		}
		return null;
	}
	@Override
	public String updateNewsNewsCoverPathById(long id,String path) {
		this.newsNewsDAO.updateLazyCoverPathById(id, path);
		return null;
	}
	
	@Override
	public List<NewsNewsPictureLazy> findInitBatchEdit(NewsNewsPictureVO vo) {
		return this.newsNewsPictureDAO.findAllByPid(vo.getPid());
	}
	@Override
	public void updateNewsNewsPictureLazyByModel(NewsNewsPictureLazy modelLazy) {
		this.newsNewsPictureDAO.updateLazy(modelLazy);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void updateBatchPictures(NewsNewsPictureVO vo) throws Exception {
		JSONArray deletesJsonArray = JSONArray.fromObject(vo.getDeletes());
		List<NewsNewsPictureLazy> deletes = (List<NewsNewsPictureLazy>)JSONArray.toCollection(deletesJsonArray, NewsNewsPictureLazy.class);
		for(NewsNewsPictureLazy modelLazy : deletes){
			MyUploadTool.deleteImage(modelLazy.getPath());
			this.newsNewsPictureDAO.deleteLazyById(modelLazy.getId());
		}
		JSONArray editsJsonArray = JSONArray.fromObject(vo.getDeletes());
		List<NewsNewsPictureLazy> edits = (List<NewsNewsPictureLazy>)JSONArray.toCollection(editsJsonArray, NewsNewsPictureLazy.class);
		boolean flag = true;
		for(NewsNewsPictureLazy modelLazy:edits){
			if(vo.getId()==modelLazy.getId()){
				modelLazy.setIsCover("是");
				this.newsNewsDAO.updateLazyCoverPathById(modelLazy.getPid(), modelLazy.getPath());
				flag = false;
			}else{
				modelLazy.setIsCover("否");
			}
			modelLazy.setPublisher(vo.getLoginUser().getName());
			this.newsNewsPictureDAO.updateLazy(modelLazy);
		}
		if(flag){
			this.newsNewsDAO.updateLazyCoverPathById(vo.getPid(), MyUploadTool.DEFAULTPICTUREPATH);
		}

	}

}
