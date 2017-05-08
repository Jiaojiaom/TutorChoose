package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javabean.StuMsg;

public class StuMsDAO {
	DBConnection dbCon;
	
	public StuMsDAO() {
		dbCon=new DBConnection();//数据库连接对象
		dbCon.createConnection();
	}
	
	public int insert(String StuID,String StuName,String DeptID,String ClassID,
			  String Sex,String SPassword,Double Grade,String tel,String Intro){
		// 插入到数据库
		String sql = "insert into TB_Student(StuID, StuName, DeptID, ClassID,Sex,SPassword,Grade,tel,Intro)"
				+ "values('"+StuID+"','"+StuName+"','"+DeptID+"','"+ClassID+"','"+Sex+"','"+SPassword+"',"+Grade+",'"+tel+"','"+Intro+"')";
		System.out.println(sql);
		// 更新数据库
		int i=dbCon.update(sql);
		return i;
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryStudentAll(){
		String sql="select * from TB_Student";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//根据教师工号查询学生信息
    public ArrayList<Map<String,String>> queryByTeacherId(String teacherId) {
		String sql = "select * from TB_Student where TeacherId='" + teacherId + "'";
		System.out.println(sql);
		//将查询结果放到结果集
		ArrayList<Map<String,String>> list= dbCon.queryForList(sql);
		return list;
	}
    
	//根据学号查询学生信息
    public StuMsg queryByStuId(String stuId) {
    	StuMsg stuMs = null;
		String sql = "select * from TB_Student where StuId='" + stuId + "'";
		System.out.println(sql);
		//将查询结果放到结果集
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					stuMs = new StuMsg();//创建javaBean
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
    
	//修改用户数据
	public int updateStudent(String stuId, String stuName, String deptId, String classId,  String sex, String password, 
			              float grade, String tel , String intro, String teacherID, String choosedState, String selectDate) {
		
		String sql = "update TB_Student set StuName='" + stuName + "',DeptId='" + deptId + "',ClassId='" + classId 
		             + "',sex='" + sex + "',spassword='" + password + "',grade=" + grade + ",intro='" + intro + "',tel='" + tel
		             + "',teacherID='" + teacherID+ "',choosedState='" + choosedState+ "',selectDate='" + selectDate 
		             +"' where StuID='" + stuId+ "'";
		
		System.out.println(sql);
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	//根据学号删除数据
	public int deleteByStuId(String stuId) {
		String sql = "delete from TB_Student where stuId='" + stuId + "'";
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	//老师挑选学生，设置学生的状态
	public int chooseStu(String stuId, int choosedState) {
		String sql = "update TB_Student set ChoosedState=" + choosedState +" where StuID='" + stuId+ "'";
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	//关闭数据库连接
	public void close() {
		dbCon.close();
	}

}

