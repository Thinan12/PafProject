package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {

	public Connection con;
	private static DBConnection single_instance = null;

	/**
	 * 
	 */
	private DBConnection() {
		try {
			if (con == null || con.isClosed()) {

				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");

				System.out.println(con);
				System.out.println("connect to db");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static DBConnection getDBconnection() {

		if (single_instance == null) {
			System.out.println("success");
			single_instance = new DBConnection();
		}
		return single_instance;
	}

}
