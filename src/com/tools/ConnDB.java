package com.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class ConnDB {
	private static String url = "jdbc:mysql://localhost:3306/web";
	private static String db_user ="root";
	private static String db_pwd = "123456";
	 public Statement stmt = null;
	public ResultSet rs = null;
	public PreparedStatement pstmt = null;
   public Connection conn=null;
	public static Connection getConnection() throws SQLException {
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = DriverManager.getConnection(url, db_user, db_pwd);
		return connection;	
	}
	
	public int executeUpdate(String sql) {
		int result = 0;							// 更新数据的记录条数
		try {// 捕捉异常
			conn = getConnection();				// 获取数据库连接
			// 创建用于执行SQL语句的Statement对象
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);		// 执行SQL语句
		} catch (SQLException ex) {				// 处理异常
			result = 0;							// 指定更新数据的记录条数为0，表示没有更新数据
			ex.printStackTrace();					// 输出异常信息
		}
		try {									// 捕捉异常
			stmt.close();						// 关闭用于执行SQL语句的Statement对象
		} catch (SQLException ex1) {				// 处理异常
			ex1.printStackTrace();				// 输出异常信息
		}
		return result;							// 返回更新数据的记录条数
	}

	
	public ResultSet executeQuery(String sql) {
		try {									// 捕捉异常
			conn = getConnection();				// 获取数据库连接
			// 创建用于执行SQL语句的Statement对象
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);			// 执行SQL语句
		} catch (SQLException ex) {				// 处理异常
			ex.printStackTrace();					// 输出异常信息
		}
		return rs;								// 返回查询结果
	}
	

	public void close() {
		try { 									// 捕捉异常
			if (rs != null) {
				rs.close();						// 关闭结果集对象
			}
			if (stmt != null) {
				stmt.close(); 					// 关闭Statement对象
			}
			if (conn != null) {
				conn.close(); 					// 关闭数据库连接对象
			}
		} catch (Exception e) {					// 处理异常
			e.printStackTrace(System.err);		// 输出异常信息
		}
	}
	public int executeUpdate_id(String sql) {
		int result = 0;
		try {// 捕捉异常
			conn = getConnection();// 获取数据库连接
			// 创建用于执行SQL语句的Statement对象
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);// 执行SQL语句
			 
			
		} catch (SQLException ex) {// 处理异常
			result = 0;
		}
		return result;// 返回获取结果
	}

	
}