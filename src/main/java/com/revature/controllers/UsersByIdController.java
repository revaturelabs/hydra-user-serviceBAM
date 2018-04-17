package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.exceptions.AuthUserException;
import com.revature.services.BamUserService;

/**
 * Handles all Zuul endpoints /users/{id}<br>
 * <br>
 * Get( /{userId} ) - {@link #getUsersById(Integer)}<br>
 * Put( /{userId} ) - {@link #updateUser(BamUser)}<br>
 * Delete( /{userId} ) - {@link #removeUser(int)}<br>
 * Put( /{userId}/{batchId} ) - {@link #addUserToBatch(int, int)}<br>
 * <br>
 * NOTE: There is no Post method for /{userId} endpoint, userId are created by
 * sequence in table, you can not add a user to a table based off a userId.
 *
 * @author John Brand (1802-Matt)
 *
 * 
 *
 */

@RestController
public class UsersByIdController {

	@Autowired
	BamUserService userService;

	/**
	 * Gets a specific user by their id
	 * 
	 * @author TJay Cargle 1801-jan8-java
	 * 
	 * @return User with that id
	 * @param userId of the user we want to grab
	 */
	@GetMapping("/{userId}")
	public BamUser getUsersById(@PathVariable Integer userId) {
		BamUser user = userService.findUserById(userId);
		return user;
	}

	/**
	 * Updates the current user
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * @author John Brand (1802-Matt)
	 * @author Joshua Stark (1802-Matt)
	 * 
	 * @return the updated BamUser
	 * @param currentUser WE THINK THIS IS THE USER LOGGED IN, PLEASE CHANGE WHEN WORKED ON
	 * @throws AuthUserException
	 */
	@PutMapping("/{userId}")
	public BamUser updateUser(@RequestBody BamUser currentUser) throws AuthUserException {

		BamUser updatedUser = userService.addOrUpdateUser(currentUser);
		if (updatedUser != null) {
			return updatedUser;
		} else {
			throw new AuthUserException("User not updated/available", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Removes user and returns updated batch
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @author John Brand (1802-Matt)
	 * 
	 * @return Updated list of people in the batch
	 * @param userID of the user we want to remove
	 * @throws AuthUserException
	 * @throws IOException
	 * @throws ServletException
	 */
	@DeleteMapping("/{userId}")
	public List<BamUser> removeUser(@PathVariable int userId) throws AuthUserException {

		BamUser user = userService.findUserById(userId);
		int batchId = user.getBatch();

		// Set the user as inactive
		user.setBatch(0);

		user.setRole(Role.INACTIVE);

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
	 * Adds users to the batch
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @author John Brand (1802-Matt)
	 * 
	 * @return Updated list of all users in the batch
	 * @param userId of the user we are adding to the batch
	 * @param batchId of the batch we want to add the user to
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

}
