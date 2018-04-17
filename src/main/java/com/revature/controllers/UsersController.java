package com.revature.controllers;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.exceptions.AuthUserException;
import com.revature.services.BamUserService;

/**
 * Handles all Zuul endpoints /users/<br>
 * <br>
 * POST ( / )- {@link #addUser(BamUser)}<br>
 * GET ( / ) - {@link #getAllUsers()}
 *
 * @author Jeffrey Camacho (1712-Steve)
 * @author John Brand (1802-Matt)
 * 
 * 
 */

@RestController
public class UsersController {

	@Autowired
	BamUserService userService;

	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Registers the current user
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @return user that was registered
	 * @param currentUser WE THINK THIS IS THE USER LOGGED IN, PLEASE CHANGE WHEN WORKED ON
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
	 * Return all the users in the database
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @return a list of all the users in the database
	 */

	@GetMapping("/")
	public List<BamUser> getAllUsers() {
		return userService.findAllUsers();
	}

}