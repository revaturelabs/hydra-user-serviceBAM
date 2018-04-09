package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revature.beans.BamUser;
import com.revature.beans.Role;
/**
 * @author Unknown
 * Last updated by: John Talanian, April 9th 2018
 * Updated Java Docs
 */
@RepositoryRestResource
public interface BamUserRepository extends JpaRepository<BamUser, Integer>{
	public BamUser findByUserId(int id);
	public BamUser findByEmail(String email);
	public List<BamUser> findByBatchId(Integer batchId);
	public List<BamUser> findByRole(Role role);
	public List<BamUser> findByFirstNameAndLastName(String firstName, String lastName);
}