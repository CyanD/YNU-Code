package com.service;

import com.model.notice.NoticeAccessoriesLazy;
import com.model.notice.NoticeGrid;
import com.model.notice.NoticeLazy;
import com.vo.bean.NoticeAccessoriesVO;
import com.vo.bean.NoticeCategoryVO;
import com.vo.bean.NoticeVO;

public interface NoticeService {

	String findNoticeCategoryTreeGrid(NoticeCategoryVO vo);

	String saveNoticeCategoryTreeGrid(NoticeCategoryVO vo);

	String deleteNoticeCategoryTreeGrid(NoticeCategoryVO vo);

	String updateNoticeCategoryTreeGrid(NoticeCategoryVO vo);

	String findNoticeGrids(NoticeVO vo);

	String saveNoticeLazy(NoticeVO vo);

	String deleteNoticeLazy(NoticeVO vo);

	String updateNoticeLazy(NoticeVO vo);

	String deleteNoticeLazyByIds(NoticeVO vo) throws Exception;

	String findNoticeAccessoriesLazyByPage(NoticeAccessoriesVO vo);

	void saveNoticeAccessoriesLazy(NoticeAccessoriesVO vo);

	NoticeAccessoriesLazy findNoticeAccessoriesLazyById(long id);

	void deleteNoticeAccessoriesLazy(NoticeAccessoriesLazy noticeAccessoriesLazy);

	String updateNoticeAccessoriesLazy(NoticeAccessoriesVO vo);

	String updateNoticeLazyStatusByIds(NoticeVO vo);

	NoticeLazy findNoticeLazyDetailById(NoticeVO vo);

	NoticeGrid findNoticeGridDetailById(NoticeVO vo);



}