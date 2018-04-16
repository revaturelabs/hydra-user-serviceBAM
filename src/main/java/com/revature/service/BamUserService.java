package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.repository.BamUserRepository;

/**
 * @Author: Devin Dellamano 
 * Purpose: Business Logic between controller and repository
 */
@Service
public class BamUserService {
	// autowire a bean to the user repository
	@Autowired
	BamUserRepository bamUserRepository;

	public BamUserService(BamUserRepository bamUserRepository) {
		this.bamUserRepository = bamUserRepository;
	}

	/**
	 * Save the user to the database if the user doesn't exist, update the
	 *         user's information if he does exist
	 *         
	 * @author Devin Dellamano
	 * 
	 * @return BamUser the BamUser we put in the database
	 * @param BamUser the BamUser we want to add or update in the database
	 */
	@Transactional
	public BamUser addOrUpdateUser(BamUser user) {
		return bamUserRepository.save(user);
	}

	/**
	 * Gets all the BamUsers in the database
	 * @author Devin Dellamano
	 * 
	 * @return List<BamUser> List of BamUsers containing all entries in the database
	 */
	public List<BamUser> findAllUsers() {
		return bamUserRepository.findAll();
	}

	/**
	 * Gets all BamUsers of a particular role
	 * @author Devin Dellamano
	 * 
	 * @return List<BamUser> List of BamUsers containing all entries related to a specific role
	 * @param Role trainer the Role we want to filter the search by
	 */
	public List<BamUser> findByRole(Role trainer) {
		return bamUserRepository.findByRole(trainer);
	}

	/**
	 * Gets a particular BamUser by their id
	 * @author Devin Dellamano
	 * 
	 * @return BamUser user with that particular id
	 * @param int userId the id of the BamUser we are trying to find
	 */
	public BamUser findUserById(int userId) {
		return bamUserRepository.findByUserId(userId);
	}

	/**
	 * Returns a specific BamUser by their email
	 * @author Devin Dellamano
	 * 
	 * @return BamUser user with that particular email
	 * @param String email the email of the BamUser we are trying to find
	 */
	public BamUser findUserByEmail(String email) {
		return bamUserRepository.findByEmail(email);
	}

	/**
	 * Find a specific user identified by his first and last name
	 * @author Devin Dellamano
	 * @LastUpdated Joshua Stark (1802-Matt) 
	 * 		variable name changes
	 * 
	 * @return List<BamUser> a list of users whose first and last names match the first and last
	 *         name provided
	 * @param String the first name of the specific user
	 * @param String the last name of the specific user
	 */
	public List<BamUser> getByFNameAndLName(String firstName, String lastName) {
		return bamUserRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	/**
	 * Find the users in a specific batch identified by batchId
	 * @author Devin Dellamano
	 * 
	 * @return List<BamUser> the users in the specific batch
	 * @param int the ID of the specific batch
	 */
	public List<BamUser> findUsersInBatch(int batchId) {
		return bamUserRepository.findByBatchId(batchId);
	}

	/**
	 * Return the users that are not in a batch
	 * @author Devin Dellamano
	 *
	 * @return List<BamUser> a list of users that are not in a batch
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
