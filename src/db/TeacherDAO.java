package db;

import java.util.ArrayList;
import java.util.Map;

public class TeacherDAO {
	//��ѯ��������
	/*public ArrayList<Map<String, String>> queryAll() {
		DBConnection dbCon = new DBConnection();
		dbCon.createConnection();
		
		String sql = "select *from tb_student where teacherid='T02001'";
		ArrayList<Map<String, String>> list=dbCon.queryForList(sql);
		dbCon.close();
		
		return list;
	}*/
	
	/**
	 * updatePwd�������µ�ʦ������
	 * @param id����ʦ����
	 * @param pwd����ʦ����
	 * @return �����Ƿ�ɹ�
	 */
	public int updatePwd(String id, String pwd){
		String sql = "update TB_Teacher set TPassword = '" + pwd + "' where TeacherID = '" + id + "';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		int i = dbc.update(sql);
		dbc.close();
		return i;
	}
	
	/**
	 * updateInfo�������µ�ʦ�ĸ�����Ϣ
	 * @param id����ʦ����
	 * @param intro����ʦ��д�ĸ�����Ϣ
	 * @param tel����ʦ�ĵ绰����
	 * @return
	 */
	public int updateInfo(String id, String intro, String tel){
		String sql = "update TB_Teacher set tel = '" + tel + "', Intro = '" + intro + "' where TeacherID = '" + id + "';";
//		System.out.println(sql);
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		int i = dbc.update(sql);
		dbc.close();
		return i;
	}
	
	/**
	 * PwdIsTrue�������õ�ʦ�����ԭ�����Ƿ���ȷ
	 * @param id:��ʦ����
	 * @param pwd ����ʦ�����ԭ����
	 * @return �õ�ʦ��ԭ�����Ƿ���ȷ
	 */
	public boolean PwdIsTrue(String id, String pwd){
		String sql = "select TPassword from TB_Teacher where TeacherID = '" + id + "' and TPassword = '" + pwd + "';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList results = dbc.queryForList(sql);
		dbc.close();
		
		if(results == null || results.isEmpty()){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * queryTeacher�������õ�ʦ����������Ƿ���ȷ�����ڵ�¼���޸�����
	 * @param id:��ʦ����
	 * @param pwd ����ʦ���������
	 * @return �ڸù��ź������µĵ�ʦ��Ϣ
	 */
	public ArrayList queryTeacher(String id, String pwd){
		String sql = "select TeacherName,Privilege from TB_Teacher where TeacherID = '" + id + "' and TPassword = '" + pwd + "';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList results = dbc.queryForList(sql);
		dbc.close();
		
		return results;
	}
	
	/**
	 * queryTeacherInfo������ѯ��ʦ����Ϣ
	 * @param id����ʦ����
	 * @return ��ʦ����Ϣ�б�
	 */
	public ArrayList queryTeacherInfo(String id){
		String sql = "select TeacherID,TeacherName,DeptName,DeptID,Sex,Title,tel,Intro,Privilege from TB_Teacher natural join TB_Dept where TeacherID = '" + id + "';";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList results = dbc.queryForList(sql);
		dbc.close();
		
		return results;
	}
	
	/**
	 * isReachLimit�����ж�ѡ��õ�ʦ��ѧ�������Ƿ�ﵽ��ʦ�Ŀɴ��������������
	 * @param studentList��ѡ��õ�ʦ��ѧ���б�
	 * @return �Ƿ�ﵽ��ʦ�Ŀɴ��������������
	 */
	public boolean isReachLimit(ArrayList studentList){
		String sql = "select studentCount from TB_Teacher limit 1";
		
		DBConnection dbc = new DBConnection();
		dbc.createConnection();
		ArrayList limitCount = dbc.queryForList(sql);
		dbc.close();
//		System.out.println("1");
		int limit = Integer.valueOf((String) ((Map) limitCount.get(0)).get("studentcount"));
		if(studentList.size()>limit){
			return true;
		}else{
			return false;
		}
	}
}
