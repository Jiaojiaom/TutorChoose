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

import db.StuMsDAO;
import db.TeacherMsDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UpdateTeacherInfoServlet")
public class UpdateTeacherInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTeacherInfoServlet() {
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
	    String Sex=request.getParameter("Sex");
	    String DeptID=request.getParameter("DeptID");
	    String TPassword=request.getParameter("TPassword");
        String Title=request.getParameter("Title");
        int studentCount=Integer.parseInt(request.getParameter("StudentCount"));
        int Privilege=Integer.parseInt(request.getParameter("Privilege"));
        String tel=request.getParameter("tel");
        String Intro=request.getParameter("Intro");
        System.out.println(TeacherID+""+TeacherName);
        System.out.println(Sex+""+DeptID);
        System.out.println(request.getParameter("StudentCount"));
        System.out.println(request.getParameter("Privilege"));
        System.out.println(TPassword+""+Title);
        System.out.println(tel+""+Intro);
        // 插入到数据库
        int i = teacherDao.updateTeacher(TeacherID,TeacherName,TPassword,DeptID,Sex,Title,studentCount,Privilege,tel,Intro);
        teacherDao.close();
	    if (i > 0) {
	    	System.out.println("成功修改教师"+TeacherName+"的信息");
	    	request.getRequestDispatcher("teacherList.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");	
			System.out.println("数据插入失败");
		}
	}
}
