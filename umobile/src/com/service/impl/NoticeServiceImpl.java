package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.NoticeAccessoriesDAO;
import com.dao.NoticeCategoryDAO;
import com.dao.NoticeDAO;
import com.model.notice.NoticeAccessoriesDelete;
import com.model.notice.NoticeAccessoriesLazy;
import com.model.notice.NoticeCategoryTreeGrid;
import com.model.notice.NoticeDelete;
import com.model.notice.NoticeGrid;
import com.model.notice.NoticeLazy;
import com.service.NoticeService;
import com.util.MyUploadTool;
import com.vo.bean.NoticeAccessoriesVO;
import com.vo.bean.NoticeCategoryVO;
import com.vo.bean.NoticeVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.ResultJson;
import com.ynu.zjx.SuperJson;
@Component("noticeService")
public class NoticeServiceImpl implements NoticeService {
	private NoticeAccessoriesDAO noticeAccessoriesDAO;
	private NoticeDAO noticeDAO;
	public NoticeDAO getNoticeDAO() {
		return noticeDAO;
	}
	@Resource
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	public NoticeAccessoriesDAO getNoticeAccessoriesDAO() {
		return noticeAccessoriesDAO;
	}
	@Resource
	public void setNoticeAccessoriesDAO(NoticeAccessoriesDAO noticeAccessoriesDAO) {
		this.noticeAccessoriesDAO = noticeAccessoriesDAO;
	}
	private NoticeCategoryDAO noticeCategoryDAO;
	public NoticeCategoryDAO getNoticeCategoryDAO() {
		return noticeCategoryDAO;
	}
	@Resource
	public void setNoticeCategoryDAO(NoticeCategoryDAO noticeCategoryDAO) {
		this.noticeCategoryDAO = noticeCategoryDAO;
	}
	@Override
	public String findNoticeCategoryTreeGrid(NoticeCategoryVO vo) {
		List<NoticeCategoryTreeGrid> rows = this.noticeCategoryDAO.findNoticeCategoryTreeGrid();
		long total = 0l; 
		SuperJson<NoticeCategoryTreeGrid> superJson = new SuperJson<NoticeCategoryTreeGrid>();
		superJson.setRows(rows);
	    superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveNoticeCategoryTreeGrid(NoticeCategoryVO vo) {
		NoticeCategoryTreeGrid noticeCategoryTreeGrid = vo.getNoticeCategoryTreeGrid();
		noticeCategoryTreeGrid.setPublisher(vo.getLoginUser().getName());
		this.noticeCategoryDAO.saveNoticeCategoryTreeGrid(noticeCategoryTreeGrid);
		return null;
	}
	@Override
	public String deleteNoticeCategoryTreeGrid(NoticeCategoryVO vo) {
		if(vo.getId()<=3){
			ResultJson resultJson = new ResultJson();
			resultJson.setSuccess(false);
			resultJson.setMsg("该资源为系统保留！不能删除！");
			return MyUtil.toJson(resultJson);
		}
		NoticeCategoryTreeGrid noticeCategoryTreeGrid = this.noticeCategoryDAO.findNoticeCategoryTreeGridById(vo.getId());
		this.noticeCategoryDAO.deleteNoticeCategoryTreeGrid(noticeCategoryTreeGrid);
		return null;
	}
	@Override
	public String updateNoticeCategoryTreeGrid(NoticeCategoryVO vo) {
		if(vo.getId()<=3&&vo.get_parentId()!=0){
			ResultJson resultJson = new ResultJson();
			resultJson.setSuccess(false);
			resultJson.setMsg("该资源为系统保留！不能修改父节点！");
			return MyUtil.toJson(resultJson);
		}
		NoticeCategoryTreeGrid noticeCategoryTreeGridNew = vo.getNoticeCategoryTreeGrid();
		noticeCategoryTreeGridNew.setPublisher(vo.getLoginUser().getName());
		noticeCategoryTreeGridNew.setCreateTime(null);
		System.out.println(MyUtil.toJson(noticeCategoryTreeGridNew));
		this.noticeCategoryDAO.updateNoticeCategoryTreeGrid(noticeCategoryTreeGridNew);
		return null;
	}
	@Override
	public String findNoticeGrids(NoticeVO vo) {
		List<NoticeGrid> rows = this.noticeDAO.findNoticeGrids(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = noticeDAO.findTotal(); 
        SuperJson<NoticeGrid> superJson = new SuperJson<NoticeGrid>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveNoticeLazy(NoticeVO vo) {
		NoticeLazy noticeLazy = vo.getNoticeLazy();
		noticeLazy.setStatus("未发布");
		noticeLazy.setVisitors(0);
		noticeLazy.setPublisher(vo.getLoginUser().getName());
		this.noticeDAO.saveNoticeLazy(noticeLazy);
		return null;
	}
	@Override
	public String deleteNoticeLazy(NoticeVO vo) {
		this.noticeDAO.deleteNoticeLazy(vo.getNoticeLazy());
		return null;
	}
	@Override
	public String updateNoticeLazy(NoticeVO vo) {
		NoticeLazy noticeNew = vo.getNoticeLazy();
		noticeNew.setPublisher(vo.getLoginUser().getName());
		noticeNew.setCreateTime(null);
		this.noticeDAO.mergeNoticeLazy(noticeNew);
		return null;
	}
	@Override
	public String deleteNoticeLazyByIds(NoticeVO vo) throws Exception{
		String[] ids = vo.getIds().split(",");
		for(int i=0;i<ids.length;i++){
			NoticeDelete notice = this.noticeDAO.findNoticeById(Long.parseLong(ids[i]));
			for(NoticeAccessoriesDelete noticeAccessories :notice.getNoticeAccessorieses()){
				MyUploadTool.deleteFile(noticeAccessories.getPath());
			}
			this.noticeDAO.deleteNotice(notice);
		}
		return null;
	}
	@Override
	public String findNoticeAccessoriesLazyByPage(NoticeAccessoriesVO vo) {
		List<NoticeAccessoriesLazy> rows = (List<NoticeAccessoriesLazy>)this.noticeAccessoriesDAO.findLazyByPage(vo.getPid(),vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = this.noticeAccessoriesDAO.findTotalByPid(vo.getPid()); 
        SuperJson<NoticeAccessoriesLazy> superJson = new SuperJson<NoticeAccessoriesLazy>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public void saveNoticeAccessoriesLazy(NoticeAccessoriesVO vo) {
		this.noticeAccessoriesDAO.save(vo.getNoticeAccessoriesLazy());
	}
	@Override
	public NoticeAccessoriesLazy findNoticeAccessoriesLazyById(long id) {
		return this.noticeAccessoriesDAO.findById(id);
	}
	@Override
	public void deleteNoticeAccessoriesLazy(NoticeAccessoriesLazy noticeAccessoriesLazy) {
		this.noticeAccessoriesDAO.delete(noticeAccessoriesLazy);
	}
	@Override
	public String updateNoticeAccessoriesLazy(NoticeAccessoriesVO vo) {
/*		v
		this.noticeAccessoriesDAO.updateNoticeAccessoriesLazyOrders(vo.getId(),vo.getOrders());*/
		return null;
	}
	@Override
	public String updateNoticeLazyStatusByIds(NoticeVO vo) {
		this.noticeDAO.updateNoticeLazyStatusByIds(vo.getIds(),vo.getStatus());
		return null;
	}
	@Override
	public NoticeLazy findNoticeLazyDetailById(NoticeVO vo) {
		return this.noticeDAO.findNoticeLazyById(vo.getId());
	}
	@Override
	public NoticeGrid findNoticeGridDetailById(NoticeVO vo) {
		return this.noticeDAO.findNoticeGridById(vo.getId());
	}
}
