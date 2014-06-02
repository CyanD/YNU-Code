package com.sys.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.core.paging.Page;
import com.jpa.BaseDAO;
import com.sys.model.LoginUser;
import com.sys.services.IUserFacade;



@Service("userFacade")
public class UserFacade extends BaseDAO<LoginUser> implements IUserFacade {
	@Override
	public LoginUser findOneByClasuse(String clause) {
		return super.findOneByClause(clause);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<LoginUser> findByClasuse(String clause) {
		return super.findByClause(clause);
	}
	
}
