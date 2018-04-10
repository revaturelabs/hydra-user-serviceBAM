package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Unknown
 *  Last updated by: (1802-Matt)
 */

@Entity
@Table(name = "USERS")
public class BamUser {

	@Id
	@Column(name = "User_Id")
	@SequenceGenerator(name = "USERID_SEQ", sequenceName = "USERID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERID_SEQ")
	private int userId;

	@Column(name = "First_Name")
	@NotNull(message = "First name cannot be empty")
	private String firstName;

	@Column(name = "Middle_Name")
	private String middleName;

	@Column(name = "Last_Name")
	@NotNull(message = "Last name cannot be empty")
	private String lastName;

	@Column(name = "EMail", unique = true)
	@NotNull(message = "e-mail address cannot be empty")
	private String email;

	@Column(name = "Password")
	@NotNull(message = "Password cannot be empty")
	private String password;

	@Column(name = "Role")
	private Role role;

	@Column(name = "Batch_Id")
	private Integer batchId;

	@Column(name = "Main_Phone")
	@NotNull(message = "Primary phone cannot be empty")
	private String phone;

	@Column(name = "Second_Phone")
	private String phone2;

	@Column(name = "Skype_ID")
	private String skype;

	@Column(name = "Password_Bak") // This is a backup password that will be
									// used when
	private String temporaryPassword;// the user needs to reset their password.

	@Column(name = "AssignForce_ID")
	private Integer assignForceID;

	public BamUser() {
		super();
	}

	public BamUser(String fName, String mName, String lName, String email, String pwd, Role role, int batch,
			String phone, String phone2, String skype, String pwd2) {// NOSONAR
		super();
		this.firstName = fName;
		this.middleName = mName;
		this.lastName = lName;
		this.email = email;
		this.password = pwd;
		this.role = role;
		this.batchId = batch;
		this.phone = phone;
		this.phone2 = phone2;
		this.skype = skype;
		this.temporaryPassword = pwd2;
	}


	public BamUser(String fName, String mName, String lName, String email, String pwd, Role role, int batch,
			String phone, String phone2, String skype, String pwd2, Integer AssignForceID) {// NOSONAR

		this(fName, mName, lName, email, pwd, role, batch, phone, phone2, skype, pwd2);
		this.assignForceID = AssignForceID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setfirstName(String fName) {
		this.firstName = fName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String mName) {
		this.middleName = mName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role i) {
		this.role = i;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(String pwd2) {
		this.temporaryPassword = pwd2;
	}

	public Integer getAssignForceID() {
		return assignForceID;
	}

	public void setAssignForceID(Integer assignForceID) {
		this.assignForceID = assignForceID;
	}

	public int getBatch() {
		return batchId;
	}

	public void setBatch(Integer invalid) {
		this.batchId = invalid;
	}

	/**
	 * @author Unknown
	 * @return toString
	 * Last updated by: John Talanian, April 9th 2018
	 * Updated Java Docs
	 */	
	@Override
	public String toString() {
		return "BamUser [/n(User Id) /t userId = " + userId
				+ "/n(First Name) /t fName = " + firstName
				+ "/n(Middle Name) /t mName = " + middleName
				+ "/n(Last Name) /t lName = " + lastName
				+ "/n(Email) /t email = " + email
				+ "/n(Password) /t pwd = " + password
				+ "/n(Role) /t role = " + role
				+ "/n(Batch) /t batch = " + batchId
				+ "/n(Phone Number) /t phone = " + phone
				+ "/n(2nd Phone Number) /t phone2 = " + phone2
				+ "/n(Skype ID) /t skype = " + skype
				+ "/n(Back up Password) /t pwd2 = " + temporaryPassword
				+ "/n(Forcefully assigns an ID) /t assignForceID = " + assignForceID + "]";
	}
}