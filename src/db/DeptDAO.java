package db;

import java.util.ArrayList;
import java.util.HashMap;

public class DeptDAO implements java.io.Serializable{
	
	/**
	 * queryDeptName������ȡϵ��
	 * @param deptID��ϵ���
	 * @return ϵ��
	 */
	public String queryDeptName(String deptID){
		String sql = "select DeptName from TB_Dept where DeptID = '" + deptID +"';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList results = dbc.queryForList(sql);
		dbc.close();
		
		HashMap hm = (HashMap) results.get(0);
		return (String) hm.get("deptname");
	}
}
