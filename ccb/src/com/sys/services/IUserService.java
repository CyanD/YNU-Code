/**
 * @版权：
 * @作者：韩强
 * @创建时间:2013年12月10日
 * @模块功能：
 * @修改记录： 1. 2.
 */
package com.sys.services;

import java.util.List;

import com.core.paging.Page;
import com.sys.model.LoginUser;
import com.sys.model.User;

public interface IUserService {
	  public List<LoginUser> findPageByClasuse(String clause, Page page);
	  public LoginUser findById(long id);
	  public String removeById(long id);
}
