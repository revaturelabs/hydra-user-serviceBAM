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
@RequestMapping("/api/v2/user/")
@CrossOrigin
public class UserControllerExternal {

	@Autowired
	BamUserService userService;

	@Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Removes user from batch then
	 *         returns user with updated batch. "0" role does not exist in the
	 *         database, when implemented this code will run.
	 * 
	 * @param int
	 *            USERID
	 * @return List<BamUser>: users from batch
	 * @throws IOException
	 * @throws ServletException
	 */
	@PostMapping("drop/{userId}")
	public List<BamUser> dropUserFromBatch(@PathVariable int userId) {

		BamUser user = userService.findUserById(userId);
		int batchId = user.getBatch();

		// Drop user from the batch
		user.setBatch(null);
		user.setRole(Role.NONE);// 0 role does not exist in database, use 1 to test method checks good.
		userService.addOrUpdateUser(user);

		// Return users from batch without the user
		return userService.findUsersInBatch(batchId);
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Updates the current user
	 * 
	 * @param currentUser
	 * @return BamUser
	 * @throws AuthUserException 
	 */
	@PostMapping("update")
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
	 * @return BamUser
	 * @throws AuthUserException 
	 */
	@PostMapping("register")
	public BamUser addUser(@RequestBody BamUser currentUser) throws AuthUserException {
		if (userService.findUserByEmail(currentUser.getEmail()) == null) {
			currentUser.setRole(Role.ASSOCIATE);
			String password = currentUser.getPwd();
			String hashed = this.bCryptPasswordEncoder.encode(password);
			currentUser.setPwd(hashed);
			return userService.addOrUpdateUser(currentUser);
		} else {
			throw new AuthUserException("User not added", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method removes user and returns
	 *         updated batch
	 * 
	 * @param int
	 *            USERID
	 * @return List<BamUser>
	 * @throws AuthUserException 
	 * @throws IOException
	 * @throws ServletException
	 */
	@PostMapping("remove/{userId}")
	public List<BamUser> removeUser(@PathVariable int userId) throws AuthUserException {

		BamUser user = userService.findUserById(userId);
		int batchId = user.getBatch();

		// Set the user as inactive
		user.setBatch(0);
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
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Method adds users to the batch
	 * 
	 * @param int
	 *            USERID, int BATCHID
	 * @return List<BamUser>
	 * @throws AuthUserException 
	 */
	@PostMapping("addUserToBatch/{userId}/{batchId}")
	public List<BamUser> addUserToBatch(@PathVariable int userId, @PathVariable int batchId) throws AuthUserException {

		BamUser user = userService.findUserById(userId);
		user.setBatch(batchId);

		BamUser addedUser = userService.addOrUpdateUser(user);

		if (addedUser != null) {
			return userService.findUsersNotInBatch();
		} else {
			throw new AuthUserException("User not added/available", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @author TJay Cargle 1801-jan8-java
	 * 
	 * @param
	 * @return BamUser
	 */
	@GetMapping("getById/{id}")
	public BamUser getUsersById(@PathVariable Integer id) {
		BamUser user = userService.findUserById(id);
		return user;
	}
	/**@author Jeffrey Camacho 1712-dec10-java-Steve
	 * Method gets all users in batch
	 * @param int BATCHID
	 * @return List<BamUser> : users in batch
	 * @throws IOException
	 * @throws ServletException
	 */
	@GetMapping("inbatch/{batchId}")
	public ResponseEntity<List<BamUser>> getUsersInBatch(@PathVariable int batchId) {
		
		//Retrieve and return users in a batch from the database
		List<BamUser> usersInBatch = userService.findUsersInBatch(batchId);
		
		return new ResponseEntity<List<BamUser>>(usersInBatch, HttpStatus.OK);
	}
	
	/**@author Jeffrey Camacho 1712-dec10-java-Steve
	 * Method returns users not in batch
	 * 
	 * @param 
	 * @return List<BamUser>
	 */
	@GetMapping("notinabatch")
	public List<BamUser> getUsersNotInBatch() {
		List<BamUser> usersNotInBatch = userService.findUsersNotInBatch();
		return usersNotInBatch;
	}
}