/**
 * @版权：
 * @作者：韩强
 * @创建时间:2013年12月10日
 * @模块功能：
 * @修改记录： 1. 2.
 */
package com.sys.services.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.core.paging.Page;
import com.jpa.BaseDAO;
import com.sys.model.LoginUser;
import com.sys.model.User;
import com.sys.services.IUserService;

@Service("userService")
public class UserService extends BaseDAO<LoginUser> implements IUserService{
	public UserService() {
	}
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public LoginUser findById(long id) {
		return super.find(id);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<LoginUser> findPageByClasuse(String clause, Page page) {
		int _allRow = super.getCount(clause);
		page.init(_allRow, page.getCurrentPage(),
				page.getPageSize() > 0 ? page.getPageSize() : Page.PAGE_SIZE);
		return super.findPageByClasuse(page, clause);
	}
	public String removeById(long id) {
		removeByClause("LoginUser", " where id=" + id);
		// super.delete(id);
		return "success";
	}

	public void removeByClause(String clause) {
		removeByClause("LoginUser", clause);
	}
	
	@SuppressWarnings("unchecked")	
	private void removeByClause(String objName, String clause) {

		try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("delete from LoginUser");
			
			if (clause != null && !"".equals(clause)) {
				sb.append(clause);
			}
			Query query1 = em.createQuery(sb.toString());// session.createQuery(sb.toString());
			query1.executeUpdate();
			
		} catch (RuntimeException re) {

			throw re;
		}
	}
	

	
	
}