package javabean;

public class StuMsg implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StuMsg() {};
	private String StuID; //ѧ��
	private String StuName; //����
	private String DeptID; //ϵ
	private String ClassID; //�༶
	private String Sex; //ѧ��
	private String SPassword; //����
	private float Grade; //����
	private String tel; //����
	private String Intro; //
	private String TeacherID; //����
	private int choosedState; //�����̶�
	private String SelectDate; //����
	public String getStuID() {
		return StuID;
	}
	public void setStuID(String stuID) {
		StuID = stuID;
	}
	public String getStuName() {
		return StuName;
	}
	public void setStuName(String stuName) {
		StuName = stuName;
	}
	public String getDeptID() {
		return DeptID;
	}
	public void setDeptID(String deptID) {
		DeptID = deptID;
	}
	public String getClassID() {
		return ClassID;
	}
	public void setClassID(String classID) {
		ClassID = classID;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getSPassword() {
		return SPassword;
	}
	public void setSPassword(String sPassword) {
		SPassword = sPassword;
	}
	public float getGrade() {
		return Grade;
	}
	public void setGrade(float grade) {
		Grade = grade;
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
	public String getTeacherID() {
		return TeacherID;
	}
	public void setTeacherID(String teacherID) {
		TeacherID = teacherID;
	}
	public int getChoosedState() {
		return choosedState;
	}
	public void setChoosedState(int choosedState) {
		this.choosedState = choosedState;
	}
	public String getSelectDate() {
		return SelectDate;
	}
	public void setSelectDate(String selectDate) {
		SelectDate = selectDate;
	}
}

