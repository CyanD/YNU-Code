package com.dao;

import java.util.List;

import com.model.sys.SysLog;

public interface SysLogDAO {

	public abstract void save(SysLog transientInstance);

	public abstract void delete(SysLog persistentInstance);

	public abstract SysLog findById(java.lang.Long id);

	public abstract List<SysLog> findByExample(SysLog instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<SysLog> findByUserName(Object userName);

	public abstract List<SysLog> findByUserId(Object userId);

	public abstract List<SysLog> findByUserIp(Object userIp);

	public abstract List<SysLog> findByUserDept(Object userDept);

	public abstract List<SysLog> findByOper(Object oper);

	public abstract List<SysLog> findByOperTable(Object operTable);

	public abstract List<SysLog> findByOperObjId(Object operObjId);

	public abstract List<SysLog> findByOperDetail(Object operDetail);

	public abstract List findAll();

	public abstract SysLog merge(SysLog detachedInstance);

	public abstract void attachDirty(SysLog instance);

	public abstract void attachClean(SysLog instance);

}