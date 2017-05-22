package com.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.AdminMsDAO;
import db.StudentMsDAO;
import db.TeacherMsDAO;
import db.DeptMsDAO;
import db.ClassMsDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/admin/UpdateStuInfoServlet")
public class UpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	ServletContext application;
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
		String functionType= request.getParameter("functionType");
		System.out.println("functionType: "+functionType);
		session = request.getSession();
		switch(functionType){
		case "updateInfo":
			saveInfo(userType, request, response);
			break;
		case "resetPassword":
			resetPassword(userType,functionType, request, response);
			break;
		case "setRules":
			setRules(request, response);
			break;
		case "setStudentCount":
			setStudentCount(request, response);
			break;
		case "togglePrivilege":
			togglePrivilege(request, response);
			break;
		case "toggleAuthority":
			toggleAuthority(request, response);
			break;
		case "toggleSinglePrivilege":
			try {
				toggleSinglePrivilege(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "toggleLimit":
			toggleLimit(request, response);
			break;
		default:
			break;
		}
	}

	private void saveInfo(String userType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String result = "";
		switch(userType){
			case "teacher":
				TeacherMsDAO teacherDao = new TeacherMsDAO();
				String TeacherID = request.getParameter("TeacherID");
			    String TeacherName=request.getParameter("TeacherName");
			    String Sex = "男";
			    switch(request.getParameter("Sex")){
			        case "男":
			        	Sex = "M";
			        	break;
			        case "女":
			        	Sex = "F";
			        	break;
			        default:
			        	break;
		        }
			    String DeptID=request.getParameter("DeptID");
		        String Title=request.getParameter("Title");
		        int studentCount=Integer.parseInt(request.getParameter("StudentCount"));
		        int Privilege=0;
		        if(request.getParameter("Privilege").equals("是")){
		           Privilege=1;
		        }
		        String tel=request.getParameter("tel");
		        String Intro=request.getParameter("Intro");
		        // 插入到数据库
		        int i = teacherDao.updateByTeacherId(TeacherID,TeacherName,DeptID,Sex,Title,studentCount,Privilege,tel,Intro);
		        teacherDao.close();
			    if (i > 0) {
			    	result="成功修改教师"+TeacherName+"信息";
					session.setAttribute("isError", "0");
				} else {
					result="修改教师"+TeacherName+"信息失败";
					session.setAttribute("isError", "1");
					response.setContentType("text/html;charset=UTF-8");	
				}
			    session.setAttribute("result", result);
			    response.sendRedirect(request.getContextPath()+"/admin/teacherDetail.jsp?teacherid="+TeacherID); 
				break;
			case "student":
				StudentMsDAO stuDao = new StudentMsDAO();
				String stuID = request.getParameter("StuID");
			    String stuName=request.getParameter("StuName");
			    Sex = "男";
			    switch(request.getParameter("Sex")){
			        case "男":
			        	Sex = "M";
			        	break;
			        case "女":
			        	Sex = "F";
			        	break;
			        default:
			        	break;
		        }
			    DeptID=request.getParameter("DeptID");
			    String classID=request.getParameter("ClassID");
		        float grade=Float.parseFloat(request.getParameter("Grade"));
		        tel=request.getParameter("tel");
		        Intro=request.getParameter("Intro");
		        String teacherId=request.getParameter("TeacherID");
		        int ChoosedState = 0;
		        String getChoosedState = request.getParameter("ChoosedState");
		        switch(getChoosedState){
			        case "未选择":
			        	ChoosedState = 0;
			        	break;
			        case "待定":
			        	ChoosedState = 1;
			        	break;
			        case "淘汰":
			        	ChoosedState = 2;
			        	break;
			        case "成功":
			        	ChoosedState = 3;
			        	break;
			        default:
			        	break;
		        }
				// 插入到数据库
		        i = stuDao.updateByStudentID(stuID,stuName,DeptID,classID,Sex,
		        		                     grade,tel,Intro,teacherId,ChoosedState);
		        stuDao.close();
		        if (i > 0) {
			    	result="成功修改学生"+stuName+"信息";
					session.setAttribute("isError", "0");
				} else {
					result="修改学生信息"+stuName+"失败";
					session.setAttribute("isError", "1");
					response.setContentType("text/html;charset=UTF-8");	
				}
			    session.setAttribute("result", result);
			    System.out.println(session.getAttribute("result"));
			    response.sendRedirect(request.getContextPath()+"/admin/studentDetail.jsp?stuid="+stuID); 
				break;
			case "dept":
				DeptMsDAO deptDao = new DeptMsDAO();
			    DeptID=request.getParameter("DeptID");
			    String deptName=request.getParameter("DeptName");
			    System.out.println(DeptID+deptName);
				// 插入到数据库
		        i = deptDao.updateByDeptId(DeptID,deptName);
		        deptDao.close();
			    if (i > 0) {
			    	result="成功修改系"+deptName+"的信息";
			    	session.setAttribute("isError", "0");
				} else {
					response.setContentType("text/html;charset=UTF-8");	
					System.out.println("数据插入失败");
					session.setAttribute("isError", "1");
					result="修改班级"+deptName+"的信息失败";
				}
			    session.setAttribute("result", result);
			    session.setAttribute("clickType", userType);
			    System.out.println(session.getAttribute("result"));
			    response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
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
			    	result="成功修改班级"+className+"的信息";
			    	session.setAttribute("isError", "0");
				} else {
					result="修改班级"+className+"的信息失败";
					session.setAttribute("isError", "1");
				}
			    session.setAttribute("result", result);
			    session.setAttribute("clickType", userType);
			    System.out.println(session.getAttribute("result"));
			    response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
				break;
			default:
				break;
		}
	}

	private void resetPassword(String userType, String functionType, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = "";
		switch(userType){
		  case "teacher":
			TeacherMsDAO teacherDao = new TeacherMsDAO();
			String TeacherID=request.getParameter("TeacherID");
	        // 插入到数据库
	        int i = teacherDao.resetPassword(TeacherID);
	        teacherDao.close();
	        if (i > 0) {
	        	result="成功重置密码";
	        	session.setAttribute("isError", "0");
			} else {
				result="重置密码失败";
				session.setAttribute("isError", "1");
				response.setContentType("text/html;charset=UTF-8");	
			}
		    //提示信息保存到request中
			session.setAttribute("result", result);
		    response.sendRedirect(request.getContextPath()+"/admin/teacherDetail.jsp?teacherid="+TeacherID);
			break;
		case "student":
			StudentMsDAO studentDao = new StudentMsDAO();
		    String StudID=request.getParameter("StuID");
		    String StudName=request.getParameter("StuName");
			// 插入到数据库
	        i = studentDao.resetPassword(StudID);
	        studentDao.close();
	        if (i > 0) {
	        	result="成功重置学生"+StudName+"的密码";
	        	session.setAttribute("isError", "0");
			} else {
				result="重置学生"+StudName+"的密码失败";
				session.setAttribute("isError", "1");
				response.setContentType("text/html;charset=UTF-8");	
			}
		    //提示信息保存到request中
	        session.setAttribute("result", result);
		    response.sendRedirect(request.getContextPath()+"/admin/studentDetail.jsp?stuid="+StudID); 
			break;
		default:
			break;
		}
	}
	
	private void setRules(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String result = "";
		TeacherMsDAO teacherDao = new TeacherMsDAO();
		String rules = "先到先得";
		int Privilege= Integer.parseInt(request.getParameter("rules"));
		if(Privilege== 4){
			rules = "按照绩点排名筛选";
		}
		// 设置规则
		int i = teacherDao.setRules(Privilege);
		teacherDao.close();
		if (i > 0) {
        	result="成功设置规则为"+rules;
        	session.setAttribute("isError", "0");
		} else {
			result="设置规则"+rules+"失败";
			session.setAttribute("isError", "1");
			response.setContentType("text/html;charset=UTF-8");	
		}
		session.setAttribute("result", result);
	    response.sendRedirect(request.getContextPath()+"/admin/setting.jsp");
	}
	
	/*
	 *application：全局作用范围，整个应用程序共享，就是在部署文件中的同一个webApp共享，生命周期为：应用程序启动到停止。  
                 服务器启动后就产生了这个application对象，当客户再所访问的网站的各个页面之间浏览时，这个application对象都是同一个，直到服务 器关闭。
                 但是与session不同的是，所有客户的application对象都是同一个，即所有客户共享这个内置的application对象 
	 */
	private void setStudentCount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String result = "";
		TeacherMsDAO teacherDao = new TeacherMsDAO();
		String rules = "先到先得";
		int StudentCount= Integer.parseInt(request.getParameter("StudentCount"));
		// 设置规则
		int i = teacherDao.setStudentCount(StudentCount);
		teacherDao.close();
		if (i > 0) {
        	result="成功设置最大学生人数为"+StudentCount;
        	session.setAttribute("isError", "0");
		} else {
			result="设置最大学生人数为"+StudentCount+"失败";
			session.setAttribute("isError", "1");
			response.setContentType("text/html;charset=UTF-8");	
		}
		session.setAttribute("result", result);
	    response.sendRedirect(request.getContextPath()+"/admin/setting.jsp");
	}
	
	private void togglePrivilege(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String result="";
		AdminMsDAO adminDao = new AdminMsDAO();
		String PrivilegeModel= request.getParameter("PrivilegeModel");
		// 设置规则
		System.out.println("PrivilegeModel"+PrivilegeModel);
		if(request.getParameter("PrivilegeModel")==null){
			PrivilegeModel = "off";
		}
		int i = adminDao.setPrivilegeModel(PrivilegeModel);
		adminDao.close();
		if (i > 0) {
        	result="成功设置导师反选模式为"+PrivilegeModel;
        	session.setAttribute("isError", "0");
        	ServletContext application = session.getServletContext();
        	application.setAttribute("PrivilegeModel", PrivilegeModel);
		} else {
			result="设置设置导师反选模式为"+PrivilegeModel+"失败";
			session.setAttribute("isError", "1");
			response.setContentType("text/html;charset=UTF-8");	
		}
		session.setAttribute("result", result);
	    response.sendRedirect(request.getContextPath()+"/admin/setting.jsp");
	}
	
	private void toggleAuthority(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	    String result="";
		AdminMsDAO adminDao = new AdminMsDAO();
		String AuthorityModel= request.getParameter("AuthorityModel");
		System.out.println(AuthorityModel);
		if(request.getParameter("AuthorityModel")==null){
			AuthorityModel = "off";
		}
		// 设置规则
		int i = adminDao.setAuthorityModel(AuthorityModel);
		adminDao.close();
		if (i > 0) {
        	result="成功设置系统开启模式为"+AuthorityModel;
        	session.setAttribute("isError", "0");
        	ServletContext application = session.getServletContext();
        	application.setAttribute("AuthorityModel", AuthorityModel);
		} else {
			result="设置设置系统开启模式为"+AuthorityModel+"失败";
			session.setAttribute("isError", "1");
			response.setContentType("text/html;charset=UTF-8");	
		}
		session.setAttribute("result", result);
	    response.sendRedirect(request.getContextPath()+"/admin/setting.jsp");
	}
	

	private void toggleSinglePrivilege(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		// TODO Auto-generated method stub
		String result="";
		TeacherMsDAO teacherDao = new TeacherMsDAO();
		String TeacherID= request.getParameter("TeacherID");
		String TeacherName= request.getParameter("TeacherName");
		teacherDao = new TeacherMsDAO();
		int SinglePrivilege=teacherDao.getRules();
		if(request.getParameter("SinglePrivilege")!=null){
			SinglePrivilege= Integer.parseInt(request.getParameter("SinglePrivilege"));
		}
		System.out.println("设置单个老师的反选模式"+TeacherID+" "+TeacherName+SinglePrivilege);
		// 设置规则
		int i = teacherDao.setPrivilege(TeacherID, SinglePrivilege);
		teacherDao.close();
		if (i > 0) {
        	result="成功设置"+TeacherName+"导师反选模式";
        	session.setAttribute("isError", "0");
		} else {
			result="设置导师"+TeacherName+"反选模式"+"失败";
			session.setAttribute("isError", "1");
			response.setContentType("text/html;charset=UTF-8");	
		}
		session.setAttribute("result", result);
		response.sendRedirect(request.getContextPath()+"/admin/teacherDetail.jsp?teacherid="+TeacherID);
	}
	
	private void toggleLimit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	    String result="";
		AdminMsDAO adminDao = new AdminMsDAO();
		String LimitModel= request.getParameter("LimitModel");
		System.out.println(LimitModel);
		if(request.getParameter("LimitModel")==null){
			LimitModel = "off";
		}
		// 设置规则
		int i = adminDao.setLimitModel(LimitModel);
		adminDao.close();
		if (i > 0) {
        	result="成功设置选导师功能为"+LimitModel;
        	if(LimitModel.equals("off")){
        		adminDao.updateAllChoosedState();
        	}
        	session.setAttribute("isError", "0");
        	ServletContext application = session.getServletContext();
        	application.setAttribute("LimitModel", LimitModel);
		} else {
			result="设置选导师功能失败"+LimitModel;
			session.setAttribute("isError", "1");
			response.setContentType("text/html;charset=UTF-8");	
		}
		session.setAttribute("result", result);
	    response.sendRedirect(request.getContextPath()+"/admin/setting.jsp");
	}
}

