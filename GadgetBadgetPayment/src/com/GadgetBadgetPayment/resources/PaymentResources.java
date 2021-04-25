package com.GadgetBadgetPayment.resources;

import javax.swing.text.Document;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.GadgetBadgetPayment.model.Payment;
import com.GadgetBadgetPayment.service.payment.PaymentService;
import com.mysql.cj.xdevapi.JsonParser;

import org.json.*;

//For XML
import org.jsoup.nodes.*;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;


@Path("/Payment")
public class PaymentResources {

	Payment payment = new Payment();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String StoreOnlinePayment(@FormParam("paymentmethod") String paymentmethod,
			@FormParam("userid") int userid, @FormParam("orderid") int orderid,
			@FormParam("researchid") int researchid, @FormParam("fundingid") int fundingid,
			@FormParam("purpose") String purpose, @FormParam("statues") String statues, @FormParam("amount") float amount,
			@FormParam("cardtype") String cardtype, @FormParam("card_number") String card_number,
			@FormParam("cvv") String cvv, @FormParam("expiryDate") String expiryDate

	) {
		PaymentService paymentobj = new PaymentService();
		String output;
		if (paymentmethod.equals("Card") || paymentmethod.equals("card") || paymentmethod.equals("Cash")
				|| paymentmethod.equals("cash")) {
			payment.setPaymentmethod(paymentmethod);
			payment.setUserid(userid);
			payment.setorderid(orderid);
			payment.setresearchid(researchid);
			payment.setfundingid(fundingid);
			payment.setPurpose(purpose);
			payment.setStatues(statues);
			payment.setAmount(amount);
			if (cardtype.equals("visa") || cardtype.equals("Visa") || cardtype.equals("master")
					|| cardtype.equals("Master") || cardtype.equals("credit card") || cardtype.equals("Credit card")
					|| cardtype.equals("Credit Card")) {
				payment.setCardtype(cardtype);
				payment.setCardnumber(card_number);
				payment.setCvv(cvv);
				payment.setExpiredate(expiryDate);
			} else {
				output = "card type has misspelled or inccorect";
				return output;
			}
			output = paymentobj.StoreOnlinePayment(payment);
		}

		else {
			output = "payment method is incorrect";
		}

		return output;

	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		PaymentService paymentobj = new PaymentService();

		String output = paymentobj.readAllPymentRecords();
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayments(String paymentData) {
		// convert data to json object
		System.out.println(paymentData);
		// new com.google.gson.JsonParser().parse(paymentData).getAsJsonObject();
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		JsonObject itemObject = parser.parse(paymentData).getAsJsonObject();
		PaymentService paymentobj = new PaymentService();
		// Read the values from the JSON object
		
		String paymentmethod = itemObject.get("paymentmethod").toString();
		String purpose = itemObject.get("purpose").toString();
		String amount = itemObject.get("amount").toString();
		String statues = itemObject.get("statues").toString();
		String paymentid = itemObject.get("paymentid").toString();
		
		payment.setPaymentid(Integer.parseInt(paymentid));
		payment.setPaymentmethod(paymentmethod);
		payment.setPurpose(purpose);
		payment.setAmount(Float.parseFloat(amount));
		payment.setStatues(statues);
		String output = paymentobj.updatePaymentdetails(payment);
		return output;

	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletecarddetails(String paymentData) {
		System.out.println(paymentData);
		org.jsoup.nodes.Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		// Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		PaymentService paymentobj = new PaymentService();
		// Read the value from the element <userID>
		String paymentid = doc.select("paymentid").text();
		String output = paymentobj.deletecardDetails(paymentid);
		return output;
	}
	public PaymentResources() {
		
	}

}
