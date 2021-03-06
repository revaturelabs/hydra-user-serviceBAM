package com.revature.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.demo.beans.BamUser;
import com.revature.demo.beans.Role;
import com.revature.demo.repository.BamUserRepository;


/*
 * Author: Devin Dellamano
 * Purpose: Business Logic between controller and repository
 */
@Service
public class BamUserService {
	// autowire a bean to the user repository
	@Autowired
	BamUserRepository bamUserRepository;
	
	public BamUserService(BamUserRepository bamUserRepository){
		this.bamUserRepository = bamUserRepository;
	}
	
	// save the user to the database
	// if the user exists, update the user's information
	@Transactional
	public BamUser addOrUpdateUser(BamUser user) {
		return bamUserRepository.save(user);
	}
	
	// return a list of all the users
	public List<BamUser> findAllUsers() {
		return bamUserRepository.findAll();
	}
	
	// return a list of all the users by a role
	public List<BamUser> findByRole(Role trainer) {
		return bamUserRepository.findByRole(trainer);
	}
	
	// return a specific user by id
	public BamUser findUserById(int userId) {
		return bamUserRepository.findByUserId(userId);
	}
	
	// return a specific user by their email
	public BamUser findUserByEmail(String email) {
		return bamUserRepository.findByEmail(email);
	}
	
	// return a specific user by their first and last name
	public List<BamUser> getByFNameAndLName(String f, String l) {
		return bamUserRepository.findByFNameAndLName(f, l);
	}
	
	// return the users located in a batch based off batch id
	public List<BamUser> findUsersInBatch(int batchId) {
		return bamUserRepository.findByBatch(batchId);
	}
	
	// return the users not in any batches (a.k.a. the batch id is null)
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
