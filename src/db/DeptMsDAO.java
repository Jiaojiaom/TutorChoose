package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.DeptMsg;
import javabean.SelectTeacherMsg;

public class DeptMsDAO {
	DBConnection dbCon;
		
    public DeptMsDAO(){
		dbCon=new DBConnection();//数据库连接对象
		dbCon.createConnection();
	}
    
	public int insert(String DeptID,String DeptName){
		// 插入到数据库
		String sql = "insert into TB_Dept(DeptID, DeptName)"
				   + "values('"+DeptID+"','"+DeptName+"')";
		System.out.println(sql);
		// 更新数据库
		int i=dbCon.update(sql);
		return i;
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryDeptAll(){
		String sql="select * from TB_Dept";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//根据系查询教师信息
    public DeptMsg queryByName(String deptId) {
    	DeptMsg deptMsg = null;
		String sql = "select * from TB_Dept where DeptID='" + deptId + "'";
		System.out.println(sql);
		//将查询结果放到结果集
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					deptMsg = new DeptMsg();//创建javaBean
					deptMsg.setDeptID(rs.getString("DeptID"));
					deptMsg.setDeptName(rs.getString("DeptName"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deptMsg;//返回javaBean
	}
	//修改系
	public int updateDept(String deptID, String deptName) {
		String sql = "update TB_SelectTeacher set DeptName='" + deptName +"'where TeacherID='" + deptID+ "'";
		System.out.println(sql);
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	//根据系删除数据
	public int deleteById(String deptId) {
		String sql = "delete from TB_Dept where DeptID='" + deptId + "'";
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	//关闭数据库连接
	public void close() {
		dbCon.close();
	}
}

