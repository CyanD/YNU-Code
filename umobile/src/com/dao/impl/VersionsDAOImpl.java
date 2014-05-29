package com.dao.impl;

import org.springframework.stereotype.Component;

import com.dao.VersionsDAO;
import com.model.calendar.Versions;


@Component("VersionsDAO")
public class VersionsDAOImpl extends TopDAO implements VersionsDAO{
	@Override
	public void updateversion() {
	   
		this.getSession().createQuery("update Versions nnl set nnl.versionnumber= nnl.versionnumber+1 where nnl.version_category = 'a'" ).executeUpdate();
	}
}
