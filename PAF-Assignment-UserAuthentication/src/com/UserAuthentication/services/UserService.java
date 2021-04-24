package com.UserAuthentication.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.UserAuthentication.util.DBconnection;
import com.UserAuthentication.model.User;

public class UserService {

	Connection con = null;

	public UserService() {

		con = DBconnection.connecter();
	}

	public String insertUser(User user) {


		String output;
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			
			
		String query = " insert into user(`id`,`first_name`,`last_name`,`email`,`user_role`,`password`)"
					+ " values (?, ?, ?, ?, ?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, user.getId());
			preparedStmt.setString(2,user.getFirst_name());
			preparedStmt.setString(3, user.getLast_name());
			preparedStmt.setString(4, user.getEmail());
			preparedStmt.setString(5, user.getUser_role());
			preparedStmt.setString(6, user.getPassword());
			
			
			preparedStmt.execute();

			output = "Inserted successfully";

		} catch (SQLException e) {
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readUser() {
		String output = " ";
		// Prepare the html table to be displayed
		output = "<table border=\"1\"><tr><th>id</th>" + "<th>first_name</th><th>last_name</th>"
				+ "<th>email</th>" + "<th>user_role</th>" + "<th>password</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			String query = "select * from user";
			
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			

			// iterate through the rows in the result set
			while (results.next()) {
				String id = results.getString("id");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				String email = results.getString("email");
				String user_role = results.getString("user_role");
				String password = results.getString("password");
				
				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + first_name + "</td>";
				output += "<td>" + last_name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + user_role +"</td>";
				output += "<td>" + password +"</td>";
				
				   
			}

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateUser(User user) {
		
		
		String output = "";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE user SET first_name=?,last_name=?,email=?,user_role=?,password=?WHERE id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, user.getFirst_name());
			preparedStmt.setString(2, user.getLast_name());
			preparedStmt.setString(3, user.getEmail());
			preparedStmt.setString(4, user.getUser_role());
			preparedStmt.setString(5, user.getPassword());
			preparedStmt.setString(6, user.getId());
			
			// execute the statement
			preparedStmt.executeUpdate();
			
			
			
			if(preparedStmt.executeUpdate() == 1) {
				output = "Updated successfully";
			}else{
				output = "Error while updating the User.";			}
			
				con.close();
			
		} catch (SQLException e) {
			output = "Error while updating the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(User user) {
		
		
		String output="";
		

		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			String query = "delete from user where id=?";

			
			// create a prepared statement

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, user.getId());
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			
			
		} catch (Exception e) {
			output = "Error while deleting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String login(User user) {
		String output = " ";
		
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			//String query = "SELECT * FROM user WHERE id=? and password=?";
			
			String query = "SELECT * FROM user WHERE id="+user.getId()+ " and password='"+user.getPassword()+"'";
			
			System.out.println(query);
			
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			
			if (!results.isBeforeFirst() ) {    
			    System.out.println("Invalid User Credentials"); 
			    
			    output = "Invalid User Credentials !!!!";
			}
			// iterate through the rows in the result set
			while (results.next()) {
				
				output = "Valid User, Welcome "+results.getString("first_name")+" "+results.getString("last_name")+", User Role: "+results.getString("user_role");				   
			}

			
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}


