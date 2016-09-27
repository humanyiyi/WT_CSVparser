package com.udbac.parser.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BeyondbConnection {
public static Connection getConnection() {
	String driver = "org.postgresql.Driver";

	Connection con = null;
	/**
	 * localhost指本机，也可以用本地ip地址代替，可以修改为指定的ip，
	 * 5432为postgresql数据库的默认端口号
	 * postgre为数据库名
	 */
	String url ="jdbc:postgresql://localhost:5432/postgres";
//	String url ="jdbc:postgresql://192.168.4.5:5432/mydb";
	/**
	 * user为数据库用户名
	 * password为用户密码
	 */
	String user = "postgres";
	String password = "postgres";
	
	try {
		/**
		 * 加载驱动程序，此处运行隐式注册驱动程序的方法
		 */
		Class.forName(driver);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {	
		con = DriverManager.getConnection(url, user, password);
	} catch (SQLException ex) {
		Logger.getLogger(BeyondbConnection.class.getName()).log(Level.SEVERE, null, ex);
	}
	return con;

	}

//public static void close(Connection con, Statement sm, ResultSet rs){
//	try{
//		if(con != null){
//			con.close();
//		}
//		if(sm != null){
//			sm.close();
//		}
//		if(rs != null){
//			rs.close();
//		}
//	}catch(SQLException e){
//		e.printStackTrace();
//	}
//	
//}

}