package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.DeptMsg;
import javabean.SelectTeacherMsg;

public class DeptMsDAO extends MsDAO {
	// ��ѯ�õ���ϸ��Ϣ
    public DeptMsg getDeptMsg(String sql) {	
		DeptMsg deptMsg = null;
		//����ѯ����ŵ������
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					deptMsg = new DeptMsg();//����javaBean
					deptMsg.setDeptID(rs.getString("DeptID"));
					deptMsg.setDeptName(rs.getString("DeptName"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deptMsg;//����javaBean
	}
    
    // ���ϵ, ������ھ͸��£������ھͲ���
   	public int addDept(String deptID, String deptName){
   		if(findOneDept(deptID)!=null){
   		   // ��������
   		   sql = "update TB_Dept set DeptName='" + deptName + "' where DeptID='" + deptID+ "'";
   		}else {
   		   // �������
   	 	   sql = "insert into TB_Dept(DeptID, DeptName)"
   				+ "values('"+deptID+"','"+deptName+"')";  
   		}
   		System.out.println(sql);
   		// �������ݿ�
   		return updateDB(sql);
   	}
   	
	// �����Ƿ��������û�
	public DeptMsg findOneDept(String deptId) {
		sql = "select * from TB_Dept where DeptID='" + deptId + "'";
		return getDeptMsg(sql);//����javaBean
	}

	// ����ϵ��ѯϵ����Ϣ
    public DeptMsg findByDeptID(String deptId) {
		sql = "select * from TB_Dept where DeptID='" + deptId + "'";
		return getDeptMsg(deptId);//����javaBean
	}
	
    // �޸�ϵ
	public int updateByDeptId(String deptID, String deptName) {
		sql = "update TB_SelectTeacher set DeptName='" + deptName +"'where TeacherID='" + deptID+ "'";
		return updateDB(sql);
	}
	
	//����ϵɾ������
	public int deleteByDeptId(String deptId) {
		String sql = "delete from TB_Dept where DeptID='" + deptId + "'";
		return updateDB(sql);
	}
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryDeptAll(){
		sql="select * from TB_Dept";
		return queryDBForList(sql);
	}

}

