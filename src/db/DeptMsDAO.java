package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.DeptMsg;
import javabean.SelectTeacherMsg;

public class DeptMsDAO extends MsDAO {
	// 查询得到详细信息
    public DeptMsg getDeptMsg(String sql) {	
		DeptMsg deptMsg = null;
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
    
    // 添加系, 如果存在就更新，不存在就插入
   	public int addDept(String deptID, String deptName){
   		if(findOneDept(deptID)!=null){
   		   // 更新数据
   		   sql = "update TB_Dept set DeptName='" + deptName + "' where DeptID='" + deptID+ "'";
   		}else {
   		   // 添加数据
   	 	   sql = "insert into TB_Dept(DeptID, DeptName)"
   				+ "values('"+deptID+"','"+deptName+"')";  
   		}
   		System.out.println(sql);
   		// 更新数据库
   		return updateDB(sql);
   	}
   	
	// 查找是否存在这个用户
	public DeptMsg findOneDept(String deptId) {
		sql = "select * from TB_Dept where DeptID='" + deptId + "'";
		return getDeptMsg(sql);//返回javaBean
	}

	// 根据系查询系的信息
    public DeptMsg findByDeptID(String deptId) {
		sql = "select * from TB_Dept where DeptID='" + deptId + "'";
		return getDeptMsg(deptId);//返回javaBean
	}
	
    // 修改系
	public int updateByDeptId(String deptID, String deptName) {
		sql = "update TB_SelectTeacher set DeptName='" + deptName +"'where TeacherID='" + deptID+ "'";
		return updateDB(sql);
	}
	
	//根据系删除数据
	public int deleteByDeptId(String deptId) {
		String sql = "delete from TB_Dept where DeptID='" + deptId + "'";
		return updateDB(sql);
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryDeptAll(){
		sql="select * from TB_Dept";
		return queryDBForList(sql);
	}

}

