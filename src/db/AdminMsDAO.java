package db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class AdminMsDAO {
	DBConnection dbCon;
	
    public AdminMsDAO(){
		dbCon=new DBConnection();//数据库连接对象
		dbCon.createConnection();
	}
    
	public int insert(String AdminID,String AdminName,String APassword,String tel){
		// 插入到数据库
		String sql = "insert into TB_Admin(AdminID, AdminName, APassword,tel)"
				+ "values('"+AdminID+"','"+AdminName+"','"+APassword+"','"+tel+"')";
		System.out.println(sql);
		// 更新数据库
		int i=dbCon.update(sql);
		dbCon.close();
		return i;
	}
	// 从数据库里面选出所有记录
	public ArrayList<Map<String,String>> queryAll(){
		String sql="select * from TB_Admin";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//删除管理员数据
	public int deleteById(String adminId) {
		String sql = "delete from TB_Admin where adminID='" + adminId + "'";
		int i = dbCon.update(sql);//更新数据库
		return i;
	}
	
	public void close() {
		dbCon.close();
	}
}

