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
 * @author Feb 1802: John Brand, Matt's Batch
 * 
 * handles all zuul endpoints /users/batches
 * 
 * GET( /batches/{batchId} ) - Returns all users in batch with {batchId}
 * GET( /batches/none ) - Returns all users not currently in a batch. Currently {batchId} = zero
 */


@RestController
public class UsersByBatches {

	@Autowired
	BamUserService userService;
	
	/**
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Gets all users in batch
	 * @param batchId
	 *            id of the batch we are trying to find
	 * @return List of all users in batch
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
	 * @author Jeffrey Camacho 1712-dec10-java-Steve Returns users not in any batch
	 * 
	 * @return List of users that are not in a batch
	 */
	@GetMapping("/batches/none")
	public List<BamUser> getUsersNotInBatch() {
		List<BamUser> usersNotInBatch = userService.findUsersNotInBatch();
		return usersNotInBatch;
	}
	
	
	
}
