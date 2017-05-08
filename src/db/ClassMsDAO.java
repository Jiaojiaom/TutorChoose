package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.ClassMsg;
import javabean.DeptMsg;

public class ClassMsDAO {
	DBConnection dbCon;
	
    public ClassMsDAO(){
		dbCon=new DBConnection();//数据库连接对象
		dbCon.createConnection();
	}
    
	public int insert(String ClassID,String ClassName,String DeptID){
		// 插入到数据库
		String sql = "insert into TB_Class(ClassID, ClassName, DeptID)"
				   + "values('"+ClassID+"','"+ClassName+"','"+DeptID+"')";
		System.out.println(sql);
		// 更新数据库
		int i=dbCon.update(sql);
		return i;
	}
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryClassAll(){
		String sql="select * from TB_Class";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	// 根据班级编号查询班级信息
    public ClassMsg queryByName(String classId) {
    	ClassMsg classMsg = null;
		String sql = "select * from TB_Class where ClassID='" + classId + "'";
		System.out.println(sql);
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
	// 修改系
	public int updateDept(String classID, String className,String deptId) {
		String sql = "update TB_Class set className='" + className +"', deptID='" + deptId
				     +"'where classID='" + classID+ "'";
		System.out.println(sql);
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	//根据系删除数据
	public int deleteById(String classId) {
		String sql = "delete from TB_Class where classID='" + classId + "'";
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	//关闭数据库连接
	public void close() {
		dbCon.close();
	}
}

