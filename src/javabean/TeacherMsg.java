package javabean;

public class TeacherMsg implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TeacherMsg() {};
	private String TeacherID; //����
	private String TeacherName; //����
	private String TPassword; //����
	private String DeptID; //ϵ
	private String Sex; //�Ա�
	private String Title; //ְ��
	private int studentCount; //ѧ������
	private int Privilege; 
	private String tel; //�绰
	private String Intro; //����
	public String getTeacherID() {
		return TeacherID;
	}
	public void setTeacherID(String teacherID) {
		TeacherID = teacherID;
	}
	public String getTeacherName() {
		return TeacherName;
	}
	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}
	public String getTPassword() {
		return TPassword;
	}
	public void setTPassword(String tPassword) {
		TPassword = tPassword;
	}
	public String getDeptID() {
		return DeptID;
	}
	public void setDeptID(String deptID) {
		DeptID = deptID;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	public int getPrivilege() {
		return Privilege;
	}
	public void setPrivilege(int privilege) {
		Privilege = privilege;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIntro() {
		return Intro;
	}
	public void setIntro(String intro) {
		Intro = intro;
	}
}