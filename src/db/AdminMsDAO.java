package db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class AdminMsDAO {
	DBConnection dbCon;
	
    public AdminMsDAO(){
		dbCon=new DBConnection();//���ݿ����Ӷ���
		dbCon.createConnection();
	}
    
	public int insert(String AdminID,String AdminName,String APassword,String tel){
		// ���뵽���ݿ�
		String sql = "insert into TB_Admin(AdminID, AdminName, APassword,tel)"
				+ "values('"+AdminID+"','"+AdminName+"','"+APassword+"','"+tel+"')";
		System.out.println(sql);
		// �������ݿ�
		int i=dbCon.update(sql);
		dbCon.close();
		return i;
	}
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryAll(){
		String sql="select * from TB_Admin";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//ɾ������Ա����
	public int deleteById(String adminId) {
		String sql = "delete from TB_Admin where adminID='" + adminId + "'";
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	
	public void close() {
		dbCon.close();
	}
}

