package com.stu.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.StudentDAO;

@WebServlet("/student/SelectTeacher")
public class SelectTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectTeacher() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get parameters
		request.setCharacterEncoding("utf8");
		String teacherId = request.getParameter("teacherId");
		String count = request.getParameter("selectCon");
		String pri = request.getParameter("pri");
		String[] cnt = count.split("/");
		//get the number of students who choose the teacher now
		int cntNow = Integer.parseInt(cnt[0]);
		int cntMax = Integer.parseInt(cnt[1]);
		
		StudentDAO so = new StudentDAO();
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		
		//get the attribute that if the teacher can choose students 
		String systemPri = (String) application.getAttribute("PrivilegeModel");
		
		String stuId = (String) session.getAttribute("stuId");
		Map<String,String> ot = (Map<String,String>)session.getAttribute("stuInfo");
		int grade = Integer.parseInt(ot.get("grade"));
		//get the privilege of teacher
		int privilege = 0;
		ArrayList<Map<String,String>> teacherlist = so.teacherList(stuId);
		//if the system open the privilege then teacher can choose students
		if(pri.equals("能") && systemPri.equals("on")){
			privilege = 1;
		}
		//else the teacher has the system rule
		else{
			int i = 0;
			do{
				privilege = Integer.parseInt(teacherlist.get(i).get("privilege"));
				i++;
			}while(privilege == 1);
		}
		System.out.println("privilege"+privilege);
		//get student's state now
		int state = so.studentChooseState(stuId);
		if(state == 0){
			//the student had teacher
			String result = "无法选择导师，请确认自己的状态";
			session.setAttribute("result", result);
			session.setAttribute("isError", "1");
			response.sendRedirect("detail.jsp?teacherid="+teacherId); 	
			return;
		}
		//if the teacher is my dept's teacher
		if(so.isMyDeptTeacher(stuId,teacherId) == 0){
			//the number of teacher's students is max 
			if(so.myDeptTeacher(stuId) == 1){
				String result = "请选择本专业导师，本专业导师被选择完才可选择其他专业导师";
				session.setAttribute("result", result);
				session.setAttribute("isError", "1");
				response.sendRedirect("detail.jsp?teacherid="+teacherId); 	
				return;
			}
		}
		//the number of students reached the max
		if(cntNow >= cntMax && privilege != 1 && privilege != 4){
			String result = "人数限制无法选择";
			session.setAttribute("result", result);
			session.setAttribute("isError", "1");
			response.sendRedirect("detail.jsp?teacherid="+teacherId); 	
			return;
		}
		//now student choose the teacher
			int num = so.chooseTeacher(stuId, teacherId, grade, privilege);
			System.out.println("selectResult:"+num);
			//student can choose teacher and his state is pending(unsettled)
			if(num == 3){
				String result = "正处于待定状态，请及时确认状态";
				session.setAttribute("result", result);
				session.setAttribute("isError", "0");
				request.setAttribute("teacherid", teacherId);
				response.sendRedirect("detail.jsp?teacherid="+teacherId); 	
			}
			//student can choose teacher and his state is pending(unsettled)
			else if(num == 2){
				String result = "正处于待定状态，请及时确认状态";
				session.setAttribute("result", result);
				session.setAttribute("isError", "0");
				request.setAttribute("teacherid", teacherId);
				response.sendRedirect("detail.jsp?teacherid="+teacherId);
			}
			//student's grade is not satisfied the rule
			else if(num == 4){
				String result = "你不符合当前选择导师规则";
				session.setAttribute("result", result);
				session.setAttribute("isError", "1");
				response.sendRedirect("detail.jsp?teacherid="+teacherId);
			}
			else{
				String result = "选择导师失败";
				session.setAttribute("result", result);
				session.setAttribute("isError", "1");
				response.sendRedirect("detail.jsp?teacherid="+teacherId);
			}
	}

}
