package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javabean.SelectTeacherMsg;
import javabean.TeacherMsg;

public class SelectTeacherDAO extends MsDAO {
	
	// �õ�ѡ���ʦ����Ϣ
	public SelectTeacherMsg getSelectTeacherMsg(String sql) {
    	SelectTeacherMsg selectTeacherMs = null;
		//����ѯ����ŵ������
		ResultSet rs = dbCon.queryForRS(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					selectTeacherMs = new SelectTeacherMsg();//����javaBean
					selectTeacherMs.setStuID(rs.getString("StuID"));
					selectTeacherMs.setTeacherID(rs.getString("TeacherID"));
					selectTeacherMs.setGrade(Float.parseFloat(rs.getString("Grade")));
					selectTeacherMs.setChoosedState(Integer.parseInt(rs.getString("choosedState")));
					selectTeacherMs.setSelectDate(rs.getString("SelectDate"));	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return selectTeacherMs;//����javaBean
	}
	
	public int insert(String StuID,String TeacherID,Float Grade,int choosedState){
		Calendar cal=Calendar.getInstance();//������������ʵ��
		String date=String.format("%4d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
		String time=String.format("%2d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
		// ���뵽���ݿ�
		sql = "insert into TB_SelectTeacher(StuID, TeacherID, Grade,choosedState,SelectDate)"
				+ "values('"+StuID+"','"+TeacherID+","+Grade+","+choosedState+",'"+(date+" "+time)+"')";
		System.out.println(sql);
		// �������ݿ�
		return updateDB(sql);
	}
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> querySelectTeacherAll(){
		sql="select * from TB_SelectTeacher";
		return queryDBForList(sql);
	}
	
	//���ݽ�ʦ��ѯѡ����Ϣ
    public SelectTeacherMsg queryByTeacherId(String teacherID) {
		sql = "select * from TB_SelectTeacher where TeacherID='" + teacherID + "'";
		return getSelectTeacherMsg(sql);//����javaBean
	}
    
    // ����ѧ�Ų�ѯѡ����Ϣ
    public SelectTeacherMsg findByStuId(String stuId) {
		sql = "select * from TB_SelectTeacher where stuId='" + stuId + "'";
		return getSelectTeacherMsg(sql);
	}
    
	//�޸��û�����
	public int updateTeacher(String StuID, String TeacherID, float Grade, int choosedState, String SelectDate) {
		sql = "update TB_SelectTeacher set TeacherID='" + TeacherID + "',Grade=" + Grade 
				     + ",choosedState=" + choosedState + "," + "',SelectDate='" + SelectDate 
				     +"' where TeacherID='" + TeacherID+ "'";
		return updateDB(sql);
	}
	
	//����ѧ��ɾ������
	public int deleteByStuId(String stuId) {
		String sql = "delete from TB_SelectTeacher where stuId='" + stuId + "'";
		return updateDB(sql);
	}
	
	// ������ʦ�Ĺ��ţ�ѡ��������ѧ��
	public ArrayList<Map<String,String>> selectByTeacherId(String teacherID){
		sql="select * from TB_SelectTeacher where TeacherID='" + teacherID + "'";
		return queryDBForList(sql);
	}
}

