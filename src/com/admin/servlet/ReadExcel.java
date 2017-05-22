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
	   //保存到数据库
	   try {
		   // 打开文件
		   Workbook book = Workbook.getWorkbook(new File(pathname));
		   // 取得第一个sheet
		   Sheet sheet = book.getSheet(0);
		   // 取得行数
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
		   // 关闭文件
		   book.close();
	   } catch (BiffException e) {
		   e.printStackTrace();
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
   }
   
   // 导入学生信息
   public static void importStuMsg(HttpSession session, Sheet sheet,int rows) {
	   StudentMsDAO stuDao=new StudentMsDAO();
	   String result = "导入成功";
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
	       // 插入到数据库
		   int j = stuDao.addStudent(StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro);
		   if (j == -1) {
				session.setAttribute("isError", "1");
//				studentList += StuName+" ";
//		    	result = "系不存在, 导入学生"+studentList+"的信息失败";
		    	result = "系不存在, 导入学生的信息失败";
		   }
		   else if (j == -2 ) {
				session.setAttribute("isError", "1");
//				studentList += StuName+" ";
//		    	result = "班级不存在, 导入学生"+studentList+"的信息失败";
		    	result = "班级不存在, 导入学生的信息失败";
		   }
	   }
	   session.setAttribute("result", result);
	   stuDao.close();
   }
   
   // 导入教师信息
   public static void importTeacherMsg(HttpSession session, Sheet sheet,int rows) {
	   TeacherMsDAO teacherDao=new TeacherMsDAO();
	   String result = "导入成功";
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
	       // 插入到数据库
	       System.out.println(TeacherID+TeacherName+DeptID+Sex+tel+Intro);
	       int j = teacherDao.addTeacher(TeacherID,TeacherName,DeptID,Sex,title,tel,Intro);
	       if (j < 0) {
				session.setAttribute("isError", "1");
//				teacherList += TeacherName+" ";
//		    	result = "系不存在, 导入教师"+teacherList+"的信息失败";
		    	result = "系不存在, 导入教师失败";
		   }
	   }
	   session.setAttribute("result", result);
	   teacherDao.close();
   }

   // 导入班级信息
   public static void importDeptMsg(HttpSession session, Sheet sheet,int rows) {
	   DeptMsDAO deptDao=new DeptMsDAO();
	   String result = "导入成功";
	   String deptList="";
	   session.setAttribute("isError", "0");
	   for(int i=1;i<rows;i++) {
		   String DeptID=sheet.getCell(0,i).getContents();
		   String DeptName=sheet.getCell(1,i).getContents();
	       // 插入到数据库
		   int j = deptDao.addDept(DeptID,DeptName); 
		   if (j < 0) {
				session.setAttribute("isError", "1");
//				deptList += DeptName+" ";
//		    	result = "导入系"+deptList+"的信息失败";
		    	result = "导入系的信息失败";
		   }
	   }
	   session.setAttribute("result", result);
	   deptDao.close();
   }
   
   // 导入系信息
   public static void importClassMsg(HttpSession session, Sheet sheet,int rows) {
	   ClassMsDAO classDao=new ClassMsDAO();
	   String result = "导入成功";
	   String classList="";
	   session.setAttribute("isError", "0");
	   for(int i=1;i<rows;i++) {
		   String ClassID=sheet.getCell(0,i).getContents();
		   String ClassName=sheet.getCell(1,i).getContents();
		   String DeptID=sheet.getCell(2,i).getContents();
	       // 插入到数据库
		   int j = classDao.addClass(ClassID,ClassName,DeptID);
		   if (j < 0) {
				session.setAttribute("isError", "1");
//				classList += ClassName+" ";
//		    	result = "系不存在， 导入班级"+classList+"的信息失败";
		    	result = "系不存在， 导入班级信息失败";
		   }
	   }
	   session.setAttribute("result", result);
	   classDao.close();
   }
}
