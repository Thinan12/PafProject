package com.Order.service.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Order.util.DBconnection;
import com.Order.model.Order;

public class OrderService {
	

	Connection con = null;

	public OrderService() {

		con = DBconnection.connecter();
	}
	

	public String insertOrders(Order order) {


		String output;
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			
			
		String query = " insert into orders(`orderId`,`userId`,`fundingBodiesId`,`researchProjectId`,`researchProjectFundId`,`orderTotalAmount`)"
					+ " values (?, ?, ?, ?, ?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, order.getOrderId());
			preparedStmt.setString(2, order.getUserId());
			preparedStmt.setString(3, order.getFundingBodiesId());
			preparedStmt.setString(4, order.getResearchProjectId());
			preparedStmt.setString(5, order.getResearchProjectFundId());
			preparedStmt.setString(6, order.getOrderTotalAmount());
			
			
			preparedStmt.execute();

			output = "Inserted successfully";

		} catch (SQLException e) {
			output = "Error while inserting the order.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readOrders() {
		String output = " ";
		// Prepare the html table to be displayed
		output = "<table border=\"1\"><tr><th>Order ID</th>" + "<th>User ID</th><th>FD ID</th>"
				+ "<th>RP ID</th>" + "<th>RPF ID</th>" + "<th> OT Amount</th> </tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			String query = "select * from orders";
			
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			

			// iterate through the rows in the result set
			while (results.next()) {
				String orderId = results.getString("orderId");
				String userId = results.getString("userId");
				String fundingBodiesId = results.getString("fundingBodiesId");
				String researchProjectId = results.getString("researchProjectId");
				String researchProjectFundId = results.getString("researchProjectFundId");
				String orderTotalAmount = results.getString("orderTotalAmount");
				
				
				
				// Add into the html table
				output += "<tr><td>" + orderId + "</td>";
				output += "<td>" + userId + "</td>";
				output += "<td>" + fundingBodiesId + "</td>";
				output += "<td>" + researchProjectId + "</td>";
				output += "<td>" + researchProjectFundId + "</td>";
				output += "<td>" + orderTotalAmount +"</td>";
				   
			}

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Order.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateOrders(Order order) {
		
		
		String output = "";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE orders SET userId=?,fundingBodiesId=?,researchProjectId=?,researchProjectFundId=?,orderTotalAmount=? WHERE orderId=? ";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, order.getUserId());
			preparedStmt.setString(2, order.getFundingBodiesId());
			preparedStmt.setString(3, order.getResearchProjectId());
			preparedStmt.setString(4, order.getResearchProjectFundId());
			preparedStmt.setString(5, order.getOrderTotalAmount());
			preparedStmt.setString(6, order.getOrderId());
		
			preparedStmt.executeUpdate();
			
			
			
			if(preparedStmt.executeUpdate() == 1) {
				output = "Updated successfully";
			}else{
				output = "Error while updating the Orders.";			}
			
				con.close();
			
		} catch (SQLException e) {
			output = "Error while updating the Orders.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteOrders(Order order) {
		
		
		String output="";
		

		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			String query = "delete from orders where orderId=?";

			
			// create a prepared statement

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, order.getOrderId());
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			
			
		} catch (Exception e) {
			output = "Error while deleting the Orders.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String viewOrderShedual() {
		String output = " ";
		// Prepare the html table to be displayed
		output = "<table border=\"1\"><tr><th>Order Id</th>"+"<th>User ID</th>"+ "<th>FD ID</th> "+ "<th>RP ID</th>" + "<th>RPF ID</th>" +"<th>Amount</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			String query = "select * from orders";
			
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			

			// iterate through the rows in the result set
			while (results.next()) {
				String orderId = results.getString("orderId");
				String userId = results.getString("userId");
				String fundingBodiesId = results.getString("fundingBodiesId");
				String researchProjectId = results.getString("researchProjectId");
				String researchProjectFundId = results.getString("researchProjectFundId");
				String orderTotalAmount = results.getString("orderTotalAmount");
				
				
				// Add into the html table
				output += "<tr><td>" + orderId + "</td>";
				output += "<tr><td>" + userId + "</td>";
				output += "<td>" + fundingBodiesId + "</td>";
				output += "<td>" + researchProjectId +"</td>";
				output += "<td>" + researchProjectFundId +"</td>";
				output += "<td>" + orderTotalAmount +"</td>";
				
				   
			}

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Order.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	

}
