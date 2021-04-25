package com;

import model.Project;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Project")

public class ProjectService {
	Project proObj = new Project();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProjects()
	 {
	 return proObj.readProjects();
     }

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("proCode") String proCode,
	 @FormParam("proName") String proName,
	 @FormParam("proPrice") String proPrice,
	 @FormParam("proDesc") String proDesc,
	 @FormParam("proType") String proType)
	{
	 String output = proObj.insertProject(proCode, proName, proPrice, proDesc,proType);
	return output;
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String proData)
	{
	//Convert the input string to a JSON object
	 JsonObject proObject = new JsonParser().parse(proData).getAsJsonObject();
	//Read the values from the JSON object
	 String proID = proObject.get("proID").getAsString();
	 String proCode = proObject.get("proCode").getAsString();
	 String proName = proObject.get("proName").getAsString();
	 String proPrice = proObject.get("proPrice").getAsString();
	 String proDesc = proObject.get("proDesc").getAsString();
	 String proType = proObject.get("proType").getAsString();
	 String output = proObj.updateProject(proID, proCode, proName, proPrice, proDesc, proType);
	return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String proData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(proData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String proID = doc.select("proID").text();
	 String output = proObj.deleteItem(proID);
	return output;
	}


	}
