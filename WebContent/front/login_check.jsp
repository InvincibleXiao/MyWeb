<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*"%>
<%@ page import="com.model.Member"%>
<%@ page import="com.tools.ConnDB"%>
<%@ page import="com.model.User" %>>
<jsp:useBean id="conn" scope="page" class="com.tools.ConnDB" />

<%
String username = request.getParameter("username");
String pwd = request.getParameter("PWD");
int id=100;
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
PreparedStatement pstmt = null;

	connection = ConnDB.getConnection();
	String sql1 = "SELECT * FROM t_user WHERE username = ? AND password = ?";
	pstmt = connection.prepareStatement(sql1);
	pstmt.setString(1, username);
	pstmt.setString(2, pwd);
	resultSet = pstmt.executeQuery();
	User users = null;
	while (resultSet.next()) {
		users = new User();
		users.setUsername(resultSet.getString("userName"));
		users.setPassword(resultSet.getString("passWord"));
		id=resultSet.getInt("id");
		session.setAttribute("user_id",id);
		users.setId(resultSet.getInt("id"));
	}
   if(users!=null){
		session.setAttribute("username", username); 
		session.setAttribute("user_id",id);
		response.sendRedirect("index.jsp");
	   
   }
   else{
	   out.println(
				"<script language='javascript'>alert('您输入的用户名或密码错误，请与管理员联系!');"
						        +"window.location.href='login.jsp';</script>");
   }
	








%>
