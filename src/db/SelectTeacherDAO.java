package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javabean.SelectTeacherMsg;
import javabean.TeacherMsg;

public class SelectTeacherDAO {
    DBConnection dbCon;
	
    public SelectTeacherDAO(){
		dbCon=new DBConnection();//数据库连接对象
		dbCon.createConnection();
	}
    
	public int insert(String StuID,String TeacherID,Float Grade,int choosedState){
		Calendar cal=Calendar.getInstance();//创建日历对象实例
		String date=String.format("%4d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
		String time=String.format("%2d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
		
		// 插入到数据库
		String sql = "insert into TB_SelectTeacher(StuID, TeacherID, Grade,choosedState,SelectDate)"
				+ "values('"+StuID+"','"+TeacherID+","+Grade+","+choosedState+",'"+(date+" "+time)+"')";
		System.out.println(sql);
		// 更新数据库
		int i=dbCon.update(sql);
		return i;
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> querySelectTeacherAll(){
		String sql="select * from TB_SelectTeacher";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	// 根据老师的工号，选出所带的学生
	public ArrayList<Map<String,String>> selectByTeacherId(String teacherID){
		String sql="select * from TB_SelectTeacher where TeacherID='" + teacherID + "'";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//根据学号查询教师信息
    public SelectTeacherMsg queryByTeacherId(String teacherID) {
    	SelectTeacherMsg selectTeacherMs = null;
		String sql = "select * from TB_SelectTeacher where TeacherID='" + teacherID + "'";
		System.out.println(sql);
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
	//修改用户数据
	public int updateTeacher(String StuID, String TeacherID, float Grade, int choosedState, String SelectDate) {
	
		String sql = "update TB_SelectTeacher set TeacherID='" + TeacherID + "',Grade=" + Grade 
				     + ",choosedState=" + choosedState + "," + "',SelectDate='" + SelectDate 
				     +"' where TeacherID='" + TeacherID+ "'";
		
		System.out.println(sql);
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	public int findById(String stuId) {
		int i = 0;
		String sql = "select count(*) from TB_SelectTeacher where stuId='" + stuId + "'";
		ResultSet rs = dbCon.queryForRS(sql);//更新数据库
		if(rs!=null){
			i=1;
		}
		return i;
	}
	
	//根据学号删除数据
	public int deleteById(String stuId) {
		String sql = "delete from TB_SelectTeacher where stuId='" + stuId + "'";
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	//关闭数据库连接
	public void close() {
		dbCon.close();
	}
}

