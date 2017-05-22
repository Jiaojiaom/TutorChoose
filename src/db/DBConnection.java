package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import  java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import  java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import  java.io.IOException; 

public class DBConnection implements java.io.Serializable{
	private String driver;
	private String url;
	private String user;
	private String pwd;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	/**
	 * DBConnection���캯�Ӳ����ļ��ж�ȡ���ݿ����Ӳ���
	 */
	public DBConnection(){
		//��properties�ļ��ж�ȡ���ݿ����Ӳ���
		Properties prop =  new  Properties();
		try  {
			InputStream in = getClass().getResourceAsStream("/db.properties");
            prop.load(in);    
            this.setDriver(prop.getProperty( "driver" ));    
            this.setUrl(prop.getProperty( "url" ));
            this.setUser(prop.getProperty( "username" ));
            this.setPwd(prop.getProperty( "password" ));
            if(in != null)  in.close();  
        }  catch  (IOException e) {    
            e.printStackTrace();    
        }  
		
	}
	
	/**
	 * createConnection�����������ݿ�
	 */
	public void createConnection(){
		try{
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url,user,pwd);
			//System.out.println("success");
		}catch (ClassNotFoundException e) {
			//System.out.println("fail");
			e.printStackTrace();
		} catch (SQLException e) {
			//System.out.println("fail");
			e.printStackTrace();
		}
	}
	
	/**
	 * queryForRS���������ݿ��в�ѯ��¼���õ���Ӧ�Ľ����
	 * @param sql:���ݿ��ѯ���
	 * @return ��ѯ���Ľ����
	 */
	public ResultSet queryForRS(String sql){
		ResultSet rs=null;
		try{
			stmt=(Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	//ִ�в�ѯ
	/**
	 * queryForList���������ݿ��в�ѯ��¼���õ���Ӧ�Ľ�������ѽ�����е����ݷ�װ��ArrayList������
	 * @param sql�����ݿ��ѯ���
	 * @return �ѽ�����е����ݷ�װ��ArrayList���ͣ�����ArrayList���ͱ���
	 */
	public  ArrayList<Map<String,String>> queryForList(String sql){
		ArrayList<Map<String,String>> results=null;
		try {
			stmt=(Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);
			if(rs!=null){
				results=new ArrayList<Map<String,String>>();
				while(rs.next()){
					Map<String,String> result=new HashMap<String,String>();
					ResultSetMetaData rsmd=rs.getMetaData();
					int columnCount=rsmd.getColumnCount();
					for(int i=1;i<=columnCount;i++){
						String fieldName=rsmd.getColumnName(i).toLowerCase();
						String fieldValue=rs.getString(i);
						result.put(fieldName, fieldValue);
					}
					results.add(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	
	/**
	 * update����ִ�и������SQL��䣺insert,update,delete
	 * @param sql:���ݿ��ѯ���
	 * @return ���ݿ���¼�¼������
	 */
	public int update(String sql){
		int i=0;
		try {
			stmt=(Statement) con.createStatement();
			i=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * setsetAutoCommit���������������ݿ��Ƿ��Զ��ύ����
	 * @param flag:���ݿ��Ƿ��Զ��ύ����false:��ֹ�Զ��ύ��true:�Զ��ύ��
	 * @throws SQLException 
	 */
	public void setAutoCommit(boolean flag) throws SQLException{
		con.setAutoCommit(flag);
	}
	
	/**
	 * setCommit��������ͳһ�ύ����
	 * @throws SQLException
	 */
	public void setCommit() throws SQLException{
		con.commit();
	}
	
	/**
	 * setRollback����������������
	 */
	public void setRollback(){
		try {
			con.rollback();
		} catch (SQLException e) {
			System.out.println("�ع�ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	/**
	 *closeConnection�����ر����ݿ����� 
	 */
	public void close(){
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
