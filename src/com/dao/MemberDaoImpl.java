package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.User;
import com.model.Member;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.tools.ChStr;
import com.tools.ConnDB;

public class MemberDaoImpl implements MemberDao {
    private ConnDB conn=new ConnDB();
    private ChStr chStr=new ChStr();
    Connection connection=null;
    PreparedStatement stmt;
	@Override
	public int insert(User user) {
		int ret = -1;									// 用于记录更新记录的条数
		try {		
	connection=(Connection) conn.getConnection();
	stmt = (PreparedStatement) connection.prepareStatement(
"INSERT INTO t_user(id,username,password,email,phone,qq) VALUES(?,?,?,?,?,?)");  
	stmt.setInt(1,user.getId());
	stmt.setString(2,user.getUsername());
	stmt.setString(3,user.getPassword());
	stmt.setString(4,user.getEmail());
	stmt.setString(5,user.getPhone());
	stmt.setString(6,user.getQq());

	stmt.executeUpdate();
	ret=1;
			
		} catch (Exception e) {							// 处理异常
			e.printStackTrace();							// 输出异常信息
			ret = 0;										// 设置变量的值为0，表示保存会员信息失败
		}
		conn.close();									// 关闭数据库的连接
		return ret;										// 返回更新记录的条数

		
		
		
	}

	@Override
	public List select() {
		Member form = null;								// 声明会员对象
		List<Member> users = new ArrayList();						// 创建一个List集合对象，用于保存会员信息
		String sql = "select * from tb_member";			// 查询全部会员信息的SQL语句
		ResultSet rs = conn.executeQuery(sql);					// 执行查询操作
		try {												// 捕捉异常
			while (rs.next()) {
				form = new Member();							// 实例化一个会员对象
				form.setID(Integer.valueOf(rs.getString(1)));	// 获取会员ID
				users.add(form);								// 把会员信息添加到List集合对象中
			}
		} catch (SQLException ex) {							// 处理异常
		}
		conn.close();										// 关闭数据库的连接
		return users;

		
		
	}
	public int delete(Member m) {
		String sql = "delect from tb_member where ID=" + m.getID();
		int ret = conn.executeUpdate(sql);
		conn.close();
		return 0;
	}


	// 执行修改操作
	public int update(User user) {
		int ret = -1;
		try {
			connection=(Connection) conn.getConnection();
			stmt = (PreparedStatement) connection.prepareStatement(
		"update t_user set password=?,phone=?,email=?,qq=? where username=?");  
		
			stmt.setString(1,user.getPassword());
			stmt.setString(2,user.getPhone());
			stmt.setString(3,user.getEmail());
			stmt.setString(4,user.getQq());
			stmt.setString(5,user.getUsername());

			stmt.executeUpdate();
			ret=1;
					
				} catch (Exception e) {							// 处理异常
					e.printStackTrace();							// 输出异常信息
					ret = 0;										// 设置变量的值为0，表示保存会员信息失败
				}
				conn.close();									// 关闭数据库的连接
				return ret;										// 返回更新记录的条数

				
			
			
		
	}
	public Member select1() {
		return null;
	}

}
