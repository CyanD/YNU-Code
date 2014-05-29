/**
@模块编号：
@模块名称：控制器基类
@版权：
@作者：丁强龙
@创建时间:2010年8月5日
@模块功能：控制器基类
@修改记录：
1.
2.
 */
package com.actions;

import java.util.Map;

import java.sql.Timestamp;
import com.constants.SysConstants;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseAction{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7807688976958744216L;
	private Timestamp webSrvDate;
    private long webSrvDateLong;
    private String navigationString;
    private String navigationSplitString = ">>";
    /**
     * 调用Action的结果信息，例如success,input等
     */
    private String actionresult = "none";
    public final static String LOGIN = "login";
    /**
     * 调用Action的结果提示信息，例如 数据保存成功。
     */
    private String actionresultmessage = "none";
    
    public final static String SUCCESS = "success";
    public final static String INPUT = "input";

    //public static String ACTION_RESULT_MESSAGE = "ACTION_RESULT_MESSAGE";
    /**
     * 超级管理角色ID，程序固定为1。
     * 
     * @return
     */
    public long getSuperRoleId() {
        return SysConstants.SUPER_ROLE_ID;
    }

    public BaseAction() {
        webSrvDate = new Timestamp(System.currentTimeMillis());
    }

    public String getActionresult() {
        return actionresult;
    }

    public void setActionresult(String actionresult) {
        this.actionresult = actionresult;
    }

    public String getActionresultmessage() {
        return actionresultmessage;
    }

    public void setActionresultmessage(String actionresultmessage) {
        this.actionresultmessage = actionresultmessage;
    }

    public long getWebSrvDateLong() {
        return webSrvDateLong;
    }

    public void setWebSrvDateLong(long webSrvDateLong) {
        this.webSrvDateLong = webSrvDateLong;
    }

    public Timestamp getWebSrvDate() {
        return webSrvDate;
    }

    public void setWebSrvDate(Timestamp webSrvDate) {
        this.webSrvDate = webSrvDate;
    }
    


    /**
     * 获取当前会话
     * 
     * @return
     */
//    protected Map<String, Object> getSession() {
//        return ActionContext.getContext().getSession();
//    }

    public String getNavigationString() {
        return navigationString;
    }

    public void setNavigationString(String navigationString) {
        this.navigationString = navigationString;
    }

    public String getNavigationSplitString() {
        return navigationSplitString;
    }

    public void setNavigationSplitString(String navigationSplitString) {
        this.navigationSplitString = navigationSplitString;
    }

//    public HttpServletRequest getRequest() {
//        return ServletActionContext.getRequest();
//    }
//
//    public HttpServletResponse getResponse() {
//        return ServletActionContext.getResponse();
//    }

    public void outJsonString(String str, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		// getResponse().setContentType("text/javascript;charset=UTF-8");
		outString(str, response);
	}

	public void outJson(Object obj, HttpServletResponse response) {
		String str = JSONObject.fromObject(obj).toString();
		outJsonString(str, response);
	}

	public void outJsonArray(Object array, HttpServletResponse response) {
		outJsonString(JSONArray.fromObject(array).toString(), response);
	}

	public void outString(String str, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
		}
	}

	public void outXMLString(String xmlStr, HttpServletResponse response) {
		response.setContentType("application/xml;charset=UTF-8");
		outString(xmlStr, response);
	}
}
