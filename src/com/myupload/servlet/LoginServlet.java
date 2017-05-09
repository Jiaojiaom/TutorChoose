package com.myupload.servlet;

import java.io.IOException;

import javabean.AdminMsg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.AdminMsDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//以下代码用来获取表单传递过来的数据
		request.setCharacterEncoding("UTF-8");   
		AdminMsDAO adminDao = new AdminMsDAO();
		String adminId = request.getParameter("adminId");
	    String aPassword=request.getParameter("adminPassword");
	    System.out.println(adminId+aPassword);
        // 插入到数据库
	    int id=1;
        AdminMsg adminMsg = adminDao.queryByAdminId(adminId);
        id++;
        adminDao.close();
        if(adminMsg==null){
        	System.out.println("用户不存在,请注册");
        	request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (adminMsg.getAPassword().equals(aPassword)) {
	    	System.out.println("成功登陆");
	    	HttpSession session = request.getSession();
			session.setAttribute("username", adminMsg.getAdminName());
	    	request.getRequestDispatcher("importInfo.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");	
			System.out.println("登陆失败， 请再次登陆");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
