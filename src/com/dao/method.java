package com.dao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.User;
import com.model.Article;
import com.model.Member;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.tools.ChStr;
import com.tools.ConnDB;
import com.model.Reply;
public class method {
	  private ConnDB conn=new ConnDB();
	  ResultSet rs = null;
	    Connection connection=null;
	    PreparedStatement stmt;
	    
	    public List<Article> findall() {
			List<Article> arts=new ArrayList();								
			String sql = "select * from t_article";			
			ResultSet rs = conn.executeQuery(sql);	
			
			try {												
				while (rs.next()) {
					Article art=new Article();
				 art.setContent(rs.getString("content"));
				 art.setId(rs.getInt("id"));
				 art.setPubDate(rs.getDate("pub_date"));
				 art.setTitle(rs.getString("title"));
				 art.setUser_id(rs.getInt("user_id"));
				arts.add(art);
											
				}
			} catch (SQLException ex) {							
			}
			conn.close();										
			return arts;
		}
	    
	    
	    public List<Article> findbyUser(int id) {
			List<Article> arts=new ArrayList();		
			try {	
				connection=(Connection) conn.getConnection();
				String sql = "select * from t_article where user_id=?";	
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setInt(1,id);
				rs= stmt.executeQuery();
				while (rs.next()) {
				Article art=new Article();
				 art.setContent(rs.getString("content"));
				 art.setId(rs.getInt("id"));
				 art.setPubDate(rs.getDate("pub_date"));
				 art.setTitle(rs.getString("title"));
				 art.setUser_id(rs.getInt("user_id"));
				arts.add(art);
											
				}
			} catch (SQLException ex) {							
			}
			conn.close();										
			return arts;
		}
	  
	    public int insertReply(Reply reply) {
			int ret=-1;
	    	try {
				connection=(Connection)conn.getConnection();
				String sql="insert into t_reply(article_id,content,username,time) values(?,?,?,?)";
				stmt = (PreparedStatement) connection.prepareStatement(sql);
		        stmt.setInt(1,reply.getId());	
				stmt.setString(2,reply.getContent());
				stmt.setString(3,reply.getUsername());
				stmt.setDate(4,(Date)reply.getTime());
				ret= stmt.executeUpdate();
				return ret;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	conn.close();
	         return ret;
	    	
	    }
	    
	    public List<Reply> findbyArticleid(int id) {
			List<Reply> reply=new ArrayList();		
			try {	
				connection=(Connection) conn.getConnection();
				String sql = "select * from t_reply where article_id=?";	
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setInt(1,id);
				rs= stmt.executeQuery();
				while (rs.next()) {
				Reply rp=new Reply();
				rp.setId(rs.getInt("article_id"));
			     rp.setContent(rs.getString("content"));
				rp.setUsername(rs.getString("username"));	
				rp.setTime(rs.getDate("time"));
				reply.add(rp);
				}
			} catch (SQLException ex) {							
			}
			conn.close();										
			return reply;
		}
	    
	    public int insertMessage(Article article){
	    	int ret=-1;
	    	try {
				connection=(Connection) conn.getConnection();
				String sql="insert into t_article(title,content,user_id) values(?,?,?)";
				stmt = (PreparedStatement) connection.prepareStatement(sql);
		        stmt.setString(1,article.getTitle());	
				stmt.setString(2,article.getContent());
				stmt.setInt(3,article.getUser_id());
				ret= stmt.executeUpdate();
				return ret;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	conn.close();
	    	return ret;
	    	
	    }
	    
	   public int deleteMessage(int articleid) {
		   
		   int ret=-1;
	    	try {
				connection=(Connection) conn.getConnection();
				String sql="delete from t_article where id=?";
				stmt = (PreparedStatement) connection.prepareStatement(sql);
		        stmt.setInt(1,articleid);	
				ret= stmt.executeUpdate();
				return ret;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	conn.close();
	    	return ret;
	    	
		   
		   
		   
	   }
	    
	    
	
}
