/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.constants;

/**
 *
 * @author Administrator
 */
public class SysConstants {

    /**
     * 超级管理员角色ID设定为1。
     */
    public static final int SUPER_ROLE_ID = 1;
    public final static String APP_ONlINE_USERS = "APP_ONlINE_USERS";
    /**
     * 会话中保存的登录用户信息 SESSION_SYSUSER
     */
    public final static String SESSION_SYSUSER = "SESSION_SYSUSER";
    /**
     * 会话中保存的登录用户信息 SESSION_SQR
     */
    public final static String SESSION_SQR = "SESSION_SQR";
    /**
     * 会话中保存的用户模块列表 SESSION_USER_MODULES
     */
    public final static String SESSION_USER_MODULES = "SESSION_USER_MODULES";
      /**
     * 会话中保存的用户功能列表 SESSION_USER_FUNS
     */
    public final static String SESSION_USER_FUNS = "SESSION_USER_FUNS";
    /**
     * 布尔型变量真值true的字符串表达
     */
    public final static String TRUE_STRING = "true";
    /**
     * 布尔型变量真值false的字符串表达
     */
    public final static String FALSE_STRING = "false";
    
    /**
     * Y的字符串表达
     */
    public final static String Y_STRING = "Y";
    /**
     * N的字符串表达
     */
    public final static String N_STRING = "N";
    
    /**
     * true的char表达
     */
    public final static char TRUE_CHAR = '1';
    /**
     * false的char表达
     */
    public final static char FALSE_CHAR = '0';
    
    public final static String SORT_DIRECTION_ASC = "asc";// 升序
    public final static String SORT_DIRECTION_DESC = "desc";// 降序
    public final static String CHANGE_SORT_DIRECTION_FLAG = "1";// 改变排序方式
    /**
     * 默认的编码方式
     */
    public final static String DEFAULT_ENCODING = "UTF-8";
    /**
     * 默认的报表模板配置文件
     */
    public final static String STRUTS_RPT_CONF_FILENAME = "./struts-rpt.xml";// "ISO8859-1";	
    public final static String UNDERLINE = "_";//下划线
    public final static int BATCH_INPUT_MAX_SIZE = 100;//批量录入最大记录数默认值
    public final static int BATCH_OUTPUT_MAX_SIZE = 10000;//批量导出最大记录数默认值
    /**
     * 时间格式 yyyy-MM-dd HH:mm
     */
    public static final String DT_YMDHM_FORMAT_STRING = "yyyy-MM-dd HH:mm";
    /**
     * 时间格式 yyyy-MM-dd
     */
    public static final String DT_YMD_FORMAT_STRING = "yyyy-MM-dd";
    /**
     * 时间格式 HH:mm
     */
    public static final String DT_HM_FORMAT_STRING = "HH:mm";
}
