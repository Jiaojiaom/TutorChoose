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
	   //���浽���ݿ�
	   try {
		   // ���ļ�
		   Workbook book = Workbook.getWorkbook(new File(pathname));
		   // ȡ�õ�һ��sheet
		   Sheet sheet = book.getSheet(0);
		   // ȡ������
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
		   // �ر��ļ�
		   book.close();
	   } catch (BiffException e) {
		   e.printStackTrace();
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
   }
   
   // ����ѧ����Ϣ
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
	       // ���뵽���ݿ�
		   stuDao.insert(StuID,StuName,DeptID,ClassID,Sex,SPassword,Grade,tel,Intro);
	   }
	   stuDao.close();
   }
   
   //�����ʦ��Ϣ
   public static void importTeacherMsg(Sheet sheet,int rows) {
	   TeacherMsDAO teacherDao=new TeacherMsDAO();
	   for(int i=0;i<rows;i++) {
		   String TeacherID=sheet.getCell(0,i).getContents();
		   String TeacherName=sheet.getCell(1,i).getContents();
	       String DeptID=sheet.getCell(3,i).getContents();
	       String Sex=sheet.getCell(4,i).getContents();
	       String tel=sheet.getCell(8,i).getContents();
	       String Intro=sheet.getCell(9,i).getContents();
	       // ���뵽���ݿ�
	       teacherDao.insert(TeacherID,TeacherName,DeptID,Sex,tel,Intro);
	   }
	   teacherDao.close();
   }

   // ����༶��Ϣ
   public static void importDeptMsg(Sheet sheet,int rows) {
	   DeptMsDAO deptDao=new DeptMsDAO();
	   for(int i=0;i<rows;i++) {
		   String DeptID=sheet.getCell(0,i).getContents();
		   String DeptName=sheet.getCell(1,i).getContents();
	       // ���뵽���ݿ�
		   deptDao.insert(DeptID,DeptName);
	   }
	   deptDao.close();
   }
   
   // ����ϵ��Ϣ
   public static void importClassMsg(Sheet sheet,int rows) {
	   ClassMsDAO classDao=new ClassMsDAO();
	   for(int i=0;i<rows;i++) {
		   String ClassID=sheet.getCell(0,i).getContents();
		   String ClassName=sheet.getCell(1,i).getContents();
		   String DeptID=sheet.getCell(2,i).getContents();
	       // ���뵽���ݿ�
		   classDao.insert(ClassID,ClassName,DeptID);
	   }
	   classDao.close();
   }
}
