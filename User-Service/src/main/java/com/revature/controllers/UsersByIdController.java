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
import com.revature.exception.AuthUserException;
import com.revature.service.BamUserService;

/**
 * 
 * @author Feb-1802- John Brand -Matt's Batch
 *
 *	handles all zuul endpoints /users/{id}
 *
 *	Get( /{userId} ) - Get a user by their userId
 *	Put( /{userId} ) - Update user information
 *	Delete( /{userId} ) - "remove" user from table.  User information isn't deleted from table
 *						- but should not be returnable to client.  Preserved in Database for administrative purposes.
 *	Put( /{userId}/{batchId} ) - updates user of {userId}'s batch with {batchId}
 *
 *	NOTE: There is no Post method for /{userId} endpoint, userId are created by sequence in table, you can not add a user to 
 *		a table based off a userId.
 *
 */

@RestController
public class UsersByIdController {

	@Autowired
	BamUserService userService;
	
	/**
	 * @author TJay Cargle 1801-jan8-java Gets a specific user by their id
	 * 
	 * @param userId
	 *            id of the user we want to grab
	 * @return User with that id
	 */
	@GetMapping("/{userId}")
	public BamUser getUsersById(@PathVariable Integer userId) {
		BamUser user = userService.findUserById(userId);
		return user;
	}
	
	
	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Updates the current user
	 * 
	 * @LastUpdated Feb-1802-John Brand-Matt's Batch
	 * 		Moved to UsersByUserIdController
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
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method removes user and returns
	 *         updated batch
	 *         
	 * @LastUpdated Feb-1802-John Brand-Matt's Batch
	 * 		Moved to UsersByUserIdController
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
	
}
