package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.BamUser;
import com.revature.service.BamUserService;

/**
 * 
 * @author John Brand (1802-Matt)
 * 
 * handles all Zuul endpoints /users/batches
 * 
 * GET( /batches/{batchId} ) - Returns all users in batch with {batchId}
 * GET( /batches/none ) - Returns all users not currently in a batch. Currently {batchId} = zero
 */


@RestController
public class UsersByBatches {

	@Autowired
	BamUserService userService;
	
	/**
	 * Gets all users in batch
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @return List of all users in batch
	 * @param int batchId of the batch we are trying to find
	 * @throws IOException
	 * @throws ServletException
	 */
	@GetMapping("/batches/{batchId}")
	public ResponseEntity<List<BamUser>> getUsersInBatch(@PathVariable int batchId) {

		// Retrieve and return users in a batch from the database
		List<BamUser> usersInBatch = userService.findUsersInBatch(batchId);

		return new ResponseEntity<List<BamUser>>(usersInBatch, HttpStatus.OK);
	}

	/**
	 * Returns users not in any batch
	 * 
	 * @author Jeffrey Camacho (1712-Steve)
	 * 
	 * @return List<BamUser> List of users that are not in a batch
	 */
	@GetMapping("/batches/none")
	public List<BamUser> getUsersNotInBatch() {
		List<BamUser> usersNotInBatch = userService.findUsersNotInBatch();
		return usersNotInBatch;
	}
	
	
	
}
