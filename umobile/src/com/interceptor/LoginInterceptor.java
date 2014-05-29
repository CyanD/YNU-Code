package com.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.util.Sys;
import com.ynu.zjx.ResultJson;

public class LoginInterceptor extends AbstractInterceptor{
	@Override
	public String intercept(ActionInvocation invocation) throws Exception{
		System.out.println("LoginInterceptor");
		/*if(LoginAction.class == invocation.getAction().getClass()){
			return invocation.invoke();
		}*/
		@SuppressWarnings("rawtypes")
		Map mapSession = invocation.getInvocationContext().getSession();
		if(isAjaxRequest(ServletActionContext.getRequest())){
			System.out.println("isajax");
		}
		if(null == mapSession.get(Sys.LOGINUSER)){
			if(isAjaxRequest(ServletActionContext.getRequest())){
				ResultJson resultJson = new ResultJson();
				resultJson.setSuccess(false);
				resultJson.setMsg("长时间无操作，用户已失效，请重新登录！");
				Gson gson = new Gson();
				outputJson(gson.toJson(resultJson), true);
				return null;
			}
			return Action.LOGIN;
		}
		return invocation.invoke();
	}
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
	public void outputJson(String jsonString,boolean needJson){
		HttpServletResponse response = ServletActionContext.getResponse();
		if(needJson){
			response.setContentType("application/json; charset=UTF-8");
		}else{
			response.setContentType("text/html;charset=UTF-8");
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out=null;
		 try {
	            out = response.getWriter();
	            out.println(jsonString);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	        	if(out!=null){
	        		out.flush();
	        		out.close();
	        		out=null;
	        	}
	        }
	}

}
