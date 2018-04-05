package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
import com.revature.repository.BamUserRepository;

/**
 * Author: Devin Dellamano Purpose: Business Logic between controller and
 * repository
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
	 * @author 
	 * Save the user to the database if the user doesn't exist, update the
	 *         user's information if he does exist
	 * 
	 * @param user the BamUser we want to add or update in the database
	 * @return the BamUser we put in the database
	 */
	@Transactional
	public BamUser addOrUpdateUser(BamUser user) {
		return bamUserRepository.save(user);
	}

	/**
	 * @author 
	 * Gets all the BamUsers in the database
	 * 
	 * @return List of BamUsers containing all entries in the database
	 */
	public List<BamUser> findAllUsers() {
		return bamUserRepository.findAll();
	}

	/**
	 * @author 
	 * Gets all BamUsers of a particular role
	 * 
	 * @param trainer the Role we want to filter the search by
	 * @return List of BamUsers containing all entries related to a specific role
	 */
	public List<BamUser> findByRole(Role trainer) {
		return bamUserRepository.findByRole(trainer);
	}

	/**
	 * @author 
	 * Gets a particular BamUser by their id
	 * @param userId the id of the BamUser we are trying to find
	 * @return BamUser with that particular id
	 */
	public BamUser findUserById(int userId) {
		return bamUserRepository.findByUserId(userId);
	}

	/**
	 * @author 
	 * Returns a specific BamUser by their email
	 * @param email the email of the BamUser we are trying to find
	 * @return BamUser with that particular email
	 */
	public BamUser findUserByEmail(String email) {
		return bamUserRepository.findByEmail(email);
	}

	/**
	 * @author 
	 * Find a specific user identified by his first and last name
	 *
	 * @param f the first name of the specific user
	 * @param l the last name of the specific user
	 * @return a list of users whose first and last names match the first and last
	 *         name provided
	 */
	public List<BamUser> getByFNameAndLName(String f, String l) {
		return bamUserRepository.findByFNameAndLName(f, l);
	}

	/**
	 * @author 
	 * Find the users in a specific batch identified by batchId
	 *
	 * @param batchId the ID of the specific batch
	 * @return the users in the specific batch
	 */
	public List<BamUser> findUsersInBatch(int batchId) {
		return bamUserRepository.findByBatch(batchId);
	}

	/**
	 * @author 
	 * Return the users that are not in a batch
	 *
	 * @return a list of users that are not in a batch
	 */
	public List<BamUser> findUsersNotInBatch() {
		List<BamUser> users = bamUserRepository.findByBatch(0);
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
