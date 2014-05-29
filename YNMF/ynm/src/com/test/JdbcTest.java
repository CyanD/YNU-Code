package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.ConnectionFactory;
import com.database.DBAFactory;
import com.database.IDataBaseAccess;
import com.database.OracleAccess;

public class JdbcTest {
	public static void main(String[] args) throws SQLException {	
		testcommCounts();
//		IDataBaseAccess orcl=null;
//		try {
//			orcl = DBAFactory.getDataBaseAccess(false);
//			List<String> outString = new ArrayList<String>();
//			orcl.testDBConnection(outString);
//			Timestamp tsp = orcl.queryTimestamp("select sysdate from dual");
//			orcl.Expire();
//			System.out.println("tsp:"+tsp.toString());
//			System.out.println("ex:"+outString);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
	}
	
	static void testcommCounts() throws SQLException
	{
		ResultSet rs = null;
		Statement stmt = null;
		List<Connection> conns = new ArrayList<Connection>();
		Connection conn = null;
		
		
		Timestamp b= new Timestamp(System.currentTimeMillis());
		conn = ConnectionFactory.getConnection();
		for(int i=0;i<5000;i++)
		{
			
			//conns.add(conn);
			if(conn != null)
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select sysdate from dual");
				
				Timestamp o = null;
				if (rs.next()) {
					o = rs.getTimestamp(1);
				}
				rs.close();
				stmt.close();
				System.out.println(i+":true"+o.toString());
			}else{
				System.out.println(i+":false");
			}
			
			//conn.close();
		}
		Timestamp e= new Timestamp(System.currentTimeMillis());
		System.out.println("times "+(e.getTime()-b.getTime()));
	}
}
