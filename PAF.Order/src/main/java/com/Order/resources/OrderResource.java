package com.Order.resources;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Order.model.Order;
import com.Order.service.order.OrderService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Order")
public class OrderResource {
	

	Order OrdObj = new Order();

//	@RolesAllowed({"patient","admin"})
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String readOrders() {
		OrderService OrdObj = new OrderService();

		String output = OrdObj.readOrders();
		return output;

	}
	

//	@RolesAllowed({"patient","admin"})
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrders(String orderData) {
		// Convert the input string to a JSON object
		JsonObject OrderObj = new JsonParser().parse(orderData).getAsJsonObject();
		// Read the values from the JSON object

		String orderId = OrderObj.get("orderId").getAsString();
		String userId = OrderObj.get("userId").getAsString();
		String fundingBodiesId = OrderObj.get("fundingBodiesId").getAsString();
		String researchProjectId = OrderObj.get("researchProjectId").getAsString();
		String researchProjectFundId = OrderObj.get("researchProjectFundId").getAsString();
		String orderTotalAmount = OrderObj.get("orderTotalAmount").getAsString();
		
		
		
		OrderService orderobject1 = new OrderService();
		OrdObj.setOrderId(orderId);
		OrdObj.setUserId(userId);
		OrdObj.setFundingBodiesId(fundingBodiesId);
		OrdObj.setResearchProjectId(researchProjectId);
		OrdObj.setResearchProjectFundId(researchProjectFundId);
		OrdObj.setOrderTotalAmount(orderTotalAmount);
		

		String output = orderobject1.insertOrders(OrdObj);
		return output;
	}
//	@RolesAllowed({"patient","admin"})
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrders(String orderData) throws SQLException {
		// Convert the input string to a JSON object
		JsonObject OrderObj = new JsonParser().parse(orderData).getAsJsonObject();
		// Read the values from the JSON object

		String orderId = OrderObj.get("orderId").getAsString();
		String userId = OrderObj.get("userId").getAsString();
		String fundingBodiesId = OrderObj.get("fundingBodiesId").getAsString();
		String researchProjectId = OrderObj.get("researchProjectId").getAsString();
		String researchProjectFundId = OrderObj.get("researchProjectFundId").getAsString();
		String orderTotalAmount = OrderObj.get("orderTotalAmount").getAsString();
		
		

		OrderService orderobject2 = new OrderService();
		
		OrdObj.setOrderId(orderId);
		OrdObj.setUserId(userId);
		OrdObj.setFundingBodiesId(fundingBodiesId);
		OrdObj.setResearchProjectId(researchProjectId);
		OrdObj.setResearchProjectFundId(researchProjectFundId);
		OrdObj.setOrderTotalAmount(orderTotalAmount);
		

		String output = orderobject2.updateOrders(OrdObj);
		return output;
	}
//	@RolesAllowed({"patient","admin"})
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteObjects(String orderData) {
		JsonObject ordObject = new JsonParser().parse(orderData).getAsJsonObject();

		// Read the value from the element <doctorID>
		String orderId = ordObject.get("orderId").getAsString();

		OrderService orderobject3 = new OrderService();
		OrdObj.setOrderId(orderId);
		String output = orderobject3.deleteOrders(OrdObj);
		return output;
	}
//	@RolesAllowed({"patient","admin"})
	@GET
	@Path("/shedual")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String viewOrderShedual() {
		OrderService OrdObj = new OrderService();

		
		
		
		String output = OrdObj.viewOrderShedual();
		return output;
	}
	
	


}
