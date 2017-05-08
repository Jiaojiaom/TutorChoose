package javabean;

public class DeptMsg implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DeptMsg() {};
	private String DeptID; //Ñ§ºÅ
	private String DeptName; //ÐÕÃû
	public String getDeptID() {
		return DeptID;
	}
	public void setDeptID(String deptID) {
		DeptID = deptID;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
}

