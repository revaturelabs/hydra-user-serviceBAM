package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.services.BamUserService;

/**
 * Handles all Zuul endpoints /users/*ROLE* <br>
 * <br>
 * GET( /trainers ) - {@link #getAllTrainers()}<br>
 * GET( /associates ) - {@link #getAllAssociates()}
 * 
 * @author John Brand (1802-Matt)
 *
 */

@RestController
public class UsersByRole {

	@Autowired
	BamUserService userService;

	/**
	 * Return all the trainers in the database
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @return a list of all the users in the database whose role is trainer
	 */

	@GetMapping("/trainers")
	public List<BamUser> getAllTrainers() {
		return userService.findByRole(Role.TRAINER);
	}

	/**
	 * Return all the associates in the database
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @return a list of all the users in the database whose role is associate
	 */

	@GetMapping("/associates")
	public List<BamUser> getAllAssociates() {
		return userService.findByRole(Role.ASSOCIATE);
	}

}
