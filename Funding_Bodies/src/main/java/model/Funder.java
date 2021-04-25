package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Funder {
	
		
		//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gajetbuget", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;

	}
		
		
		public String insertFunder(String name, String contact, String email, String address)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into funder (`fundID`,`fundName`,`fundContact`,`fundEmail`,`fundAdress`)"
		 + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, contact);
		 preparedStmt.setString(4, email);
		 preparedStmt.setString(5, address);
		 
		// execute the statement

		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }	
		
		
		public String readFunders()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Project Code</th><th>Funder Name</th>" +
		 "<th>Funder Contact</th>" +
		 "<th>Funder Email</th>" +
		 "<th>Funder Address</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from funder";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String fundID = Integer.toString(rs.getInt("fundID"));
		 String fundName = rs.getString("fundName");
		 String fundContact = rs.getString("fundContact");
		 String fundEmail = rs.getString("fundEmail");
		 String fundAdress = rs.getString("fundAdress");
		 // Add into the html table
		 output += "<tr><td>" + fundName + "</td>";
		 output += "<td>" + fundContact + "</td>";
		 output += "<td>" + fundEmail + "</td>";
		 output += "<td>" + fundAdress + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='funders.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='fundID' type='hidden' value='" + fundID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		public String updateFunder(String ID,String name, String contact, String email, String address)

		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE funder SET fundName=?,fundContact=?,fundEmail=?,fundAdress=? WHERE fundID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, contact);
		 preparedStmt.setString(3, email);
		 preparedStmt.setString(4, address);
		 preparedStmt.setInt(5, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }

		
		public String deleteItem(String fundID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from funder where fundID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(fundID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
} 

