package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.NoticeDAO;
import com.model.notice.NoticeDelete;
import com.model.notice.NoticeGrid;
import com.model.notice.NoticeLazy;

@Component("noticeDAO")
public class NoticeDAOImpl extends TopDAO implements NoticeDAO {
	@Override
	public List<NoticeGrid> findNoticeGrids(int i,
			int rows, String sort, String order) {
		return this.getSession().createQuery("from NoticeGrid order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();

	}

	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from NoticeGrid").uniqueResult();
	}

	@Override
	public void saveNoticeGrid(NoticeGrid noticeGrid) {
		this.getHibernateTemplate().save(noticeGrid);
	}

	@Override
	public NoticeLazy findNoticeLazyById(Long id) {
		return (NoticeLazy) this.getSession().get(NoticeLazy.class, id);
	}

	@Override
	public void deleteNoticeLazy(NoticeLazy noticeLazy) {
		this.getHibernateTemplate().delete(noticeLazy);
	}

	@Override
	public NoticeGrid findNoticeGridById(Long id) {
		return (NoticeGrid) this.getSession().get(NoticeGrid.class, id);
	}

	@Override
	public void mergeNoticeLazy(NoticeLazy noticeLazy) {
		this.getHibernateTemplate().merge(noticeLazy);
	}

	@Override
	public void mergeNoticeGrid(NoticeGrid noticeNew) {
		this.getHibernateTemplate().merge(noticeNew);
	}

	@Override
	public void saveNoticeLazy(NoticeLazy noticeLazy) {
		this.getHibernateTemplate().save(noticeLazy);
	}

	@Override
	public void updateNoticeLazyStatusByIds(String ids, String status) {
		this.getSession().createQuery("update NoticeLazy set status = '"+status+"' where id in ("+ids+")").executeUpdate();
	}

	@Override
	public NoticeDelete findNoticeById(long id) {
		return (NoticeDelete) this.getSession().get(NoticeDelete.class, id);
	}

	@Override
	public void deleteNotice(NoticeDelete notice) {
		this.getSession().delete(notice);
	}
}