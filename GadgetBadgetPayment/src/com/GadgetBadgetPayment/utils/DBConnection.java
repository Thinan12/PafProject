package com.GadgetBadgetPayment.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {

	public Connection connection;
	private static DBConnection single_instance = null;

	/**
	 * 
	 */
	private DBConnection() {
		try {
			if (connection == null || connection.isClosed()) {

				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment?serverTimezone=UTC", "root",
						"");

				System.out.println(connection);
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
