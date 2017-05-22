package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import com.bean.ClassMsg;
import com.bean.DeptMsg;

import db.DeptMsDAO;

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
		DeptMsDAO deptDao = new DeptMsDAO();
		if(deptDao.findOneDept(DeptID)!=null){
			if(findOneClass(ClassID)!=null){
			   // ��������
			   sql = "update TB_Class set ClassName='" + ClassName + "',DeptID='" + DeptID 
				     +"' where ClassID='" + ClassID+ "'";
			}else {
			   // �������
		 	   sql = "insert into TB_Class(ClassID, ClassName, DeptID)"
		 			  + "values('"+ClassID+"','"+ClassName+"','"+DeptID+"')";  
			}  
//			System.out.println(sql);
			return updateDB(sql);
   		}else {
//   			System.out.println("ϵ������");
//   			System.out.println("");
   			return -1;
   		}
	}
	
	// ���ݰ༶��Ų�ѯ�༶��Ϣ
    public ClassMsg findOneClass(String classId) {
		sql = "select * from TB_Class where ClassID='" + classId + "'";
		return getClassMsg(sql);//����javaBean
	}
	// �޸İ༶
	public int updateByClassId(String classID, String className,String deptId) {
		DeptMsDAO deptDao = new DeptMsDAO();
		if(deptDao.findOneDept(deptId)!=null){
			sql = "update TB_Class set className='" + className +"', deptID='" + deptId
					     +"'where classID='" + classID+ "'";
			return updateDB(sql);
		}else {
//			System.out.println("ϵ������");
			return -1;
		}
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

	public String findDeptIDByClassID(String classID) {
		// TODO Auto-generated method stub
		sql="select DeptID from TB_Class where ClassID = " + classID;
		getClassMsg(sql);
		return null;
	}
	
	public String getClassName(String ClassID) throws SQLException{
		sql = "select ClassName from TB_Class where ClassID = "+ ClassID;
		String ClassName = "";
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					ClassName = rs.getString("ClassName");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ClassName;
	}
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryClassByDeptID(String DeptID){
		sql="select * from TB_Class where DeptID = '"+DeptID+"'";
		return queryDBForList(sql);
	}


}

