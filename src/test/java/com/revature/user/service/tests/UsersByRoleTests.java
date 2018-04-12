package com.revature.user.service.tests;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import com.revature.beans.BamUser;

import antlr.collections.List;
import io.restassured.RestAssured;

/**
 * 
 * @author Joshua Stark and John Brand (1802-Matt)
 * 
 * Tests for:
 *  GET( /trainers ) 
 *  GET( /associates )
 *
 */
public class UsersByRoleTests {

	/**
	 * @author Joshua Stark (1802-Matt)
	 * 
	 * RESTAssured test to get all trainers
	 */
	@Test
	public void testGetAllTrainers() {

		RestAssured.get("http://localhost:9001/api/v2/users/trainers")
//	        .then()
//	        .statusCode(200);
		.then()
		.body("[0].firstName", equalTo("Ryan"));
	}
	
	/**
	 * @author Joshua Stark (1802-Matt)
	 * 
	 * RESTAssured test to get all associates
	 */
	@Test
	public void testGetAllAssociates() {

		RestAssured.get("http://localhost:9001/api/v2/users/associates")
//	        .then()
//	        .statusCode(200);
		.then()
		.body("[0].firstName", equalTo("Gale"));
	}
	
}
