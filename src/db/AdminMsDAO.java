package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.bean.AdminMsg;

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
//		System.out.println(sql);
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
	
	public int setPrivilegeModel(String PrivilegeModel) {
		sql = "update TB_Admin set PrivilegeModel = '"+PrivilegeModel+"'"; 
//		System.out.println(sql);
		return updateDB(sql);
	}
	
	public int setAuthorityModel(String AuthorityModel) {
		sql = "update TB_Admin set AuthorityModel = '"+AuthorityModel+"'"; 
//		System.out.println(sql);
		return updateDB(sql);
	}
	
	public String getAuthorityModel() throws SQLException{
		sql = "select AuthorityModel from TB_Admin limit 1";
		String AuthorityModel = "";
//		System.out.println(sql);
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					AuthorityModel = rs.getString("AuthorityModel");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return AuthorityModel;
	}
	
	public String getPrivilegeModel() throws SQLException{
		sql = "select PrivilegeModel from TB_Admin limit 1";
		String PrivilegeModel = "";
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					PrivilegeModel = rs.getString("PrivilegeModel");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return PrivilegeModel;
	}
	public int setLimitModel(String LimitModel) {
		sql = "update TB_Admin set LimitModel = '"+LimitModel+"'"; 
//		System.out.println(sql);
		return updateDB(sql);
	}
	
	public String getLimitModel() throws SQLException{
		sql = "select LimitModel from TB_Admin limit 1";
		String LimitModel = "";
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					LimitModel = rs.getString("LimitModel");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return LimitModel;
	}
	
	public int updateAllChoosedState(){
		//修改selectTeacher表的ChoosedState的值的sql
		String sql_st = "update TB_SelectTeacher set choosedState = 2 where choosedState = 0";
		//修改student表的ChoosedState的值的sql
		String sql_s = "update TB_Student set choosedState = 3 where choosedState = 1";
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
}

