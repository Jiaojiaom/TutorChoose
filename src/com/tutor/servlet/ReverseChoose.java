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

import db.SelectTeacherDAO;

/**
 * Servlet implementation class ReverseChoose
 */
@WebServlet("/teacher/ReverseChoose")
public class ReverseChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReverseChoose() {
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
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out=response.getWriter();  
		
	    String result = "";
	    
		String[] stuArray = request.getParameterValues("chooseStu");
		String flag = request.getParameter("flag");
		int state = 2;
		if(flag.equals("2")){
			state = 1;
		}
		
		SelectTeacherDAO stdao = new SelectTeacherDAO();
		HttpSession session = request.getSession();
		int i = stdao.updateChoosedState(state, stuArray, (String)session.getAttribute("teaId"));
		if(i>0){
			result = "操作成功！";
			session.setAttribute("isError", "0");
		}else{
			result = "操作失败！";
			session.setAttribute("isError", "1");
		}
		
		//提示信息保存到request中
		session.setAttribute("result", result);
//		ServletContext context=getServletContext();
//		RequestDispatcher rd=context.getRequestDispatcher("/homepage.jsp");
//		rd.forward(request,response);
		response.sendRedirect("homepage.jsp");
	}  

}
