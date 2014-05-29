package com.sys.services;

import java.util.List;

import com.core.paging.Page;
import com.sys.model.LoginUser;


/**
 * @版权：
 * @作者：丁强龙
 * @创建时间:2012年8月21日
 * @模块功能：
 * @修改记录： 1. 2.
 */
public interface IUserFacade {



    public LoginUser findOneByClasuse(String clause);
    public List<LoginUser> findByClasuse(String clause);
}
