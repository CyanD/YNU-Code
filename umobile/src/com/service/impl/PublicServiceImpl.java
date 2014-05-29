package com.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.AddressbookDepartmentDAO;
import com.dao.AddressbookDeptDAO;
import com.dao.CourseCategoryDAO;
import com.dao.NewsAlbumCategoryDAO;
import com.dao.NewsNewsCategoryDAO;
import com.dao.NoticeCategoryDAO;
import com.dao.StudentFieldKindDAO;
import com.dao.SysDeptDAO;
import com.dao.SysRoleDAO;
import com.dao.SysUserDAO;
import com.dao.TeacherHomeKindDAO;
import com.model.addressbook.work.AddressbookDepartmentTree;
import com.model.addressbook.work.AddressbookDeptTree;
import com.model.course.CourseCategoryTree;
import com.model.login.LoginUser;
import com.model.news.album.NewsAlbumCategoryTree;
import com.model.news.album.NewsAlbumCategoryTreeGrid;
import com.model.news.news.NewsNewsCategoryTreeGet;
import com.model.news.news.NewsNewsCategoryTreeGridGet;
import com.model.notice.NoticeCategoryTree;
import com.model.publicservice.StudentFieldKindTree;
import com.model.publicservice.TeacherHomeKindTree;
import com.model.sys.SysDeptTreeGet;
import com.model.sys.SysRoleCombogrid;
import com.service.PublicService;
import com.vo.bean.LoginUserVO;
import com.vo.bean.NewsAlbumCategoryVO;
import com.vo.bean.NewsNewsCategoryVO;
import com.ynu.zjx.MyUtil;


@Component("publicService")
public class PublicServiceImpl implements PublicService{
	private SysDeptDAO sysDeptDAO;
	private NewsNewsCategoryDAO newsNewsCategoryDAO;
	private NewsAlbumCategoryDAO newsAlbumCategoryDAO;
	private AddressbookDeptDAO addressbookDeptDAO;
	private CourseCategoryDAO  courseCategoryDAO;
	private NoticeCategoryDAO  noticeCategoryDAO;
	private TeacherHomeKindDAO teacherHomeKindDAO;
	private StudentFieldKindDAO studentFieldKindDAO;
	
	public StudentFieldKindDAO getStudentFieldKindDAO() {
		return studentFieldKindDAO;
	}
	@Resource
	public void setStudentFieldKindDAO(StudentFieldKindDAO studentFieldKindDAO) {
		this.studentFieldKindDAO = studentFieldKindDAO;
	}
	public TeacherHomeKindDAO getTeacherHomeKindDAO() {
		return teacherHomeKindDAO;
	}
	@Resource
	public void setTeacherHomeKindDAO(TeacherHomeKindDAO teacherHomeKindDAO) {
		this.teacherHomeKindDAO = teacherHomeKindDAO;
	}
	
	public CourseCategoryDAO getCourseCategoryDAO() {
		return courseCategoryDAO;
	}
	public NoticeCategoryDAO getNoticeCategoryDAO() {
		return noticeCategoryDAO;
	}
	@Resource
	public void setNoticeCategoryDAO(NoticeCategoryDAO noticeCategoryDAO) {
		this.noticeCategoryDAO = noticeCategoryDAO;
	}
	@Resource
	public void setCourseCategoryDAO(CourseCategoryDAO courseCategoryDAO) {
		this.courseCategoryDAO = courseCategoryDAO;
	}
	private AddressbookDepartmentDAO addressbookDepartmentDAO;
	public AddressbookDepartmentDAO getAddressbookDepartmentDAO() {
		return addressbookDepartmentDAO;
	}
	@Resource
	public void setAddressbookDepartmentDAO(
			AddressbookDepartmentDAO addressbookDepartmentDAO) {
		this.addressbookDepartmentDAO = addressbookDepartmentDAO;
	}
	public AddressbookDeptDAO getAddressbookDeptDAO() {
		return addressbookDeptDAO;
	}
	@Resource
	public void setAddressbookDeptDAO(AddressbookDeptDAO addressbookDeptDAO) {
		this.addressbookDeptDAO = addressbookDeptDAO;
	}
	private SysRoleDAO sysRoleDAO;
	private SysUserDAO sysUserDAO;
	@Override
	public LoginUser findLoginUser(LoginUserVO vo) {
		return this.sysUserDAO.findLoginUser(vo.getAccount());
	}
	@Override
	public String findNewsAlbumCategoryTreeGet() {
		List<NewsAlbumCategoryTree> rows = this.newsAlbumCategoryDAO.findNewsAlbumCategoryTree();
		return MyUtil.toJson(rows);
	}
	@Override
	public String findNewsAlbumCategoryTreeGridGetByPid(NewsAlbumCategoryVO vo) {
		List<NewsAlbumCategoryTreeGrid> modelLazys = this.newsAlbumCategoryDAO.findNewsAlbumCategoryTreeGridByPid(vo.get_parentId());
		return MyUtil.toJson(modelLazys);
	}
	@Override
	public String findNewsNewsCategoryTreeGet() {
		List<NewsNewsCategoryTreeGet> rows = this.newsNewsCategoryDAO.findTreeGet();
		return MyUtil.toJson(rows);
	}
	/* (non-Javadoc)
	 * @see com.service.impl.PublicService#findNewsNewsCategoryTreeGridGetByPid(com.vo.bean.NewsNewsCategoryVO)
	 */
	@Override
	public String findNewsNewsCategoryTreeGridGetByPid(NewsNewsCategoryVO vo) {
		List<NewsNewsCategoryTreeGridGet> modelLazys = this.newsNewsCategoryDAO.findTreeGridGetByPid(vo.get_parentId());
		return MyUtil.toJson(modelLazys);
	}
	@Override
	public String findRoleCombogrid() {
		List<SysRoleCombogrid> rows = this.sysRoleDAO.findAllSysRoleCombogrid();
		return MyUtil.toJson(rows);
	}
	@Override
	public String findSysDeptTreeGet() {
		List<SysDeptTreeGet> rows = this.sysDeptDAO.findTreeGet();
		return MyUtil.toJson(rows);
	}
	public NewsAlbumCategoryDAO getNewsAlbumCategoryDAO() {
		return newsAlbumCategoryDAO;
	}
	
	public NewsNewsCategoryDAO getNewsNewsCategoryDAO() {
		return newsNewsCategoryDAO;
	}
	
	
	public SysDeptDAO getSysDeptDAO() {
		return this.sysDeptDAO;
	}
	public SysRoleDAO getSysRoleDAO() {
		return sysRoleDAO;
	}
	
	public SysUserDAO getSysUserDAO() {
		return sysUserDAO;
	}
	
	@Resource
	public void setNewsAlbumCategoryDAO(NewsAlbumCategoryDAO newsAlbumCategoryDAO) {
		this.newsAlbumCategoryDAO = newsAlbumCategoryDAO;
	}
	
	@Resource
	public void setNewsNewsCategoryDAO(NewsNewsCategoryDAO newsNewsCategoryDAO) {
		this.newsNewsCategoryDAO = newsNewsCategoryDAO;
	}
	
	
	@Resource
	public void setSysDeptDAO(SysDeptDAO sysDeptDAO) {
		this.sysDeptDAO = sysDeptDAO;
	}
	@Resource
	public void setSysRoleDAO(SysRoleDAO sysRoleDAO) {
		this.sysRoleDAO = sysRoleDAO;
	}
	
	@Resource
	public void setSysUserDAO(SysUserDAO sysUserDAO) {
		this.sysUserDAO = sysUserDAO;
	}
	@Override
	public String findAddressbookDeptTree() {
		List<AddressbookDeptTree> rows = this.addressbookDeptDAO.findAddressbookDeptTree();
		return MyUtil.toJson(rows);
	}
	@Override
	public String findAddressbookDepartmentTree() {
		List<AddressbookDepartmentTree> rows = this.addressbookDepartmentDAO.findAddressbookDepartmentTree();
		return MyUtil.toJson(rows);
	}
	@Override
	public String findCourseCategoryTree() {
		List<CourseCategoryTree> rows = this.courseCategoryDAO.findCourseCategoryTree();
		return MyUtil.toJson(rows);
	}
	@Override
	public String findNoticeCategoryTree() {
		List<NoticeCategoryTree> rows = this.noticeCategoryDAO.findNoticeCategoryTree();
		return MyUtil.toJson(rows);
	}
    @Override
	public String findTeacherHomeKindTree() {
		List<TeacherHomeKindTree> rows = this.teacherHomeKindDAO.findAllTeacherHomeKindTree();
		return MyUtil.toJson(rows);
	}
    @Override
    public String findStudentFieldKindTree(){
    	List<StudentFieldKindTree> rows = this.studentFieldKindDAO.findAllStudentFieldKindTree();
		return MyUtil.toJson(rows);
    }
}
