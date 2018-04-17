package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.repositories.BamUserRepository;

/**
 * Purpose: Business Logic between controller and repository
 * 
 * @author: Devin Dellamano
 * 
 */
@Service
public class BamUserService {
	
	@Autowired
	BamUserRepository bamUserRepository;

	/**
	 * Save the user to the database if the user doesn't exist, update the user's
	 * information if he does exist
	 * 
	 * @author Devin Dellamano
	 * 
	 * @return the BamUser we put in the database
	 * @param the BamUser we want to add or update in the database
	 */
	@Transactional
	public BamUser addOrUpdateUser(BamUser user) {
		return bamUserRepository.save(user);
	}

	/**
	 * Gets all the BamUsers in the database
	 * 
	 * @author Devin Dellamano
	 * 
	 * @return List of BamUsers containing all entries in the database
	 */
	public List<BamUser> findAllUsers() {
		return bamUserRepository.findAll();
	}

	/**
	 * Gets all BamUsers of a particular role
	 * 
	 * @author Devin Dellamano
	 * 
	 * @return List of BamUsers containing all entries related to a specific role
	 * @param the Role we want to filter the search by
	 */
	public List<BamUser> findByRole(Role trainer) {
		return bamUserRepository.findByRole(trainer);
	}

	/**
	 * Gets a particular BamUser by their id
	 * 
	 * @author Devin Dellamano
	 * 
	 * @return user with that particular id
	 * @param  the id of the BamUser we are trying to find
	 */
	public BamUser findUserById(int userId) {
		return bamUserRepository.findByUserId(userId);
	}

	/**
	 * Returns a specific BamUser by their email
	 * 
	 * @author Devin Dellamano
	 * 
	 * @return user with that particular email
	 * @param the email of the BamUser we are trying to find
	 */
	public BamUser findUserByEmail(String email) {
		return bamUserRepository.findByEmail(email);
	}

	/**
	 * Find a specific user identified by his first and last name
	 * 
	 * @author Devin Dellamano
	 * @author Joshua Stark (1802-Matt)
	 * 
	 * @return a list of users whose first and last names match the first and last
	 *         name provided
	 * @param the first name of the specific user
	 * @param the last name of the specific user
	 */
	public List<BamUser> getByFNameAndLName(String firstName, String lastName) {
		return bamUserRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	/**
	 * Find the users in a specific batch identified by batchId
	 * 
	 * @author Devin Dellamano
	 * 
	 * @return the users in the specific batch
	 * @param the ID of the specific batch
	 */
	public List<BamUser> findUsersInBatch(int batchId) {
		return bamUserRepository.findByBatchId(batchId);
	}

	/**
	 * Return the users that are not in a batch
	 * 
	 * @author Devin Dellamano
	 *
	 * @return a list of users that are not in a batch
	 */
	public List<BamUser> findUsersNotInBatch() {
		List<BamUser> users = bamUserRepository.findByBatchId(0);
		for (int i = 0; i < users.size(); i++) {
			// remove a user if their role is not an associate
			if (users.get(i).getRole().ordinal() != 1) {
				users.remove(i);
				i--;
			}
		}
		return users;
	}

}
