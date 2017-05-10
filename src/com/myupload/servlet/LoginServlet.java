package com.myupload.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.StudentMsg;
import javabean.TeacherMsg;
import javabean.AdminMsg;

import db.StudentMsDAO;
import db.TeacherMsDAO;
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
		String userType = request.getParameter("userType");
		String userId = request.getParameter("userId");
	    String userPwd=request.getParameter("userPwd");
	    Boolean exist = false;
	    String confirmPwd = "";
	    String username = "";
	    switch(userType){
	       case "studentLogin":
	    	   try{
		    	   StudentMsDAO studentDao = new StudentMsDAO();
		    	   if(studentDao!=null){
		    		   exist=true;
			    	   StudentMsg studentMsg = studentDao.findByStudentId(userId);
			    	   username=studentMsg.getStuName();
			    	   confirmPwd=studentMsg.getSPassword();
		    	   }
		    	   studentDao.close();
	    	   } catch(java.lang.NullPointerException ex){
	    		   System.out.print(ex.getMessage());
	    	   } 
	    	   break;
	       case "teacherLogin":
	    	   try{
	    	       TeacherMsDAO teacherDao = new TeacherMsDAO();
		    	   if(teacherDao!=null){
		    		   exist=true;
			    	   TeacherMsg teacherMsg = teacherDao.findByTeacherID(userId);
			    	   username=teacherMsg.getTeacherName();
			    	   confirmPwd=teacherMsg.getTPassword();
		    	   }
		    	   teacherDao.close();
	    	   } catch(java.lang.NullPointerException ex){
	    		   System.out.print(ex.getMessage());
	    	   } 	  
	    	   break;
	       case "adminLogin":
	    	   try{
	    		   AdminMsDAO adminDao = new AdminMsDAO();
		    	   if(adminDao!=null){
		    		   exist=true;
			    	   AdminMsg adminMsg = adminDao.findByAdminId(userId);
			    	   username=adminMsg.getAdminName();
			    	   confirmPwd=adminMsg.getAPassword();
		    	   }
		    	   adminDao.close();
	    	   } catch(java.lang.NullPointerException ex){
	    		   System.out.print(ex.getMessage());
	    	   } 
	    	   break;
	    } 
	    
	    if(exist==false){
	    	response.setContentType("text/html;charset=UTF-8");	
			System.out.println("用户不存在，请重新登陆");
			request.getRequestDispatcher("login.jsp").forward(request, response);
	    }else if (confirmPwd.equals(userPwd)) {
	    	System.out.println("成功登陆");
	    	HttpSession session = request.getSession();
			session.setAttribute("username", username);
	    	request.getRequestDispatcher("dataList.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");	
			System.out.println("登陆失败， 请再次登陆");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
