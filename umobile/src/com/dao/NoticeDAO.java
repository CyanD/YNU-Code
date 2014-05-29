package com.dao;

import java.util.List;

import com.model.notice.NoticeDelete;
import com.model.notice.NoticeGrid;
import com.model.notice.NoticeLazy;

public interface NoticeDAO {


	List<NoticeGrid> findNoticeGrids(int i, int rows,
			String sort, String order);

	long findTotal();

	void saveNoticeGrid(NoticeGrid noticeGrid);

	NoticeLazy findNoticeLazyById(Long id);

	void deleteNoticeLazy(NoticeLazy noticeLazy);

	NoticeGrid findNoticeGridById(
			Long id);

	void mergeNoticeLazy(NoticeLazy noticeLazy);

	void mergeNoticeGrid(NoticeGrid noticeNew);

	void saveNoticeLazy(NoticeLazy noticeLazy);

	void updateNoticeLazyStatusByIds(String ids, String status);

	NoticeDelete findNoticeById(long id);

	void deleteNotice(NoticeDelete notice);

}