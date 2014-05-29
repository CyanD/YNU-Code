package com.service;

import com.model.login.LoginUser;
import com.vo.bean.LoginUserVO;
import com.vo.bean.NewsAlbumCategoryVO;
import com.vo.bean.NewsNewsCategoryVO;

public interface PublicService {

	public abstract LoginUser findLoginUser(LoginUserVO vo);

	public abstract String findNewsAlbumCategoryTreeGet();

	public abstract String findNewsAlbumCategoryTreeGridGetByPid(
			NewsAlbumCategoryVO vo);

	public abstract String findNewsNewsCategoryTreeGet();

	public abstract String findNewsNewsCategoryTreeGridGetByPid(
			NewsNewsCategoryVO vo);


	public abstract String findRoleCombogrid();

	public abstract String findSysDeptTreeGet();

	public abstract String findAddressbookDeptTree();

	String findAddressbookDepartmentTree();

	public abstract String findCourseCategoryTree();

	public abstract String findNoticeCategoryTree();

    public abstract String findTeacherHomeKindTree();
    public abstract String findStudentFieldKindTree();    
}