package com.admin.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import db.ClassMsDAO;
import db.DeptMsDAO;
import db.StudentMsDAO;
import db.TeacherMsDAO;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
   public static void readExcel(HttpSession session, String pathname, String type) {
	   //���浽���ݿ�
	   try {
		   // ���ļ�
		   Workbook book = Workbook.getWorkbook(new File(pathname));
		   // ȡ�õ�һ��sheet
		   Sheet sheet = book.getSheet(0);
		   // ȡ������
		   int rows = sheet.getRows();
		   System.out.println(type);
		   if(type=="stuExcel"){
			   importStuMsg(session, sheet, rows);
		   } else if(type=="teacherExcel"){
			   importTeacherMsg(session, sheet, rows);
		   } else if(type=="deptExcel"){
			   importDeptMsg(session, sheet, rows);
		   } else if(type=="classExcel"){
			   importClassMsg(session, sheet, rows);
		   }
		   // �ر��ļ�
		   book.close();
	   } catch (BiffException e) {
		   e.printStackTrace();
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
   }
   
   // ����ѧ����Ϣ
   public static void importStuMsg(HttpSession session, Sheet sheet,int rows) {
	   StudentMsDAO stuDao=new StudentMsDAO();
	   String result = "����ɹ�";
	   String studentList="";
	   session.setAttribute("isError", "0");
	   for(int i=1;i<rows;i++) {
		   String StuID=sheet.getCell(0,i).getContents();
		   String StuName=sheet.getCell(1,i).getContents();
		   String DeptID=sheet.getCell(2,i).getContents();
		   String ClassID=sheet.getCell(3,i).getContents();
	       String Sex=sheet.getCell(4,i).getContents();
	       Float Grade=Float.parseFloat(sheet.getCell(6,i).getContents());
	       String tel=sheet.getCell(7,i).getContents();
	       String Intro=sheet.getCell(8,i).getContents();
	       // ���뵽���ݿ�
		   int j = stuDao.addStudent(StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro);
		   if (j == -1) {
				session.setAttribute("isError", "1");
//				studentList += StuName+" ";
//		    	result = "ϵ������, ����ѧ��"+studentList+"����Ϣʧ��";
		    	result = "ϵ������, ����ѧ������Ϣʧ��";
		   }
		   else if (j == -2 ) {
				session.setAttribute("isError", "1");
//				studentList += StuName+" ";
//		    	result = "�༶������, ����ѧ��"+studentList+"����Ϣʧ��";
		    	result = "�༶������, ����ѧ������Ϣʧ��";
		   }
	   }
	   session.setAttribute("result", result);
	   stuDao.close();
   }
   
   // �����ʦ��Ϣ
   public static void importTeacherMsg(HttpSession session, Sheet sheet,int rows) {
	   TeacherMsDAO teacherDao=new TeacherMsDAO();
	   String result = "����ɹ�";
	   String teacherList="";
	   session.setAttribute("isError", "0");
	   for(int i=1;i<rows;i++) {
		   String TeacherID=sheet.getCell(0,i).getContents();
		   String TeacherName=sheet.getCell(1,i).getContents();
	       String DeptID=sheet.getCell(3,i).getContents();
	       String Sex=sheet.getCell(4,i).getContents();
	       String title=sheet.getCell(5,i).getContents();
	       String tel=sheet.getCell(8,i).getContents();
	       String Intro=sheet.getCell(9,i).getContents();
	       // ���뵽���ݿ�
	       System.out.println(TeacherID+TeacherName+DeptID+Sex+tel+Intro);
	       int j = teacherDao.addTeacher(TeacherID,TeacherName,DeptID,Sex,title,tel,Intro);
	       if (j < 0) {
				session.setAttribute("isError", "1");
//				teacherList += TeacherName+" ";
//		    	result = "ϵ������, �����ʦ"+teacherList+"����Ϣʧ��";
		    	result = "ϵ������, �����ʦʧ��";
		   }
	   }
	   session.setAttribute("result", result);
	   teacherDao.close();
   }

   // ����༶��Ϣ
   public static void importDeptMsg(HttpSession session, Sheet sheet,int rows) {
	   DeptMsDAO deptDao=new DeptMsDAO();
	   String result = "����ɹ�";
	   String deptList="";
	   session.setAttribute("isError", "0");
	   for(int i=1;i<rows;i++) {
		   String DeptID=sheet.getCell(0,i).getContents();
		   String DeptName=sheet.getCell(1,i).getContents();
	       // ���뵽���ݿ�
		   int j = deptDao.addDept(DeptID,DeptName); 
		   if (j < 0) {
				session.setAttribute("isError", "1");
//				deptList += DeptName+" ";
//		    	result = "����ϵ"+deptList+"����Ϣʧ��";
		    	result = "����ϵ����Ϣʧ��";
		   }
	   }
	   session.setAttribute("result", result);
	   deptDao.close();
   }
   
   // ����ϵ��Ϣ
   public static void importClassMsg(HttpSession session, Sheet sheet,int rows) {
	   ClassMsDAO classDao=new ClassMsDAO();
	   String result = "����ɹ�";
	   String classList="";
	   session.setAttribute("isError", "0");
	   for(int i=1;i<rows;i++) {
		   String ClassID=sheet.getCell(0,i).getContents();
		   String ClassName=sheet.getCell(1,i).getContents();
		   String DeptID=sheet.getCell(2,i).getContents();
	       // ���뵽���ݿ�
		   int j = classDao.addClass(ClassID,ClassName,DeptID);
		   if (j < 0) {
				session.setAttribute("isError", "1");
//				classList += ClassName+" ";
//		    	result = "ϵ�����ڣ� ����༶"+classList+"����Ϣʧ��";
		    	result = "ϵ�����ڣ� ����༶��Ϣʧ��";
		   }
	   }
	   session.setAttribute("result", result);
	   classDao.close();
   }
}
