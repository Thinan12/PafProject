package com.GadgetBadgetPayment.service.payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.GadgetBadgetPayment.model.*;
import com.GadgetBadgetPayment.resources.*;
import com.GadgetBadgetPayment.utils.*;



public class PaymentService {
	
	DBConnection db = DBConnection.getDBconnection() ;
	Connection connection;
	PaymentResources paymentresources=new PaymentResources();
	
	public String StoreOnlinePayment(Payment payment) {
		String query1 = " INSERT INTO `paymentmain`( `userid`, `orderid`, `researchid`, `fundingid`, `paymentmethod`, `purpose`, `statues`, `amount`) VALUES (?,?,?,?,?,?,?,?)";
		
		String query2 = " INSERT INTO `carddetails`(`paymentid`, `cardnumber`, `cvv`, `cardtype`, `expiredate`) VALUES (?,?,?,?,?)";
		PreparedStatement preparedStmt1;
		PreparedStatement preparedStmt2;
		
		String output;
		
		try {
			preparedStmt1 = db.connection.prepareStatement(query1);
			 preparedStmt2 =db.connection.prepareStatement(query2);
			    preparedStmt1.setInt(1, payment.getUserid());
				preparedStmt1.setInt(2,payment.getorderid());
				preparedStmt1.setInt(3,payment.getresearchid());
				preparedStmt1.setInt(4,payment.getfundingid());
				preparedStmt1.setString(5,payment.getPaymentmethod());
				preparedStmt1.setString(6,payment.getPurpose());
				preparedStmt1.setString(7,payment.getStatues());
				preparedStmt1.setFloat(8,payment.getAmount());
				preparedStmt1.execute();
			if(payment.getPaymentmethod().equals("Card")==true) {
							
				String id ="SELECT max(payment_id) as payment_id FROM `paymentmain`";
				Statement stmt1 = db.connection.createStatement();
				ResultSet rs1=stmt1.executeQuery(id);
				 preparedStmt2 =db.connection.prepareStatement(query2);
				 
				  
				 
				while(rs1.next()) { 	
			  		  preparedStmt2.setInt(1,rs1.getInt("payment_id"));
					  preparedStmt2.setString(4,payment.getCardtype());
					  preparedStmt2.setString(2,payment.getCardnumber());
					  preparedStmt2.setString(3,payment.getCvv());
					  preparedStmt2.setString(5,payment.getExpiredate()); 
					  preparedStmt2.execute();
					
				}
			  			
			
			
			}
			 
			  //db.connection.close(); }
			 
			
			 output = "Inserted successfully";
			
		} catch (SQLException e) {
		    output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
public String readAllPymentRecords() {
		
		String output="";
		// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>Payment ID</th><th>User ID</th><th>Order ID</th><th>Research ID</th><th>Funding ID</th><th>Payment METHOD</th><th>Payment time</th><th>Purpose</th><th>Statues</th><th>Amount</th></tr>";
					String query = "SELECT * FROM `paymentmain`";
					try {
					
						Statement stmt = db.connection.createStatement();
						ResultSet rs=stmt.executeQuery(query);
						// iterate through the rows in the result set
						while (rs.next()) {
							String paymentid = Integer.toString(rs.getInt("paymentid"));
							String userid = Integer.toString(rs.getInt("userid"));
							String orderid = Integer.toString(rs.getInt("orderid"));
							String researchid = Integer.toString(rs.getInt("researchid"));
							String fundingid = Integer.toString(rs.getInt("fundingid"));
							String paymentmethod = rs.getString("paymentmethod");
							DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
							String paymenttime =dateFormat.format(rs.getDate("paymenttime")); 	
							String purpose = rs.getString("purpose");
							String Amount = Float.toString(rs.getFloat("amount"));
							String statues = rs.getString("statues");
							
							// Add into the html table
							output += "<tr><td>" + paymentid + "</td>";
							output += "<td>" + userid + "</td>";
							output += "<td>" + orderid + "</td>";
							output += "<td>" + researchid + "</td>";
							output += "<td>" + fundingid + "</td>";
							output += "<td>" + paymentmethod + "</td>";
							output += "<td>" + paymenttime + "</td>";
							output += "<td>" + purpose + "</td>";
							output += "<td>" + statues + "</td>";
							output += "<td>" + Amount + "</td>";
					} 
				//		db.connection.close();
						// Complete the html table
						output += "</table>";	
					}catch (SQLException e) {
						output = "Error while reading the payments.";
					System.err.println(e.getMessage());
					}
		return output;
		
	}

public String updatePaymentdetails(Payment payment) { 
	String output = "";
	String query = "UPDATE `paymentmain` SET `paymentmethod`=?,`purpose`=?,`statues`=?,`amount`=? WHERE `paymentid`=?";
	PreparedStatement preparedStmt;
	
	try {
		
		 java.util.Date today = new java.util.Date();
		     
		
		 preparedStmt = db.connection.prepareStatement(query);
		// preparedStmt.setInt(1,payment.getUserid());
		 preparedStmt.setString(1,payment.getPaymentmethod());
		 preparedStmt.setString(2,payment.getPurpose());
		 preparedStmt.setFloat(4,payment.getAmount());
		 preparedStmt.setString(3,payment.getStatues());
		 preparedStmt.setInt(5,payment.getPaymentid());
		// execute the statement
			preparedStmt.execute();
			output = "Updated successfully";
	} catch (SQLException e) {
		output = "Error while updating the user.";
		System.err.println(e.getMessage());
	}
	return output;
}


public String deletecardDetails(String payment_id) {
	String output;
	
	// create a prepared statement
	            String query1 = "UPDATE `paymentmain` SET `statues`='cancel' WHERE `paymentid`=?";
				String query2 = "DELETE FROM `carddetails` WHERE `paymentid`=?";
				PreparedStatement preparedStmt1;
				PreparedStatement preparedStmt2;
				try {
					System.out.print("==============="+payment_id+"===============");
				preparedStmt1= db.connection.prepareStatement(query1);
				preparedStmt2= db.connection.prepareStatement(query2);
				// binding values
				preparedStmt1.setInt(1, Integer.parseInt(payment_id));
				preparedStmt2.setInt(1, Integer.parseInt(payment_id));
				// execute the statement
				preparedStmt1.execute();
				preparedStmt2.execute();
				
				output = "Deleted successfully";
				
			} catch (Exception e) {
				output = "Error while deleting the card information.";
				System.err.println(e.getMessage());
			}
				
			return output;
}
	public PaymentService() {
		// TODO Auto-generated constructor stub
	}
	
	

}
