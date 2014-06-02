package com.database;

import java.net.URL;

import com.cfg.Configuration;

/**
 * 获得JDBC数据库操作接口
 * 2010年9月10日
 * @author dql
 *
 */
public class DBAFactory {

    public final static String DATABASE_TYPE = "databasetype";
    public final static String DATABASE_TYPE_ORACLE = "oracle";
    public final static String CONN_ATTRIBUTE_DIRVER = "driver";
    public final static String CONN_ATTRIBUTE_DATABASE = "database";
    public final static String CONN_ATTRIBUTE_PASSWORD = "password";
    public final static String CONN_ATTRIBUTE_USERNAME = "username";
    public final static String CONN_ATTRIBUTE_URL = "url";
    public final static String CONF_FILENAME = "./jdbcDemo.properties";//表示在Class根目录下

    /**
     * 该方法目前未实现
     * @param dataBaseType
     * @return
     */
    public static IDataBaseAccess getDataBaseAccess(String dataBaseType,boolean colseConn) {
        return null;
    }

    /**
     * 默认获取IDataBaseAccess，使用配置文件信息
     * @return IDataBaseAccess
     * @throws Exception 
     */
    public static IDataBaseAccess getDataBaseAccess(boolean colseConn) throws Exception {
        IDataBaseAccess _dba = null;

        URL url = Configuration.findAsResource(CONF_FILENAME);
        if (url == null)//没有发现配置文件
        {
            return null;
        }
        String _filename = url.getFile();
        Configuration cfg = new Configuration(_filename);
        String _databasetype = cfg.getValue(DATABASE_TYPE);       

        if (DATABASE_TYPE_ORACLE.equals(_databasetype.toLowerCase())) {
            _dba = new OracleAccess(colseConn);    
            _dba.setConn(ConnectionFactory.getConnection());
        }

        return _dba;
    }
}
