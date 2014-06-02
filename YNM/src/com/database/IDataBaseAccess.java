package com.database;

import com.core.paging.Page;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author dql
 */
public interface IDataBaseAccess {

    /**
     * 属性
     * @return
     */
    public boolean testDBConnection(List<String> outString) throws SQLException;

    /**
     * 方法
     * @return
     */
    public Connection getConn();
    public void setConn(Connection conn);
    
    public void Expire();

    public boolean executeSql(String sqlText);

    public boolean executeSqls(List<String> sqlTexts);

   /**
     * 建议不使用或少使用该方法
     * @param queryText
     * @return 
     */ 
    public ResultSet executeSet(String queryText);

    /**
     * 
     * @param page
     * @param sqlText
     * @return
     */
    public ResultSet executeSet(Page page, String sqlText);

    /**
    //	 * 只适用于sqlText带条件从句
    //	 * @param page
    //	 * @param sqlText
    //	 * @return
    //	 */
//	public ResultSet executeSetWithClause(Page page,String sqlText,String orderClause);
    /**
     * 返回单个值
     * @param sqlText
     * @return
     */
    public Object queryOneValue(String sqlText);

    public List executeQueryOne(String sql) throws SQLException;

    public Map executeQueryOneMap(String sql) throws SQLException;

    public Map result2Map(ResultSet rs) throws SQLException;

    public Map executeQueryOneMapWithoutBlob(String sql) throws SQLException;

    public Map executeQueryOneMapWithoutLob(String sql) throws SQLException;

    public List executeQuery(String sql) throws SQLException;

    public List executeQueryMap(String sql) throws SQLException;

    public Map executeQueryMap(String keyField, String sql) throws SQLException;

    public void executeUpdate(String sql) throws SQLException;

    public long getMaxID(String tablename, String fieldName, String whereSQL) throws SQLException;

    public int queryInteger(String sql, boolean closeConn)
            throws SQLException;

    public long queryLong(String sql) throws SQLException;

    public Timestamp queryTimestamp(String sql) throws SQLException;

    public double queryDouble(String sql) throws SQLException;

    public double queryFloat(String sql) throws SQLException;

    public String queryString(String sql) throws SQLException;

    public int getBlobType();

    public int getClobType();

    public int getLongType();
}
