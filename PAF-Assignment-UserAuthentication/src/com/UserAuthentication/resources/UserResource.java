package com.UserAuthentication.resources;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;


import com.UserAuthentication.model.User;
import com.UserAuthentication.services.UserService;
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserResource {
	
	User UserObj = new User();

		@RolesAllowed({"admin","registercustomer","registerresearch","fundingbodies"})
		@GET
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_HTML)
		public String readUser() {
			UserService UserObj = new UserService();

			String output = UserObj.readUser();
			return output;

		}
		

		@RolesAllowed({"admin","registercustomer","registerresearch","fundingbodies"})
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertUser(String userData) {
			// Convert the input string to a JSON object
			JsonObject UserJsonObj = new JsonParser().parse(userData).getAsJsonObject();
			// Read the values from the JSON object

			String id = UserJsonObj.get("id").getAsString();
			String first_name = UserJsonObj.get("first_name").getAsString();
			String last_name = UserJsonObj.get("last_name").getAsString();
			String email = UserJsonObj.get("email").getAsString();
			String password = UserJsonObj.get("password").getAsString();
			String user_role = UserJsonObj.get("user_role").getAsString();
			
			
			UserService userobject1 = new UserService();
			UserObj.setId(id);
			UserObj.setFirst_name(first_name);
			UserObj.setLast_name(last_name);
			UserObj.setEmail(email);
			UserObj.setPassword(password);
			UserObj.setUser_role(user_role);
			

			String output = userobject1.insertUser(UserObj);
			return output;
		}
		
		@RolesAllowed({"admin","registercustomer\",\"registerresearch\",\"fundingbodies"})
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateUser(String userData) throws SQLException {
			// Convert the input string to a JSON object
			JsonObject UserJsonObj = new JsonParser().parse(userData).getAsJsonObject();
			// Read the values from the JSON object

			String id = UserJsonObj.get("id").getAsString();
			String first_name = UserJsonObj.get("first_name").getAsString();
			String last_name = UserJsonObj.get("last_name").getAsString();
			String email = UserJsonObj.get("email").getAsString();
			String password = UserJsonObj.get("password").getAsString();
			String user_role = UserJsonObj.get("user_role").getAsString();
			
			

			UserService userobject2 = new UserService();
			
			UserObj.setId(id);
			UserObj.setFirst_name(first_name);
			UserObj.setLast_name(last_name);
			UserObj.setEmail(email);
			UserObj.setPassword(password);
			UserObj.setUser_role(user_role);
		
			String output = userobject2.updateUser(UserObj);
			return output;
		}
		
		@RolesAllowed({"admin","registercustomer\\\",\\\"registerresearch\\\",\\\"fundingbodies"})
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteUser(String userData) {
			JsonObject UserJsonObj = new JsonParser().parse(userData).getAsJsonObject();

			// Read the value from the element <doctorID>
			String id = UserJsonObj.get("id").getAsString();

			UserService userobject3 = new UserService();
			UserObj.setId(id);
			String output = userobject3.deleteUser(UserObj);
			return output;
		}
		
		@RolesAllowed({"admin","registercustomer\\\\\\\",\\\\\\\"registerresearch\\\\\\\",\\\\\\\"fundingbodies"})
		@GET
		@Path("/login")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_HTML)
		public String viewUserShedual(String userData) {
			
			JsonObject UserJsonObj = new JsonParser().parse(userData).getAsJsonObject();
			String id = UserJsonObj.get("id").getAsString();
			String password = UserJsonObj.get("password").getAsString();
			
			UserService userobject3 = new UserService();
			UserObj.setId(id);
			UserObj.setPassword(password);
			String output = userobject3.login(UserObj);
			return output;
		}
		
		
	}