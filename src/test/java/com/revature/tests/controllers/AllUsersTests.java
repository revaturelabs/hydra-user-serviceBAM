package com.revature.tests.controllers;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import io.restassured.RestAssured;

/**
 * 
 * @author Joshua Stark (1802-Matt)
 * @author John Brand (1802-Matt)
 * 
 * Tests for:
 *	GET ( / )
 *
 * No test for Post ( / ) because we are unsure if this method will last
 *
 */
public class AllUsersTests {

	/**
	 * RESTAssured test to get all users
	 * 
	 * @author Joshua Stark (1802-Matt)
	 */
	@Test
	public void testGetAllUsers() {

		RestAssured.get("http://localhost:9001/api/v2/users/")
			.then()
			.body("[0].firstName", equalTo("Ryan"));
	}
	
}
