package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.ClassMsg;
import javabean.DeptMsg;

public class ClassMsDAO {
	DBConnection dbCon;
	
    public ClassMsDAO(){
		dbCon=new DBConnection();//���ݿ����Ӷ���
		dbCon.createConnection();
	}
    
	public int insert(String ClassID,String ClassName,String DeptID){
		// ���뵽���ݿ�
		String sql = "insert into TB_Class(ClassID, ClassName, DeptID)"
				   + "values('"+ClassID+"','"+ClassName+"','"+DeptID+"')";
		System.out.println(sql);
		// �������ݿ�
		int i=dbCon.update(sql);
		return i;
	}
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryClassAll(){
		String sql="select * from TB_Class";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	// ���ݰ༶��Ų�ѯ�༶��Ϣ
    public ClassMsg queryByName(String classId) {
    	ClassMsg classMsg = null;
		String sql = "select * from TB_Class where ClassID='" + classId + "'";
		System.out.println(sql);
		//����ѯ����ŵ������
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					classMsg = new ClassMsg();//����javaBean
					classMsg.setClassID(rs.getString("ClassID"));
					classMsg.setClassName(rs.getString("ClassName"));
					classMsg.setDeptID(rs.getString("DeptID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return classMsg;//����javaBean
	}
	// �޸�ϵ
	public int updateDept(String classID, String className,String deptId) {
		String sql = "update TB_Class set className='" + className +"', deptID='" + deptId
				     +"'where classID='" + classID+ "'";
		System.out.println(sql);
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	//����ϵɾ������
	public int deleteById(String classId) {
		String sql = "delete from TB_Class where classID='" + classId + "'";
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	//�ر����ݿ�����
	public void close() {
		dbCon.close();
	}
}

