package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Funder;



@Path("/Funder")

public class FunderService {
	
	Funder fundObj = new Funder();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("fundName") String fundName,
	 @FormParam("fundContact") String fundContact,
	 @FormParam("fundEmail") String fundEmail,
	 @FormParam("fundAdress") String fundAdress)

	
	{
	 String output = fundObj.insertFunder(fundName,fundAdress,fundEmail,fundAdress);
	return output;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProjects()
	 {
	 return fundObj.readFunders();
     }
	

	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String fundData)
	{
	//Convert the input string to a JSON object
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	//Read the values from the JSON object
	 String fundID = fundObject.get("fundID").getAsString();
	 String fundName = fundObject.get("fundName").getAsString();
	 String fundContact = fundObject.get("fundContact").getAsString();
	 String fundEmail = fundObject.get("fundEmail").getAsString();
	 String fundAdress = fundObject.get("fundAdress").getAsString();
	 String output = fundObj.updateFunder(fundID, fundName, fundContact, fundEmail, fundAdress);
	return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String fundData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String fundID = doc.select("fundID").text();
	 String output = fundObj.deleteItem(fundID);
	return output;
	}

}
