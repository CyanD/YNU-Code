package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.VersionsDAO;
import com.service.VersionsService;

@Component("VersionsService")
public class VersionsServiceImpl implements VersionsService{
	private VersionsDAO verDAO;
	
	public VersionsDAO getVerDAO() {
		return verDAO;
	}
	@Resource
	public void setVerDAO(VersionsDAO verDAO) {
		this.verDAO = verDAO;
	}

	@Override
	public void updateversion() {
		// TODO Auto-generated method stub
		this.verDAO.updateversion();
	}

}
