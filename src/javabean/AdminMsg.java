package javabean;

public class AdminMsg implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminMsg() {};
	private String AdminID; //����Աid
	private String AdminName; //����Ա����
	private String APassword; //����Ա����
	private String tel; //��ϵ��ʽ
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

