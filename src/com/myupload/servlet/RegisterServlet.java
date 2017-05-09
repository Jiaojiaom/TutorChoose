package com.myupload.servlet;

import java.io.IOException;

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
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String adminName = request.getParameter("adminName");
	    String adminPassword=request.getParameter("adminPassword");
        // 插入到数据库
	    int id=1;
        int i = adminDao.addAdmin(id+"", adminName,adminPassword, "");
        id++;
        adminDao.close();
	    if (i > 0) {
	    	System.out.println("成功注册管理员"+adminName);
	    	HttpSession session = request.getSession();
	    	session.setAttribute("username", adminName);
	    	request.getRequestDispatcher("importInfo.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");	
			System.out.println("注册失败");
		}
	}
}
