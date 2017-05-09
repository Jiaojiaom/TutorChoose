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
		dbCon=new DBConnection();//���ݿ����Ӷ���
		dbCon.createConnection();
	}
    
    // ��������
	public int insert(String AdminID,String AdminName,String APassword,String tel){
		// ���뵽���ݿ�
		String sql = "insert into TB_Admin(AdminID, AdminName, APassword,tel)"
				+ "values('"+AdminID+"','"+AdminName+"','"+APassword+"','"+tel+"')";
		System.out.println(sql);
		// �������ݿ�
		int i=dbCon.update(sql);
		return i;
	}
		
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryAll(){
		String sql="select * from TB_Admin";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	// ���ݰ༶��Ų�ѯ�༶��Ϣ
    public AdminMsg queryByAdminId(String adminId) {
    	AdminMsg adminMsg = null;
		String sql = "select * from TB_Admin where AdminID='" + adminId + "'";
		//����ѯ����ŵ������
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					adminMsg = new AdminMsg();//����javaBean
					adminMsg.setAdminID(rs.getString("AdminID"));
					adminMsg.setAdminName(rs.getString("AdminName"));
					adminMsg.setAPassword(rs.getString("APassword"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return adminMsg;//����javaBean
	}
    
	//ɾ������Ա����
	public int deleteById(String adminId) {
		String sql = "delete from TB_Admin where adminID='" + adminId + "'";
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	
	// �ر����ݿ�����
	public void close() {
		dbCon.close();
	}
}

