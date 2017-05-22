package com.admin.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import db.ClassMsDAO;
import db.DeptMsDAO;
import db.StudentMsDAO;
import db.TeacherMsDAO;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportExcel {
	public static WritableFont font1 =new WritableFont(WritableFont.createFont("΢���ź�"), 10 ,WritableFont.BOLD);
	public static WritableFont font2 =new WritableFont(WritableFont.createFont("΢���ź�"), 9 ,WritableFont.NO_BOLD);
	public static WritableCellFormat wcf = new WritableCellFormat(font1);
	public static WritableCellFormat wcf2 = new WritableCellFormat(font2);
	public static WritableCellFormat wcf3 = new WritableCellFormat(font2);//������ʽ������
	static DecimalFormat df = new DecimalFormat("#.00");
	
	public void ExportExcel() throws WriteException{
		wcf.setAlignment(Alignment.CENTRE);  //ƽ�о���
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);  //��ֱ����
		wcf3.setAlignment(Alignment.CENTRE);  //ƽ�о���
		wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);  //��ֱ����
		wcf3.setBackground(Colour.LIGHT_ORANGE);
		wcf2.setAlignment(Alignment.CENTRE);  //ƽ�о���
		wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);  //��ֱ����
	}
	
	public static void exportExcel(String pathname, String type) throws WriteException, SQLException {
	  try {
	    //	���ļ�
		WritableWorkbook book = Workbook.createWorkbook(new File(pathname));
	    //	������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
		WritableSheet sheet = book.createSheet( "sheet 1 " , 0);
		// ������ʽ
	    setStyle(sheet, type);
	    // ��д����
	    switch(type){
			case "teacher":
				setTeacherData(sheet);
				break;	
			case "student":
				setStudentData(sheet);
				break;
			case "dept":
				setDeptData(sheet);
				break;
			case "class":
				setClassData(sheet);
				break;
			case "UnSelectStudent":
				System.out.println("����δѡ��ʦѧ��");
				setUnSelectStudentData(sheet);
				break;
			default:
				break;
		}
		// ��ʼд����
		book.write();
		// �رձ�
		book.close();
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
	}
	
	
	public static void setStyle(WritableSheet sheet, String type) throws WriteException{
		SheetSettings ss = sheet.getSettings();
		ss.setVerticalFreeze(2);  // �����ж���ǰ2��
		sheet.mergeCells( 1 , 0 , 13 , 0 ); // �ϲ���Ԫ��  	
	}
	
//	static void setTeacherData(WritableSheet sheet) throws WriteException, SQLException{
//		Label titleLabel = new Label( 1 , 0 , "��ʦ��", wcf);
//		//	������õĵ�Ԫ����ӵ���������
//		sheet.addCell(titleLabel);
//		sheet.setRowView(1, 500); // ���õ�һ�еĸ߶�500
//		int[] headerArrHight = {10,10,10,8,10,15,15,10,10,10,10,8,15};
//		String headerArr[] = {"����","��ʦ����","ϵ","�Ա�","ְ��","�Ƿ�Ϊ��ѡ��ʦ","�绰","ѧ��","ѧ������","ϵ", "�༶","�Ա�","�绰"};
//		for (int i = 0; i < headerArr.length; i++) {
//			sheet.addCell(new Label( i , 1 , headerArr[i],wcf));
//			sheet.setColumnView(i, headerArrHight[i]);
//		}
//		
//		int conut = 2;
//		TeacherMsDAO teacherDao = new TeacherMsDAO();
//		DeptMsDAO deptDao = new DeptMsDAO();
//		ClassMsDAO classDao = new ClassMsDAO();
//		ArrayList<Map<String, String>> teacherMsgs =teacherDao.queryTeacherList();
//		StudentMsDAO studentDao = new StudentMsDAO();
//		for (Map<String, String> teacherMsg : teacherMsgs) {
//			ArrayList<Map<String, String>> studentMsgs =studentDao.querySelectStudentListByTeacherID(teacherMsg.get("teacherid"));
//			System.out.println("studentMsgs"+studentMsgs);
//			if(studentMsgs.isEmpty()==false){
//				for (Map<String, String> studentMsg : studentMsgs) {
//					writeTeacherMsg(sheet, conut, teacherMsg);
//					writeStudentMsg(sheet, conut, studentMsg);
//					sheet.setRowView(conut, 370); // ���õ�һ�еĸ߶�
//					conut++;
//				}
//			}else {
//				writeTeacherMsg(sheet, conut, teacherMsg);
//			}
//			conut ++;
//		}
//		deptDao.close();
//		studentDao.close();
//		classDao.close();
//		teacherDao.close();
//	}
//	static void writeTeacherMsg(WritableSheet sheet, int conut, Map<String, String> teacherMsg) throws RowsExceededException, WriteException, SQLException{
//		String privilege = "";
//		if(teacherMsg.get("privilege").equals("1")){
//			privilege = "��";
//		}else {
//			privilege = "��";
//		}
//		sheet.addCell(new Label( 0 , conut , teacherMsg.get("teacherid"), wcf2));
//		sheet.addCell(new Label( 1 , conut , teacherMsg.get("teachername") ,wcf2));
//		sheet.addCell(new Label( 2 , conut , getDeptName(teacherMsg.get("deptid")) ,wcf2));
//		sheet.addCell(new Label( 3 , conut , convertSex(teacherMsg.get("sex")), wcf2));
//		sheet.addCell(new Label( 4 , conut , teacherMsg.get("title") ,wcf2));
//		sheet.addCell(new Label( 5 , conut , privilege ,wcf2));
//		sheet.addCell(new Label( 6 , conut , teacherMsg.get("tel"), wcf2));
//	}
//	
//	static void writeStudentMsg(WritableSheet sheet, int conut, Map<String, String> studentMsg) throws RowsExceededException, WriteException, SQLException{
//		sheet.addCell(new Label( 7 , conut , studentMsg.get("stuid"), wcf2));
//		sheet.addCell(new Label( 8 , conut , studentMsg.get("stuname") ,wcf2));
//		sheet.addCell(new Label( 9 , conut , getDeptName(studentMsg.get("deptid")) ,wcf2));
//		sheet.addCell(new Label( 10 , conut , getClassName(studentMsg.get("classid")), wcf2));
//		sheet.addCell(new Label( 11 , conut , convertSex(studentMsg.get("sex")) ,wcf2));
//		sheet.addCell(new Label( 12 , conut , studentMsg.get("tel"), wcf2));
//	}

	static void setTeacherData(WritableSheet sheet) throws WriteException, SQLException{
		Label titleLabel = new Label( 1 , 0 , "��ʦ��", wcf);
		//	������õĵ�Ԫ����ӵ���������
		sheet.addCell(titleLabel);
		sheet.setRowView(1, 500); // ���õ�һ�еĸ߶�500
		int[] headerArrHight = {10,10,10,8,10,15,15,10,10,10,10,8,15};
		String headerArr[] = {"����","��ʦ����","ϵ","�Ա�","ְ��","�Ƿ�Ϊ��ѡ��ʦ","�绰","ѧ��","ѧ������","ϵ", "�༶","�Ա�","�绰"};
		for (int i = 0; i < headerArr.length; i++) {
			sheet.addCell(new Label( i , 1 , headerArr[i],wcf));
			sheet.setColumnView(i, headerArrHight[i]);
		}
		
		int conut = 2;
		String privilege = "";
		TeacherMsDAO teacherDao = new TeacherMsDAO();
		DeptMsDAO deptDao = new DeptMsDAO();
		ClassMsDAO classDao = new ClassMsDAO();
		ArrayList<Map<String, String>> teacherMsgs =teacherDao.queryTeacherList();
		for (Map<String, String> teacherMsg : teacherMsgs) {
			if(teacherMsg.get("privilege").equals("1")){
				privilege = "��";
			}else {
				privilege = "��";
			}
			sheet.addCell(new Label( 0 , conut , teacherMsg.get("teacherid"), wcf2));
			sheet.addCell(new Label( 1 , conut , teacherMsg.get("teachername") ,wcf2));
			sheet.addCell(new Label( 2 , conut , getDeptName(teacherMsg.get("deptid")) ,wcf2));
			sheet.addCell(new Label( 3 , conut , convertSex(teacherMsg.get("sex")), wcf2));
			sheet.addCell(new Label( 4 , conut , teacherMsg.get("title") ,wcf2));
			sheet.addCell(new Label( 5 , conut , privilege ,wcf2));
			sheet.addCell(new Label( 6 , conut , teacherMsg.get("tel"), wcf2));
			sheet.setRowView(conut, 370); // ���õ�һ�еĸ߶�
			conut++;
			conut += setSelectStudentData(sheet,conut, teacherMsg.get("teacherid"), teacherMsg.get("teachername"));
		}
		deptDao.close();
		classDao.close();
		teacherDao.close();
	}

	
	static int setSelectStudentData(WritableSheet sheet, int conut, String TeacherID, String TeacherName) throws RowsExceededException, WriteException, SQLException{
		int StudentConut = 0;
		StudentMsDAO studentDao = new StudentMsDAO();
		ArrayList<Map<String, String>> studentMsgs =studentDao.querySelectStudentListByTeacherID(TeacherID);
		for (Map<String, String> studentMsg : studentMsgs) {
			sheet.addCell(new Label( 7 , conut+StudentConut , studentMsg.get("stuid"), wcf2));
			sheet.addCell(new Label( 8 , conut+StudentConut , studentMsg.get("stuname") ,wcf2));
			sheet.addCell(new Label( 9 , conut+StudentConut , getDeptName(studentMsg.get("deptid")) ,wcf2));
			sheet.addCell(new Label( 10 , conut+StudentConut , getClassName(studentMsg.get("classid")), wcf2));
			sheet.addCell(new Label( 11 , conut+StudentConut , convertSex(studentMsg.get("sex")) ,wcf2));
			sheet.addCell(new Label( 12 , conut+StudentConut , studentMsg.get("tel"), wcf2));
			sheet.setRowView(conut, 370); // ���õ�һ�еĸ߶�
			StudentConut++;
		}
		studentDao.close();
		return StudentConut+1;
	}

	static void setStudentData(WritableSheet sheet) throws RowsExceededException, WriteException, SQLException{
		Label titleLabel = new Label( 1 , 0 , "ѧ����", wcf);
		//	������õĵ�Ԫ����ӵ���������
		sheet.addCell(titleLabel);
		sheet.setRowView(1, 500); // ���õ�һ�еĸ߶�500
		int[] headerArrHight = {13,10,13,13,13,20,13,20,13,50};
		String headerArr[] = {"ѧ��","����","ϵ","�༶", "�Ա�","�ɼ�","�绰", "��ʦ","���ҽ���"};
		for (int i = 0; i < headerArr.length; i++) {
			sheet.addCell(new Label( i , 1 , headerArr[i],wcf));
			sheet.setColumnView(i, headerArrHight[i]);
		}
		
		int conut = 2;
		StudentMsDAO studentDao = new StudentMsDAO();
		ArrayList<Map<String, String>> studentMsgs =studentDao.queryStudentList();
		for (Map<String, String> studentMsg : studentMsgs) {
			sheet.addCell(new Label( 0 , conut , studentMsg.get("stuid"), wcf2));
			sheet.addCell(new Label( 1 , conut , studentMsg.get("stuname") ,wcf2));
			sheet.addCell(new Label( 2 , conut , getDeptName(studentMsg.get("deptid")) ,wcf2));
			sheet.addCell(new Label( 3 , conut , getClassName(studentMsg.get("classid")), wcf2));
			sheet.addCell(new Label( 4 , conut , convertSex(studentMsg.get("sex")) ,wcf2));
			sheet.addCell(new Label( 5 , conut , studentMsg.get("grade") ,wcf2));
			sheet.addCell(new Label( 6 , conut , studentMsg.get("tel"), wcf2));
			sheet.addCell(new Label( 7 , conut , studentMsg.get("teacherid") ,wcf2));
			sheet.addCell(new Label( 8 , conut , studentMsg.get("intro") ,wcf2));
			sheet.setRowView(conut, 370); // ���õ�һ�еĸ߶�
			conut++;
		}
		studentDao.close();
	}

	static void setUnSelectStudentData(WritableSheet sheet) throws RowsExceededException, WriteException, SQLException{
		Label titleLabel = new Label( 1 , 0 , "δѡ��ʦ��Ϣѧ����", wcf);
		//	������õĵ�Ԫ����ӵ���������
		sheet.addCell(titleLabel);
		sheet.setRowView(1, 500); // ���õ�һ�еĸ߶�500
		int[] headerArrHight = {13,10,13,13,13,20,20};
		String headerArr[] = {"ѧ��","����","ϵ","�༶", "�Ա�","�ɼ�","�绰"};
		for (int i = 0; i < headerArr.length; i++) {
			sheet.addCell(new Label( i , 1 , headerArr[i],wcf));
			sheet.setColumnView(i, headerArrHight[i]);
		}
		
		int conut = 2;
		StudentMsDAO studentDao = new StudentMsDAO();
		System.out.println("��ʼ����δѡ��ʦ��ѧ���б�");
		ArrayList<Map<String, String>> studentMsgs =studentDao.queryUnSelectStudentList();
		for (Map<String, String> studentMsg : studentMsgs) {
			sheet.addCell(new Label( 0 , conut , studentMsg.get("stuid"), wcf2));
			sheet.addCell(new Label( 1 , conut , studentMsg.get("stuname") ,wcf2));
			sheet.addCell(new Label( 2 , conut , getDeptName(studentMsg.get("deptid")) ,wcf2));
			sheet.addCell(new Label( 3 , conut , getClassName(studentMsg.get("classid")), wcf2));
			sheet.addCell(new Label( 4 , conut , convertSex(studentMsg.get("sex")) ,wcf2));
			sheet.addCell(new Label( 5 , conut , studentMsg.get("grade") ,wcf2));
			sheet.addCell(new Label( 6 , conut , studentMsg.get("tel"), wcf2));
			sheet.setRowView(conut, 370); // ���õ�һ�еĸ߶�
			conut++;
		}
		studentDao.close();
	}
	static void setDeptData(WritableSheet sheet) throws WriteException{
		Label titleLabel = new Label( 1 , 0 , "ϵ��", wcf);
		//	������õĵ�Ԫ����ӵ���������
		sheet.addCell(titleLabel);
		sheet.setRowView(1, 500); // ���õ�һ�еĸ߶�500
		int[] headerArrHight = {13,10};
		String headerArr[] = {"ϵ��","ϵ��"};
		for (int i = 0; i < headerArr.length; i++) {
			sheet.addCell(new Label( i , 1 , headerArr[i],wcf));
			sheet.setColumnView(i, headerArrHight[i]);
		}
		
		int conut = 2;
		DeptMsDAO deptDao = new DeptMsDAO();
		ArrayList<Map<String, String>> deptMsgs = deptDao.queryDeptAll();
		for (Map<String, String> deptMsg : deptMsgs) {
			sheet.addCell(new Label( 0 , conut , deptMsg.get("deptid"), wcf2));
			sheet.addCell(new Label( 1 , conut , deptMsg.get("deptname") ,wcf2));
			sheet.setRowView(conut, 370); // ���õ�һ�еĸ߶�
			conut++;
		}
		deptDao.close();
	}
    
	static void setClassData(WritableSheet sheet) throws RowsExceededException, WriteException{
		Label titleLabel = new Label( 1 , 0 , "�༶��", wcf);
		//	������õĵ�Ԫ����ӵ���������
		sheet.addCell(titleLabel);
		sheet.setRowView(1, 500); // ���õ�һ�еĸ߶�500
		int[] headerArrHight = {13,10,13};
		String headerArr[] = {"�༶���","�༶��", "ϵ��"};
		for (int i = 0; i < headerArr.length; i++) {
			sheet.addCell(new Label( i , 1 , headerArr[i],wcf));
			sheet.setColumnView(i, headerArrHight[i]);
		}
		
		int conut = 2;
		ClassMsDAO classDao = new ClassMsDAO();
		ArrayList<Map<String, String>> classMsgs =classDao.queryClassAll();
		for (Map<String, String> classMsg : classMsgs) {
			sheet.addCell(new Label( 0 , conut , classMsg.get("classid"), wcf2));
			sheet.addCell(new Label( 1 , conut , classMsg.get("classname") ,wcf2));
			sheet.addCell(new Label( 2 , conut , classMsg.get("deptid") ,wcf2));
			sheet.setRowView(conut, 370); // ���õ�һ�еĸ߶�
			conut++;
		}
		classDao.close();
    }
	
	private static String getDeptName(String DeptID) throws SQLException {
		// TODO Auto-generated method stub
		DeptMsDAO deptDao = new DeptMsDAO();
		String DeptName =  deptDao.getDeptName(DeptID);
		deptDao.close();
		return DeptName;
	}
	
	private static String getClassName(String ClassID) throws SQLException {
		// TODO Auto-generated method stub
		ClassMsDAO classDao = new ClassMsDAO();
		String ClassName =  classDao.getClassName(ClassID);
		classDao.close();
		return ClassName;
	}
	
	private static String convertSex(String sex) {
		// TODO Auto-generated method stub
		if(sex.equals("F")){
			return "Ů";
		}else {
			return "��";
		}
	}
}