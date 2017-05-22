package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.bean.StudentMsg;

public class StudentMsDAO extends MsDAO{
	//根据学号查询学生信息
    public StudentMsg getStudentMsg(String sql) {
    	StudentMsg stuMs = null;
		//将查询结果放到结果集
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					stuMs = new StudentMsg();//创建javaBean
					stuMs.setStuID(rs.getString("stuID"));
					stuMs.setStuName(rs.getString("stuName"));
					stuMs.setDeptID(rs.getString("deptID"));
					stuMs.setClassID(rs.getString("classID"));
					stuMs.setSex(rs.getString("sex"));
					stuMs.setSPassword(rs.getString("spassword"));
					stuMs.setGrade(rs.getInt("grade"));
					stuMs.setTel(rs.getString("tel"));
					stuMs.setIntro(rs.getString("Intro"));
					stuMs.setTeacherID(rs.getString("TeacherID"));
					stuMs.setChoosedState(Integer.parseInt(rs.getString("choosedState")));
					stuMs.setSelectDate(rs.getString("selectDate"));
//					System.out.println(stuMs.getStuName());
//					System.out.println(stuMs.getSPassword());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stuMs;//返回javaBean
	}
    
	public int addStudent(String StuID,String StuName,String DeptID,String ClassID,
			              String Sex,float Grade,String tel,String Intro){
		DeptMsDAO deptDao = new DeptMsDAO();
		if(deptDao.findOneDept(DeptID)!=null){
			ClassMsDAO classDao = new ClassMsDAO();
			if(classDao.findOneClass(ClassID)!=null){
				if(findOneStudent(StuID)!=null){
					sql = "update TB_Student set StuName='" + StuName + "',DeptId='" + DeptID 
							 + "',ClassId='" + ClassID + "',Sex='" + Sex + "',Grade=" + Grade 
							 + ",Intro='" + Intro + "',tel='" + tel
				             + "' where StuID='" + StuID+ "'";
				} else {
					// 插入到数据库
					sql = "insert into TB_Student(StuID, StuName, DeptID, ClassID,Sex,Grade,tel,Intro)"
							+ "values('"+StuID+"','"+StuName+"','"+DeptID+"','"+ClassID+"','"+Sex+"',"+Grade+",'"+tel+"','"+Intro+"')";
				}
//				System.out.println(sql);
				return updateDB(sql);
			}
			else {
				return -2;
			}
		}else {
			return -1;
		}
	}
		
	//根据学号查询学生信息
    public StudentMsg findOneStudent(String stuId) {
		sql = "select * from TB_Student where StuId='" + stuId + "'";
		return getStudentMsg(sql);//返回javaBean
	}
    
	//修改用户数据
	public int updateByStudentID(String stuId, String stuName, String deptId, String classId,  String sex,
			              float grade, String tel , String intro, String teacherID, int choosedState) {
		DeptMsDAO deptDao = new DeptMsDAO();
		if(deptDao.findOneDept(deptId)!=null){
			ClassMsDAO classDao = new ClassMsDAO();
			if(classDao.findOneClass(classId)!=null){
				sql = "update TB_Student set StuName='" + stuName + "',DeptId='" + deptId + "',ClassId='" + classId 
				             + "',Sex='" + sex + "',Grade=" + grade + ",Intro='" + intro + "',Tel='" + tel
				             + "',TeacherID='" + teacherID+ "',ChoosedState=" + choosedState
				             +" where StuID='" + stuId+ "'";
//				System.out.println(sql);
				return updateDB(sql);
			}
			else {
//				System.out.println("班级不存在");
				return -2;
			}
		}else {
//			System.out.println("系不存在");
			return -1;
		}
	}
	// 按照管理员编号查询
    public StudentMsg findByStudentId(String stuId) {
		sql = "select * from TB_Student where StuID='" + stuId + "'";
//		System.out.println(sql);
		return getStudentMsg(sql);//返回javaBean
	}
    
	//根据学号删除数据
	public int deleteByStuId(String stuId) {
		sql = "delete from TB_Student where StuId='" + stuId + "'";
		return updateDB(sql);
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryStudentList(){
		sql="select * from TB_Student";
		return queryDBForList(sql);
	}
	
	public ArrayList<Map<String,String>> querySelectStudentListByTeacherID(String TeacherID){
		sql="select * from TB_Student where ChoosedState = 3 and TeacherID = '"+TeacherID+"'";
//		System.out.println(sql);
		return queryDBForList(sql);
	}
	
	// 找出所有未选导师的学生
	public ArrayList<Map<String,String>> queryUnSelectStudentList(){
		sql="select * from TB_Student where ChoosedState != 1 and ChoosedState != 3";
//		System.out.println(sql);
		return queryDBForList(sql);
	}
	
	//根据教师工号查询学生信息
    public ArrayList<Map<String,String>> queryStudentByTeacherId(String teacherId) {
		sql = "select * from TB_Student where TeacherId='" + teacherId + "'";
		return queryDBForList(sql);
	}
    
    public int resetPassword(String StudentID) {
		sql = "update TB_Student set SPassword='123456'"
	             +" where StuID='" + StudentID+ "'";
//		System.out.println(sql);
		return updateDB(sql);
	}
	
}

