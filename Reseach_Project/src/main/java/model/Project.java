package model;

import java.sql.*;

public class Project {
	
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
	
	
	
	public String insertProject(String code, String name, String price, String desc, String type)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into projects (`proID`,`proCode`,`proName`,`proPrice`,`proDesc`,`proType`)"
	 + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);
	 preparedStmt.setString(3, name);
	 preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setString(5, desc);
	 preparedStmt.setString(6, type);
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
	
	
	
	
	public String readProjects()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Project Code</th><th>Project Name</th>" +
	 "<th>Project Price</th>" +
	 "<th>Project Description</th>" +
	 "<th>Project Type</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from projects";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String proID = Integer.toString(rs.getInt("proID"));
	 String proCode = rs.getString("proCode");
	 String proName = rs.getString("proName");
	 String proPrice = Double.toString(rs.getDouble("proPrice"));
	 String proDesc = rs.getString("proDesc");
	 String proType = rs.getString("proType");
	 // Add into the html table
	 output += "<tr><td>" + proCode + "</td>";
	 output += "<td>" + proName + "</td>";
	 output += "<td>" + proPrice + "</td>";
	 output += "<td>" + proDesc + "</td>";
	 output += "<td>" + proType + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='projects.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='proID' type='hidden' value='" + proID
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
	
	
	
	public String updateProject(String ID,String code, String name, String price, String desc, String type)

	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE projects SET proCode=?,proName=?,proPrice=?,proDesc=?,proType=? WHERE proID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, code);
	 preparedStmt.setString(2, name);
	 preparedStmt.setDouble(3, Double.parseDouble(price));
	 preparedStmt.setString(4, desc);
	 preparedStmt.setString(5, type);
	 preparedStmt.setInt(6, Integer.parseInt(ID));
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
	
	
	
	public String deleteItem(String proID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from projects where proID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(proID));
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

