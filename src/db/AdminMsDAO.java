package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.AdminMsg;
import javabean.ClassMsg;

public class AdminMsDAO {
	DBConnection dbCon;
	
    public AdminMsDAO(){
		dbCon=new DBConnection();//数据库连接对象
		dbCon.createConnection();
	}
    
    // 插入数据
	public int insert(String AdminID,String AdminName,String APassword,String tel){
		// 插入到数据库
		String sql = "insert into TB_Admin(AdminID, AdminName, APassword,tel)"
				+ "values('"+AdminID+"','"+AdminName+"','"+APassword+"','"+tel+"')";
		System.out.println(sql);
		// 更新数据库
		int i=dbCon.update(sql);
		return i;
	}
		
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryAll(){
		String sql="select * from TB_Admin";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	// 根据班级编号查询班级信息
    public AdminMsg queryByAdminId(String adminId) {
    	AdminMsg adminMsg = null;
		String sql = "select * from TB_Admin where AdminID='" + adminId + "'";
		//将查询结果放到结果集
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					adminMsg = new AdminMsg();//创建javaBean
					adminMsg.setAdminID(rs.getString("AdminID"));
					adminMsg.setAdminName(rs.getString("AdminName"));
					adminMsg.setAPassword(rs.getString("APassword"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return adminMsg;//返回javaBean
	}
    
	//删除管理员数据
	public int deleteById(String adminId) {
		String sql = "delete from TB_Admin where adminID='" + adminId + "'";
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	// 关闭数据库连接
	public void close() {
		dbCon.close();
	}
}

