package com.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.model.Reply;
import com.dao.method;
/**
 * Servlet implementation class commentManage
 */
@WebServlet("/commentManage")
public class commentManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public commentManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		
		String comment=request.getParameter("message");
		HttpSession session=request.getSession();
		int articleid=(int) session.getAttribute("articleid");
		String username=(String)session.getAttribute("username");
		
		String Nowtime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

		java.sql.Date mysqldate=java.sql.Date.valueOf(Nowtime);
	   System.out.println(mysqldate);

		out.println(comment);
		out.println(username);
		out.println(articleid);
		if(username==""||username==null) {
			out.println("<script language='javascript'>alert('请先登录!');window.location.href='front/login.jsp';</script>");
		}else {
		
		Reply reply=new Reply();
		reply.setContent(comment);
        reply.setUsername(username);		
        reply.setId(articleid);	
        reply.setTime(mysqldate);
       
		method method=new method();
        int i=2;
		i=method.insertReply(reply);
		out.println(i);
		response.sendRedirect("front/article_detail.jsp");
		}
		
		
		
		
		
		
	}

}
