package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.exception.AuthUserException;
import com.revature.service.BamUserService;

@RestController
@RequestMapping("")
@CrossOrigin
public class UserControllerInternal {

	@Autowired
	BamUserService userService;
	
// REPEAT OF EXTERNAL CONTROLLER
//	/**
//	 * @author Jeffrey Camacho 1712-dec10-java-Steve 
//	 * 
//	 * Return the users that are not in any existing batch
//	 * 
//	 * @return a list of users that are not in any existing batch 
//	 */
//	@GetMapping("notinabatch")
//	public List<BamUser> getUsersNotInBatch() {
//		return userService.findUsersNotInBatch();
//	}
	
	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve 
	 * 
	 * Return all the users in the database
	 * 
	 * @return a list of all the users in the database 
	 */
//	@GetMapping("all")
	@GetMapping("/users")
	public List<BamUser> getAllUsers() {
		return userService.findAllUsers();
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve 
	 * 
	 * Return all the trainers in the database
	 * 
	 * @return a list of all the users in the database whose role is trainer
	 */
//	@GetMapping("alltrainers")
	@GetMapping("/users/trainers")
	public List<BamUser> getAllTrainers() {
		return userService.findByRole(Role.TRAINER);
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve 
	 * 
	 * Return all the associates in the database
	 * 
	 * @return a list of all the users in the database whose role is associate
	 */
//	@GetMapping("allassociates")
	@GetMapping("/users/associates")
	public List<BamUser> getAllAssociates() {
		return userService.findByRole(Role.ASSOCIATE);
	}

//	REPEAT OF EXTERNAL CONTROLLER
//	/**
//	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method gets all users in batch
//	 * Find the users in a specific batch identified by batchId
//	 * 
//     * @param	batchId		the ID of the specific batch
//     * @return	a list of all the users in the specific batch
//	 * @throws 	IOException
//	 * @throws 	ServletException
//	 */
////	@GetMapping("inbatch/{batchId}")
//	@GetMapping("/users/batches/{batchId}")
//	public List<BamUser> getUsersInBatch(@PathVariable int batchId) {
//		return userService.findUsersInBatch(batchId);
//	}
	
	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method gets all users in batch
	 * Find a specific user identified by email
	 * 
     * @param	email		the email of the specific user
     * @return	the specific user identified by his email
	 * @throws 	IOException
	 * @throws 	ServletException
	 */
//	@GetMapping("byEmail/{email}")
	@GetMapping("/users/{email}")
	public BamUser getUsersByEmail(@PathVariable String email) {
		BamUser user = userService.findUserByEmail(email);
		return user;
	}
	
//	REMOVED BECUASE REPEATED FROM EXTERNAL
//	/**
//	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method gets all users in batch
//	 * Find a specific user identified by ID
//	 * 
//     * @param	id		the ID of the specific user
//     * @return	the specific user identified by his ID
//	 * @throws 	IOException
//	 * @throws 	ServletException
//	 */
//	@GetMapping("getById/{id}")
//	public BamUser getUsersById(@PathVariable Integer id) {
//		BamUser user = userService.findUserById(id);
//		return user;
//	}
	
//	REMOVED BECAUSE REPEATED FROM EXTERNAL
//	/**
//	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method gets all users in batch
//	 * Update the currently logged in user
//	 * 
//     * @param	currentUser		the user object of the currently logged in user
//     * @return	the updated user object of the currently logged in user
//	 * @throws 	IOException
//	 * @throws 	ServletException
//	 */
//	@PostMapping("update")
//	public BamUser updateUser(@RequestBody BamUser currentUser) throws AuthUserException {
//		BamUser user = userService.findUserByEmail(currentUser.getEmail());
//		currentUser.setUserId(user.getUserId());
//
//		BamUser updatedUser = userService.addOrUpdateUser(currentUser);
//		if (updatedUser != null) {
//			return updatedUser;
//		} else {
//			throw new AuthUserException("User not updated/available", HttpStatus.BAD_REQUEST);
//		}
//	}

}