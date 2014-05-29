/**
 * @版权：
 * @作者：丁强龙
 * @创建时间:2012年8月21日
 * @模块功能：
 * @修改记录： 1. 2.
 */
package com.sys.actions;


import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actions.BaseAction;
import com.sys.model.LoginUser;
import com.sys.services.IUserFacade;
import com.util.WebUtil;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class UserAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -175016157364925587L;
	private LoginUser user;
    private LoginUser filter;
    @Resource(name="userFacade")
    private IUserFacade userFacade = null;
    
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager.getLogger(UserAction.class);
//    private List<Sysrolepermissions> rolePerms;

    public UserAction() {
        filter = new LoginUser();
        user = new LoginUser();
       
    }

    

    public LoginUser getUser() {
		return user;
	}



	public void setUser(LoginUser user) {
		this.user = user;
	}



	public LoginUser getFilter() {
		return filter;
	}



	public void setFilter(LoginUser filter) {
		this.filter = filter;
	}



	public String initUser() throws Exception {
        System.out.println("init user......");
        return "success";
//        findPageUsers();
    }

    @RequestMapping("/sys/xlogin.do")
    public String xlogin(HttpServletRequest request,HttpServletResponse response) {
    	
    	this.user = WebUtil.request2Bean("user",request, LoginUser.class);
        if (!validateUserFilled()) {
            return "/xlogin";
        }
        boolean tresult = false;
        LoginUser tuser = null;
        try {
            tuser = userFacade.findOneByClasuse(" where userName='" + user.getUserName() + "'");
            if (tuser == null)//测试用代码
            {
                //addFieldError("user.accountno", "该账号不存在");
                return "/xlogin";
            }
            if (tuser.getPassword() != null && user.getPassword().equals(tuser.getPassword())) {
                tresult = true;
                user = tuser;
                //验证通过
            }
        } catch (Exception ex) {
            //addFieldError("user.accountno", "数据库连接异常" + ex.getMessage());
            return "/xlogin";
        }
        if (tresult) {
            if ("注销".equals(user.getCustomerStatus())) {
               // addFieldError("user.accountno", "该账号已被禁用");
            } else {

            	request.setAttribute("user", user);
                saveCookies(request,response);
                return "/loginsuccess";
            }
        } 
        return "/xlogin";
    }

    private void saveCookies(HttpServletRequest request,HttpServletResponse response) {
        Cookie uaccountnoCookie = new Cookie("uaccountno", user.getUserName());
        // Cookie upasswordCookie = new Cookie("upassword", user.getPassword());
        uaccountnoCookie.setPath("/");
        //upasswordCookie.setPath("/");
        uaccountnoCookie.setMaxAge(1296000);//15 * 24 * 60 * 60
        //upasswordCookie.setMaxAge(1296000);//15 * 24 * 60 * 60
        response.addCookie(uaccountnoCookie);
    }

    private boolean validateUserFilled() {
        boolean flag = true;
//		String rand = (String) getSession().get("rand");
//		if(this.imgCode == null || "".equals(this.imgCode))
//		{
//			addFieldError("imgCode","必须填写验证码");			
//			flag = false;
//		}
//		else if(!this.imgCode.toLowerCase().equals(rand.toLowerCase()))
//		{
//			addFieldError("imgCode","验证码不正确");
//			flag = false;
//		}
        //System.out.print(user.getUserName());
        if ("".equals(user.getUserName()) || null == user.getUserName()) {
            //this.addFieldError("user.accountno", "用户名不能为空");//"用户名不能为空！");
            flag = false;
        }
        if ("".equals(user.getPassword()) || null == user.getPassword()) {
            //this.addFieldError("user.password", "密码不能为空");// "密码不能为空！");
            flag = false;
        }
        return flag;
    }
    
}
