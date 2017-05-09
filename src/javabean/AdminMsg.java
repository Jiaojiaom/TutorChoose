package javabean;

public class AdminMsg implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminMsg() {};
	private String AdminID; //管理员id
	private String AdminName; //管理员姓名
	private String APassword; //管理员密码
	private String tel; //联系方式
	public String getAdminID() {
		return AdminID;
	}
	public void setAdminID(String adminID) {
		AdminID = adminID;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public String getAPassword() {
		return APassword;
	}
	public void setAPassword(String aPassword) {
		APassword = aPassword;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}

