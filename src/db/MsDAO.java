package db;

import java.util.ArrayList;
import java.util.Map;

public class MsDAO {
	DBConnection dbCon;
	String sql = "";
	
	// ��ʼ�����������ݿ�
    public MsDAO(){
		dbCon=new DBConnection();//���ݿ����Ӷ���
		dbCon.createConnection();
	}
    
    // �������ݿ�
    public int updateDB(String sql){
    	int i=dbCon.update(sql);
	    return i;
    }
    
    // �������ݿ�
    public ArrayList<Map<String,String>> queryDBForList(String sql){
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
    
   // �ر����ݿ�����
 	public void close() {
 		dbCon.close();
 	}
}
