package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javabean.StudentMsg;

public class StudentMsDAO extends MsDAO{
	//����ѧ�Ų�ѯѧ����Ϣ
    public StudentMsg getStudentMsg(String sql) {
    	StudentMsg stuMs = null;
		//����ѯ����ŵ������
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					stuMs = new StudentMsg();//����javaBean
					stuMs.setStuID(rs.getString("stuID"));
					stuMs.setStuName(rs.getString("stuName"));
					stuMs.setDeptID(rs.getString("deptID"));
					stuMs.setClassID(rs.getString("classID"));
					stuMs.setSex(rs.getString("sex"));
					stuMs.setSPassword(rs.getString("spassword"));
					stuMs.setGrade(rs.getInt("grade"));
					stuMs.setTel(rs.getString("tel"));
					stuMs.setIntro(rs.getString("Intro"));
					stuMs.setTeacherID(rs.getString("TeacherID"));
					stuMs.setChoosedState(Integer.parseInt(rs.getString("choosedState")));
					stuMs.setSelectDate(rs.getString("selectDate"));
					System.out.println(stuMs.getStuName());
					System.out.println(stuMs.getSPassword());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stuMs;//����javaBean
	}
    
	public int addStudent(String StuID,String StuName,String DeptID,String ClassID,
			              String Sex,float Grade,String tel,String Intro){
		if(findOneStudent(StuID)!=null){
			sql = "update TB_Student set StuName='" + StuName + "',DeptId='" + DeptID 
					 + "',ClassId='" + ClassID + "',Sex='" + Sex + "',Grade=" + Grade 
					 + ",Intro='" + Intro + "',tel='" + tel
		             + "' where StuID='" + StuID+ "'";
		} else {
			// ���뵽���ݿ�
			sql = "insert into TB_Student(StuID, StuName, DeptID, ClassID,Sex,Grade,tel,Intro)"
					+ "values('"+StuID+"','"+StuName+"','"+DeptID+"','"+ClassID+"','"+Sex+"',"+Grade+",'"+tel+"','"+Intro+"')";
		}
		System.out.println(sql);
		return updateDB(sql);
	}
		
	//����ѧ�Ų�ѯѧ����Ϣ
    public StudentMsg findOneStudent(String stuId) {
		sql = "select * from TB_Student where StuId='" + stuId + "'";
		return getStudentMsg(sql);//����javaBean
	}
    
	//�޸��û�����
	public int updateByStudentID(String stuId, String stuName, String deptId, String classId,  String sex, String password, 
			              float grade, String tel , String intro, String teacherID, String choosedState, String selectDate) {
		sql = "update TB_Student set StuName='" + stuName + "',DeptId='" + deptId + "',ClassId='" + classId 
		             + "',Sex='" + sex + "',SPassword='" + password + "',Grade=" + grade + ",Intro='" + intro + "',Tel='" + tel
		             + "',TeacherID='" + teacherID+ "',ChoosedState='" + choosedState+ "',SelectDate='" + selectDate 
		             +"' where StuID='" + stuId+ "'";
		return updateDB(sql);
	}
	// ���չ���Ա��Ų�ѯ
    public StudentMsg findByStudentId(String stuId) {
		sql = "select * from TB_Student where StuID='" + stuId + "'";
		System.out.println(sql);
		return getStudentMsg(sql);//����javaBean
	}
    
	//����ѧ��ɾ������
	public int deleteByStuId(String stuId) {
		sql = "delete from TB_Student where StuId='" + stuId + "'";
		return updateDB(sql);
	}
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> queryStudentList(){
		sql="select * from TB_Student";
		return queryDBForList(sql);
	}
	
	//���ݽ�ʦ���Ų�ѯѧ����Ϣ
    public ArrayList<Map<String,String>> queryStudentByTeacherId(String teacherId) {
		sql = "select * from TB_Student where TeacherId='" + teacherId + "'";
		return queryDBForList(sql);
	}
	
}

