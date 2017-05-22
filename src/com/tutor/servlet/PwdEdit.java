package com.tutor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import db.TeacherDAO;

/**
 * Servlet implementation class PwdEdit
 */
@WebServlet("/teacher/PwdEdit")
public class PwdEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdEdit() {
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

		String result = "";
	    
	    //��ȡ��ԭ���롢������
	    String oldPassword = StringEscapeUtils.escapeSql(request.getParameter("oldPassword").trim());
		String newPassword = StringEscapeUtils.escapeSql(request.getParameter("newPassword").trim());
		String reNewpassword = StringEscapeUtils.escapeSql(request.getParameter("reNewPassword").trim());
		HttpSession session = request.getSession();
		if(oldPassword!=null && newPassword!=null && reNewpassword!=null) {
			if(oldPassword.equals("") || newPassword.equals("") || reNewpassword.equals("")) {
				result = "���벻��Ϊ��";
				session.setAttribute("isError", "1");
			}
			else if(!newPassword.equals(reNewpassword)) {
				result = "�����������벻һ��";
				session.setAttribute("isError", "1");
			}
			else if(newPassword.length()<6) {
				result = "���벻��С��6λ";
				session.setAttribute("isError", "1");
			}
			else {
				TeacherDAO teadao = new TeacherDAO();
				boolean rs = teadao.PwdIsTrue((String)session.getAttribute("teaId"), oldPassword);
				if(!rs){
					result = "�������";
					session.setAttribute("isError", "1");
				}else{
					int i = teadao.updatePwd((String)session.getAttribute("teaId"), newPassword);
					if(i>0){
						result = "�����޸ĳɹ�";
						session.setAttribute("isError", "0");
					}else{
						result = "�����޸�ʧ��";
						session.setAttribute("isError", "1");
					}
				}
			}
		} 
		
		//��ʾ��Ϣ���浽session��
		session.setAttribute("result", result);
//		ServletContext context=getServletContext();
//		RequestDispatcher rd=context.getRequestDispatcher("teacher/setting.jsp");
//		rd.forward(request,response);
		response.sendRedirect("setting.jsp");
	}

}
