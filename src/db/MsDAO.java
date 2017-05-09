package db;

import java.util.ArrayList;
import java.util.Map;

public class MsDAO {
	DBConnection dbCon;
	String sql = "";
	
	// 初始化，连接数据库
    public MsDAO(){
		dbCon=new DBConnection();//数据库连接对象
		dbCon.createConnection();
	}
    
    // 更新数据库
    public int updateDB(String sql){
    	int i=dbCon.update(sql);
	    return i;
    }
    
    // 查找数据库
    public ArrayList<Map<String,String>> queryDBForList(String sql){
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
    
   // 关闭数据库连接
 	public void close() {
 		dbCon.close();
 	}
}
