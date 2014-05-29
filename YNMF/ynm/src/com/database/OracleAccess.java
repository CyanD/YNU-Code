package com.database;

import com.core.paging.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * JDBC ORACLE数据库访问接口 该模块主要是查询功能, 注意使用完要调用Expire关闭数据库连接
 * 
 * @author dql
 * 
 */
public class OracleAccess implements IDataBaseAccess {

	
	private Connection conn = null;
	private boolean closeConn = false;
	private static Logger log = org.apache.log4j.LogManager.getLogger(OracleAccess.class);

	public OracleAccess() {
	}

	public OracleAccess(boolean closeConn) {
		this.closeConn = closeConn;
	}

	public OracleAccess(Connection _conn, boolean closeConn) {
		conn = _conn;
		this.closeConn = closeConn;
		// if(_conn != null)
		// {
		// }
	}

//	/**
//	 * the method return a connection object
//	 */
//	public Connection getConnection()//
//	{
////		try {
////			if(conn==null||conn.isClosed())
////			{
////				conn = ConnectionFactory.getConnection();
////			}			
////		} catch (Exception ex) {
////			return null;
////		}
//		return conn;
//	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 执行查询语句，返回ResultSet
	 */
	public ResultSet executeSet(String queryText) {
		ResultSet rs = null;
		try {
//			conn = getConn();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(queryText);
			// Expire();
		} catch (Exception ex) {
			log.error("executeSet,SQL:"+queryText+"。ex："+ex.getMessage());
			return null;
		}
		return rs;
	}

	/**
	 * 执行SQL语句返回执行结果
	 * 
	 */
	public boolean executeSql(String sqlText) {
		boolean result = false;
		try {
//			conn = getConnection();
			Statement stmt = conn.createStatement();
			result = !(stmt.execute(sqlText));
			// Expire();
		} catch (Exception ex) {
			log.error("executeSql,SQL:"+sqlText+"。ex："+ex.getMessage());
			return false;
		} finally {
			if (closeConn) {
				this.Expire();
			}
		}
		return result;
	}

	/**
	 * 执行批量SQL语句返回执行结果
	 * 
	 */
	public boolean executeSqls(List<String> sqlTexts) {
		try {
//			conn = getConnection();

			conn.setAutoCommit(false);// 取消自动事务提交

			Statement stmt = conn.createStatement();

			for (int i = 0; i < sqlTexts.size(); i++) {
				if (sqlTexts.get(i) != null) {
					stmt.addBatch(sqlTexts.get(i));
				}
			}

			stmt.executeBatch();
			conn.commit();// 提交事务

			return true;
		} catch (Exception ex) {
			log.error("executeSqls,SQL:"+sqlTexts+"。ex："+ex.getMessage());
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}// 在批处理命令中，如果有一个命令出现了错误，则回滚
			}
			return false;
		} finally {
			if (closeConn) {
				this.Expire();
			}
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @throws SQLException
	 */
	public void Expire() // destroy the object
	{
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
     * 不推荐使用该方法
     */
	public ResultSet executeSet(Page page, String sqlText) {
		ResultSet rs = null;
		String sqlText2 = "";
		try {
			sqlText2 = BuilderPagerSql(page, sqlText);
//			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlText2);
			// Expire();
		} catch (Exception ex) {
			log.error("executeSet,SQL:"+sqlText2+"。ex："+ex.getMessage());
			return null;
		}
		return rs;
	}

	// /**
	// * 只适用于sqlText 带条件从句
	// */
	// public ResultSet executeSetWithClause(Page page, String sqlText,String
	// orderClause) {
	// ResultSet rs= null;
	// try
	// {
	// String sqlText2 = BuilderPagerSql(page,sqlText,orderClause,true);
	// conn=getConnection();
	// Statement stmt=conn.createStatement();
	// rs=stmt.executeQuery(sqlText2);
	// }
	// catch(Exception ex)
	// {
	// return null;
	// }
	// return rs;
	// }
	/**
	 * 构造分页查询语句
	 * 
	 * @param page
	 * @param sqlText
	 * @param withClause
	 * @return
	 */
	private String BuilderPagerSql(Page page, String sqlText) {
		/*
		 * paging query 1: SELECT * FROM (SELECT vexptransceiver.*, ROWNUM RN
		 * FROM (SELECT * FROM vexptransceiver) vexptransceiver WHERE ROWNUM <=
		 * 4) WHERE RN >= 2
		 * 
		 * 2: SELECT * FROM (SELECT page_t.*, ROWNUM RN FROM (SELECT * FROM
		 * vexptransceiver WHERE ROWNUM <= 4) page_t ) WHERE RN >= 2
		 * 
		 * 3: SELECT * FROM (SELECT page_t.*,ROWNUM RN FROM (SELECT * FROM
		 * (select * FROM Vexptransceiver t ORDER BY deliveryfactory desc) WHERE
		 * ROWNUM <=20) page_t) WHERE RN >0;
		 * 
		 * sb.append("SELECT * FROM ");
		 * 
		 * 4: select * from (select t.*,ROWNUM rn from (select * from
		 * Vexptransceiver t ORDER BY departuretime desc) t )where rn >20 and rn
		 * <=40
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM");/* use the forth method */
		sb.append("(select page_t.*,ROWNUM rn from (").append(sqlText);
		sb.append(") page_t");
		sb.append(" where ROWNUM<=").append(page.getEnd());
		sb.append(") where ");
		sb.append(" rn>").append(page.getBegin());
		// sb.append(" and rn<=").append(page.getEnd());
		return sb.toString();
	}

	public Object queryOneValue(String sqlText) {
		ResultSet rs = null;
		Object obj = null;
		try {
//			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlText);

			/**
			 * ResultSetMetaData md = rs.getMetaData(); //数据结构 int columnCount =
			 * md.getColumnCount();
			 * 
			 * for(int i=1;i<=columnCount;i++) { String colname =
			 * md.getColumnName(i); }
			 */
			if (rs != null) {
				if (rs.next()) {
					obj = rs.getObject(1);
				}
			}
			rs.close();
			conn.close();
			// Expire();
		} catch (Exception ex) {
			log.error("queryOneValue,SQL:"+sqlText+"。ex："+ex.getMessage());
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		} finally {
			if (closeConn) {
				this.Expire();
			}
		}
		return obj;
	}

	public boolean testDBConnection(List<String> outString) {

		try {
			Connection tconn = conn;//this.getConnection();
			if (tconn != null && !tconn.isClosed()) {
				return true;
			}
		} catch (Exception e) {
			outString.clear();
			outString.add(e.getMessage());
			return false;
		} finally {
			if (closeConn) {
				this.Expire();
			}
		}
		return true;
	}

	public List executeQueryOne(String sql) throws SQLException {
		List list = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList();
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					list.add(rs.getObject(j));
				}
			}
		} catch (SQLException ex) {
			log.error("executeQueryOne,SQL:"+sql+"。ex："+ex.getMessage());			
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
		return list;
	}

	public Map executeQueryOneMap(String sql) throws SQLException {
		Map map = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			log.debug(sql);
			if (rs.next()) {
				map = new HashMap();
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					map.put(rs.getMetaData().getColumnName(j).toUpperCase(),
							rs.getObject(j));
				}
			}
		} catch (SQLException ex) {
			log.error("executeQueryOneMap,SQL:"+sql+"。ex："+ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
		return map;
	}

	public Map result2Map(ResultSet rs) throws SQLException {
		Map map = null;
		if (rs.next()) {
			map = new HashMap();
			for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
				map.put(rs.getMetaData().getColumnName(j).toUpperCase(),
						rs.getObject(j));
			}
		}
		return map;
	}

	public Map executeQueryOneMapWithoutBlob(String sql) throws SQLException {
		Map map = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				map = new HashMap();
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					if (rs.getMetaData().getColumnType(j) == getBlobType()) {
						continue;
					}
					map.put(rs.getMetaData().getColumnName(j).toUpperCase(),
							rs.getObject(j));
				}
			}
		} catch (SQLException ex) {
			log.error("executeQueryOneMapWithoutBlob,SQL:"+sql+"。ex："+ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
		return map;
	}

	public Map executeQueryOneMapWithoutLob(String sql) throws SQLException {
		Map map = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				map = new HashMap();
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					if ((rs.getMetaData().getColumnType(j) == getBlobType())
							|| (rs.getMetaData().getColumnType(j) == getClobType())) {
						continue;
					}
					map.put(rs.getMetaData().getColumnName(j).toUpperCase(),
							rs.getObject(j));
				}
			}
		} catch (SQLException ex) {
			log.error("executeQueryOneMapWithoutLob,SQL:"+sql+"。ex："+ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
		return map;
	}

	public List executeQuery(String sql) throws SQLException {
		List list = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			list = new ArrayList();
			while (rs.next()) {
				List l = new ArrayList();
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					if ((rs.getMetaData().getColumnType(j) == getBlobType())
							|| (rs.getMetaData().getColumnType(j) == getClobType())
							|| (rs.getMetaData().getColumnType(j) == getLongType())) {
						continue;
					}
					l.add(rs.getObject(j));
				}
				list.add(l);
			}
			log.debug(sql);
		} catch (SQLException ex) {
			//System.out.println(ex.getMessage());
			log.error("executeQuery,SQL:"+sql+"。ex："+ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
		return list;
	}

	public List executeQueryMap(String sql) throws SQLException {
		List list = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			list = new ArrayList();
			while (rs.next()) {
				Map map = new HashMap();
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					if ((rs.getMetaData().getColumnType(j) == getBlobType())
							|| (rs.getMetaData().getColumnType(j) == getClobType())
							|| (rs.getMetaData().getColumnType(j) == getLongType())) {
						continue;
					}
					map.put(rs.getMetaData().getColumnName(j).toUpperCase(),
							rs.getObject(j));
				}

				list.add(map);
			}
			log.debug(sql);
		} catch (SQLException ex) {
			log.error("executeQueryMap,SQL:"+sql+"。ex："+ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
		return list;
	}

	public Map executeQueryMap(String keyField, String sql) throws SQLException {
		Map hmap = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			hmap = new HashMap();
			while (rs.next()) {
				Map map = new HashMap();
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					if ((rs.getMetaData().getColumnType(j) == getBlobType())
							|| (rs.getMetaData().getColumnType(j) == getClobType())
							|| (rs.getMetaData().getColumnType(j) == getLongType())) {
						continue;
					}
					map.put(rs.getMetaData().getColumnName(j).toUpperCase(),
							rs.getObject(j));
				}
				hmap.put("" + map.get(keyField.toUpperCase()), map);
			}
		} catch (SQLException ex) {
			log.error("executeQueryMap,SQL:"+sql+"。ex："+ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
		return hmap;
	}

	public void executeUpdate(String sql) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			log.error("executeUpdate,SQL:"+sql+"。ex："+ex.getMessage());
			throw new SQLException("sql执行失败：" + sql + ". Error:"
					+ ex.getMessage());
		} finally {
			pstmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
	}

	public long getMaxID(String tablename, String fieldName, String whereSQL)
			throws SQLException {
		if (whereSQL == null) {
			whereSQL = "";
		}
		String sql = "select max(" + fieldName + ") from " + tablename + " "
				+ whereSQL;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
			long l1 = -1L;
			return l1;
		} catch (Exception ex) {
			log.error("getMaxID,SQL:"+sql+"。ex："+ex.getMessage());
			return -1L;
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
	}

	public int queryInteger(String sql, boolean closeConn) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int i = 0;
			if (rs.next()) {
				i = rs.getInt(1);
			}
			return i;
		} catch (SQLException ex) {
			log.error("queryInteger,SQL:"+sql+"。ex："+ex.getMessage());
			throw new SQLException(ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
	}

	public long queryLong(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			long l = 0L;
			if (rs.next()) {
				l = rs.getLong(1);
			}
			long l1 = l;
			return l1;
		} catch (SQLException ex) {
			log.error("queryLong,SQL:"+sql+"。ex："+ex.getMessage());
			throw new SQLException(ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
	}

	public Timestamp queryTimestamp(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			Timestamp t = null;
			if (rs.next()) {
				t = rs.getTimestamp(1);
			}
			Timestamp localTimestamp1 = t;
			return localTimestamp1;
		} catch (SQLException ex) {
			log.error("queryTimestamp,SQL:"+sql+"。ex："+ex.getMessage());
			throw new SQLException(ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
	}

	public double queryDouble(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			double l = 0.0D;
			if (rs.next()) {
				l = rs.getDouble(1);
			}
			double d1 = l;
			return d1;
		} catch (SQLException ex) {
			log.error("queryDouble,SQL:"+sql+"。ex："+ex.getMessage());
			throw new SQLException(ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
	}

	public double queryFloat(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			double l = 0.0D;
			if (rs.next()) {
				l = rs.getFloat(1);
			}
			double d1 = l;
			return d1;
		} catch (SQLException ex) {
			log.error("queryFloat,SQL:"+sql+"。ex："+ex.getMessage());
			throw new SQLException(ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
	}

	public String queryString(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String str = null;
			if (rs.next()) {
				str = rs.getString(1);
			}
			String str1 = str;
			return str1;
		} catch (SQLException ex) {
			log.error("queryString,SQL:"+sql+"。ex："+ex.getMessage());
			throw new SQLException(ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				this.Expire();
			}
		}
	}

	public int getBlobType() {

		return -4;
	}

	public int getClobType() {

		return -1;
	}

	public int getLongType() {
		return -1;
	}
}
