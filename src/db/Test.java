package db;

import java.util.ArrayList;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		StudentDAO so = new StudentDAO();
		System.out.println(so.myDeptTeacher("04080202"));
//		System.out.println(so.isMyDeptTeacher("04020109","T02001"));
//		int num = so.changeStuPassword("04020102","123456");
		
//		System.out.println(num);
//		ArrayList<Map<String,String>> list = so.teacherList();
//		for(Map<String, String> teacher : list){
			//字段都要变成小写
//			System.out.println(teacher.get("teacherid") + "-" + teacher.get("teachername"));
//		}
//		Map<String,String> ot = so.teacherInfoDetail("T08004");
//		System.out.println(ot);
//		int num = so.chooseTeacher("04020109","T10005",3);
//		Map<String,String> ot = so.studentInfo("04020105");
//		System.out.println(num);
//		int num = so.chooseTeacher("04080202", "T02001", 3);
//		System.out.println(num);
//		ArrayList<Map<String,String>> list = so.studentChoosedTeacher("T02001");
//		for(Map<String, String> teacher : list){
////			字段都要变成小写
//			System.out.println(teacher.get("stuid") + "-" + teacher.get("stuname")+ "-" + teacher.get("classname"));
//		}
//		int num = so.cancelTeacher("04020102", "T08004");
//		System.out.println(num);
	}

}
