package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import com.bean.SelectTeacherMsg;

public class SelectTeacherDAO extends MsDAO implements java.io.Serializable{
	
	/**
	 * querySelectStu函数查询选择该导师并且未淘汰的学生信息
	 * @param teaId：导师工号
	 * @return 选择该导师并且未淘汰的学生信息列表
	 */
	public ArrayList<Map<String, String>> querySelectStu_1(String teaId){
		String sql = "select TB_SelectTeacher.stuID as stuID,stuName,TB_Dept.DeptName as deptName,TB_Class.ClassName as className,Sex,TB_Student.Grade as grade,tel,Intro, TB_SelectTeacher.choosedState as choosedState "
				   + "from TB_SelectTeacher join TB_Student on TB_SelectTeacher.StuID = TB_Student.StuID join TB_Class on TB_Class.ClassID = TB_Student.ClassID join TB_Dept on TB_Dept.DeptID = TB_Student.DeptID "
				   + "where TB_SelectTeacher.TeacherID = '" + teaId + "' and TB_SelectTeacher.choosedState != 1;";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList<Map<String, String>> results = dbc.queryForList(sql);
		dbc.close();
		
		return results;
	}
	
	/**
	 * updateChoosedState函数是当老师对所选学生做出选择后修改selectTeacher表和student表中ChoosedState的值。
	 * @param state：老师对学生做出什么样的选择（1:淘汰学生,2：选定学生）
	 * @param stuId：所选学生的学号数组
	 * @param teaId：老师的工号
	 * @return 是否成功修改
	 */
	public int updateChoosedState(int state, String[] stuId, String teaId){
		//修改selectTeacher表的ChoosedState的值的sql
		String sql_st = "update TB_SelectTeacher set choosedState = " + state + " where TeacherID = '" + teaId + "' and choosedState = '0' and ( StuID = '" + stuId[0] +"'";
		for(int i=1;i<stuId.length;i++){
			sql_st = sql_st + " or StuID = '" + stuId[i] + "'";
		}
		sql_st += ");";
		//修改student表的ChoosedState的值的sql
		String sql_s = "update TB_Student set choosedState = " + (state+1) + " where StuID = '" + stuId[0] +"'";
		for(int i=1;i<stuId.length;i++){
			sql_s = sql_s + " or StuID = '" + stuId[i] + "'";
		}
		sql_st += ";";
//		System.out.println(sql_s);
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		int i = 1;
		try{
			dbc.setAutoCommit(false);
			int j = dbc.update(sql_st);
			int k = dbc.update(sql_s);
			dbc.setCommit();	//统一提交事务
			//判断是否成功修改
			if(j==0||k==0){
				throw new SQLException();
			}
		}catch(SQLException e){
			i = 0;
			//如果其中一项SQL操作失败，就不会执行commit()方法，而是产生相应的sqlexception，此时就可以捕获异常代码块中调用rollback()方法撤销事务
		    dbc.setRollback();
		}
		dbc.close();
		
		return i;
	}
	
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
//			System.out.println(sql);
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
		
		public ArrayList<Map<String, String>> querySelectStu(String teaId){
			String sql = "select TB_SelectTeacher.stuID as stuID,stuName,TB_Student.Grade as grade, TB_SelectTeacher.choosedState as choosedState, TB_SelectTeacher.selectDate "
					   + "from TB_SelectTeacher join TB_Student on TB_SelectTeacher.StuID = TB_Student.StuID "
					   + "where TB_SelectTeacher.TeacherID = '" + teaId + "' and TB_SelectTeacher.choosedState != 1;";
//			System.out.println(sql);
			return queryDBForList(sql);
		}
		
		public ArrayList<Map<String, String>> querySelectTeacher(String stuId){
			String sql = "select TB_SelectTeacher.teacherID as teacherID,teacherName,TB_SelectTeacher.choosedState as choosedState, TB_SelectTeacher.selectDate "
					   + "from TB_SelectTeacher join TB_Teacher on TB_SelectTeacher.TeacherID = TB_Teacher.TeacherID "
					   + "where TB_SelectTeacher.StuID = '" + stuId + "'";
//			System.out.println(sql);
			return queryDBForList(sql);
		}
		public String getTeacherName(String TeacherID) throws SQLException{
			sql = "select TeacherName from TB_Teacher where TeacherID = '"+ TeacherID+"'";
			String TeacherName = "";
			ResultSet rs = dbCon.queryForRS(sql);
//			System.out.println(sql);
			if (rs != null) {
				try {
					if (rs.next()) {
						TeacherName = rs.getString("TeacherName");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return TeacherName;
		}
}
