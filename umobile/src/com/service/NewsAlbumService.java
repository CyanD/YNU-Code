package com.service;

import java.io.File;
import java.util.List;

import com.model.news.album.NewsAlbumLazy;
import com.model.news.album.NewsAlbumPictureLazy;
import com.vo.bean.NewsAlbumPictureVO;
import com.vo.bean.NewsAlbumVO;

public interface NewsAlbumService {

	String findNewsAlbumGridByPage(NewsAlbumVO vo);


	String saveNewsAlbumLazy(NewsAlbumVO vo);


	void updateNewsAlbumLazy(NewsAlbumVO vo);


	String deleteNewsAlbumLazyById(NewsAlbumVO vo) throws Exception;


	String findNewsAlbumPictureListByPage(NewsAlbumPictureVO vo);


	Long findNewsAlbumPictureLazyTotalByPid(Long pid);


	Long findNewsAlbumPictureCoverTotalByPid(Long pid);


	void saveNewsAlbumPictureLazyByModel(File file,
			NewsAlbumPictureLazy modelLazy, String imgType) throws Exception;


	NewsAlbumPictureLazy findNewsAlbumPictureLazyById(long id);


	void updateNewsAlbumPictureLazy(NewsAlbumPictureVO vo);


	void deleteNewsAlbumPictureLazyByModel(NewsAlbumPictureLazy modelLazy)
			throws Exception;


	String updateNewsAlbumLazyStatusByIds(NewsAlbumVO vo);


	NewsAlbumLazy findNewsAlbumLazyDetailById(NewsAlbumVO vo);


	String updateNewsAlbumPictureDescription(NewsAlbumPictureVO vo);


	String updateNewsAlbumPictureIsCorver(NewsAlbumPictureVO vo);


	String updateNewsAlbumCoverPathById(long id, String path);


	List<NewsAlbumPictureLazy> findInitBatchEdit(NewsAlbumPictureVO vo);


	void updateNewsAlbumPictureLazyByModel(NewsAlbumPictureLazy modelLazy);


	void updateBatchPictures(NewsAlbumPictureVO vo) throws Exception;

}