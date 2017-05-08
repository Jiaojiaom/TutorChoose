package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javabean.TeacherMsg;

public class TeacherMsDAO {
	DBConnection dbCon;
	
	public TeacherMsDAO(){
		dbCon=new DBConnection();//数据库连接对象
		dbCon.createConnection();
	}
	
	public int insert(String TeacherID,String TeacherName,String DeptID,String Sex,String tel,String Intro){
		// 插入到数据库
		String sql = "insert into TB_Teacher(TeacherID, TeacherName, DeptID, Sex,tel,Intro)"
				+ "values('"+TeacherID+"','"+TeacherName+"','"+DeptID+"','" +Sex+"','"+tel+"','"+Intro+"')";
		System.out.println(sql);
		// 更新数据库
		int i=dbCon.update(sql);
		return i;
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryTeacherAll(){
		String sql="select * from TB_Teacher";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//根据学号查询教师信息
    public TeacherMsg queryByTeacherId(String teacherId) {
    	TeacherMsg teacherMs = null;
		String sql = "select * from TB_Teacher where TeacherId='" + teacherId + "'";
		System.out.println(sql);
		//将查询结果放到结果集
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					teacherMs = new TeacherMsg();//创建javaBean
					teacherMs.setTeacherID(rs.getString("TeacherID"));
					teacherMs.setTeacherName(rs.getString("TeacherName"));
					teacherMs.setTPassword(rs.getString("TPassword"));
					teacherMs.setDeptID(rs.getString("DeptID"));
					teacherMs.setSex(rs.getString("Sex"));
					teacherMs.setTitle(rs.getString("Title"));
					teacherMs.setStudentCount(rs.getInt("studentCount"));
					teacherMs.setPrivilege(Integer.parseInt(rs.getString("Privilege")));
					teacherMs.setTel(rs.getString("tel"));
					teacherMs.setIntro(rs.getString("Intro"));	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return teacherMs;//返回javaBean
	}

	//修改用户数据
	public int updateTeacher(String TeacherID, String TeacherName, String TPassword, String DeptID,  String Sex,
			              String Title, int studentCount, int Privilege, String tel,String Intro) {
	
		String sql = "update TB_Teacher set TeacherName='" + TeacherName + "',TPassword='" + TPassword + "',DeptID='" + DeptID 
		             + "',Sex='" + Sex + "',Title='" + Title + "',studentCount=" + studentCount 
		             + ",Privilege=" + Privilege+ ",tel='" + tel+ "',Intro='" + Intro 
		             +"' where TeacherID='" + TeacherID+ "'";
		
		System.out.println(sql);
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	//根据学号删除数据
	public int deleteByTeacherId(String teacherId) {
		String sql = "delete from TB_Teacher where TeacherId='" + teacherId + "'";
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	//关闭数据库连接
	public void close() {
		dbCon.close();
	}
	
}

