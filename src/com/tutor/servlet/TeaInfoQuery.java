package com.tutor.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DeptDAO;
import db.TeacherDAO;

/**
 * Servlet implementation class TeaInfoQuery
 */
@WebServlet("/teacher/TeaInfoQuery")
public class TeaInfoQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaInfoQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherDAO teadao = new TeacherDAO();
		HttpSession session = request.getSession();
		ArrayList infoList = teadao.queryTeacherInfo((String)session.getAttribute("teaId"));
		HashMap info = (HashMap) infoList.get(0);
		
		DeptDAO deptdao = new DeptDAO();
		info.put("deptname", deptdao.queryDeptName((String)info.get("deptid")));
		
		request.setAttribute("info", info);
		//ÇëÇó×ª·¢
		RequestDispatcher rd = request.getRequestDispatcher("info.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
