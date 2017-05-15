package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javabean.SelectTeacherMsg;
import javabean.TeacherMsg;

public class SelectTeacherDAO extends MsDAO {
	
	// 得到选择教师的信息
	public SelectTeacherMsg getSelectTeacherMsg(String sql) {
    	SelectTeacherMsg selectTeacherMs = null;
		//将查询结果放到结果集
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					selectTeacherMs = new SelectTeacherMsg();//创建javaBean
					selectTeacherMs.setStuID(rs.getString("StuID"));
					selectTeacherMs.setTeacherID(rs.getString("TeacherID"));
					selectTeacherMs.setGrade(Float.parseFloat(rs.getString("Grade")));
					selectTeacherMs.setChoosedState(Integer.parseInt(rs.getString("choosedState")));
					selectTeacherMs.setSelectDate(rs.getString("SelectDate"));	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return selectTeacherMs;//返回javaBean
	}
	
	public int insert(String StuID,String TeacherID,Float Grade,int choosedState){
		Calendar cal=Calendar.getInstance();//创建日历对象实例
		String date=String.format("%4d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
		String time=String.format("%2d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
		// 插入到数据库
		sql = "insert into TB_SelectTeacher(StuID, TeacherID, Grade,choosedState,SelectDate)"
				+ "values('"+StuID+"','"+TeacherID+","+Grade+","+choosedState+",'"+(date+" "+time)+"')";
		System.out.println(sql);
		// 更新数据库
		return updateDB(sql);
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> querySelectTeacherAll(){
		sql="select * from TB_SelectTeacher";
		return queryDBForList(sql);
	}
	
	//根据教师查询选择信息
    public SelectTeacherMsg queryByTeacherId(String teacherID) {
		sql = "select * from TB_SelectTeacher where TeacherID='" + teacherID + "'";
		return getSelectTeacherMsg(sql);//返回javaBean
	}
    
    // 根据学号查询选择信息
    public SelectTeacherMsg findByStuId(String stuId) {
		sql = "select * from TB_SelectTeacher where stuId='" + stuId + "'";
		return getSelectTeacherMsg(sql);
	}
    
	//修改用户数据
	public int updateTeacher(String StuID, String TeacherID, float Grade, int choosedState, String SelectDate) {
		sql = "update TB_SelectTeacher set TeacherID='" + TeacherID + "',Grade=" + Grade 
				     + ",choosedState=" + choosedState + "," + "',SelectDate='" + SelectDate 
				     +"' where TeacherID='" + TeacherID+ "'";
		return updateDB(sql);
	}
	
	//根据学号删除数据
	public int deleteByStuId(String stuId) {
		String sql = "delete from TB_SelectTeacher where stuId='" + stuId + "'";
		return updateDB(sql);
	}
	
	// 根据老师的工号，选出所带的学生
	public ArrayList<Map<String,String>> selectByTeacherId(String teacherID){
		sql="select * from TB_SelectTeacher where TeacherID='" + teacherID + "'";
		return queryDBForList(sql);
	}
}

