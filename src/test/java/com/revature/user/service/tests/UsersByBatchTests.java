package com.revature.user.service.tests;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import io.restassured.RestAssured;

/**
 * 
 * @author Joshua Stark and John Brand (1802-Matt)
 * 
 * Tests for:
 *  GET( /batches/{batchId} ) 
 *  GET( /batches/none )
 *
 */

public class UsersByBatchTests {

	/**
	 * @author Joshua Stark (1802-Matt)
	 * 
	 * RESTAssured test to get all users to a batch
	 */
	@Test
	public void testGetUsersInBatch() {

		RestAssured.given().pathParam("batchId", 1234).get("http://localhost:9001/api/v2/users/batches/{batchId}")
	        .then()
	        .statusCode(200);
//		.then()
//		.body("firstName", equalTo("Matt"));
	}
	
	/**
	 * @author Joshua Stark (1802-Matt)
	 * 
	 * RESTAssured test to get all users to a batch
	 */
	@Test
	public void testGetUsersNotInBatch() {

		RestAssured.get("http://localhost:9001/api/v2/users/batches/none")
	        .then()
	        .statusCode(200);
//		.then()
//		.body("firstName", equalTo("Hurri"));
	}
	
}
