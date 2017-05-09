package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javabean.TeacherMsg;

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
	
	public int addTeacher(String TeacherID,String TeacherName,String DeptID,String Sex,String tel,String Intro){
		if(findByTeacherID(TeacherID)!=null){
			String Title=null, TPassword="123456", Privilege=null;
			int studentCount=0;
			sql = "update TB_Teacher set TeacherName='" + TeacherName + "',TPassword='" + TPassword + "',DeptID='" + DeptID 
		             + "',Sex='" + Sex + "',Title='" + Title + "',studentCount=" + studentCount 
		             + ",Privilege=" + Privilege+ ",tel='" + tel+ "',Intro='" + Intro 
		             +"' where TeacherID='" + TeacherID+ "'";
		}
		else {
			// ���뵽���ݿ�
			sql = "insert into TB_Teacher(TeacherID, TeacherName, DeptID, Sex,tel,Intro)"
					+ "values('"+TeacherID+"','"+TeacherName+"','"+DeptID+"','" +Sex+"','"+tel+"','"+Intro+"')";
		}
		return updateDB(sql);
	}
	
	//����ѧ�Ų�ѯ��ʦ��Ϣ
    public TeacherMsg findByTeacherID(String teacherId) {
		sql = "select * from TB_Teacher where TeacherId='" + teacherId + "'";
		return getTeacherMsg(sql);//����javaBean
	}
    
	//�޸��û�����
	public int updateByTeacherId(String TeacherID, String TeacherName, String TPassword, String DeptID,  String Sex,
			              String Title, int studentCount, int Privilege, String tel,String Intro) {
	
		sql = "update TB_Teacher set TeacherName='" + TeacherName + "',TPassword='" + TPassword + "',DeptID='" + DeptID 
		             + "',Sex='" + Sex + "',Title='" + Title + "',studentCount=" + studentCount 
		             + ",Privilege=" + Privilege+ ",tel='" + tel+ "',Intro='" + Intro 
		             +"' where TeacherID='" + TeacherID+ "'";
		return updateDB(sql);
	}
	
	//����ѧ��ɾ������
	public int deleteByTeacherId(String teacherId) {
		sql = "delete from TB_Teacher where TeacherId='" + teacherId + "'";
		return updateDB(sql);
	}	
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryTeacherList(){
		sql="select * from TB_Teacher";
		return queryDBForList(sql);
	}
}

