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

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/AddStudentInfoServlet")
public class AddStudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentInfoServlet() {
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
		StuMsDAO stuDao = new StuMsDAO();
		String StuID = request.getParameter("StuID");
	    String StuName=request.getParameter("StuName");
	    String DeptID=request.getParameter("DeptID");
	    String ClassID=request.getParameter("ClassID");
        String Sex=request.getParameter("Sex");
        String SPassword=request.getParameter("SPassword");
        Double Grade=Double.parseDouble(request.getParameter("Grade"));
        String tel=request.getParameter("tel");
        String Intro=request.getParameter("Intro");
        // 插入到数据库
        int i = stuDao.insert(StuID,StuName,DeptID,ClassID,Sex,SPassword,Grade,tel,Intro);
        stuDao.close();
	    if (i > 0) {
	    	System.out.println("成功插入学生"+StuName+"的数据");
	    	request.getRequestDispatcher("result.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");	
			System.out.println("数据插入失败");
		}
	}
}
