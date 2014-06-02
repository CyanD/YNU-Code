
package com.database;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

import com.cfg.Configuration;


public class DBPool {

	private static Logger log = org.apache.log4j.LogManager.getLogger(OracleAccess.class);
	public final static String CONF_FILENAME = "./jdbcDemo.properties";
    static {
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
        	URL url = Configuration.findAsResource(CONF_FILENAME);
            if (url == null)//没有发现配置文件
            {
                return null;
            }
            String _filename = url.getFile();
            Configuration cfg = new Configuration(_filename);
            
            String DB1_DATASOURCE = cfg.getValue("dsname");//"java:comp/env/jdbc/myoracle";

            //log.info("debug version:finding datasource...");
            InitialContext ictx = new InitialContext();
            DataSource ds = (DataSource) ictx.lookup(DB1_DATASOURCE);
            conn = ds.getConnection();
            int i = 0;
            while (conn == null || conn.isClosed()) {
                conn = ds.getConnection();
                Thread.sleep(200L);
                if (++i > 100) {
                    throw new Exception("DBPool 错误:无法获取数据库连接");
                }
            }
        } catch (Exception e) {
            Timestamp tsp = new Timestamp(System.currentTimeMillis());
            log.error(">>>>> At " + tsp.toString() + "从连接池获取连接时发生异常:" + e.getMessage());
        }
        return conn;
    }

    public static void attemptClose(ResultSet o) {
        try {
            if (o != null) {
                o.close();
            }
        } catch (Exception e) {
            log.error("关闭RS失败！");
        }
    }

    public static void attemptClose(Statement o) {
        try {
            if (o != null) {
                o.close();
            }
        } catch (Exception e) {
            log.error("关闭Statement失败！");
        }
    }

    public static void attemptClose(Connection o) {
        try {
            if (o != null) {
                o.close();
            }
        } catch (Exception e) {
            log.error("关闭连接失败！");
        }
    }
}
