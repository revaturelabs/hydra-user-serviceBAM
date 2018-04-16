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
import com.revature.exception.AuthUserException;
import com.revature.service.BamUserService;

/**
 * 
 * @author John Brand (1802-Matt)
 *
 *	handles all Zuul endpoints /users/{id}
 *
 *	Get( /{userId} ) - Get a user by their userId
 *	Put( /{userId} ) - Update user information
 *	Delete( /{userId} ) - "remove" user from table.  User information isn't deleted from table
 *						- but should not be returnable to client.  Preserved in Database for administrative purposes.
 *	Put( /{userId}/{batchId} ) - updates user of {userId}'s batch with {batchId}
 *
 *	NOTE: There is no Post method for /{userId} endpoint, userId are created by sequence in table, 
 *		you can not add a user to a table based off a userId.
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
	 * @param Integer userId of the user we want to grab
	 */
	@GetMapping("/{userId}")
	public BamUser getUsersById(@PathVariable Integer userId) {
		BamUser user = userService.findUserById(userId);
		return user;
	}
	
	
	/**
	 * Updates the current user
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @LastUpdated John Brand and Joshua Stark (1802-Matt)
	 * 		Moved to UsersByUserIdController
	 * 
	 * @return the updated BamUser
	 * @param currentUser
	 *            WE THINK THIS IS THE USER LOGGED IN, PLEASE CHANGE WHEN WORKED ON
	 * @throws AuthUserException
	 */
	// TODO: FIND OUT WHAT CURRENTUSER IS
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
	 * Method removes user and returns updated batch
	 * @author Jeffrey Camacho (1712-Steve)  
	 *         
	 * @LastUpdated John Brand (1802-Matt)
	 * 		Moved to UsersByUserIdController
	 * 
	 * @return Updated list of people in the batch
	 * @param int userID of the user we want to remove
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
	 * adds users to the batch
	 * @author Jeffrey Camacho (1712-Steve) 
	 * 
	 * @LastUpdated John Brand (1802-Matt)
	 * 		Moved to UsersByUserIdController
	 * 
	 * @return Updated list of all users in the batch
	 * @param int userId of the user we are adding to the batch
	 * @param int batchId of the batch we want to add the user to
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
