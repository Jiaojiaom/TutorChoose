package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javabean.SelectTeacherMsg;
import javabean.TeacherMsg;

public class SelectTeacherDAO {
    DBConnection dbCon;
	
    public SelectTeacherDAO(){
		dbCon=new DBConnection();//���ݿ����Ӷ���
		dbCon.createConnection();
	}
    
	public int insert(String StuID,String TeacherID,Float Grade,int choosedState){
		Calendar cal=Calendar.getInstance();//������������ʵ��
		String date=String.format("%4d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
		String time=String.format("%2d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
		
		// ���뵽���ݿ�
		String sql = "insert into TB_SelectTeacher(StuID, TeacherID, Grade,choosedState,SelectDate)"
				+ "values('"+StuID+"','"+TeacherID+","+Grade+","+choosedState+",'"+(date+" "+time)+"')";
		System.out.println(sql);
		// �������ݿ�
		int i=dbCon.update(sql);
		return i;
	}
	
	// �����ݿ�����ѡ�����м�¼
	public ArrayList<Map<String,String>> querySelectTeacherAll(){
		String sql="select * from TB_SelectTeacher";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	// ������ʦ�Ĺ��ţ�ѡ��������ѧ��
	public ArrayList<Map<String,String>> selectByTeacherId(String teacherID){
		String sql="select * from TB_SelectTeacher where TeacherID='" + teacherID + "'";
		ArrayList<Map<String,String>> list=dbCon.queryForList(sql);
		return list;
	}
	
	//����ѧ�Ų�ѯ��ʦ��Ϣ
    public SelectTeacherMsg queryByTeacherId(String teacherID) {
    	SelectTeacherMsg selectTeacherMs = null;
		String sql = "select * from TB_SelectTeacher where TeacherID='" + teacherID + "'";
		System.out.println(sql);
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
	//�޸��û�����
	public int updateTeacher(String StuID, String TeacherID, float Grade, int choosedState, String SelectDate) {
	
		String sql = "update TB_SelectTeacher set TeacherID='" + TeacherID + "',Grade=" + Grade 
				     + ",choosedState=" + choosedState + "," + "',SelectDate='" + SelectDate 
				     +"' where TeacherID='" + TeacherID+ "'";
		
		System.out.println(sql);
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	
	public int findById(String stuId) {
		int i = 0;
		String sql = "select count(*) from TB_SelectTeacher where stuId='" + stuId + "'";
		ResultSet rs = dbCon.queryForRS(sql);//�������ݿ�
		if(rs!=null){
			i=1;
		}
		return i;
	}
	
	//����ѧ��ɾ������
	public int deleteById(String stuId) {
		String sql = "delete from TB_SelectTeacher where stuId='" + stuId + "'";
		int i = dbCon.update(sql);//�������ݿ�
		return i;
	}
	
	//�ر����ݿ�����
	public void close() {
		dbCon.close();
	}
}

