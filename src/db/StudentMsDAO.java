package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javabean.StudentMsg;

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
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stuMs;//返回javaBean
	}
    
	public int addStudent(String StuID,String StuName,String DeptID,String ClassID,
			              String Sex,String SPassword,float Grade,String tel,String Intro){
		if(findOneStudent(StuID)!=null){
			String teacherID=null, selectDate=null;
			int choosedState =0;
			
			sql = "update TB_Student set StuName='" + StuName + "',DeptId='" + DeptID + "',ClassId='" + ClassID 
		             + "',sex='" + Sex + "',spassword='" + SPassword + "',grade=" + Grade + ",intro='" + Intro + "',tel='" + tel
		             + "',teacherID='" + teacherID+ "',choosedState=" + choosedState+ ",selectDate='" + selectDate 
		             +"' where StuID='" + StuID+ "'";
		} else {
			// 插入到数据库
			sql = "insert into TB_Student(StuID, StuName, DeptID, ClassID,Sex,SPassword,Grade,tel,Intro)"
					+ "values('"+StuID+"','"+StuName+"','"+DeptID+"','"+ClassID+"','"+Sex+"','"+SPassword+"',"+Grade+",'"+tel+"','"+Intro+"')";
		}
		System.out.println(sql);
		return updateDB(sql);
	}
		
	//根据学号查询学生信息
    public StudentMsg findOneStudent(String stuId) {
		sql = "select * from TB_Student where StuId='" + stuId + "'";
		return getStudentMsg(sql);//返回javaBean
	}
    
	//修改用户数据
	public int updateByStudentID(String stuId, String stuName, String deptId, String classId,  String sex, String password, 
			              float grade, String tel , String intro, String teacherID, String choosedState, String selectDate) {
		sql = "update TB_Student set StuName='" + stuName + "',DeptId='" + deptId + "',ClassId='" + classId 
		             + "',sex='" + sex + "',spassword='" + password + "',grade=" + grade + ",intro='" + intro + "',tel='" + tel
		             + "',teacherID='" + teacherID+ "',choosedState='" + choosedState+ "',selectDate='" + selectDate 
		             +"' where StuID='" + stuId+ "'";
		return updateDB(sql);
	}
	// 按照管理员编号查询
    public StudentMsg findByStudentId(String stuId) {
		sql = "select * from TB_Student where StuID='" + stuId + "'";
		return getStudentMsg(sql);//返回javaBean
	}
    
	//根据学号删除数据
	public int deleteByStuId(String stuId) {
		sql = "delete from TB_Student where stuId='" + stuId + "'";
		return updateDB(sql);
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryStudentList(){
		sql="select * from TB_Student";
		return queryDBForList(sql);
	}
	
	//根据教师工号查询学生信息
    public ArrayList<Map<String,String>> queryStudentByTeacherId(String teacherId) {
		sql = "select * from TB_Student where TeacherId='" + teacherId + "'";
		return queryDBForList(sql);
	}
	
}

