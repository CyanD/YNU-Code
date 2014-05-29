package com.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Component;

import com.dao.NewsAlbumDAO;
import com.dao.NewsAlbumPictureDAO;
import com.model.news.album.NewsAlbumDelete;
import com.model.news.album.NewsAlbumGrid;
import com.model.news.album.NewsAlbumLazy;
import com.model.news.album.NewsAlbumPictureDelete;
import com.model.news.album.NewsAlbumPictureLazy;
import com.service.NewsAlbumService;
import com.util.MyUploadTool;
import com.util.Sys;
import com.vo.bean.NewsAlbumPictureVO;
import com.vo.bean.NewsAlbumVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.ResultJson;
import com.ynu.zjx.SuperJson;

@Component("newsAlbumService")
public class NewsAlbumServiceImpl implements NewsAlbumService {
	private NewsAlbumDAO newsAlbumDAO;
	private NewsAlbumPictureDAO newsAlbumPictureDAO;
	public NewsAlbumPictureDAO getNewsAlbumPictureDAO() {
		return newsAlbumPictureDAO;
	}
	@Resource
	public void setNewsAlbumPictureDAO(NewsAlbumPictureDAO newsAlbumPictureDAO) {
		this.newsAlbumPictureDAO = newsAlbumPictureDAO;
	}
	public NewsAlbumDAO getNewsAlbumDAO() {
		return newsAlbumDAO;
	}
	@Resource
	public void setNewsAlbumDAO(NewsAlbumDAO newsAlbumDAO) {
		this.newsAlbumDAO = newsAlbumDAO;
	}
	@Override
	public String findNewsAlbumGridByPage(NewsAlbumVO vo) {
		List<NewsAlbumGrid> rows = this.newsAlbumDAO.findNewsAlbums(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = newsAlbumDAO.findTotal(); 
        SuperJson<NewsAlbumGrid> superJson = new SuperJson<NewsAlbumGrid>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveNewsAlbumLazy(NewsAlbumVO vo) {
		NewsAlbumLazy newsAlbumLazy = vo.getNewsAlbumLazy();
		newsAlbumLazy.setPublisher(vo.getLoginUser().getName());
		newsAlbumLazy.setStatus("未发布");
		newsAlbumLazy.setCoverPath(Sys.DEFAULTPICTUREPATH);
		newsAlbumLazy.setVisitors(0);
		this.newsAlbumDAO.save(newsAlbumLazy);
		return null;
	}
	@Override
	public void updateNewsAlbumLazy(NewsAlbumVO vo) {
		NewsAlbumLazy newsAlbumLazy = this.newsAlbumDAO.findById(vo.getId());
		NewsAlbumLazy newsAlbumLazyNew = vo.getNewsAlbumLazy();
		newsAlbumLazyNew.setPublisher(vo.getLoginUser().getName());
		newsAlbumLazyNew.setStatus("未发布");
		newsAlbumLazyNew.setCoverPath(newsAlbumLazy.getCoverPath());
		newsAlbumLazyNew.setVisitors(newsAlbumLazy.getVisitors());
		newsAlbumLazyNew.setStatus(newsAlbumLazy.getStatus());
		this.newsAlbumDAO.merge(newsAlbumLazyNew);
	}
	@Override
	public String deleteNewsAlbumLazyById(NewsAlbumVO vo) throws Exception {
		String[] ids = vo.getIds().split(",");
		for(int i=0;i<ids.length;i++){
			NewsAlbumDelete newsAlbum = this.newsAlbumDAO.findNewsAlbumById(Long.parseLong(ids[i]));
			for(NewsAlbumPictureDelete newsAlbumPicture :newsAlbum.getNewsAlbumPictures()){
				MyUploadTool.deleteImage(newsAlbumPicture.getPath());
			}
			this.newsAlbumDAO.deleteNewsAlbum(newsAlbum);
		}
		return null;
	}
	@Override
	public String findNewsAlbumPictureListByPage(NewsAlbumPictureVO vo) {
		List<NewsAlbumPictureLazy> rows = (List<NewsAlbumPictureLazy>)this.newsAlbumPictureDAO.findLazyByPage(vo.getPid(),vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = this.newsAlbumPictureDAO.findTotalByPid(vo.getPid()); 
        SuperJson<NewsAlbumPictureLazy> superJson = new SuperJson<NewsAlbumPictureLazy>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		
		return MyUtil.toJson(superJson);
	}
	@Override
	public Long findNewsAlbumPictureLazyTotalByPid(Long pid) {
		return this.newsAlbumPictureDAO.findTotalByPid(pid);
	}
	@Override
	public Long findNewsAlbumPictureCoverTotalByPid(Long pid) {
		return this.newsAlbumPictureDAO.findLazyCoverTotalByPid(pid);
	}
	@Override
	public void saveNewsAlbumPictureLazyByModel(File fileData ,NewsAlbumPictureLazy modelLazy,String imgType) throws Exception{
		if("是".equals(modelLazy.getIsCover())){
			this.newsAlbumDAO.updateLazyCoverPathById(modelLazy.getPid(),modelLazy.getPath());
		}
		this.newsAlbumPictureDAO.saveLazy(modelLazy);
		MyUploadTool.saveImage(fileData, modelLazy.getPath(),imgType);
	}
	@Override
	public NewsAlbumPictureLazy findNewsAlbumPictureLazyById(long id) {
		return this.newsAlbumPictureDAO.findLazyById(id);
	}
	@Override
	public void updateNewsAlbumPictureLazy(NewsAlbumPictureVO vo) {
		NewsAlbumPictureLazy modelLazy = this.newsAlbumPictureDAO.findLazyById(vo.getId());
		if("是".equals(vo.getIsCover())&&"否".equals(modelLazy.getIsCover())){
			this.newsAlbumDAO.updateLazyCoverPathById(modelLazy.getPid(), modelLazy.getPath());
			this.newsAlbumPictureDAO.updateCoverByPid(modelLazy.getPid());
		}else if("否".equals(vo.getIsCover())&&"是".equals(modelLazy.getIsCover())){
			this.newsAlbumDAO.updateLazyCoverPathById(modelLazy.getPid(), MyUploadTool.DEFAULTPICTUREPATH);
		}
		modelLazy.setPublisher(vo.getLoginUser().getName());
		modelLazy.setIsCover(vo.getIsCover());
		modelLazy.setDescription(vo.getDescription());
		this.newsAlbumPictureDAO.updateLazy(modelLazy);
	}
	@Override
	public void deleteNewsAlbumPictureLazyByModel(NewsAlbumPictureLazy modelLazy)
			throws Exception {
		if("是".equals(modelLazy.getIsCover())){
			this.newsAlbumDAO.updateLazyCoverPathById(modelLazy.getPid(),MyUploadTool.DEFAULTPICTUREPATH);
		}
		this.newsAlbumPictureDAO.deleteLazy(modelLazy);
		MyUploadTool.deleteImage(modelLazy.getPath());
		
	}
	@Override
	public String updateNewsAlbumLazyStatusByIds(NewsAlbumVO vo) {
		this.newsAlbumDAO.updateStatusByIds(vo.getIds(),vo.getStatus());
		ResultJson resultJson = new ResultJson();
		resultJson.setSuccess(true);
		resultJson.setMsg("操作成功！");
		return MyUtil.toJson(resultJson);
	}
	@Override
	public NewsAlbumLazy findNewsAlbumLazyDetailById(NewsAlbumVO vo) {
		return this.newsAlbumDAO.findLazyDetailById(vo.getId());
	}
	@Override
	public String updateNewsAlbumPictureDescription(NewsAlbumPictureVO vo) {
		NewsAlbumPictureLazy modelLazy = this.newsAlbumPictureDAO.findLazyById(vo.getId());
		modelLazy.setPublisher(vo.getLoginUser().getName());
		modelLazy.setCreateTime(null);
		modelLazy.setDescription(vo.getDescription());
		this.newsAlbumPictureDAO.updateLazy(modelLazy);
		return null;
	}
	@Override
	public String updateNewsAlbumPictureIsCorver(NewsAlbumPictureVO vo) {
		NewsAlbumPictureLazy modelLazy = this.newsAlbumPictureDAO.findLazyById(vo.getId());
		if("否".equals(modelLazy.getIsCover())){
			this.newsAlbumDAO.updateLazyCoverPathById(modelLazy.getPid(), modelLazy.getPath());
			this.newsAlbumPictureDAO.updateCoverByPid(modelLazy.getPid());
			modelLazy.setPublisher(vo.getLoginUser().getName());
			modelLazy.setIsCover("是");
			modelLazy.setCreateTime(null);
			this.newsAlbumPictureDAO.updateLazy(modelLazy);
		}
		return null;
	}
	@Override
	public String updateNewsAlbumCoverPathById(long id,String path) {
		this.newsAlbumDAO.updateLazyCoverPathById(id, path);
		return null;
	}
	
	@Override
	public List<NewsAlbumPictureLazy> findInitBatchEdit(NewsAlbumPictureVO vo) {
		return this.newsAlbumPictureDAO.findAllByPid(vo.getPid());
	}
	@Override
	public void updateNewsAlbumPictureLazyByModel(NewsAlbumPictureLazy modelLazy) {
		this.newsAlbumPictureDAO.updateLazy(modelLazy);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void updateBatchPictures(NewsAlbumPictureVO vo) throws Exception {
		JSONArray deletesJsonArray = JSONArray.fromObject(vo.getDeletes());
		List<NewsAlbumPictureLazy> deletes = (List<NewsAlbumPictureLazy>)JSONArray.toCollection(deletesJsonArray, NewsAlbumPictureLazy.class);
		for(NewsAlbumPictureLazy modelLazy : deletes){
			MyUploadTool.deleteImage(modelLazy.getPath());
			this.newsAlbumPictureDAO.deleteLazyById(modelLazy.getId());
		}
		JSONArray editsJsonArray = JSONArray.fromObject(vo.getDeletes());
		List<NewsAlbumPictureLazy> edits = (List<NewsAlbumPictureLazy>)JSONArray.toCollection(editsJsonArray, NewsAlbumPictureLazy.class);
		boolean flag = true;
		for(NewsAlbumPictureLazy modelLazy:edits){
			if(vo.getId()==modelLazy.getId()){
				modelLazy.setIsCover("是");
				this.newsAlbumDAO.updateLazyCoverPathById(modelLazy.getPid(), modelLazy.getPath());
				flag = false;
			}else{
				modelLazy.setIsCover("否");
			}
			modelLazy.setPublisher(vo.getLoginUser().getName());
			this.newsAlbumPictureDAO.updateLazy(modelLazy);
		}
		if(flag){
			this.newsAlbumDAO.updateLazyCoverPathById(vo.getPid(), MyUploadTool.DEFAULTPICTUREPATH);
		}

	}
	
}
