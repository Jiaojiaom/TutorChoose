package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.ClassMsg;
import javabean.DeptMsg;

public class ClassMsDAO extends MsDAO{
    // 找出班级的信息
	public ClassMsg getClassMsg(String sql) {
    	ClassMsg classMsg = null;
		//将查询结果放到结果集
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					classMsg = new ClassMsg();//创建javaBean
					classMsg.setClassID(rs.getString("ClassID"));
					classMsg.setClassName(rs.getString("ClassName"));
					classMsg.setDeptID(rs.getString("DeptID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return classMsg;//返回javaBean
	}
	
	// 添加班级, 如果存在就更新，不存在就插入
	public int addClass(String ClassID,String ClassName,String DeptID){
		if(findOneClass(ClassID)!=null){
		   // 更新数据
		   sql = "update TB_Class set ClassName='" + ClassName + "',DeptID='" + DeptID 
			     +"' where ClassID='" + ClassID+ "'";
		}else {
		   // 添加数据
	 	   sql = "insert into TB_Class(ClassID, ClassName, DeptID)"
				+ "values('"+ClassID+"','"+ClassName+"','"+DeptID+"')";  
		}
		// 更新数据库
		return updateDB(sql);
	}
	
	// 根据班级编号查询班级信息
    public ClassMsg findOneClass(String classId) {
		sql = "select * from TB_Class where ClassID='" + classId + "'";
		return getClassMsg(sql);//返回javaBean
	}
	// 修改班级
	public int updateByClassId(String classID, String className,String deptId) {
		sql = "update TB_Class set className='" + className +"', deptID='" + deptId
				     +"'where classID='" + classID+ "'";
		return updateDB(sql);
	}
	
	//根据班级删除数据
	public int deleteByClassId(String classId) {
		sql = "delete from TB_Class where classID='" + classId + "'";
		return updateDB(sql);
	}
	
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryClassAll(){
		sql="select * from TB_Class";
		return queryDBForList(sql);
	}
}

