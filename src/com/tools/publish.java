package com.tools;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.model.Article;
import com.dao.method;
/**
 * Servlet implementation class publish
 */
@WebServlet("/publish.do")
public class publish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public publish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	HttpSession session=request.getSession();
	PrintWriter out=response.getWriter();
		String title=request.getParameter("title");
		String message=request.getParameter("message");
		method method=null;
		int end=5;
		String username=(String)session.getAttribute("username");
		int user_id=0;
		user_id=(Integer)session.getAttribute("user_id");
		Article article=null;
		out.println(user_id);
		out.println(title);
		out.println(message);
		out.println(username);
		if(username==""||username==null||user_id==0) {
			out.println("<script language='javascript'>alert('请先登录!');window.location.href='login.jsp';</script>");
		}else {
		     article=new Article();
		     article.setContent(message);
		     article.setTitle(title);
			article.setUser_id(user_id);
		    method=new method();
			end=method.insertMessage(article);
			out.println(end);
		}
		response.sendRedirect("front/index.jsp");
	}

}
