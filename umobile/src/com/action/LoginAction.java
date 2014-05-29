package com.action;

import java.io.ByteArrayInputStream;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.login.LoginUser;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.service.SysService;
import com.util.Sys;
import com.util.VerificationCodeUtil;
import com.vo.bean.LoginUserVO;
import com.ynu.zjx.SuperAction;
@Component("loginAction")
@Scope("prototype")
public class LoginAction extends SuperAction  implements ModelDriven<LoginUserVO>{
	private LoginUserVO vo = new LoginUserVO();
	private SysService sysService;
	public String execute() throws Exception {
		if(vo.getSecurityCode()==null||"".equals(vo.getSecurityCode())||!vo.getSecurityCode().toLowerCase().equals(session.get(Sys.SECURITYCODE))){
        	return Action.LOGIN;
        }
		loginUser = (LoginUser)this.session.get(Sys.LOGINUSER);
		if(loginUser!=null&&loginUser.getAccount().equals(vo.getAccount())&&loginUser.getPassword().equals(vo.getPassword())){
			return SUCCESS;
		}
		if ( vo.getAccount() == null || "" .equals( vo.getAccount().trim() )){ 
			return Action.LOGIN;
        } 
        if ( vo.getPassword() == null || "" . equals ( vo.getPassword().trim() )){
        	return Action.LOGIN;
        }
         loginUser = this.sysService.findLoginUser(vo);
        if(loginUser==null){
        	return Action.LOGIN;
        }
    	if(!vo.getPassword().equals(loginUser.getPassword())){
    		return Action.LOGIN;
        }
    	if("未激活".equals(loginUser.getStatus())){
        	return Action.LOGIN;
        }
        if(loginUser.getLoginDept()==null){
	        	return Action.LOGIN;
	    }
        if(loginUser.getLoginRole()==null){
	        	return Action.LOGIN;
	    }
        this.session.put(Sys.LOGINUSER, loginUser);
		return SUCCESS;
	}
	public String doValidate(){
		if(vo.getSecurityCode()==null||"".equals(vo.getSecurityCode())){
        	resultJson.setMsg("验证码不能为空！");
        	print("验证码不能为空！");
			this.outputJson(toJsonString(resultJson), true);
        	return null;
        }else if(!vo.getSecurityCode().toLowerCase().equals(session.get(Sys.SECURITYCODE))){
        	print(session.get(Sys.SECURITYCODE));
        	resultJson.setMsg("验证码不正确！");
        	print("验证码不正确！");
			this.outputJson(toJsonString(resultJson), true);
        	return null;
        }
		if ( vo.getAccount() == null || "" .equals(  vo.getAccount().trim() )){ 
			resultJson.setMsg("用户名不能为空！");
			print("用户名不能为空！");
			this.outputJson(toJsonString(resultJson), true);
			return null;
        } 
        if ( vo.getPassword() == null || "" . equals ( vo.getPassword().trim() )){
        	resultJson.setMsg("密码不能为空！");
        	print("密码不能为空！");
			this.outputJson(toJsonString(resultJson), true);
        	return null;
        }
        
		try {
			loginUser = this.sysService.findLoginUser(vo);
			print(loginUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if(loginUser==null){
        	print(loginUser);
        	resultJson.setMsg("该用户不存在！");
        	print("该用户不存在！");
			this.outputJson(toJsonString(resultJson), true);
        	return null;
        }else if(!vo.getPassword().equals(loginUser.getPassword())){
        	print("密码不正确！");
        	resultJson.setMsg("密码不正确！");
			this.outputJson(toJsonString(resultJson), true);
        	return null;
        }else if("未激活".equals(loginUser.getStatus())){
        	print("该用户已被禁用！请联系管理员解决！");
        	resultJson.setMsg("该用户已被禁用！请联系管理员解决！");
			this.outputJson(toJsonString(resultJson), true);
        	return null;
        }else if(loginUser.getLoginDept()==null){
        	print("所属部门已被管理员删除，请联系管理员解决！");
        	resultJson.setMsg("所属部门已被管理员删除，请联系管理员解决！");
			this.outputJson(toJsonString(resultJson), true);
        	return null;
        }else if(loginUser.getLoginRole()==null){
        	print("权限已被管理员删除，请联系管理员解决！");
        	resultJson.setMsg("权限已被管理员删除，请联系管理员解决！");
			this.outputJson(toJsonString(resultJson), true);
        	return null;
        }
        resultJson.setSuccess(true);
        this.outputJson(toJsonString(resultJson), true);
        this.session.put(Sys.LOGINUSER, loginUser);
		return null;
	}
	@Override
	public LoginUserVO getModel() {
		return this.vo;
	}

	public SysService getSysService() {
		return sysService;
	}
	public LoginUserVO getVo() {
		return vo;
	}
	@Resource
	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	public void setVo(LoginUserVO vo) {
		this.vo = vo;
	}
	private ByteArrayInputStream imageStream;
    //session域
    
    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }
    
    
    public String showImage() throws Exception {
    	VerificationCodeUtil vcu = VerificationCodeUtil.Instance();   
    	imageStream=vcu.getImage();   
    	String  securityCode = vcu.getVerificationCodeValue().toLowerCase();
    	print(securityCode);
        session.put(Sys.SECURITYCODE, securityCode);
        return "image";
    }

}
