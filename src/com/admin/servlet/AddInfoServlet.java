package com.admin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.AdminMsDAO;
import db.ClassMsDAO;
import db.DeptMsDAO;
import db.StudentMsDAO;
import db.TeacherMsDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/admin/AddStudentInfoServlet")
public class AddInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInfoServlet() {
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
		String result = "";
		request.setCharacterEncoding("UTF-8"); 
		String userType= request.getParameter("userType");
		HttpSession session = request.getSession();
		session.setAttribute("clickType", userType);
		switch(userType){
			case "teacher":
				System.out.println("开始插入老师");
				TeacherMsDAO teacherDao = new TeacherMsDAO();
				String TeacherID = request.getParameter("TeacherID");
			    String TeacherName=request.getParameter("TeacherName");
			    String Sex=request.getParameter("Sex");
			    String Title=request.getParameter("Title");
			    String DeptID=request.getParameter("DeptID");
		        String tel=request.getParameter("tel");
		        String Intro=request.getParameter("Intro");
		        // 插入到数据库
		        int i = teacherDao.addTeacher(TeacherID,TeacherName,DeptID,Sex,Title,tel,Intro);
		        teacherDao.close();
			    if (i > 0) {
			    	session.setAttribute("isError", "0");
			    	result = "成功修改教师"+TeacherName+"的信息";
				} else {
					session.setAttribute("isError", "1");
			    	result = "修改教师"+TeacherName+"的信息失败";
				}
			    session.setAttribute("result", result);
			    response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				break;
			case "student":
				StudentMsDAO stuDao = new StudentMsDAO();
				ClassMsDAO classDao = new ClassMsDAO();
				String StuID = request.getParameter("StuID");
				String StuName=request.getParameter("StuName");
			    DeptID=request.getParameter("DeptID");
			    String ClassID=request.getParameter("ClassID");
		        Sex=request.getParameter("Sex");
		        float Grade=Float.parseFloat(request.getParameter("Grade"));
		        tel=request.getParameter("tel");
		        Intro=request.getParameter("Intro");
		        // 插入到数据库
		        i = stuDao.addStudent(StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro);
		        classDao.close();
		        stuDao.close();
			    if (i > 0) {
			    	session.setAttribute("isError", "0");
			    	result = "成功插入学生"+StuName+"的数据";
			    	System.out.println("成功插入学生"+StuName+"的数据");
				} else {
					session.setAttribute("isError", "1");
			    	result = "插入学生"+StuName+"的信息失败";
				}
			    session.setAttribute("result", result);
			    response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				break;
			case "dept":
				DeptMsDAO deptDao = new DeptMsDAO();
			    DeptID=request.getParameter("DeptID");
			    String deptName=request.getParameter("DeptName");
				// 插入到数据库
		        i = deptDao.addDept(DeptID,deptName);
		        deptDao.close();
			    if (i > 0) {
			    	session.setAttribute("isError", "0");
			    	result = "成功插入系"+deptName+"的数据";
				} else {
					session.setAttribute("isError", "1");
			    	result = "插入系"+deptName+"的信息失败";
				}
			    session.setAttribute("result", result);
			    response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				break;
			case "class":
				classDao = new ClassMsDAO();
			    ClassID=request.getParameter("ClassID");
			    String className=request.getParameter("ClassName");
			    DeptID=request.getParameter("DeptID");
				// 插入到数据库
		        i = classDao.addClass(ClassID, className, DeptID);
		        classDao.close();
			    if (i > 0) {
			    	session.setAttribute("isError", "0");
			    	result = "成功插入班级"+className+"的数据";
				} else {
					session.setAttribute("isError", "1");
			    	result = "插入班级"+className+"的信息失败";
				}
			    session.setAttribute("result", result);
			    response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				break;
			case "admin":
				AdminMsDAO adminDao = new AdminMsDAO();
			    String adminID=request.getParameter("AdminID");
			    String adminName=request.getParameter("AdminName");
			    String aPassword=request.getParameter("APassword");
			    tel=request.getParameter("tel");
				// 插入到数据库
		        i = adminDao.addAdmin(adminID, adminName, aPassword,tel);
		        adminDao.close();
			    if (i > 0) {
			    	session.setAttribute("isError", "0");
			    	result = "成功插入管理员"+adminName+"的数据";
				} else {
					session.setAttribute("isError", "1");
			    	result = "插入管理员"+adminName+"的信息失败";
				}
			    session.setAttribute("result", result);
			    response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				break;
			default:
				break;
		}
	}
}
