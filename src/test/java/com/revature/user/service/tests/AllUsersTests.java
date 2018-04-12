package com.revature.user.service.tests;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import io.restassured.RestAssured;

/**
 * 
 * @author Joshua Stark and John Brand (1802-Matt)
 * 
 * Tests for:
 *	GET ( / )
 *
 * No test for Post ( / ) because we are unsure if this method will last
 *
 */
public class AllUsersTests {

	/**
	 * @author Joshua Stark (1802-Matt)
	 * 
	 * RESTAssured test to get all users
	 */
	@Test
	public void testGetAllUsers() {

		RestAssured.get("http://localhost:9001/api/v2/users/")
		.then()
		.body("[0].firstName", equalTo("Ryan"));
	}
	
}
