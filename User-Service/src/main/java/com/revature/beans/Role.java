package com.revature.beans;

/**
 * Author: Devin Dellamano
 * Purpose: Look up table for the user's role
 * Additional Information: 
 * 		-Role 0 is for people who got dropped or fired
 * 		-Role 1 is for associates
 * 		-Role 2 is for trainers & QC
 * 		-Role 3 is for admins
 */

public enum Role {
    INACTIVE, ASSOCIATE, TRAINER, ADMIN;
}