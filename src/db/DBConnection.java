package db;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBConnection {
	private String driver="com.mysql.jdbc.Driver";
	private String url= "jdbc:mysql://127.0.0.1:3306/info?characterEncoding=utf8";
	private String username;
	private String password;
	
	private Connection con=null;
	private Statement stmt=null; 
	private ResultSet rs=null; 
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//���캯��
	public DBConnection(){
		Properties prop = new Properties();  
		try {  
			//��������ȡ�ļ�
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("db.properties");  
            prop.load(is);
            //�����ֶ�
            this.setDriver(prop.getProperty("driver"));
            this.setUrl(prop.getProperty("url"));
            this.setUsername(prop.getProperty("username"));
            this.setPassword(prop.getProperty("password"));
            
            if (is != null)  
                is.close();  //�ر��ļ���ȡ��
        } catch (Exception e) {  
            System.out.println(e + " file db.properties not found");  
        }
	}
	
	//��������
	public void createConnection(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con=DriverManager.getConnection(url,username,password);
			System.out.println("�ɹ���������");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet queryForRS(String sql){
		ResultSet rs=null;
		try{
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	//ִ�в�ѯ
	public  ArrayList<Map<String,String>> queryForList(String sql){
		ArrayList<Map<String,String>> results=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//ִ��sql���
			rs=stmt.executeQuery(sql);
			//���������
			if(rs!=null){
				results=new ArrayList<Map<String,String>>(); //��ʼ��
				while(rs.next()){
					Map<String,String> result=new HashMap<String,String>();
					ResultSetMetaData rsmd=rs.getMetaData();
					int columnCount=rsmd.getColumnCount(); //�õ�����
					for(int i=1;i<=columnCount;i++){
						String key=rsmd.getColumnName(i).toLowerCase();//�õ��ֶ���
						String value=rs.getString(i);//�õ���Ӧ��ֵ
						result.put(key, value);//����Ϊһ��Map����
					}
					results.add(result);//��ӵ������
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
		
		
	//ִ�и������SQL���
	public int update(String sql){
		int i=0;
		try {
			stmt=con.createStatement();
			//ִ�и���sql���
			i=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	//�ر�����
	public void close(){
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
			System.out.println("�ɹ��ر�����");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
}
