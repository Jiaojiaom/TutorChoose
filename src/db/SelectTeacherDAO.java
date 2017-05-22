package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import com.bean.SelectTeacherMsg;

public class SelectTeacherDAO extends MsDAO implements java.io.Serializable{
	
	/**
	 * querySelectStu������ѯѡ��õ�ʦ����δ��̭��ѧ����Ϣ
	 * @param teaId����ʦ����
	 * @return ѡ��õ�ʦ����δ��̭��ѧ����Ϣ�б�
	 */
	public ArrayList<Map<String, String>> querySelectStu_1(String teaId){
		String sql = "select TB_SelectTeacher.stuID as stuID,stuName,TB_Dept.DeptName as deptName,TB_Class.ClassName as className,Sex,TB_Student.Grade as grade,tel,Intro, TB_SelectTeacher.choosedState as choosedState "
				   + "from TB_SelectTeacher join TB_Student on TB_SelectTeacher.StuID = TB_Student.StuID join TB_Class on TB_Class.ClassID = TB_Student.ClassID join TB_Dept on TB_Dept.DeptID = TB_Student.DeptID "
				   + "where TB_SelectTeacher.TeacherID = '" + teaId + "' and TB_SelectTeacher.choosedState != 1;";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList<Map<String, String>> results = dbc.queryForList(sql);
		dbc.close();
		
		return results;
	}
	
	/**
	 * updateChoosedState�����ǵ���ʦ����ѡѧ������ѡ����޸�selectTeacher���student����ChoosedState��ֵ��
	 * @param state����ʦ��ѧ������ʲô����ѡ��1:��̭ѧ��,2��ѡ��ѧ����
	 * @param stuId����ѡѧ����ѧ������
	 * @param teaId����ʦ�Ĺ���
	 * @return �Ƿ�ɹ��޸�
	 */
	public int updateChoosedState(int state, String[] stuId, String teaId){
		//�޸�selectTeacher���ChoosedState��ֵ��sql
		String sql_st = "update TB_SelectTeacher set choosedState = " + state + " where TeacherID = '" + teaId + "' and choosedState = '0' and ( StuID = '" + stuId[0] +"'";
		for(int i=1;i<stuId.length;i++){
			sql_st = sql_st + " or StuID = '" + stuId[i] + "'";
		}
		sql_st += ");";
		//�޸�student���ChoosedState��ֵ��sql
		String sql_s = "update TB_Student set choosedState = " + (state+1) + " where StuID = '" + stuId[0] +"'";
		for(int i=1;i<stuId.length;i++){
			sql_s = sql_s + " or StuID = '" + stuId[i] + "'";
		}
		sql_st += ";";
//		System.out.println(sql_s);
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		int i = 1;
		try{
			dbc.setAutoCommit(false);
			int j = dbc.update(sql_st);
			int k = dbc.update(sql_s);
			dbc.setCommit();	//ͳһ�ύ����
			//�ж��Ƿ�ɹ��޸�
			if(j==0||k==0){
				throw new SQLException();
			}
		}catch(SQLException e){
			i = 0;
			//�������һ��SQL����ʧ�ܣ��Ͳ���ִ��commit()���������ǲ�����Ӧ��sqlexception����ʱ�Ϳ��Բ����쳣������е���rollback()������������
		    dbc.setRollback();
		}
		dbc.close();
		
		return i;
	}
	
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
//			System.out.println(sql);
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
		
		public ArrayList<Map<String, String>> querySelectStu(String teaId){
			String sql = "select TB_SelectTeacher.stuID as stuID,stuName,TB_Student.Grade as grade, TB_SelectTeacher.choosedState as choosedState, TB_SelectTeacher.selectDate "
					   + "from TB_SelectTeacher join TB_Student on TB_SelectTeacher.StuID = TB_Student.StuID "
					   + "where TB_SelectTeacher.TeacherID = '" + teaId + "' and TB_SelectTeacher.choosedState != 1;";
//			System.out.println(sql);
			return queryDBForList(sql);
		}
		
		public ArrayList<Map<String, String>> querySelectTeacher(String stuId){
			String sql = "select TB_SelectTeacher.teacherID as teacherID,teacherName,TB_SelectTeacher.choosedState as choosedState, TB_SelectTeacher.selectDate "
					   + "from TB_SelectTeacher join TB_Teacher on TB_SelectTeacher.TeacherID = TB_Teacher.TeacherID "
					   + "where TB_SelectTeacher.StuID = '" + stuId + "'";
//			System.out.println(sql);
			return queryDBForList(sql);
		}
		public String getTeacherName(String TeacherID) throws SQLException{
			sql = "select TeacherName from TB_Teacher where TeacherID = '"+ TeacherID+"'";
			String TeacherName = "";
			ResultSet rs = dbCon.queryForRS(sql);
//			System.out.println(sql);
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
