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

/**
 * 
 * @author Jeffrey Camacho 1712-dec10-java-Steve Updates the current user
 * @LastUpdated 1802-Matt-John Brand
 * 
 * handles all zuul endpoints /users/
 *
 *	POST ( / )- takes in a JSON BamUser, returns JSON of the registered BamUser
 *	GET ( / ) - Returns a list<BamUser> of all users
 */

@RestController
public class UsersController {

	@Autowired
	BamUserService userService;

	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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