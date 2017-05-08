package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javabean.TeacherMsg;

public class TeacherMsDAO {
	DBConnection dbCon;
	
	public TeacherMsDAO(){
		dbCon=new DBConnection();//���ݿ����Ӷ���
		dbCon.createConnection();
	}
	
	public int insert(String TeacherID,String TeacherName,String DeptID,String Sex,String tel,String Intro){
		// ���뵽���ݿ�
		String sql = "insert into TB_Teacher(TeacherID, TeacherName, DeptID, Sex,tel,Intro)"
				+ "values('"+TeacherID+"','"+TeacherName+"','"+DeptID+"','" +Sex+"','"+tel+"','"+Intro+"')";
		System.out.println(sql);
		// �������ݿ�
		int i=dbCon.update(sql);
		return i;
	}
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryTeacherAll(){
		String sql="select * from TB_Teacher";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//����ѧ�Ų�ѯ��ʦ��Ϣ
    public TeacherMsg queryByTeacherId(String teacherId) {
    	TeacherMsg teacherMs = null;
		String sql = "select * from TB_Teacher where TeacherId='" + teacherId + "'";
		System.out.println(sql);
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

	//�޸��û�����
	public int updateTeacher(String TeacherID, String TeacherName, String TPassword, String DeptID,  String Sex,
			              String Title, int studentCount, int Privilege, String tel,String Intro) {
	
		String sql = "update TB_Teacher set TeacherName='" + TeacherName + "',TPassword='" + TPassword + "',DeptID='" + DeptID 
		             + "',Sex='" + Sex + "',Title='" + Title + "',studentCount=" + studentCount 
		             + ",Privilege=" + Privilege+ ",tel='" + tel+ "',Intro='" + Intro 
		             +"' where TeacherID='" + TeacherID+ "'";
		
		System.out.println(sql);
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	
	//����ѧ��ɾ������
	public int deleteByTeacherId(String teacherId) {
		String sql = "delete from TB_Teacher where TeacherId='" + teacherId + "'";
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	
	//�ر����ݿ�����
	public void close() {
		dbCon.close();
	}
	
}

