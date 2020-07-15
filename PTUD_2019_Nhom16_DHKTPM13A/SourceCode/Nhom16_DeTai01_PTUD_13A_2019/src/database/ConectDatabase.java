/*
 * Người Làm : Nguyễn Văn Hoàng MSSV:17089671
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectDatabase {
	public static Connection con = null;
	private static ConectDatabase instance = new ConectDatabase();
	
	public static ConectDatabase getInstance() {
		return instance;
	}
	
	public void connect()  {
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QuanLyThuoc";
		String user = "sa";
		String password = "sapassword";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, password);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void disconnect() {
		if(con != null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		return con;
	}
}
