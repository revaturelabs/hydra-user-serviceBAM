package com.revature.beans;

/**
 * Purpose: Look up table for the user's role<br>
 * Additional Information: <br>
 * 		-Role 0 is for people who got dropped or fired<br>
 * 		-Role 1 is for associates<br>
 * 		-Role 2 is for trainers & QC<br>
 * 		-Role 3 is for admins<br>
 * 
 * Last updated by: (1802-Matt)
 * @author Devin Dellamano
 * 
 */
 

public enum Role {
	INACTIVE, ASSOCIATE, TRAINER, ADMIN;
}