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
	
	//构造函数
	public DBConnection(){
		Properties prop = new Properties();  
		try {  
			//读入流读取文件
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("db.properties");  
            prop.load(is);
            //设置字段
            this.setDriver(prop.getProperty("driver"));
            this.setUrl(prop.getProperty("url"));
            this.setUsername(prop.getProperty("username"));
            this.setPassword(prop.getProperty("password"));
            
            if (is != null)  
                is.close();  //关闭文件读取流
        } catch (Exception e) {  
            System.out.println(e + " file db.properties not found");  
        }
	}
	
	//创建连接
	public void createConnection(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con=DriverManager.getConnection(url,username,password);
			System.out.println("成功建立连接");
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
	
	//执行查询
	public  ArrayList<Map<String,String>> queryForList(String sql){
		ArrayList<Map<String,String>> results=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//执行sql语句
			rs=stmt.executeQuery(sql);
			//遍历结果集
			if(rs!=null){
				results=new ArrayList<Map<String,String>>(); //初始化
				while(rs.next()){
					Map<String,String> result=new HashMap<String,String>();
					ResultSetMetaData rsmd=rs.getMetaData();
					int columnCount=rsmd.getColumnCount(); //得到行数
					for(int i=1;i<=columnCount;i++){
						String key=rsmd.getColumnName(i).toLowerCase();//得到字段名
						String value=rs.getString(i);//得到对应的值
						result.put(key, value);//构件为一个Map类型
					}
					results.add(result);//添加到结果集
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
		
		
	//执行更新类的SQL语句
	public int update(String sql){
		int i=0;
		try {
			stmt=con.createStatement();
			//执行更新sql语句
			i=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	//关闭连接
	public void close(){
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
			System.out.println("成功关闭连接");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
}
