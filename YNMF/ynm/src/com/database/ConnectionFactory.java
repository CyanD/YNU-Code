/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.cfg.Configuration;

/**
 *
 * @author dql
 */
public class ConnectionFactory {
	
	public final static String DATABASE_TYPE = "databasetype";
    public final static String DATABASE_TYPE_ORACLE = "oracle";
    public final static String CONN_ATTRIBUTE_DIRVER = "driver";
    public final static String CONN_ATTRIBUTE_PASSWORD = "password";
    public final static String CONN_ATTRIBUTE_USERNAME = "username";
    public final static String CONN_ATTRIBUTE_URL = "url";
    public final static String CONF_FILENAME = "./jdbcDemo.properties";//表示在Class根目录下

    static String dirver = "";
    static String url = "";
    static String username = "";
    static String password = "";
    private static Logger log = org.apache.log4j.LogManager.getLogger(OracleAccess.class);
    static {
    }

    public static Connection getConnection() {
    	 URL url = Configuration.findAsResource(CONF_FILENAME);
         if (url == null)//没有发现配置文件
         {
             return null;
         }
         String _filename = url.getFile();
         Configuration cfg = new Configuration(_filename);

         //String _databasetype = cfg.getValue(DATABASE_TYPE);
         ConnectionFactory.dirver = cfg.getValue(CONN_ATTRIBUTE_DIRVER);
         ConnectionFactory.username = cfg.getValue(CONN_ATTRIBUTE_USERNAME);
         ConnectionFactory.password = cfg.getValue(CONN_ATTRIBUTE_PASSWORD);
         ConnectionFactory.url = cfg.getValue(CONN_ATTRIBUTE_URL);
         
        return getConnection(ConnectionFactory.dirver, ConnectionFactory.url, ConnectionFactory.username, ConnectionFactory.password);
    }

    public static Connection getConnection(String tdirver, String turl, String tusername, String tpassword)//
    {
        Connection conn = null;
        try {
            int i = 0;
            Class.forName(tdirver);
            conn = DriverManager.getConnection(turl, tusername, tpassword);
            while (conn == null || conn.isClosed()) {
            	Class.forName(tdirver);
                conn = DriverManager.getConnection(turl, tusername, tpassword);
                Thread.sleep(200L);
                if (++i > 100) {
                    throw new Exception("ConnectionFactory 错误:尝试100次后仍然无法获得数据库连接");
                }
            }
        } catch (Exception ex) {
        	log.error(">>>>>getConnection,ex:"+ex.getMessage());
            return null;
        }
        return conn;
    }
}
