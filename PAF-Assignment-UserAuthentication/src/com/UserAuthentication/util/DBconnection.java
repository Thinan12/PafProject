package com.UserAuthentication.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	public static  Connection connecter() {
		Connection con = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(
					"jdbc:mysql://localhost/gadgetbudget?useTimezone=true&serverTimezone=UTC",
						"root", "");
				// For testing
				System.out.print("Successfully connected");
			} catch (Exception e) {
				System.out.print("connection fail");
				e.printStackTrace();
				System.out.print(e);
			}

			return con;
		}

}
