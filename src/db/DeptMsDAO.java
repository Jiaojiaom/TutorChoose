package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.DeptMsg;
import javabean.SelectTeacherMsg;

public class DeptMsDAO {
	DBConnection dbCon;
		
    public DeptMsDAO(){
		dbCon=new DBConnection();//���ݿ����Ӷ���
		dbCon.createConnection();
	}
    
	public int insert(String DeptID,String DeptName){
		// ���뵽���ݿ�
		String sql = "insert into TB_Dept(DeptID, DeptName)"
				   + "values('"+DeptID+"','"+DeptName+"')";
		System.out.println(sql);
		// �������ݿ�
		int i=dbCon.update(sql);
		return i;
	}
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryDeptAll(){
		String sql="select * from TB_Dept";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//����ϵ��ѯ��ʦ��Ϣ
    public DeptMsg queryByName(String deptId) {
    	DeptMsg deptMsg = null;
		String sql = "select * from TB_Dept where DeptID='" + deptId + "'";
		System.out.println(sql);
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
	//�޸�ϵ
	public int updateDept(String deptID, String deptName) {
		String sql = "update TB_SelectTeacher set DeptName='" + deptName +"'where TeacherID='" + deptID+ "'";
		System.out.println(sql);
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	
	//����ϵɾ������
	public int deleteById(String deptId) {
		String sql = "delete from TB_Dept where DeptID='" + deptId + "'";
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	
	//�ر����ݿ�����
	public void close() {
		dbCon.close();
	}
}

