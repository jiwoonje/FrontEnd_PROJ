package util;

import java.sql.*;

public class JDBCUtil 
{
	public JDBCUtil () {	
		System.out.println("JDBCUtil 호출 성공  ");
	}
	
	public static Connection getConnection () {
		Connection conn = null; 
		 
		String driver = "oracle.jdbc.driver.OracleDriver";  
		String url = "jdbc:oracle:thin:@localhost:1521:XE";  
	
		try { 
			Class.forName(driver);  
			conn = DriverManager.getConnection(url,"C##HR33", "1234"); 
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} return conn; 
	}
	
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}	
	}
	
	public static void close(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		} if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}	
	}
	
	public static void close(ResultSet rs , PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		} if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		} if (conn != null)	{
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
