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
		//���´���������ȡ�����ݹ���������
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
			    String Sex = "��";
			    switch(request.getParameter("Sex")){
			        case "��":
			        	Sex = "M";
			        	break;
			        case "Ů":
			        	Sex = "F";
			        	break;
			        default:
			        	break;
		        }
			    String DeptID=request.getParameter("DeptID");
		        String Title=request.getParameter("Title");
		        int studentCount=Integer.parseInt(request.getParameter("StudentCount"));
		        int Privilege=0;
		        if(request.getParameter("Privilege").equals("��")){
		           Privilege=1;
		        }
		        String tel=request.getParameter("tel");
		        String Intro=request.getParameter("Intro");
		        // ���뵽���ݿ�
		        int i = teacherDao.updateByTeacherId(TeacherID,TeacherName,DeptID,Sex,Title,studentCount,Privilege,tel,Intro);
		        teacherDao.close();
			    if (i > 0) {
			    	result="�ɹ��޸Ľ�ʦ"+TeacherName+"��Ϣ";
					session.setAttribute("isError", "0");
				} else {
					result="�޸Ľ�ʦ"+TeacherName+"��Ϣʧ��";
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
			    Sex = "��";
			    switch(request.getParameter("Sex")){
			        case "��":
			        	Sex = "M";
			        	break;
			        case "Ů":
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
			        case "δѡ��":
			        	ChoosedState = 0;
			        	break;
			        case "����":
			        	ChoosedState = 1;
			        	break;
			        case "��̭":
			        	ChoosedState = 2;
			        	break;
			        case "�ɹ�":
			        	ChoosedState = 3;
			        	break;
			        default:
			        	break;
		        }
				// ���뵽���ݿ�
		        i = stuDao.updateByStudentID(stuID,stuName,DeptID,classID,Sex,
		        		                     grade,tel,Intro,teacherId,ChoosedState);
		        stuDao.close();
		        if (i > 0) {
			    	result="�ɹ��޸�ѧ��"+stuName+"��Ϣ";
					session.setAttribute("isError", "0");
				} else {
					result="�޸�ѧ����Ϣ"+stuName+"ʧ��";
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
				// ���뵽���ݿ�
		        i = deptDao.updateByDeptId(DeptID,deptName);
		        deptDao.close();
			    if (i > 0) {
			    	result="�ɹ��޸�ϵ"+deptName+"����Ϣ";
			    	session.setAttribute("isError", "0");
				} else {
					response.setContentType("text/html;charset=UTF-8");	
					System.out.println("���ݲ���ʧ��");
					session.setAttribute("isError", "1");
					result="�޸İ༶"+deptName+"����Ϣʧ��";
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
				// ���뵽���ݿ�
		        i = classDao.updateByClassId(classID, className, DeptID);
		        classDao.close();
			    if (i > 0) {
			    	result="�ɹ��޸İ༶"+className+"����Ϣ";
			    	session.setAttribute("isError", "0");
				} else {
					result="�޸İ༶"+className+"����Ϣʧ��";
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
	        // ���뵽���ݿ�
	        int i = teacherDao.resetPassword(TeacherID);
	        teacherDao.close();
	        if (i > 0) {
	        	result="�ɹ���������";
	        	session.setAttribute("isError", "0");
			} else {
				result="��������ʧ��";
				session.setAttribute("isError", "1");
				response.setContentType("text/html;charset=UTF-8");	
			}
		    //��ʾ��Ϣ���浽request��
			session.setAttribute("result", result);
		    response.sendRedirect(request.getContextPath()+"/admin/teacherDetail.jsp?teacherid="+TeacherID);
			break;
		case "student":
			StudentMsDAO studentDao = new StudentMsDAO();
		    String StudID=request.getParameter("StuID");
		    String StudName=request.getParameter("StuName");
			// ���뵽���ݿ�
	        i = studentDao.resetPassword(StudID);
	        studentDao.close();
	        if (i > 0) {
	        	result="�ɹ�����ѧ��"+StudName+"������";
	        	session.setAttribute("isError", "0");
			} else {
				result="����ѧ��"+StudName+"������ʧ��";
				session.setAttribute("isError", "1");
				response.setContentType("text/html;charset=UTF-8");	
			}
		    //��ʾ��Ϣ���浽request��
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
		String rules = "�ȵ��ȵ�";
		int Privilege= Integer.parseInt(request.getParameter("rules"));
		if(Privilege== 4){
			rules = "���ռ�������ɸѡ";
		}
		// ���ù���
		int i = teacherDao.setRules(Privilege);
		teacherDao.close();
		if (i > 0) {
        	result="�ɹ����ù���Ϊ"+rules;
        	session.setAttribute("isError", "0");
		} else {
			result="���ù���"+rules+"ʧ��";
			session.setAttribute("isError", "1");
			response.setContentType("text/html;charset=UTF-8");	
		}
		session.setAttribute("result", result);
	    response.sendRedirect(request.getContextPath()+"/admin/setting.jsp");
	}
	
	/*
	 *application��ȫ�����÷�Χ������Ӧ�ó����������ڲ����ļ��е�ͬһ��webApp������������Ϊ��Ӧ�ó���������ֹͣ��  
                 ������������Ͳ��������application���󣬵��ͻ��������ʵ���վ�ĸ���ҳ��֮�����ʱ�����application������ͬһ����ֱ������ ���رա�
                 ������session��ͬ���ǣ����пͻ���application������ͬһ���������пͻ�����������õ�application���� 
	 */
	private void setStudentCount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String result = "";
		TeacherMsDAO teacherDao = new TeacherMsDAO();
		String rules = "�ȵ��ȵ�";
		int StudentCount= Integer.parseInt(request.getParameter("StudentCount"));
		// ���ù���
		int i = teacherDao.setStudentCount(StudentCount);
		teacherDao.close();
		if (i > 0) {
        	result="�ɹ��������ѧ������Ϊ"+StudentCount;
        	session.setAttribute("isError", "0");
		} else {
			result="�������ѧ������Ϊ"+StudentCount+"ʧ��";
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
		// ���ù���
		System.out.println("PrivilegeModel"+PrivilegeModel);
		if(request.getParameter("PrivilegeModel")==null){
			PrivilegeModel = "off";
		}
		int i = adminDao.setPrivilegeModel(PrivilegeModel);
		adminDao.close();
		if (i > 0) {
        	result="�ɹ����õ�ʦ��ѡģʽΪ"+PrivilegeModel;
        	session.setAttribute("isError", "0");
        	ServletContext application = session.getServletContext();
        	application.setAttribute("PrivilegeModel", PrivilegeModel);
		} else {
			result="�������õ�ʦ��ѡģʽΪ"+PrivilegeModel+"ʧ��";
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
		// ���ù���
		int i = adminDao.setAuthorityModel(AuthorityModel);
		adminDao.close();
		if (i > 0) {
        	result="�ɹ�����ϵͳ����ģʽΪ"+AuthorityModel;
        	session.setAttribute("isError", "0");
        	ServletContext application = session.getServletContext();
        	application.setAttribute("AuthorityModel", AuthorityModel);
		} else {
			result="��������ϵͳ����ģʽΪ"+AuthorityModel+"ʧ��";
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
		System.out.println("���õ�����ʦ�ķ�ѡģʽ"+TeacherID+" "+TeacherName+SinglePrivilege);
		// ���ù���
		int i = teacherDao.setPrivilege(TeacherID, SinglePrivilege);
		teacherDao.close();
		if (i > 0) {
        	result="�ɹ�����"+TeacherName+"��ʦ��ѡģʽ";
        	session.setAttribute("isError", "0");
		} else {
			result="���õ�ʦ"+TeacherName+"��ѡģʽ"+"ʧ��";
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
		// ���ù���
		int i = adminDao.setLimitModel(LimitModel);
		adminDao.close();
		if (i > 0) {
        	result="�ɹ�����ѡ��ʦ����Ϊ"+LimitModel;
        	if(LimitModel.equals("off")){
        		adminDao.updateAllChoosedState();
        	}
        	session.setAttribute("isError", "0");
        	ServletContext application = session.getServletContext();
        	application.setAttribute("LimitModel", LimitModel);
		} else {
			result="����ѡ��ʦ����ʧ��"+LimitModel;
			session.setAttribute("isError", "1");
			response.setContentType("text/html;charset=UTF-8");	
		}
		session.setAttribute("result", result);
	    response.sendRedirect(request.getContextPath()+"/admin/setting.jsp");
	}
}

