package com.revature.user.service.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.restassured.RestAssured;

/**
 * 
 * @author Joshua Stark and John Brand (1802-Matt)
 * 
 * Tests for:
 *  Get( /{userId} ) 
 *	Put( /{userId} ) 
 *	Delete( /{userId} ) 
 *	Put( /{userId}/{batchId} )
 *
 */

public class UsersByIdTests {

	
	/**
	 * Simple RestAssured test. Grabs user inside H2 database UserId of 50 and checks that firstName is Ryan.
	 * 
	 * @author John Brand (1802-Matt)
	 */
	@Test
	public void testGetUser() {
		
		RestAssured.get("http://localhost:9001/api/v2/users/50")
			.then()
			.body("firstName", equalTo("Ryan"));
	
	}
	
	/**
	 * RESTAssured test to update name of user to the H2 database
	 *  
	 * @author Joshua Stark (1802-Matt)
	 */
	@Test
	public void testUpdateUser() {
		Map<String,String> user = new HashMap<>();
        user.put("assignForceID", "43");
        user.put("batch", "2434");
        user.put("email", "galeforce1@revature.com");
        user.put("firstName", "Hurri");
        user.put("lastName", "Cane");
        user.put("middleName", "Frank");
        user.put("password", "1234");
        user.put("phone", "555-555-5555");
        user.put("phone2", "867-5308");
        user.put("role", "ASSOCIATE");
        user.put("skype", "galeWinds");
        user.put("temporaryPassword", "idk");
        user.put("userId", "51");

        RestAssured.given()
	        .contentType("application/json")
	        .body(user)
	        .when().put("http://localhost:9001/api/v2/users/51")
	        .then()
	        .statusCode(200);
	}
	
	/**
	 * RESTAssured test to add user to a batch
	 * 
	 * @author Joshua Stark (1802-Matt)
	 */
	@Test
	public void testAddUserToBatch() {

		RestAssured.given()
			.pathParam("batchId", 3)
			.put("http://localhost:9001/api/v2/users/51/{batchId}")
	        .then()
	        .statusCode(200);
	}
	
	/**
	 * RESTAssured test to set a user to INACTIVE
	 * 
	 * @author Joshua Stark (1802-Matt)
	 */
	@Test
	public void testRemoveUser() {

		RestAssured.delete("http://localhost:9001/api/v2/users/51")
	        .then()
	        .statusCode(200);
	}
}
