package com.myupload.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.TeacherMsDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/AddTeacherInfoServlet")
public class AddTeacherInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeacherInfoServlet() {
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
		TeacherMsDAO teacherDao = new TeacherMsDAO();
		String TeacherID = request.getParameter("TeacherID");
	    String TeacherName=request.getParameter("TeacherName");
	    String TPassword=request.getParameter("TPassword");
	    String DeptID=request.getParameter("DeptID");
        String Sex=request.getParameter("Sex");
        String Title=request.getParameter("Title");
        int studentCount=Integer.parseInt(request.getParameter("studentCount"));
        int Privilege=Integer.parseInt(request.getParameter("Privilege"));
        String tel=request.getParameter("tel");
        String Intro=request.getParameter("Intro");
        // 插入到数据库
        int i = teacherDao.addTeacher(TeacherID,TeacherName,TPassword,DeptID,Sex,Title);
        teacherDao.close();
	    if (i > 0) {
	    	System.out.println("成功插入老师"+teacherDao+"的数据");
	    	request.getRequestDispatcher("teacherList.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");	
			System.out.println("数据插入失败");
		}
	}
}
