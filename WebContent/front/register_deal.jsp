<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.ResultSet"
    pageEncoding="UTF-8"%>
 

<jsp:useBean id="conn" scope="page" class="com.tools.ConnDB" />

<jsp:useBean id="Insert" scope="page" class="com.dao.MemberDaoImpl" />

<jsp:useBean id="user" scope="request" class="com.model.User">
	<jsp:setProperty name="user" property="*" />
</jsp:useBean>

<%
	request.setCharacterEncoding("UTF-8");					
	String username =request.getParameter("username");		
	ResultSet rs = conn.executeQuery("select * from t_user where username='" 
     + username + "'");
	if (rs.next()) {										
		out.println("<script language='javascript'>alert('该账号已经存在，请重新注册！');"
				+ "window.location.href='register.jsp';</script>");
	} else {
		int ret = 0;										
		ret = Insert.insert(user);					
		if (ret != 0) {
			session.setAttribute("username", username);	
			out.println("<script language='javascript'>alert('会员注册成功！');"
				);
			response.sendRedirect("login.jsp");
		} else {
			out.println("<script language='javascript'>alert('会员注册失败！');"
					+ "window.location.href='register.jsp';</script>");
		}
	}
%>