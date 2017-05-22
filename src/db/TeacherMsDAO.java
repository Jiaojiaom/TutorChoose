package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.bean.TeacherMsg;

public class TeacherMsDAO extends MsDAO {
	// ��ѯ���н�ʦ����Ϣ
    public TeacherMsg getTeacherMsg(String sql) {
    	TeacherMsg teacherMs = null;
		//����ѯ����ŵ������
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					teacherMs = new TeacherMsg();//����javaBean
					teacherMs.setTeacherID(rs.getString("TeacherID"));
					teacherMs.setTeacherName(rs.getString("TeacherName"));
					teacherMs.setTPassword(rs.getString("TPassword"));
					teacherMs.setDeptID(rs.getString("DeptID"));
					teacherMs.setSex(rs.getString("Sex"));
					teacherMs.setTitle(rs.getString("Title"));
					teacherMs.setStudentCount(rs.getInt("studentCount"));
					teacherMs.setPrivilege(Integer.parseInt(rs.getString("Privilege")));
					teacherMs.setTel(rs.getString("tel"));
					teacherMs.setIntro(rs.getString("Intro"));	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return teacherMs;//����javaBean
	}
	
	public int addTeacher(String TeacherID,String TeacherName,String DeptID,String Sex,String title,String tel,String Intro){
		DeptMsDAO deptDao = new DeptMsDAO();
		if(deptDao.findOneDept(DeptID)!=null){ 
			if(findByTeacherID(TeacherID)!=null){
//				System.out.println("��������");
				sql = "update TB_Teacher set TeacherName='" + TeacherName + "',DeptID='" + DeptID 
			             + "',Sex='" + Sex + "',Title='" + title + "',tel='" + tel+ "',Intro='" + Intro 
			             +"' where TeacherID='" + TeacherID+ "'";
			}
			else {
				System.out.println("��������");
				// ���뵽���ݿ�
				sql = "insert into TB_Teacher(TeacherID, TeacherName, DeptID, Sex, Title,tel,Intro)"
						+ " values('"+TeacherID+"','"+TeacherName+"','"+DeptID+"','" + Sex +"','"+title+"','"+tel+"','"+Intro+"')";
			}
//			System.out.println(sql);
			return updateDB(sql);
		}else {
//			System.out.println("ϵ������");
			return -1;
		}
	}
	
	//���ݹ��Ų�ѯ��ʦ��Ϣ
    public TeacherMsg findByTeacherID(String teacherId) {
		sql = "select * from TB_Teacher where TeacherId='" + teacherId + "'";
		return getTeacherMsg(sql);//����javaBean
	}
    
	//�޸��û�����
	public int updateByTeacherId(String TeacherID, String TeacherName, String DeptID, String Sex,
			              String Title, int studentCount, int Privilege, String tel,String Intro) {
		DeptMsDAO deptDao = new DeptMsDAO();
		if(deptDao.findOneDept(DeptID)!=null){ 
			sql = "update TB_Teacher set TeacherName='" + TeacherName + "',DeptID='" + DeptID 
			             + "',Sex='" + Sex + "',Title='" + Title + "',studentCount=" + studentCount 
			             + ",Privilege=" + Privilege+ ",tel='" + tel+ "',Intro='" + Intro 
			             +"' where TeacherID='" + TeacherID+ "'";
			return updateDB(sql);
		}else {
			return -1;
		}
	}
	
	//����ѧ��ɾ������
	public int deleteByTeacherId(String teacherId) {
		sql = "delete from TB_Teacher where TeacherId='" + teacherId + "'";
		return updateDB(sql);
	}
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryTeacherList(){
		sql="select * from TB_Teacher";
//		System.out.println(sql);
		return queryDBForList(sql);
	}

	public int resetPassword(String TeacherID) {
		sql = "update TB_Teacher set TPassword='123456'"
	             +" where TeacherID='" + TeacherID+ "'";
//		System.out.println(sql);
		return updateDB(sql);
	}
	
	public int setRules(int Privilege) {
		// 1-��Ȩ�� 3-�ȵ��ȵã� 4-���ռ���
		// ���������Ȩģʽ����ʦ�����ù���
		sql = "update TB_Teacher set Privilege="+Privilege+" where Privilege != 1"; 
//		System.out.println(sql);
		return updateDB(sql);
	}
	
	public int setStudentCount(int StudentCount) {
		// 1-��Ȩ�� 3-�ȵ��ȵã� 4-���ռ���
		// ���������Ȩģʽ����ʦ�����ù���
		sql = "update TB_Teacher set StudentCount="+StudentCount; 
//		System.out.println(sql);
		return updateDB(sql);
	}
	
	public int getRules() {
		// 1-��Ȩ�� 3-�ȵ��ȵã� 4-���ռ���
		// ���������Ȩģʽ����ʦ�����ù���
		int rules = 0;
	    sql = "select distinct Privilege from TB_Teacher where Privilege != 1";
	    ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) 
					rules = Integer.parseInt(rs.getString("Privilege"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rules;
	}
	
	public int getStudentCount() {	
		int studentCount = 1;
	    sql = "select distinct StudentCount from TB_Teacher";
	    ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) 
					studentCount = Integer.parseInt(rs.getString("StudentCount"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return studentCount;
	}
	
	public int setPrivilege(String TeacherID, int Privilege) {
		sql = "update TB_Teacher set Privilege = "+Privilege+" where TeacherID = '"+TeacherID+"'"; 
//		System.out.println(sql);
		return updateDB(sql);
	}
	
	public int getPrivilege(String TeacherID) {
		int Privilege = 0;
	    sql = "select Privilege from TB_Teacher where TeacherID = '"+TeacherID+"'";
	    ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) 
					Privilege = Integer.parseInt(rs.getString("Privilege"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Privilege;
	}
	public String getTeacherName(String TeacherID) throws SQLException{
		sql = "select TeacherName from TB_Teacher where TeacherID = '"+ TeacherID+"'";
		String TeacherName = "";
		ResultSet rs = dbCon.queryForRS(sql);
//		System.out.println(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					TeacherName = rs.getString("TeacherName");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return TeacherName;
	}
}

