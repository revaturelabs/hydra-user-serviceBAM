package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.service.BamUserService;

/**
 * 
 * @author Feb 1802: John Brand, Matt's batch
 * 
 * handles all zuul endpoints /users/*ROLE*
 * 
 * GET( /trainers ) - Returns a list<BamUser> of all the users in the database whose role is TRAINER
 * GET( /associates ) - Returns a list<BamUser> of all the users in the database whose role is ASSOCIATE
 *
 */

@RestController
public class UsersByRole {

	@Autowired
	BamUserService userService;
	
	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve
	 * 
	 *         Return all the trainers in the database
	 * 
	 * @return a list of all the users in the database whose role is trainer
	 */

	@GetMapping("/trainers")
	public List<BamUser> getAllTrainers() {
		return userService.findByRole(Role.TRAINER);
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve
	 * 
	 *         Return all the associates in the database
	 * 
	 * @return a list of all the users in the database whose role is associate
	 */

	@GetMapping("/associates")
	public List<BamUser> getAllAssociates() {
		return userService.findByRole(Role.ASSOCIATE);
	}

}
