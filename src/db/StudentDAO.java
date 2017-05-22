package db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.mysql.jdbc.UpdatableResultSet;
public class StudentDAO {
//-------------��������------------------------
	//students log in
	public int checkLog(String StuID, String SPassword){
		int num = 0;
		String sql = "select SPassword from tb_student where StuID = '" + StuID + "'";
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		ResultSet rs=dbCon.queryForRS(sql);
		if(rs == null)
			return 2;
		while(rs != null){
			try {
				while(rs.next()){
					String pwd = rs.getString("SPassword");
					if(pwd.equals(SPassword)){
						return 1;
					}
					else return 0;
				}
				return 2;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		try {
			rs.close();
			dbCon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	//update studnet's password
	public int changeStuPassword(String StuID, String newPwd){
		String sql = "update tb_student set SPassword = '" + newPwd + "' where StuID = '" + StuID + "'";
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		int num = dbCon.update(sql);
		dbCon.close();
		return num;
	}
	//get student's information
	public Map<String,String> studentInfo(String StuID){
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		String sql="select tb_student.StuID, tb_student.StuName, tb_student.sex, tb_student.grade, tb_student.intro, ClassName,TeacherID, tb_student.tel,choosedState,DeptName,tb_student.DeptID from tb_student join tb_class on tb_student.ClassID = tb_class.ClassID join tb_dept"
				+ " on tb_student.DeptID = tb_dept.DeptID where StuID = '" + StuID + "'";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		dbCon.close();
		if(list != null){
			Map<String,String> myInfo = (Map<String,String>)list.get(0);
			return myInfo;
		}
		else{
			return null;
		}
	}
	//update student's information
	public int updateInfo(String StuID, String tel, String intro){
		String sql = "update tb_student set tel = '"+tel+"',Intro = '"+intro+"' where StuID = '"+StuID+"'";;
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		int num = dbCon.update(sql);
		return num;
	}
	//teacher list
	public ArrayList<Map<String,String>> teacherList(String StuID){
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		Map<String,String> stuinfo = studentInfo(StuID);
		String dept = stuinfo.get("deptid");
		String sql="select * from tb_teacher join tb_dept on tb_teacher.DeptID = tb_dept.DeptID order by replace(tb_dept.deptID,'"+dept+"','')";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		dbCon.close();
		return list;
	}
	//teacher information
	public Map<String,String> teacherInfoDetail(String teacherID){
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		String sql="select * from tb_teacher join tb_dept on tb_teacher.DeptID = tb_dept.DeptID where TeacherID = '" + teacherID + "'";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		dbCon.close();
		if(list != null){
			Map<String,String> oneTeacher = (Map<String,String>)list.get(0);
			return oneTeacher;
		}
		else return null;
	}
	//get teacher's name
	public String getTeacherName(String teacherID){
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		String sql="select * from tb_teacher where TeacherID = '" + teacherID + "'";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		dbCon.close();
		if(list != null)
			return list.get(0).get("teachername");
		else return null;
	}
	//get my teacher
	public String getNowTeacher(String StuID){
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		String sql="select * from tb_selectteacher where StuID = '"+StuID+"' and choosedState = 0";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		dbCon.close();
		if(list != null)
			return list.get(0).get("teacherid");
		else return null;
	}
	//the students who choosed the teacher
	public ArrayList<Map<String,String>> studentChoosedTeacher(String teacherID){
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		String sql = "select tb_student.StuID as stuid,tb_student.StuName as stuname,tb_class.ClassName as classname, "
				+ "tb_selectteacher.choosedState from tb_student join tb_selectteacher on tb_student.StuID = tb_selectteacher.StuID "
				+ "join tb_class on tb_student.ClassID = tb_class.ClassID where tb_selectteacher.teacherID = '" + teacherID + "' and (tb_selectteacher.choosedState = 0 or tb_selectteacher.choosedState = 2)";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		dbCon.close();
		return list;
	}
	//the students who choosed the teacher
	public ArrayList<Map<String, String>> studentList(String teaId){
		String sql = "select TB_Student.stuID as stuID,stuName,TB_Dept.DeptName as deptName,TB_Class.ClassName as className,Sex,TB_Student.Grade as grade,tel,Intro, TB_Student.choosedState as choosedState "
				   + "from TB_Student join TB_Class on TB_Class.ClassID = TB_Student.ClassID join TB_Dept on TB_Dept.DeptID = TB_Student.DeptID "
				   + "where TB_Student.TeacherID = '" + teaId + "' and TB_Student.choosedState <> 2;";
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList<Map<String, String>> stuList = dbc.queryForList(sql);
		dbc.close();
		return stuList;
	}
	//把数据库中的某些字符转义为html转义字符
	public String turn(String str){
		str = str.replaceAll("\\r\\n","<br>");
		return str;
	}
	//get the system privilege, and if it is legal,0-close,1-open
	public int getSystemPrivilege(){
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		String sql = "select PrivilegeModel from tb_admin";
		ArrayList<Map<String, String>> systemPri = dbc.queryForList(sql);
		int pri = Integer.parseInt(systemPri.get(0).get("privilegemodel"));
		return pri;
	}
	//get the system rule
	public int getSystemRule(){
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		String sql = "select Privilege from tb_teacher where Privilege <> 1 limit 1 ";
		ArrayList<Map<String, String>> systemRule = dbc.queryForList(sql);
		int rule = Integer.parseInt(systemRule.get(0).get("privilege"));
		return rule;
	}
	//get the number of students who choosed the teacher 
	public int getNumOfStudentsToTeacher(String TeacherID){
		DBConnection dbCon = new DBConnection();
		dbCon.createConnection();
		int cnt = 0;
		String sql = "select count(TeacherID) as cnt from tb_selectteacher where TeacherID = '"+TeacherID+"' and (choosedState = 0 or choosedState = 2)";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		if(list != null){
			cnt = Integer.parseInt(list.get(0).get("cnt"));
			System.out.println("now studengcount:"+cnt);
		}
		return cnt;
	}
//-------------main function--------------
	//get the student state,1-pending or success, 0-student can choose teacher
	public int studentChooseState(String StuID){
		Map<String,String> stuInfo = studentInfo(StuID);
		if(stuInfo.get("choosedstate").equals("0") || stuInfo.get("choosedstate").equals("2")){
			return 1;
		}
		return 0;
	}
	//if the teacher is my dept's teacher
	public int isMyDeptTeacher(String StuID, String TeacherID){
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		Map<String,String> stuinfo = studentInfo(StuID);
		Map<String,String> teInfo = teacherInfoDetail(TeacherID);
		if(stuinfo.get("deptid").equals(teInfo.get("deptid"))){
			return 1;
		}
		return 0;
	}
	// if the number of students who choosed my dept's teachers reached max
	public int myDeptTeacher(String StuID){
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		Map<String,String> stuinfo = studentInfo(StuID);
		String dept = stuinfo.get("deptid");
		String sql="select * from tb_teacher where DeptID = '"+dept+"'";
		int stuCnt = 0, selRec = 0;
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		for (Map<String, String> map : list) {
			sql = "select count(*) as cnt, studentCount from tb_selectteacher join tb_teacher on tb_selectteacher.TeacherID = tb_teacher.TeacherID where tb_selectteacher.TeacherID = '"+map.get("teacherid")+"' and (choosedState = 0 or choosedState = 2)";
			ArrayList<Map<String,String>> l=dbCon.queryForList(sql);
			stuCnt += Integer.parseInt(l.get(0).get("studentcount"));
			selRec += Integer.parseInt(l.get(0).get("cnt"));
		}
		dbCon.close();
		//stuCnt is max student count, then the students can choose other teachers
		if(stuCnt <= selRec){
			return 0;
		}
		else return 1;
	}
	//parameters: student id, teacher id, student's grade, teacher's privilege
	public synchronized int chooseTeacher(String StuID, String TeacherID, int Grade, int Privilege){
		int rule = 0;
		String sql = null;
		int i = 0;
		//if the condition that teacher can choose student is legal
		//the privilege
		//1-the teacher who can choose students
		//3-according to time
		//4-according to grade
		Map<String,String> teInfo = teacherInfoDetail(TeacherID);
		int stuNum = Integer.parseInt(teInfo.get("studentcount"));
		//if the teacher can choose students, but the privilege model is no, then the teacher obey the system rule
		//get the System time
		Calendar cal=Calendar.getInstance();
		String date=String.format("%4d-%02d-%02d %2d:%02d:%02d",cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
		DBConnection dbCon = new DBConnection();
		dbCon.createConnection();
		
		//according to the time
		if(Privilege == 3){
			//the number is less than system limit
			int cnt = getNumOfStudentsToTeacher(TeacherID);
			if(cnt < stuNum){
				sql = "insert into tb_selectteacher(StuID, TeacherID, SelectDate, Grade,choosedState) values('"+StuID+"','"+TeacherID+"','"+date+"',"+Grade+", 0)";
				i=dbCon.update(sql);
				sql = "update tb_student set TeacherID = '" + TeacherID + "', choosedState = 1 where StuID = '" + StuID + "'";
				i = dbCon.update(sql);
				i = 3;
			}
			else{
				i = 1;
			}
		}
		//according to the grade
		else if(Privilege == 4){
			//get the number of students who choose this teacher
			int cnt = getNumOfStudentsToTeacher(TeacherID);
			if(cnt < stuNum){
				sql = "insert into tb_selectteacher(StuID, TeacherID, SelectDate, Grade,choosedState) values('"+StuID+"','"+TeacherID+"','"+date+"',"+Grade+", 0)";
				i = dbCon.update(sql);
				//update student info 
				sql = "update tb_student set TeacherID = '" + TeacherID + "', choosedState = 1 where StuID = '" + StuID + "'";
				i = dbCon.update(sql);
				i = 2;//pending(unsettled)
			}
			//check if the last teacher greater than this
			else{
				sql = "select StuID, Grade from tb_selectteacher where TeacherID = '"+TeacherID+"' and choosedState = 0 order by Grade, SelectDate desc";
				ArrayList<Map<String,String>> l=dbCon.queryForList(sql);
				if(l != null){
					int grade = Integer.parseInt(l.get(0).get("grade"));
					System.out.println("grade"+grade);
					//if my grade is higher than the last one
					if(Grade > grade){
						//eliminate the last one
						sql = "update tb_selectteacher set choosedState = 1 where StuID = '"
					 +l.get(0).get("stuid")+"' and TeacherID = '"+TeacherID+"' and choosedState = 0";
						i = dbCon.update(sql);
						if(i > 0){
							sql = "update tb_student set choosedState = 2 where StuID = '"+l.get(0).get("stuid")+"'";
							i = dbCon.update(sql);
							sql = "insert into tb_selectteacher(StuID, TeacherID, SelectDate, Grade,choosedState) values('"+StuID+"','"+TeacherID+"','"+date+"',"+Grade+", 0)";
							i = dbCon.update(sql);
							//update student info
							sql = "update tb_student set TeacherID = '" + TeacherID + "', choosedState = 1 where StuID = '" + StuID + "'";
							i = dbCon.update(sql);
							i = 2;//pending(unsettled)
						}
					}
					else{
						i = 4;
					}
				}
			}

		}
		else{
			//the teacher who can choose students
			sql = "insert into tb_selectteacher(StuID, TeacherID, SelectDate, Grade,choosedState) values('"+StuID+"','"+TeacherID+"','"+date+"',"+Grade+", 0)";
			i = dbCon.update(sql);
			//update student info
			sql = "update tb_student set TeacherID = '" + TeacherID + "', choosedState = 1 where StuID = '" + StuID + "'";
			i = dbCon.update(sql);
			i = 2;//pending(unsettled)
		}
		dbCon.close();
		return i;
	}
	//student's records of choosing teacher
	public ArrayList<Map<String,String>> chooseRecord(String stuId){
		//select tb_student.StuID, StuName from tb_student join tb_selectteacher on tb_student.StuID = tb_selectteacher.StuID;
		//select tb_student.StuID, StuName, tb_teacher.TeacherID, TeacherName from tb_student join tb_selectteacher on tb_student.StuID = tb_selectteacher.StuID join tb_teacher on tb_teacher.TeacherID = tb_selectteacher.TeacherID;
		String sql = "select tb_student.StuID, StuName, tb_teacher.TeacherID, TeacherName,tb_selectteacher.choosedState "
					+ "from tb_student join tb_selectteacher on tb_student.StuID = tb_selectteacher.StuID "
					+ "join tb_teacher on tb_teacher.TeacherID = tb_selectteacher.TeacherID where tb_student.StuID = '"
					+ stuId + "'";
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		dbCon.close();
		return list;
	}
	//cancel my teacher
	public synchronized int cancelTeacher(String StuID, String TeacherID){
		//if student has success teacher, he cant cancel his teacher
		Map<String,String> stuInfo = studentInfo(StuID);
		if(stuInfo.get("choosedstate").equals("3")){
			return 3;
		}
		else{
        //update his state
		String sql = "update tb_selectteacher set choosedState = 1 where StuID = '"
					 +StuID+"' and TeacherID = '"+TeacherID+"' and choosedState = 0";
		DBConnection dbCon=new DBConnection();
		dbCon.createConnection();
		int num = dbCon.update(sql);
		if(num > 0){
			sql = "update tb_student set choosedState = 0,TeacherID='' where StuID = '"+StuID+"'";
			num = dbCon.update(sql);
		}
		return num;
		}
	}
}
