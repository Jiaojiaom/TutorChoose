package com.tutor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.TeacherDAO;

/**
 * Servlet implementation class InfoEdit
 */
@WebServlet("/teacher/InfoEdit")
public class InfoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoEdit() {
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
		request.setCharacterEncoding("UTF-8");
        
		String result = "";
		
		//获取个人信息
		String teacherTel = request.getParameter("teacherTel");
		String teacherIntro = request.getParameter("teacherIntro");
		teacherIntro = new String(teacherIntro.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("teacherIntro:"+teacherIntro);
		
		TeacherDAO teadao = new TeacherDAO();
		HttpSession session = request.getSession();
		int i = teadao.updateInfo((String)session.getAttribute("teaId"), teacherIntro, teacherTel);
		if(i>0){
			session.setAttribute("isError", "0");
		}else{
			result = "个人信息保存失败";
			session.setAttribute("isError", "1");
		} 
		
		//提示信息保存到session中并重定向
		session.setAttribute("result", result);
//		ServletContext context=getServletContext();
//		RequestDispatcher rd=context.getRequestDispatcher("teacher/setting.jsp");
//		rd.forward(request,response);
		response.sendRedirect("setting.jsp");
	}

}
