<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%><%-- 导入java.sql.ResultSet类 --%>
<%-- 创建com.tools.ConnDB类的对象 --%>
<jsp:useBean id="conn" scope="page" class="com.tools.ConnDB" />
<jsp:useBean id="modify" scope="page" class="com.dao.MemberDaoImpl"/>
<jsp:useBean id="user" scope="request" class="com.model.User">

</jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>会员信息修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<%
	request.setCharacterEncoding("UTF-8");
   String username=(String)session.getAttribute("username");
	user.setUsername(username);
	
	user.setPassword(request.getParameter("password"));
   
	user.setPhone(request.getParameter("phone"));
	user.setEmail(request.getParameter("email"));
	
	
	ResultSet rs = conn.executeQuery("select * from t_user where username='" + username+ "'");
	int id=-1;
	
	
		int ret = 0;
		ret = modify.update(user);	
		if (ret != 0) {
		    session.setAttribute("username",user.getUsername());
			out.println("<script language='javascript'>alert('会员信息修改成功！');window.location.href='index.jsp';</script>");
		} else {
			out.println("<script language='javascript'>alert('会员信息修改失败！');window.location.href='modifyMember.jsp';</script>");
		}
%>
</body>
</html>
