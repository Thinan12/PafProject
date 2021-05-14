/**
 * 
 */
package com;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import com.DBConnection;

/**
 * @author IT18184440
 *
 */
public class Payment {

	DBConnection db = DBConnection.getDBconnection();
	Connection con;

	public String readAllPymentRecords() {
		String output = "";
		
			// Prepare the html table to be displayed

			output = "<table class='table table-hover'><tr><th>User ID</th><th>Order ID</th><th>Research ID</th><th>Funding ID</th><th>Payment METHOD</th><th>Payment time</th><th>Purpose</th><th>Amount</th><th>Statues</th><th>Update</th><th>Remove</th></tr>";
try {
			String query = "SELECT * FROM `paymentmain`";
			Statement stmt = db.con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentid = Integer.toString(rs.getInt("paymentid"));
				String userid = Integer.toString(rs.getInt("userid"));
				String orderid = Integer.toString(rs.getInt("orderid"));
				String researchid = Integer.toString(rs.getInt("researchid"));
				String fundingid = Integer.toString(rs.getInt("fundingid"));
				String paymentmethod = rs.getString("paymentmethod");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				String paymenttime = dateFormat.format(rs.getDate("paymenttime"));
				String purpose = rs.getString("purpose");
				String Amount = Float.toString(rs.getFloat("amount"));
				String statues = rs.getString("statues");
				
				// Add into the html table
				 output += "<tr><td><input id='hidpaymentIdUpdate' name='hidpaymentIdUpdate' type='hidden' value='"
				+ paymentid + "'>" + userid + "</td>";
					output += "<td>" + orderid + "</td>";
					output += "<td>" + researchid + "</td>";
					output += "<td>" + fundingid + "</td>";
					output += "<td>" + paymentmethod + "</td>";
					output += "<td>" + paymenttime + "</td>";
					output += "<td>" + purpose + "</td>";
					output += "<td>" + Amount + "</td>";
					output += "<td>" + statues + "</td>";
				 // buttons
					
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-paymentid='"
				 + paymentid + "'>" + "</td></tr>"; 
				
				
			
			}
			// Complete the html table
			
			output += "</table>";
			//con.close();
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String StoreOnlinePayment(String userid, String orderid, String researchid, String fundingid,
			String paymentmethod, String purpose, String Amount, String statues, String cardnumber, String cvv,
			String cardtype, String expiredate) {
		String output = "";
		System.out.println("id:" + userid);
		try {

			System.out.println("id:" + userid);
			// create a prepared statement
			String query1 = " INSERT INTO `paymentmain`(`userid`, `orderid`, `researchid`, `fundingid`, `paymentmethod`, `purpose`, `amount`,`statues` ) VALUES (?,?,?,?,?,?,?,?)";
			System.out.println("81:");
			String query2 = " INSERT INTO `carddetails`(`paymentid`, `cardnumber`, `cvv`, `cardtype`, `expiredate`) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStmt1;
			PreparedStatement preparedStmt2;
			System.out.println("85:");
			preparedStmt1 = db.con.prepareStatement(query1);
			// binding values
			System.out.println("88:");

			preparedStmt1.setInt(1, Integer.parseInt(userid));
			preparedStmt1.setInt(2, Integer.parseInt(orderid));
			preparedStmt1.setInt(3, Integer.parseInt(researchid));
			preparedStmt1.setInt(4, Integer.parseInt(fundingid));
			preparedStmt1.setString(5, paymentmethod);
			preparedStmt1.setString(6, purpose);
			System.out.println("amoun@t" + Amount);
			preparedStmt1.setFloat(7, Float.parseFloat(Amount));
			preparedStmt1.setString(8, statues);
			preparedStmt1.execute();

			if (paymentmethod.equals("Card") == true) {

				String id = "SELECT max(paymentid) as payment_id FROM `paymentmain`";
				Statement stmt1 = db.con.createStatement();
				ResultSet rs1 = stmt1.executeQuery(id);

				preparedStmt2 = db.con.prepareStatement(query2);

				while (rs1.next()) {
					preparedStmt2.setInt(1, rs1.getInt("payment_id"));
					preparedStmt2.setString(4, cardtype);
					preparedStmt2.setString(2, cardnumber);
					preparedStmt2.setString(3, cvv);
					preparedStmt2.setString(5, expiredate);
					preparedStmt2.execute();

				}
				System.out.println(preparedStmt2.toString());
				preparedStmt2.execute();

			}
			// execute the statement
			System.out.println(preparedStmt1.toString());
			// preparedStmt1.execute();

			// con.close();
			String newPayment = readAllPymentRecords();
			output = "{\"status\":\"success\", \"data\":\"data successfully saved.\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}";
			e.printStackTrace();
		}

		return output;

	}

	public String updatePaymentdetails(String paymentmethod, String purpose, String statues, String amount,
			String paymentid) {
		String output = "";
		PreparedStatement preparedStmt;

		try {

			// create a prepared statement
			String query = "UPDATE `paymentmain` SET `paymentmethod`=?,`purpose`=?,`statues`=?,`amount`=? WHERE `paymentid`=?";
			preparedStmt = db.con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, paymentmethod);
			preparedStmt.setString(2, purpose);
			preparedStmt.setString(3, statues);
			preparedStmt.setFloat(4, Float.parseFloat(amount));
			preparedStmt.setInt(5, Integer.parseInt(paymentid));

			// execute the statement
			preparedStmt.execute();
			// con.close();
			String newPayment = readAllPymentRecords();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletecardDetails(String paymentid) {
		String output = "";
		try {
			
			// create a prepared statement
			String query1 = "UPDATE `paymentmain` SET `statues`='cancel' WHERE `paymentid`=?";
			String query2 = "DELETE FROM `carddetails` WHERE `paymentid`=?";
			PreparedStatement preparedStmt1;
			PreparedStatement preparedStmt2;

			preparedStmt1 = db.con.prepareStatement(query1);
			preparedStmt2 = db.con.prepareStatement(query2);
			// binding values
			preparedStmt1.setInt(1, Integer.parseInt(paymentid));
			preparedStmt2.setInt(1, Integer.parseInt(paymentid));
			// execute the statement
			preparedStmt1.execute();
			preparedStmt2.execute();
			// con.close();
			String newPayment = readAllPymentRecords();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the card details.\"}";
			System.out.println("paymentid: "+paymentid);
			e.printStackTrace();
		}
		return output;
	}
}
