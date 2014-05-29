package com.service;
import com.model.login.LoginUser;
import com.vo.bean.LoginUserVO;
import com.vo.bean.NewsAlbumCategoryVO;
import com.vo.bean.NewsNewsCategoryVO;
import com.vo.bean.SysDeptVO;
import com.vo.bean.SysRoleVO;
import com.vo.bean.SysUserVO;
public interface SysService {


	public abstract String findSysDeptTreeGridGet(SysDeptVO vo);

	public abstract String findSysDeptTreeGridGetByPid(SysDeptVO vo);

	public abstract String saveSysDeptTreeGridSet(SysDeptVO vo);

	public abstract String deleteSysDeptTreeGridSetById(SysDeptVO vo);

	public abstract String updateSysDeptTreeGridSet(SysDeptVO vo);

	public abstract String findSysRoleLazyByPage(SysRoleVO vo);

	public abstract String saveSysRoleLazy(SysRoleVO vo);

	public abstract String updateSysRoleLazy(SysRoleVO vo);

	public abstract void deleteSysRoleLazyById(SysRoleVO vo);

	public abstract String findSysUserLazyByPage(SysUserVO vo);

	public abstract String findSysDeptTreeGet(SysUserVO vo);

	public abstract String findRoleCombogrid(SysUserVO vo);

	public abstract void saveSysUserLazy(SysUserVO vo);

	public abstract LoginUser findLoginUser(LoginUserVO vo);

	public abstract void updateSysUserLazy(SysUserVO vo);

	String saveNewsNewsCategoryTreeGridSet(NewsNewsCategoryVO vo);

	String deleteNewsNewsCategoryTreeGridSetById(NewsNewsCategoryVO vo);

	String findNewsNewsCategoryTreeGridGet(NewsNewsCategoryVO vo);

	String findNewsNewsCategoryTreeGridGetByPid(NewsNewsCategoryVO vo);

	String updateNewsNewsCategoryTreeGridSet(NewsNewsCategoryVO vo);

	String findNewsNewsCategoryTreeGet();
	String findNewsAlbumCategoryTreeGridList(NewsAlbumCategoryVO vo);

	String findNewsAlbumCategoryTreeGridGetByPid(NewsAlbumCategoryVO vo);

	String saveNewsAlbumCategoryTreeGridSet(NewsAlbumCategoryVO vo);

	String deleteNewsAlbumCategoryTreeGridSetById(NewsAlbumCategoryVO vo);

	String updateNewsAlbumCategoryTreeGridSet(NewsAlbumCategoryVO vo);

	String findNewsAlbumCategoryTreeGet();

}