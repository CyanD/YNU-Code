package com.service;

import java.io.File;
import java.util.List;

import com.model.news.news.NewsNewsLazy;
import com.model.news.picture.NewsNewsPictureLazy;
import com.vo.bean.NewsNewsPictureVO;
import com.vo.bean.NewsNewsVO;

public interface NewsService {

	long findNewsNewsLazyTotal();
	String findNewsNewsLazyByPage(NewsNewsVO vo);
	void saveNewsNewsLazy(NewsNewsVO vo);

	String deleteNewsNewsLazyByIds(NewsNewsVO vo) throws Exception;

	String updateNewsNewsLazy(NewsNewsVO vo);
	String findNewsNewsPictureLazyByPage(
			NewsNewsPictureVO vo);

	long findNewsNewsPictureLazyTotalByPid(long pid);

	void updateNewsNewsPictureLazy(NewsNewsPictureVO vo);
	void deleteNewsNewsPictureLazyByModel(NewsNewsPictureLazy modelLazy)
			throws Exception;
	String updateNewsNewsLazyStatusByIds(NewsNewsVO vo);
	NewsNewsLazy findNewsNewsLazyDetailById(NewsNewsVO vo);
	String updateNewsNewsPictureDescription(NewsNewsPictureVO vo);
	String updateNewsNewsPictureIsCorver(NewsNewsPictureVO vo);
	List<NewsNewsPictureLazy> findInitBatchEdit(NewsNewsPictureVO vo);
	void updateBatchPictures(NewsNewsPictureVO vo) throws Exception;
	Long findNewsNewsPictureCoverTotalByPid(Long pid);
	void saveNewsNewsPictureLazyByModel(File fileData,
			NewsNewsPictureLazy modelLazy, String imgType) throws Exception;
	NewsNewsPictureLazy findNewsNewsPictureLazyById(long id);
	void updateNewsNewsPictureLazyByModel(NewsNewsPictureLazy modelLazy);
	String updateNewsNewsCoverPathById(long id, String path);
	String findNewsNewsPictureLazyByPid(NewsNewsPictureVO vo);
	
	

	

	

}
