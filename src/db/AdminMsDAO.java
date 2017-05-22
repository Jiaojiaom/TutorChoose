package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.bean.AdminMsg;

public class AdminMsDAO extends MsDAO {
    // ��ѯ�õ���ϸ��Ϣ
    public AdminMsg getAdminMsg(String sql) {
    	AdminMsg adminMsg = null;
		//����ѯ����ŵ������
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					adminMsg = new AdminMsg();//����javaBean
					adminMsg.setAdminID(rs.getString("AdminID"));
					adminMsg.setAdminName(rs.getString("AdminName"));
					adminMsg.setAPassword(rs.getString("APassword"));
					adminMsg.setTel(rs.getString("Tel"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return adminMsg;//����javaBean
	}
    
    // ��ӹ���Ա, ������ھ͸��£������ھͲ���
	public int addAdmin(String AdminID,String AdminName,String APassword,String tel){
		if(findOneAdmin(AdminID)!=null){
		   // ��������
		   sql = "update TB_Admin set AdminName='" + AdminName + "',APassword='" + APassword 
				     + "',tel='" + tel +"' where AdminID='" + AdminID+ "'";
		}else {
		   // �������
	 	   sql = "insert into TB_Admin(AdminID, AdminName, APassword,tel)"
				+ "values('"+AdminID+"','"+AdminName+"','"+APassword+"','"+tel+"')";  
		}
//		System.out.println(sql);
		// �������ݿ�
		return updateDB(sql);
	}
	
	// �����Ƿ��������û�
	public AdminMsg findOneAdmin(String adminId) {
		sql = "select * from TB_Admin where AdminID='" + adminId + "'";
		return getAdminMsg(sql);//����javaBean
	}
	
	// ���չ���Ա��Ų�ѯ
    public AdminMsg findByAdminId(String adminId) {
		sql = "select * from TB_Admin where AdminID='" + adminId + "'";
		return getAdminMsg(sql);//����javaBean
	}
	
	// �޸��û�����
	public int updateByAdminId(String AdminID,String AdminName,String APassword,String tel) {
		sql = "update TB_Admin set AdminName='" + AdminName + "',APassword='" + APassword 
				     + "',tel='" + tel +"' where AdminID='" + AdminID+ "'";
		return updateDB(sql);
	}
    
	//ɾ������Ա����
	public int deleteByAdminId(String adminId) {
		sql = "delete from TB_Admin where adminID='" + adminId + "'";
		return updateDB(sql);
	}
	
	// ѡ�����е�TB_Admin�������
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
		//�޸�selectTeacher���ChoosedState��ֵ��sql
		String sql_st = "update TB_SelectTeacher set choosedState = 2 where choosedState = 0";
		//�޸�student���ChoosedState��ֵ��sql
		String sql_s = "update TB_Student set choosedState = 3 where choosedState = 1";
//		System.out.println(sql_s);
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		int i = 1;
		try{
			dbc.setAutoCommit(false);
			int j = dbc.update(sql_st);
			int k = dbc.update(sql_s);
			dbc.setCommit();	//ͳһ�ύ����
			//�ж��Ƿ�ɹ��޸�
			if(j==0||k==0){
				throw new SQLException();
			}
		}catch(SQLException e){
			i = 0;
			//�������һ��SQL����ʧ�ܣ��Ͳ���ִ��commit()���������ǲ�����Ӧ��sqlexception����ʱ�Ϳ��Բ����쳣������е���rollback()������������
		    dbc.setRollback();
		}
		dbc.close();
		
		return i;
	}
}

