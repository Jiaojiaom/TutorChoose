package com.myupload.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.StudentMsDAO;
import db.TeacherMsDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UpdateStuInfoServlet")
public class UpdateStudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentInfoServlet() {
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
		StudentMsDAO stuDao = new StudentMsDAO();
		String stuID = request.getParameter("StuID");
	    String stuName=request.getParameter("StuName");
	    String Sex=request.getParameter("Sex");
	    String DeptID=request.getParameter("DeptID");
	    String classID=request.getParameter("ClassID");
	    String SPassword=request.getParameter("SPassword");
        float grade=Float.parseFloat(request.getParameter("Grade"));
        String tel=request.getParameter("tel");
        String Intro=request.getParameter("Intro");
        String teacherId=request.getParameter("TeacherID");
        String chooseState=request.getParameter("ChooseState");
        Calendar cal=Calendar.getInstance();//创建日历对象实例
		String date=String.format("%4d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
		String time=String.format("%2d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
		String selectDate = date+""+time;
		// 插入到数据库
        int i = stuDao.updateByStudentID(stuID,stuName,DeptID,classID,Sex,SPassword,
        		                     grade,tel,Intro,teacherId,chooseState, selectDate);
        stuDao.close();
	    if (i > 0) {
	    	System.out.println("成功修改学生"+stuName+"的信息");
	    	request.getRequestDispatcher("studentList.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");	
			System.out.println("数据插入失败");
		}
	}
}
