package db;

import java.util.ArrayList;
import java.util.Map;

public class TeacherDAO {
	//查询所有数据
	/*public ArrayList<Map<String, String>> queryAll() {
		DBConnection dbCon = new DBConnection();
		dbCon.createConnection();
		
		String sql = "select *from tb_student where teacherid='T02001'";
		ArrayList<Map<String, String>> list=dbCon.queryForList(sql);
		dbCon.close();
		
		return list;
	}*/
	
	/**
	 * updatePwd函数更新导师的密码
	 * @param id：导师工号
	 * @param pwd：导师密码
	 * @return 更新是否成功
	 */
	public int updatePwd(String id, String pwd){
		String sql = "update TB_Teacher set TPassword = '" + pwd + "' where TeacherID = '" + id + "';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		int i = dbc.update(sql);
		dbc.close();
		return i;
	}
	
	/**
	 * updateInfo函数更新导师的个人信息
	 * @param id：导师工号
	 * @param intro：导师填写的个人信息
	 * @param tel：导师的电话号码
	 * @return
	 */
	public int updateInfo(String id, String intro, String tel){
		String sql = "update TB_Teacher set tel = '" + tel + "', Intro = '" + intro + "' where TeacherID = '" + id + "';";
//		System.out.println(sql);
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		int i = dbc.update(sql);
		dbc.close();
		return i;
	}
	
	/**
	 * PwdIsTrue函数检测该导师输入的原密码是否正确
	 * @param id:导师工号
	 * @param pwd ：导师输入的原密码
	 * @return 该导师的原密码是否正确
	 */
	public boolean PwdIsTrue(String id, String pwd){
		String sql = "select TPassword from TB_Teacher where TeacherID = '" + id + "' and TPassword = '" + pwd + "';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList results = dbc.queryForList(sql);
		dbc.close();
		
		if(results == null || results.isEmpty()){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * queryTeacher函数检测该导师输入的密码是否正确，用于登录和修改密码
	 * @param id:导师工号
	 * @param pwd ：导师输入的密码
	 * @return 在该工号和密码下的导师信息
	 */
	public ArrayList queryTeacher(String id, String pwd){
		String sql = "select TeacherName,Privilege from TB_Teacher where TeacherID = '" + id + "' and TPassword = '" + pwd + "';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList results = dbc.queryForList(sql);
		dbc.close();
		
		return results;
	}
	
	/**
	 * queryTeacherInfo函数查询导师的信息
	 * @param id：导师工号
	 * @return 导师的信息列表
	 */
	public ArrayList queryTeacherInfo(String id){
		String sql = "select TeacherID,TeacherName,DeptName,DeptID,Sex,Title,tel,Intro,Privilege from TB_Teacher natural join TB_Dept where TeacherID = '" + id + "';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList results = dbc.queryForList(sql);
		dbc.close();
		
		return results;
	}
	
	/**
	 * isReachLimit函数判断选择该导师的学生人数是否达到老师的可带人数的最大上限
	 * @param studentList：选择该导师的学生列表
	 * @return 是否达到老师的可带人数的最大上限
	 */
	public boolean isReachLimit(ArrayList studentList){
		String sql = "select studentCount from TB_Teacher limit 1";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList limitCount = dbc.queryForList(sql);
		dbc.close();
//		System.out.println("1");
		int limit = Integer.valueOf((String) ((Map) limitCount.get(0)).get("studentcount"));
		if(studentList.size()>limit){
			return true;
		}else{
			return false;
		}
	}
}
