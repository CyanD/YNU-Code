package com.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.NewsAlbumCategoryDAO;
import com.dao.NewsNewsCategoryDAO;
import com.dao.SysDeptDAO;
import com.dao.SysRoleDAO;
import com.dao.SysUserDAO;
import com.model.login.LoginUser;
import com.model.news.album.NewsAlbumCategoryTree;
import com.model.news.album.NewsAlbumCategoryTreeGrid;
import com.model.news.news.NewsNewsCategoryTreeGet;
import com.model.news.news.NewsNewsCategoryTreeGridGet;
import com.model.sys.SysDeptTreeGet;
import com.model.sys.SysDeptTreeGridGet;
import com.model.sys.SysRoleCombogrid;
import com.model.sys.SysRoleLazy;
import com.model.sys.SysUserLazy;
import com.model.sys.SysUserView;
import com.service.SysService;
import com.vo.bean.LoginUserVO;
import com.vo.bean.NewsAlbumCategoryVO;
import com.vo.bean.NewsNewsCategoryVO;
import com.vo.bean.SysDeptVO;
import com.vo.bean.SysRoleVO;
import com.vo.bean.SysUserVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.SuperJson;


@Component("sysService")
public class SysServiceImpl implements SysService{
	private SysDeptDAO sysDeptDAO;
	private NewsNewsCategoryDAO newsNewsCategoryDAO;
	private NewsAlbumCategoryDAO newsAlbumCategoryDAO;
	public NewsAlbumCategoryDAO getNewsAlbumCategoryDAO() {
		return newsAlbumCategoryDAO;
	}
	@Resource
	public void setNewsAlbumCategoryDAO(NewsAlbumCategoryDAO newsAlbumCategoryDAO) {
		this.newsAlbumCategoryDAO = newsAlbumCategoryDAO;
	}
	private SysRoleDAO sysRoleDAO;
	private SysUserDAO sysUserDAO;
	public NewsNewsCategoryDAO getNewsNewsCategoryDAO() {
		return newsNewsCategoryDAO;
	}
	@Resource
	public void setNewsNewsCategoryDAO(NewsNewsCategoryDAO newsNewsCategoryDAO) {
		this.newsNewsCategoryDAO = newsNewsCategoryDAO;
	}
	public SysUserDAO getSysUserDAO() {
		return sysUserDAO;
	}
	@Resource
	public void setSysUserDAO(SysUserDAO sysUserDAO) {
		this.sysUserDAO = sysUserDAO;
	}
	public SysDeptDAO getSysDeptDAO() {
		return this.sysDeptDAO;
	}
	public SysRoleDAO getSysRoleDAO() {
		return sysRoleDAO;
	}
	@Resource
	public void setSysRoleDAO(SysRoleDAO sysRoleDAO) {
		this.sysRoleDAO = sysRoleDAO;
	}
	
	@Resource
	public void setSysDeptDAO(SysDeptDAO sysDeptDAO) {
		this.sysDeptDAO = sysDeptDAO;
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#findSysDeptLazys(com.vo.bean.SysDeptVO)
	 */
	@Override
	public String findSysDeptTreeGridGet(SysDeptVO vo) {
		List<SysDeptTreeGridGet> rows = this.sysDeptDAO.findAllTreeGridGet();
		long total = this.sysDeptDAO.findTreeGridGetTotal(); 
		SuperJson<SysDeptTreeGridGet> superJson = new SuperJson<SysDeptTreeGridGet>();
		superJson.setRows(rows);
	    superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#findSysDeptTreeGrid(com.vo.bean.SysDeptVO)
	 */
	@Override
	public String findSysDeptTreeGridGetByPid(SysDeptVO vo) {
		List<SysDeptTreeGridGet> modelLazys = this.sysDeptDAO.findTreeGridGetByPid(vo.get_parentId());
		return MyUtil.toJson(modelLazys);
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#saveSysDeptTreeGrid(com.vo.bean.SysDeptVO)
	 */
	@Override
	public String saveSysDeptTreeGridSet(SysDeptVO vo) {
		vo.setPublisher(vo.getLoginUser().getName());
		System.out.println(MyUtil.toJson(vo.getTreeGridSet()));
		this.sysDeptDAO.saveTreeGridSet(vo.getTreeGridSet());
		return null;
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#deleteSysDeptTreeGridById(com.vo.bean.SysDeptVO)
	 */
	@Override
	public String deleteSysDeptTreeGridSetById(SysDeptVO vo) {
		this.sysDeptDAO.deleteTreeGridSetById(vo.getId());
		return null;
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#updateSysDeptTreeGrid(com.vo.bean.SysDeptVO)
	 */
	
	@Override
	public String updateSysDeptTreeGridSet(SysDeptVO vo) {
		vo.setPublisher(vo.getLoginUser().getName());
		this.sysDeptDAO.updateTreeGridSet(vo.getTreeGridSet());
		return null;
	}
	@Override
	public String findSysDeptTreeGet(SysUserVO vo) {
		List<SysDeptTreeGet> rows = this.sysDeptDAO.findTreeGet();
		return MyUtil.toJson(rows);
	}
	@Override
	public String findSysRoleLazyByPage(SysRoleVO vo) {
		List<SysRoleLazy> rows = (List<SysRoleLazy>)this.sysRoleDAO.findLazyByPage(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = sysRoleDAO.findLazyTotal(); 
        SuperJson<SysRoleLazy> superJson = new SuperJson<SysRoleLazy>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveSysRoleLazy(SysRoleVO vo) {
		SysRoleLazy modelLazy = new SysRoleLazy();
		modelLazy.setName(vo.getName());
		modelLazy.setAddressBook(vo.getAddressBook());
		modelLazy.setAnniversary(vo.getAnniversary());
		modelLazy.setBus(vo.getBus());
		modelLazy.setCourse(vo.getCourse());
		modelLazy.setMap(vo.getMap());
		modelLazy.setNews(vo.getNews());
		modelLazy.setSys(vo.getSys());
		modelLazy.setPublisher(vo.getPublisher());
		this.sysRoleDAO.saveLazy(modelLazy);
		return null;
	}
	@Override
	public String updateSysRoleLazy(SysRoleVO vo) {
		SysRoleLazy modelLazy = new SysRoleLazy();
		modelLazy.setId(vo.getId());
		modelLazy.setName(vo.getName());
		modelLazy.setAddressBook(vo.getAddressBook());
		modelLazy.setAnniversary(vo.getAnniversary());
		modelLazy.setBus(vo.getBus());
		modelLazy.setCourse(vo.getCourse());
		modelLazy.setMap(vo.getMap());
		modelLazy.setNews(vo.getNews());
		modelLazy.setSys(vo.getSys());
		modelLazy.setPublisher(vo.getPublisher());
		this.sysRoleDAO.updateLazy(modelLazy);
		return null;
	}
	@Override
	public void deleteSysRoleLazyById(SysRoleVO vo) {
		SysRoleLazy modelLazy=this.sysRoleDAO.findLazyById(vo.getId());
		this.sysRoleDAO.deleteLazy(modelLazy);
	}
	@Override
	public String findSysUserLazyByPage(SysUserVO vo) {
		List<SysUserView> rows = this.sysUserDAO.findLazyByPage(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = sysUserDAO.findLazyTotal(); 
        SuperJson<SysUserView> superJson = new SuperJson<SysUserView>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	
	@Override
	public String findRoleCombogrid(SysUserVO vo) {
		List<SysRoleCombogrid> rows = this.sysRoleDAO.findAllSysRoleCombogrid();
		return MyUtil.toJson(rows);
	}
	@Override
	public void saveSysUserLazy(SysUserVO vo) {
		SysUserLazy modelLazy = new SysUserLazy();
		modelLazy.setAccount(vo.getAccount());
		modelLazy.setPassword(vo.getPassword());
		modelLazy.setName(vo.getName());
		modelLazy.setRoleId(vo.getRoleId());
		modelLazy.setDeptId(vo.getDeptId());
		modelLazy.setEmail(vo.getEmail());
		modelLazy.setTel(vo.getTel());
		modelLazy.setStatus(vo.getStatus());
		modelLazy.setPublisher(vo.getPublisher());
		this.sysUserDAO.saveLazy(modelLazy);
		
	}
	@Override
	public LoginUser findLoginUser(LoginUserVO vo) {
		return this.sysUserDAO.findLoginUser(vo.getAccount());
	}
	@Override
	public void updateSysUserLazy(SysUserVO vo) {
		SysUserLazy modelLazy = this.sysUserDAO.findSysUserLazyById(vo.getId());
		modelLazy.setAccount(vo.getAccount());
		modelLazy.setPassword(vo.getPassword());
		modelLazy.setName(vo.getName());
		modelLazy.setRoleId(vo.getRoleId());
		modelLazy.setDeptId(vo.getDeptId());
		modelLazy.setEmail(vo.getEmail());
		modelLazy.setTel(vo.getTel());
		modelLazy.setStatus(vo.getStatus());
		modelLazy.setPublisher(vo.getPublisher());
		this.sysUserDAO.saveLazy(modelLazy);
	}
	
	@Override
	public String findNewsNewsCategoryTreeGridGet(NewsNewsCategoryVO vo) {
		List<NewsNewsCategoryTreeGridGet> rows = this.newsNewsCategoryDAO.findAllTreeGridGet();
		long total = this.newsNewsCategoryDAO.findTreeGridGetTotal(); 
		SuperJson<NewsNewsCategoryTreeGridGet> superJson = new SuperJson<NewsNewsCategoryTreeGridGet>();
		superJson.setRows(rows);
	    superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#findNewsNewsCategoryTreeGrid(com.vo.bean.NewsNewsCategoryVO)
	 */
	@Override
	public String findNewsNewsCategoryTreeGridGetByPid(NewsNewsCategoryVO vo) {
		List<NewsNewsCategoryTreeGridGet> modelLazys = this.newsNewsCategoryDAO.findTreeGridGetByPid(vo.get_parentId());
		return MyUtil.toJson(modelLazys);
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#saveNewsNewsCategoryTreeGrid(com.vo.bean.NewsNewsCategoryVO)
	 */
	@Override
	public String saveNewsNewsCategoryTreeGridSet(NewsNewsCategoryVO vo) {
		vo.setPublisher(vo.getLoginUser().getName());
		this.newsNewsCategoryDAO.saveTreeGridSet(vo.getTreeGridSet());
		return null;
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#deleteNewsNewsCategoryTreeGridById(com.vo.bean.NewsNewsCategoryVO)
	 */
	@Override
	public String deleteNewsNewsCategoryTreeGridSetById(NewsNewsCategoryVO vo) {
		this.newsNewsCategoryDAO.deleteTreeGridSetById(vo.getId());
		return null;
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#updateNewsNewsCategoryTreeGrid(com.vo.bean.NewsNewsCategoryVO)
	 */
	
	@Override
	public String updateNewsNewsCategoryTreeGridSet(NewsNewsCategoryVO vo) {
		vo.setPublisher(vo.getLoginUser().getName());
		this.newsNewsCategoryDAO.updateTreeGridSet(vo.getTreeGridSet());
		return null;
	}
	@Override
	public String findNewsNewsCategoryTreeGet() {
		List<NewsNewsCategoryTreeGet> rows = this.newsNewsCategoryDAO.findTreeGet();
		return MyUtil.toJson(rows);
	}
	@Override
	public String findNewsAlbumCategoryTreeGridList(NewsAlbumCategoryVO vo) {
		List<NewsAlbumCategoryTreeGrid> rows = this.newsAlbumCategoryDAO.findNewsAlbumCategoryTreeGrids();
		long total = 0l; 
		SuperJson<NewsAlbumCategoryTreeGrid> superJson = new SuperJson<NewsAlbumCategoryTreeGrid>();
		superJson.setRows(rows);
		superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#findNewsAlbumCategoryTreeGrid(com.vo.bean.NewsAlbumCategoryVO)
	 */
	@Override
	public String findNewsAlbumCategoryTreeGridGetByPid(NewsAlbumCategoryVO vo) {
		List<NewsAlbumCategoryTreeGrid> modelLazys = this.newsAlbumCategoryDAO.findNewsAlbumCategoryTreeGridByPid(vo.get_parentId());
		return MyUtil.toJson(modelLazys);
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#saveNewsAlbumCategoryTreeGrid(com.vo.bean.NewsAlbumCategoryVO)
	 */
	@Override
	public String saveNewsAlbumCategoryTreeGridSet(NewsAlbumCategoryVO vo) {
		vo.setPublisher(vo.getLoginUser().getName());
		this.newsAlbumCategoryDAO.saveTreeGridSet(vo.getTreeGridSet());
		return null;
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#deleteNewsAlbumCategoryTreeGridById(com.vo.bean.NewsAlbumCategoryVO)
	 */
	@Override
	public String deleteNewsAlbumCategoryTreeGridSetById(NewsAlbumCategoryVO vo) {
		this.newsAlbumCategoryDAO.deleteNewsAlbumCategoryTreeGridById(vo.getId());
		return null;
	}
	/* (non-Javadoc)
	 * @see com.service.impl.SysService#updateNewsAlbumCategoryTreeGrid(com.vo.bean.NewsAlbumCategoryVO)
	 */
	
	@Override
	public String updateNewsAlbumCategoryTreeGridSet(NewsAlbumCategoryVO vo) {
		vo.setPublisher(vo.getLoginUser().getName());
		this.newsAlbumCategoryDAO.updateTreeGridSet(vo.getTreeGridSet());
		return null;
	}
	@Override
	public String findNewsAlbumCategoryTreeGet() {
		List<NewsAlbumCategoryTree> rows = this.newsAlbumCategoryDAO.findNewsAlbumCategoryTree();
		return MyUtil.toJson(rows);
	}

}
