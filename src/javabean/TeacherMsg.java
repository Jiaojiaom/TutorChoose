package javabean;

public class TeacherMsg implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TeacherMsg() {};
	private String TeacherID; //工号
	private String TeacherName; //姓名
	private String TPassword; //密码
	private String DeptID; //系
	private String Sex; //性别
	private String Title; //职称
	private int studentCount; //学生人数
	private int Privilege; 
	private String tel; //电话
	private String Intro; //爱好
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