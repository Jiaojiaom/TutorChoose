package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javabean.AdminMsg;

public class AdminMsDAO extends MsDAO {
    // 查询得到详细信息
    public AdminMsg getAdminMsg(String sql) {
    	AdminMsg adminMsg = null;
		//将查询结果放到结果集
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					adminMsg = new AdminMsg();//创建javaBean
					adminMsg.setAdminID(rs.getString("AdminID"));
					adminMsg.setAdminName(rs.getString("AdminName"));
					adminMsg.setAPassword(rs.getString("APassword"));
					adminMsg.setTel(rs.getString("Tel"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return adminMsg;//返回javaBean
	}
    
    // 添加管理员, 如果存在就更新，不存在就插入
	public int addAdmin(String AdminID,String AdminName,String APassword,String tel){
		if(findOneAdmin(AdminID)!=null){
		   // 更新数据
		   sql = "update TB_Admin set AdminName='" + AdminName + "',APassword='" + APassword 
				     + "',tel='" + tel +"' where AdminID='" + AdminID+ "'";
		}else {
		   // 添加数据
	 	   sql = "insert into TB_Admin(AdminID, AdminName, APassword,tel)"
				+ "values('"+AdminID+"','"+AdminName+"','"+APassword+"','"+tel+"')";  
		}
		// 更新数据库
		return updateDB(sql);
	}
	
	// 查找是否存在这个用户
	public AdminMsg findOneAdmin(String adminId) {
		sql = "select * from TB_Admin where AdminID='" + adminId + "'";
		return getAdminMsg(sql);//返回javaBean
	}
	
	// 按照管理员编号查询
    public AdminMsg findByAdminId(String adminId) {
		sql = "select * from TB_Admin where AdminID='" + adminId + "'";
		return getAdminMsg(sql);//返回javaBean
	}
	
	// 修改用户数据
	public int updateByAdminId(String AdminID,String AdminName,String APassword,String tel) {
		sql = "update TB_Admin set AdminName='" + AdminName + "',APassword='" + APassword 
				     + "',tel='" + tel +"' where AdminID='" + AdminID+ "'";
		return updateDB(sql);
	}
    
	//删除管理员数据
	public int deleteByAdminId(String adminId) {
		sql = "delete from TB_Admin where adminID='" + adminId + "'";
		return updateDB(sql);
	}
	
	// 选出所有的TB_Admin表的数据
	public ArrayList<Map<String,String>> queryAdminList(){
		sql="select * from TB_Admin";
		return queryDBForList(sql);
	}
}

