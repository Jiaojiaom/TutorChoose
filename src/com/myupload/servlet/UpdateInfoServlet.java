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
import db.DeptMsDAO;
import db.ClassMsDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UpdateStuInfoServlet")
public class UpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoServlet() {
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
				TeacherMsDAO teacherDao = new TeacherMsDAO();
				String TeacherID = request.getParameter("TeacherID");
			    String TeacherName=request.getParameter("TeacherName");
			    String Sex=request.getParameter("Sex");
			    String DeptID=request.getParameter("DeptID");
			    String TPassword=request.getParameter("TPassword");
		        String Title=request.getParameter("Title");
		        int studentCount=Integer.parseInt(request.getParameter("StudentCount"));
		        int Privilege=Integer.parseInt(request.getParameter("Privilege"));
		        String tel=request.getParameter("tel");
		        String Intro=request.getParameter("Intro");
		        // 插入到数据库
		        int i = teacherDao.updateByTeacherId(TeacherID,TeacherName,TPassword,DeptID,Sex,Title,studentCount,Privilege,tel,Intro);
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
				String stuID = request.getParameter("StuID");
			    String stuName=request.getParameter("StuName");
			    Sex=request.getParameter("Sex");
			    DeptID=request.getParameter("DeptID");
			    String classID=request.getParameter("ClassID");
			    String SPassword=request.getParameter("SPassword");
		        float grade=Float.parseFloat(request.getParameter("Grade"));
		        tel=request.getParameter("tel");
		        Intro=request.getParameter("Intro");
		        String teacherId=request.getParameter("TeacherID");
		        String chooseState=request.getParameter("ChooseState");
		        Calendar cal=Calendar.getInstance();//创建日历对象实例
				String date=String.format("%4d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
				String time=String.format("%2d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
				String selectDate = date+""+time;
				// 插入到数据库
		        i = stuDao.updateByStudentID(stuID,stuName,DeptID,classID,Sex,SPassword,
		        		                     grade,tel,Intro,teacherId,chooseState, selectDate);
		        stuDao.close();
			    if (i > 0) {
			    	System.out.println("成功修改学生"+stuName+"的信息");
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
		        i = deptDao.updateByDeptId(DeptID,deptName);
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
			    classID=request.getParameter("ClassID");
			    String className=request.getParameter("ClassName");
			    DeptID=request.getParameter("DeptID");
				// 插入到数据库
		        i = classDao.updateByClassId(classID, className, DeptID);
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

