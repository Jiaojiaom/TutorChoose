package com.bean;

public class SelectTeacherMsg implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SelectTeacherMsg() {};
	private String StuID; //ѧ��
	private String TeacherID; //��ʦ����
	private String SelectDate; //ѡ��ʱ��
	private float Grade; //����
	private int choosedState; //ѡ��״̬
	public String getStuID() {
		return StuID;
	}
	public void setStuID(String stuID) {
		StuID = stuID;
	}
	public String getTeacherID() {
		return TeacherID;
	}
	public void setTeacherID(String teacherID) {
		TeacherID = teacherID;
	}
	public String getSelectDate() {
		return SelectDate;
	}
	public void setSelectDate(String selectDate) {
		SelectDate = selectDate;
	}
	public float getGrade() {
		return Grade;
	}
	public void setGrade(float grade) {
		Grade = grade;
	}
	public int getChoosedState() {
		return choosedState;
	}
	public void setChoosedState(int choosedState) {
		this.choosedState = choosedState;
	}
}

