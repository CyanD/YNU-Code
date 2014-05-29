package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DBUtil_Ext {

	public static void instatllJDBCConnection(Connection c) {
	}

	private DBUtil_Ext() {
	}

	public static int getBlobType() {
		return -4;
	}

	public static int getClobType() {
		return -1;
	}

	public static int getLongType() {
		return -1;
	}

	public static List executeQueryOne(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
		return list;
	}

	public static Map executeQueryOneMap(String sql, Connection conn,
			boolean closeConn) throws SQLException {
		Map map = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			// log.debug(sql);
			if (rs.next()) {
				map = new HashMap();
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					map.put(rs.getMetaData().getColumnName(j).toUpperCase(),
							rs.getObject(j));
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
		return map;
	}

	public static Map result2Map(ResultSet rs) throws SQLException {
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

	public static Map executeQueryOneMapWithoutBlob(String sql,
			Connection conn, boolean closeConn) throws SQLException {
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
					map.put(rs.getMetaData().getColumnName(j).toUpperCase(),rs.getObject(j));
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
		return map;
	}

	public static Map executeQueryOneMapWithoutLob(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
		return map;
	}

	public static List executeQuery(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			// log.debug(sql);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
		return list;
	}

	public static List executeQueryMap(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			// log.debug(sql);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
		return list;
	}

	public static Map executeQueryMap(String keyField, String sql,
			Connection conn, boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
		return hmap;
	}

	public static void executeUpdate(String sql, Connection conn,
			boolean closeConn) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
			throw new SQLException("sql执行失败：" + sql + ". Error:"
					+ ex.getMessage());
		} finally {
			pstmt.close();
			if (closeConn) {
				conn.close();
			}

		}
	}

	public static long getMaxID(String tablename, String fieldName,
			String whereSQL, Connection conn, boolean closeConn)
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:" + ex.getMessage());
			long l2 = -1L;
			return l2;
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
	}

	public static int executeQueryIntegerField(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:错误的方法调用！字段1不应该是int类型.");
			throw new SQLException("错误的方法调用！字段1不应该是int类型.");
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
	}

	public static long executeQueryLongField(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:错误的方法调用！字段1不应该是long类型.");
			throw new SQLException("错误的方法调用！字段1不应该是long类型.");
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
	}

	public static Timestamp executeQueryTimestampField(String sql,
			Connection conn, boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:错误的方法调用！字段1不应该是Timestamp类型." +
			// ex.getMessage());
			throw new SQLException("错误的方法调用！字段1不应该是Timestamp类型.");
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
	}

	public static double executeQueryDoubleField(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:错误的方法调用！字段1不应该是double类型.");
			throw new SQLException("错误的方法调用！字段1不应该是double类型.");
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
	}

	public static double executeQueryFloatField(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:错误的方法调用！字段1不应该是float类型.");
			throw new SQLException("错误的方法调用！字段1不应该是float类型.");
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}

		}
	}

	public static String executeQueryStringField(String sql, Connection conn,
			boolean closeConn) throws SQLException {
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
			System.out.println(ex.getMessage());
			// log.error(sql + " err:错误的方法调用！字段1不应该是String类型.");
			throw new SQLException("错误的方法调用！字段1不应该是String类型.");
		} finally {
			rs.close();
			stmt.close();
			if (closeConn) {
				conn.close();
			}
		}
	}
}