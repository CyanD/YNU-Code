package com.dao;

import java.util.List;

import com.model.login.LoginUser;
import com.model.sys.SysUser;
import com.model.sys.SysUserLazy;
import com.model.sys.SysUserView;
import com.vo.bean.SysUserBean;

public interface SysUserDAO {

	public abstract void save(SysUser transientInstance);

	public abstract void delete(SysUser persistentInstance);

	public abstract SysUser findById(java.lang.Long id);

	public abstract List<SysUser> findByExample(SysUser instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<SysUser> findByAccount(Object account);

	public abstract List<SysUser> findByPassword(Object password);

	public abstract List<SysUser> findByName(Object name);

	public abstract List<SysUser> findByTel(Object tel);

	public abstract List<SysUser> findByEmail(Object email);

	public abstract List<SysUser> findByStatus(Object status);

	public abstract List<SysUser> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract SysUser merge(SysUser detachedInstance);

	public abstract void attachDirty(SysUser instance);

	public abstract void attachClean(SysUser instance);

	public abstract List<SysUserView> findLazyByPage(int i, int rows,
			String sort, String order);

	public abstract long findLazyTotal();

	public abstract void saveLazy(SysUserLazy modelLazy);

	public abstract LoginUser findLoginUser(String account);

	public abstract SysUserLazy findSysUserLazyById(Long id);


}