package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javabean.ClassMsg;
import javabean.DeptMsg;

public class ClassMsDAO extends MsDAO{
    // �ҳ��༶����Ϣ
	public ClassMsg getClassMsg(String sql) {
    	ClassMsg classMsg = null;
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
	
	// ��Ӱ༶, ������ھ͸��£������ھͲ���
	public int addClass(String ClassID,String ClassName,String DeptID){
		if(findOneClass(ClassID)!=null){
		   // ��������
		   sql = "update TB_Class set ClassName='" + ClassName + "',DeptID='" + DeptID 
			     +"' where ClassID='" + ClassID+ "'";
		}else {
		   // �������
	 	   sql = "insert into TB_Class(ClassID, ClassName, DeptID)"
				+ "values('"+ClassID+"','"+ClassName+"','"+DeptID+"')";  
		}
		// �������ݿ�
		return updateDB(sql);
	}
	
	// ���ݰ༶��Ų�ѯ�༶��Ϣ
    public ClassMsg findOneClass(String classId) {
		sql = "select * from TB_Class where ClassID='" + classId + "'";
		return getClassMsg(sql);//����javaBean
	}
	// �޸İ༶
	public int updateByClassId(String classID, String className,String deptId) {
		sql = "update TB_Class set className='" + className +"', deptID='" + deptId
				     +"'where classID='" + classID+ "'";
		return updateDB(sql);
	}
	
	//���ݰ༶ɾ������
	public int deleteByClassId(String classId) {
		sql = "delete from TB_Class where classID='" + classId + "'";
		return updateDB(sql);
	}
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryClassAll(){
		sql="select * from TB_Class";
		return queryDBForList(sql);
	}
}

