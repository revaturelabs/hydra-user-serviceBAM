package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.exception.AuthUserException;
import com.revature.service.BamUserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	BamUserService userService;

	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Updates the current user
	 * 
	 * @param currentUser
	 *            WE THINK THIS IS THE USER LOGGED IN, PLEASE CHANGE WHEN WORKED ON
	 * @return the updated BamUser
	 * @throws AuthUserException
	 */
	// TODO: FIND OUT WHAT CURRENTUSER IS
	@PutMapping("/{userId}")
	public BamUser updateUser(@RequestBody BamUser currentUser) throws AuthUserException {
		BamUser user = userService.findUserByEmail(currentUser.getEmail());
		currentUser.setUserId(user.getUserId());

		BamUser updatedUser = userService.addOrUpdateUser(currentUser);
		if (updatedUser != null) {
			return updatedUser;
		} else {
			throw new AuthUserException("User not updated/available", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method registers the current
	 *         user
	 * 
	 * @param currentUser
	 *            WE THINK THIS IS THE USER LOGGED IN, PLEASE CHANGE WHEN WORKED ON
	 * @return BamUser that was registered
	 * @throws AuthUserException
	 */
	@PostMapping("/")
	public BamUser addUser(@RequestBody BamUser currentUser) throws AuthUserException {
		if (userService.findUserByEmail(currentUser.getEmail()) == null) {
			currentUser.setRole(Role.ASSOCIATE);
			String password = currentUser.getPassword();
			String hashedPassword = this.bCryptPasswordEncoder.encode(password);
			currentUser.setPassword(hashedPassword);
			return userService.addOrUpdateUser(currentUser);
		} else {
			throw new AuthUserException("User not added", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method removes user and returns
	 *         updated batch
	 * 
	 * @param userID
	 *            the id of the user we want to remove
	 * @return Updated list of people in the batch
	 * @throws AuthUserException
	 * @throws IOException
	 * @throws ServletException
	 */
	@DeleteMapping("/{userId}")
	public List<BamUser> removeUser(@PathVariable int userId) throws AuthUserException {

		BamUser user = userService.findUserById(userId);
		int batchId = user.getBatch();

		// Set the user as inactive
		user.setBatch(0); // TODO: zero may not exist in DATABASE, may need to be null
		userService.addOrUpdateUser(user);

		// Return users from batch without the user
		List<BamUser> newBatchList = userService.findUsersInBatch(batchId);
		if (newBatchList != null) {
			return newBatchList;
		} else {
			throw new AuthUserException("User not removed/available", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve adds users to the batch
	 * 
	 * @param userId
	 *            id of the user we are adding to the batch
	 * @param batchId
	 *            id of the batch we want to add the user to
	 * @return Updated list of all users in the batch
	 * @throws AuthUserException
	 */
	@PutMapping("/{userId}/{batchId}")
	public List<BamUser> addUserToBatch(@PathVariable int userId, @PathVariable int batchId) throws AuthUserException {

		BamUser user = userService.findUserById(userId);
		user.setBatch(batchId);

		// Adds user to database and returns a new user object.
		BamUser addUserCheck = userService.addOrUpdateUser(user);
		// Checks if user was added to database successfully. addUserCheck will be null
		// if it failed to add to DB
		if (addUserCheck != null) {
			return userService.findUsersNotInBatch();
		} else {
			throw new AuthUserException("User not added/available", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author TJay Cargle 1801-jan8-java Gets a specific user by their id
	 * 
	 * @param id
	 *            id of the user we want to grab
	 * @return User with that id
	 */
	@GetMapping("/{id}")
	public BamUser getUsersById(@PathVariable Integer id) {
		BamUser user = userService.findUserById(id);
		return user;
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Gets all users in batch
	 * @param batchId
	 *            id of the batch we are trying to find
	 * @return List of all users in batch
	 * @throws IOException
	 * @throws ServletException
	 */
	@GetMapping("/batches/{batchId}")
	public ResponseEntity<List<BamUser>> getUsersInBatch(@PathVariable int batchId) {

		// Retrieve and return users in a batch from the database
		List<BamUser> usersInBatch = userService.findUsersInBatch(batchId);

		return new ResponseEntity<List<BamUser>>(usersInBatch, HttpStatus.OK);
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Returns users not in any batch
	 * 
	 * @return List of users that are not in a batch
	 */
	@GetMapping("/batches/none")
	public List<BamUser> getUsersNotInBatch() {
		List<BamUser> usersNotInBatch = userService.findUsersNotInBatch();
		return usersNotInBatch;
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve
	 * 
	 *         Return all the users in the database
	 * 
	 * @return a list of all the users in the database
	 */

	@GetMapping("/")
	public List<BamUser> getAllUsers() {
		return userService.findAllUsers();
	}

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

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method gets all users in batch
	 *         Find a specific user identified by email
	 * 
	 * @param email
	 *            the email of the specific user
	 * @return the specific user identified by his email
	 * @throws IOException
	 * @throws ServletException
	 */
	// TODO fix ambiguous mapping
	// @GetMapping("/{email}")
	// public BamUser getUsersByEmail(@PathVariable String email) {
	// BamUser user = userService.findUserByEmail(email);
	// return user;
	// }
}