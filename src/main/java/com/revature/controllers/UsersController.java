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
import com.revature.exception.AuthUserException;
import com.revature.service.BamUserService;

/**
 * 
 * @author Jeffrey Camacho (1712-Steve)
 * @LastUpdated John Brand (1802-Matt)
 * 
 * handles all Zuul endpoints /users/
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
	 * Method registers the current user
	 * @author Jeffrey Camacho (1712-Steve) 
	 * 
	 * @return BamUser user that was registered
	 * @param BamUser currentUser
	 *            WE THINK THIS IS THE USER LOGGED IN, PLEASE CHANGE WHEN WORKED ON
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
	 * @return List<BamUser> a list of all the users in the database
	 */

	@GetMapping("/")
	public List<BamUser> getAllUsers() {
		return userService.findAllUsers();
	}

	
}