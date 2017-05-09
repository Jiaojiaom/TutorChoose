package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javabean.AdminMsg;

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
}

