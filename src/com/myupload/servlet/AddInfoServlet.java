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

import db.ClassMsDAO;
import db.DeptMsDAO;
import db.StudentMsDAO;
import db.TeacherMsDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/AddStudentInfoServlet")
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
		request.setCharacterEncoding("UTF-8"); 
		String userType= request.getParameter("userType");
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
			    	System.out.println("成功修改教师"+TeacherName+"的信息");
			    	response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				} else {
					response.setContentType("text/html;charset=UTF-8");	
					System.out.println("数据插入失败");
				}
				break;
			case "student":
				StudentMsDAO stuDao = new StudentMsDAO();
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
		        stuDao.close();
			    if (i > 0) {
			    	System.out.println("成功插入学生"+StuName+"的数据");
			    	response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				} else {
					response.setContentType("text/html;charset=UTF-8");	
					System.out.println("数据插入失败");
				}
				break;
			case "dept":
				DeptMsDAO deptDao = new DeptMsDAO();
			    DeptID=request.getParameter("DeptID");
			    String deptName=request.getParameter("DeptName");
				// 插入到数据库
		        i = deptDao.addDept(DeptID,deptName);
		        deptDao.close();
			    if (i > 0) {
			    	System.out.println("成功修改系"+deptName+"的信息");
			    	response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				} else {
					response.setContentType("text/html;charset=UTF-8");	
					System.out.println("数据插入失败");
				}
				break;
			case "class":
				ClassMsDAO classDao = new ClassMsDAO();
			    ClassID=request.getParameter("ClassID");
			    String className=request.getParameter("ClassName");
			    DeptID=request.getParameter("DeptID");
				// 插入到数据库
		        i = classDao.addClass(ClassID, className, DeptID);
		        classDao.close();
			    if (i > 0) {
			    	System.out.println("成功修改班级"+className+"的信息");
			    	response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				} else {
					response.setContentType("text/html;charset=UTF-8");	
					System.out.println("数据插入失败");
				}
				break;
			default:
				break;
		}
	}
}
