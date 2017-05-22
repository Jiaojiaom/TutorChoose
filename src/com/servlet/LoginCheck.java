package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AdminMsg;

import db.AdminMsDAO;
import db.StudentDAO;
import db.TeacherDAO;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("username");
		String userPwd = request.getParameter("password");
		String role = request.getParameter("role");
		HttpSession session=request.getSession();	
		session.removeAttribute("result");
		session.removeAttribute("isError");
		//System.out.println(stuId + " " + stuPwd+ " " + role);
		if(role != null){
			System.out.println(role);
			//学生登录
			if(role.equals("student")){
				//检测用户名和密码
				StudentDAO login = new StudentDAO();
				int num=login.checkLog(userId,userPwd);
				System.out.println(num);
				if(num != 1){
					//提示错误信息，并返回主页
					request.setAttribute("failTip", "登录失败");
					response.sendRedirect("login.jsp?fail=1");
				}
				else{
					Map<String,String> ot = login.studentInfo(userId);
					System.out.println(ot);
					//跳转到主页,并将用户和密码保存再session内
					session.setAttribute("stuId", userId);
					session.setAttribute("stuPwd", userPwd);
					session.setAttribute("stuInfo",ot);
					session.setAttribute("isLogined","yes");
					session.setAttribute("role", role);
					response.sendRedirect("student/homepage.jsp");
//					request.getRequestDispatcher("student/homepage.jsp").forward(request, response);  
				}
			}else if(role.equals("teacher")){
				TeacherDAO teadao = new TeacherDAO();
				ArrayList results = teadao.queryTeacher(userId, userPwd);
				if(results == null || results.isEmpty()){
					//提示错误信息，并返回主页
					request.setAttribute("failTip", "登录失败");  
					response.sendRedirect("login.jsp?fail=1");
				}else{
					//跳转到主页,并将用户信息保存到session
					HashMap hm = (HashMap) results.get(0);
					session.setAttribute("teaId", userId);
					session.setAttribute("teaName", hm.get("teachername"));
					session.setAttribute("privilege", hm.get("privilege"));
					session.setAttribute("isLogined","yes");
					session.setAttribute("role", role);
					response.sendRedirect("teacher/homepage.jsp");
				}
			}else if(role.equals("manager")){
				String confirmPwd = "";
			    String username = "";
				try{
		    		   AdminMsDAO adminDao = new AdminMsDAO();
			    	   if(adminDao!=null){
				    	   AdminMsg adminMsg = adminDao.findByAdminId(userId);
				    	   username=adminMsg.getAdminName();
				    	   confirmPwd=adminMsg.getAPassword();
			    	   }
			    	   adminDao.close();
		    	   } catch(java.lang.NullPointerException ex){
		    		   System.out.print(ex.getMessage());
		    	   }
				if (confirmPwd.equals(userPwd)) {
			    	System.out.println("成功登陆");
					session.setAttribute("username", username);
					response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
					session.setAttribute("isLogined","yes");
					session.setAttribute("role", role);
				}
				else{
					//提示错误信息，并返回主页
					request.setAttribute("failTip", "登录失败");  
					response.sendRedirect("login.jsp?fail=1");
				}
			}
		}
		else{
			response.sendRedirect("login.jsp?fail=2");
		}
	}

}
