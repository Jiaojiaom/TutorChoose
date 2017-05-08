package com.myupload.servlet;

import java.io.File;
import java.io.IOException;

import db.ClassMsDAO;
import db.DeptMsDAO;
import db.StuMsDAO;
import db.TeacherMsDAO;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
   public static void readExcel(String pathname, String type) {
	   //保存到数据库
	   try {
		   // 打开文件
		   Workbook book = Workbook.getWorkbook(new File(pathname));
		   // 取得第一个sheet
		   Sheet sheet = book.getSheet(0);
		   // 取得行数
		   int rows = sheet.getRows();
		   if(type=="stuExcel"){
			   importStuMsg(sheet, rows);
		   } else if(type=="teacherExcel"){
			   importTeacherMsg(sheet, rows);
		   } else if(type=="deptExcel"){
			   importDeptMsg(sheet, rows);
		   } else if(type=="classExcel"){
			   importClassMsg(sheet, rows);
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
   public static void importStuMsg(Sheet sheet,int rows) {
	   StuMsDAO stuDao=new StuMsDAO();
	   for(int i=0;i<rows;i++) {
		   String StuID=sheet.getCell(0,i).getContents();
		   String StuName=sheet.getCell(1,i).getContents();
		   String DeptID=sheet.getCell(2,i).getContents();
		   String ClassID=sheet.getCell(3,i).getContents();
	       String Sex=sheet.getCell(4,i).getContents();
	       String SPassword=sheet.getCell(5,i).getContents();
	       Double Grade=Double.parseDouble(sheet.getCell(6,i).getContents());
	       String tel=sheet.getCell(7,i).getContents();
	       String Intro=sheet.getCell(8,i).getContents();
	       // 插入到数据库
		   stuDao.insert(StuID,StuName,DeptID,ClassID,Sex,SPassword,Grade,tel,Intro);
	   }
	   stuDao.close();
   }
   
   //导入教师信息
   public static void importTeacherMsg(Sheet sheet,int rows) {
	   TeacherMsDAO teacherDao=new TeacherMsDAO();
	   for(int i=0;i<rows;i++) {
		   String TeacherID=sheet.getCell(0,i).getContents();
		   String TeacherName=sheet.getCell(1,i).getContents();
	       String DeptID=sheet.getCell(3,i).getContents();
	       String Sex=sheet.getCell(4,i).getContents();
	       String tel=sheet.getCell(8,i).getContents();
	       String Intro=sheet.getCell(9,i).getContents();
	       // 插入到数据库
	       teacherDao.insert(TeacherID,TeacherName,DeptID,Sex,tel,Intro);
	   }
	   teacherDao.close();
   }

   // 导入班级信息
   public static void importDeptMsg(Sheet sheet,int rows) {
	   DeptMsDAO deptDao=new DeptMsDAO();
	   for(int i=0;i<rows;i++) {
		   String DeptID=sheet.getCell(0,i).getContents();
		   String DeptName=sheet.getCell(1,i).getContents();
	       // 插入到数据库
		   deptDao.insert(DeptID,DeptName);
	   }
	   deptDao.close();
   }
   
   // 导入系信息
   public static void importClassMsg(Sheet sheet,int rows) {
	   ClassMsDAO classDao=new ClassMsDAO();
	   for(int i=0;i<rows;i++) {
		   String ClassID=sheet.getCell(0,i).getContents();
		   String ClassName=sheet.getCell(1,i).getContents();
		   String DeptID=sheet.getCell(2,i).getContents();
	       // 插入到数据库
		   classDao.insert(ClassID,ClassName,DeptID);
	   }
	   classDao.close();
   }
}
